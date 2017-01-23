package me.matus.demo.timetrack.frontend.cdi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

/**
 * Interceptor binding annotation for TracingInterceptor. 
 * @author Matus_Majchrak
 */
@InterceptorBinding
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TracedExecution {
    
}
