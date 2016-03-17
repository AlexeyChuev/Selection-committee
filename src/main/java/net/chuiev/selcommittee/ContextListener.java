package net.chuiev.selcommittee;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Content Listener for init Log4j and CommandManager.
 *
 * @author Oleksii Chuiev
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
        servletContext.log("ServletContextListener destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
        servletContext.log("ServletContextListener initialization started");
        initLog4J(servletContext);
        initCommandContainer();
        servletContext.log("ServletContextListener initialization finished");
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        servletContext.log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext
                    .getRealPath("WEB-INF/classes/log4j.properties"));
        } catch (Exception e) {
            LOG.error("Cannot configure Log4j", e);
        }
        servletContext.log("Log4J initialization finished");
        LOG.debug("Log4j has been initialized");
    }

    /**
     * Initializes CommandManager.
     */
    private void initCommandContainer() {
        try {
            Class.forName("net.chuiev.selcommittee.controller.CommandManager");
        } catch (ClassNotFoundException e) {
            LOG.error("Cannot initialize Command Manager", e);
            throw new IllegalStateException("Cannot initialize Command Manager", e);
        }
    }
}
