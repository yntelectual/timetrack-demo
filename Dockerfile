FROM frekele/wildfly
RUN /opt/wildfly/bin/add-user.sh admin Admin#70365 --silent
ADD target/*.war /opt/wildfly/standalone/deployments/