package br.com.roberto;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/v1")
public class SistemaC extends Application{

	public SistemaC(@Context ServletConfig servletConfig) {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("SistemaC API");
		beanConfig.setBasePath("/cdiexample/v1");
		beanConfig.setResourcePackage("br.com.roberto");
		beanConfig.setScan(true);
	}
	
}
