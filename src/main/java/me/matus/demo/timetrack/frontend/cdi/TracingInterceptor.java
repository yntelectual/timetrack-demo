package me.matus.demo.timetrack.frontend.cdi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collection;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple interceptor that keeps track of business method execution in CDI beans.
 *
 * @author Matus_Majchrak
 */
@TracedExecution
@Interceptor
public class TracingInterceptor {

    @AroundInvoke
    public Object trackExecution(InvocationContext ic) throws Exception {
        String methodName = ic.getMethod().getName();
        Logger classLogger = LoggerFactory.getLogger(getClassName(ic.getTarget().getClass()));
        long start = System.currentTimeMillis();
        classLogger.info("Start - {} {}", methodName, Arrays.toString(ic.getParameters()));
        Object result = ic.proceed();
        if (result instanceof Exception) {
            classLogger.info("Failed - {} [{}s] ", methodName, String.format("%.3f", (System.currentTimeMillis() - start) / 1000f));
        } else {
            StringBuilder resultAsString = new StringBuilder("");
            if (result instanceof Collection<?>) {
                Collection<?> resultCollection = (Collection<?>) result;
                Class clazz = Object.class;
                if (result.getClass().getGenericSuperclass() instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) result.getClass().getGenericSuperclass();
                    Object type = parameterizedType.getActualTypeArguments()[0];
                    if (type instanceof Class<?>) {
                        clazz = (Class<?>) type;
                    } else if (type instanceof TypeVariable && !resultCollection.isEmpty()) {
                        Object obj = resultCollection.iterator().next();
                        if (obj != null) {
                            clazz = obj.getClass();
                        }
                    }
                } else if (!resultCollection.isEmpty()) {
                    Object obj = resultCollection.iterator().next();
                    if (obj != null) {
                        clazz = obj.getClass();
                    }
                }
                resultAsString.append(result.getClass().getSimpleName()).append("(").append(resultCollection.size()).append(" ").append(clazz.getSimpleName()).append(")");
            } else {
                if (result == null) {
                    resultAsString.append("void");
                } else {
                    resultAsString.append(result.toString());
                }
            }
            classLogger.info("Finish - {} in [{}s] result {}", methodName, String.format("%.3f ", (System.currentTimeMillis() - start) / 1000f), resultAsString);
        }
        return result;
    }

    protected String getClassName(Class clazz) {
        String result = null;
        if (clazz != null) {
            result = clazz.getName();
            int index = result.indexOf('$');
            if (index != -1) {
                result = result.substring(0, index);
            }
        }
        return result;
    }
}
