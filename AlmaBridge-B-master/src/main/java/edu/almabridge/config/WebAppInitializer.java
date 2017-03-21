package edu.almabridge.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("In getRootConfigClasses method of WebAppInitializer class");
		return new Class[] { AlmaBridgeConfiguration.class,WebSocketConfig.class};
		
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("In getServletConfigClasses method of WebAppInitializer class");
		
		return new Class[] {AlmaBridgeConfiguration.class};
	}
	@Override
	protected String[] getServletMappings() {
		System.out.println("In getServletMappings method of WebAppInitializer class");
		 return new String[] { "/" };
	}
	@Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }
	
	protected ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("ApplicationContextConfig.xml");
}
