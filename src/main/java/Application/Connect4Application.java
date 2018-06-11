package Application;

import Controllers.GameController;
import Controllers.GameControllerInt;
import Utils.CORSFilter;
import Utils.ResourceLoader;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Connect4Application {

    public static void main(String[] args) throws Exception {
        // TODO : export to config

        Tomcat tomcat = new Tomcat();
        String port = "8080"; // Also change in index.html
        tomcat.setPort(Integer.parseInt(port));
        String webAppDirLocation = "";
        Context context = tomcat.addWebapp("", new File(webAppDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));

        context.setResources(resources);
        tomcat.addServlet(context, "jersey-container-servlet", resourceConfig());
        context.addServletMapping("/rest/*", "jersey-container-servlet");

        tomcat.start();
        tomcat.getServer().await();
    }

    private static ServletContainer resourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig(new ResourceLoader().getClasses());
        resourceConfig.register(new CORSFilter());
        resourceConfig.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(GameController.class).to(GameControllerInt.class);
            }
        });
        return new ServletContainer(resourceConfig);
    }

}