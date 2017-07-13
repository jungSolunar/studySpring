package ekgo.study.spring.tomcat.impl;

import ekgo.study.spring.tomcat.TomcatServer;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by dhjung on 2017. 7. 13..
 */
public class TomcatServerImpl implements TomcatServer{
    private static Logger logger = LoggerFactory.getLogger(TomcatServerImpl.class);

    private static final String PATH_CONF_FILE = "config-server.properties";
    private static final String PATH_WEBAPP_DIR = "src/main/webapp";
    private static final String PATH_TARGET_CLASSES = "target/classes";
    private static final String PATH_WEBINF_CLASSES = "/WEB-INF/classes";

    private int port;
    private org.apache.catalina.startup.Tomcat tomcat;

    public void init(){
        try {
            Configuration config = new PropertiesConfiguration(PATH_CONF_FILE);
            this.port = config.getInt("server.port");

            tomcat = new org.apache.catalina.startup.Tomcat();
            tomcat.setPort(this.port);

            StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(PATH_WEBAPP_DIR).getAbsolutePath());
            logger.debug("Configuring app with basedir: " + new File("./" + PATH_WEBAPP_DIR).getAbsolutePath());

            File additionWebInfClasses = new File(PATH_TARGET_CLASSES);
            WebResourceRoot resources = new StandardRoot(ctx);
            resources.addPreResources(new DirResourceSet(resources, PATH_WEBINF_CLASSES, additionWebInfClasses.getAbsolutePath(), "/"));
            ctx.setResources(resources);
        }catch (Exception e){
            logger.info("Tomcat INIT Exception");
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            this.tomcat.start();
            this.tomcat.getServer().await();
        }catch (Exception e){
            logger.info("Tomcat RUN Exception");
            e.printStackTrace();
        }
    }

    public void start(){
        init();
        run();
    }

}
