package com.sinosoft.ie.booster.admin.websocket;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class WebsocketConfig implements ServletContextInitializer {

	//websocket接收base64超长
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("org.apache.tomcat.websocket.textBufferSize");
		servletContext.addListener(WebAppRootListener.class);
		servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize", "10240000");
	}

	/**
	 * 支持websocket
	 * 如果不使用内置tomcat，则无需配置
	 *
	 * @return
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
