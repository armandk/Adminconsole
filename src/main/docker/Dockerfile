FROM java:8

ENV TOMCAT_VERSION 7.0.55

ENV CATALINA_HOME=/opt/tomcat
# Get and Unpack Tomcat
RUN wget http://archive.apache.org/dist/tomcat/tomcat-7/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/catalina.tar.gz \
    && tar xzf /tmp/catalina.tar.gz -C /opt && mv /opt/apache-tomcat-${TOMCAT_VERSION} ${CATALINA_HOME} && rm /tmp/catalina.tar.gz


# Remove unneeded apps
 RUN rm -rf ${CATALINA_HOME}/webapps/examples ${CATALINA_HOME}/webapps/docs

 RUN rm -rf ${CATALINA_HOME}/logs ${CATALINA_HOME}/work ${CATALINA_HOME}/temp


VOLUME /tmp

#Adding files in the Tomcat installation
ADD /maven/*.war $CATALINA_HOME/webapps


ENV PATH $PATH:$CATALINA_HOME/bin



CMD ["catalina.sh", "run"]
