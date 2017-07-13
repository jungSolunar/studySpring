package ekgo.study.spring;

import ekgo.study.spring.tomcat.TomcatServer;
import ekgo.study.spring.tomcat.impl.TomcatServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dhjung on 2017. 7. 13..
 */
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) throws Exception{
        try{
            logger.info("Server Starting..");
            TomcatServer tomcat = new TomcatServerImpl();
            tomcat.start();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
