package server;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class ServerApp {

    public static final String FILE_SERVLET = "FileServlet";
    public static final int PORT = 8083;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORT);
        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
        Tomcat.addServlet(ctx, FILE_SERVLET, new FileServlet());
        ctx.addServletMapping("*.jar", FILE_SERVLET);
        tomcat.start();
        tomcat.getServer().await();
    }
}
