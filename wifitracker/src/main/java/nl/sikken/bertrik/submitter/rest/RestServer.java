package nl.sikken.bertrik.submitter.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public final class RestServer {
	
	public static void main(String[] args) throws Exception {
		RestServer restServer = new RestServer();
		restServer.start();
	}

	private void start() throws Exception {
		Server server = createRestServer(3000, "", RestApiImpl.class);
		server.start();
	}
	
    private Server createRestServer(int port, String contextPath, Class<?> clazz) throws Exception {
        Server server = new Server(port);

        // setup context
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        
        // setup web services container
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter(ServerProperties.PROVIDER_CLASSNAMES, clazz.getCanonicalName());
        context.addServlet(sh, contextPath + "/*");
        server.setHandler(context);
        
        return server;
    }
}
