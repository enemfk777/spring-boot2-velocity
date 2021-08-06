package io.github.enemfk777.springboot2velocity.velocity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import io.github.enemfk777.springboot2velocity.support.VelocityEngineFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.IOException;

public class VelocityConfigurer extends VelocityEngineFactory implements VelocityConfig, InitializingBean, ResourceLoaderAware, ServletContextAware {
  private static final String SPRING_MACRO_RESOURCE_LOADER_NAME = "springMacro";
  private static final String SPRING_MACRO_RESOURCE_LOADER_CLASS = "springMacro.resource.loader.class";
//  private static final String SPRING_MACRO_LIBRARY = "org/springframework/web/servlet/view/velocity/spring.vm";
  private static final String SPRING_MACRO_LIBRARY = "templates/spring.vm";

  private final Log logger = LogFactory.getLog(this.getClass());

  private VelocityEngine velocityEngine;
  private ServletContext servletContext;

  public VelocityConfigurer() {
  }

  public void setVelocityEngine(VelocityEngine velocityEngine) {
    this.velocityEngine = velocityEngine;
  }

  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  public void afterPropertiesSet() throws IOException, VelocityException {
    if (this.velocityEngine == null) {
      this.velocityEngine = this.createVelocityEngine();
    }

  }

  protected void postProcessVelocityEngine(VelocityEngine velocityEngine) {
    velocityEngine.setApplicationAttribute(ServletContext.class.getName(), this.servletContext);
    velocityEngine.setProperty(SPRING_MACRO_RESOURCE_LOADER_CLASS, ClasspathResourceLoader.class.getName());
    velocityEngine.addProperty("resource.loader", SPRING_MACRO_RESOURCE_LOADER_NAME);
    velocityEngine.addProperty("velocimacro.library", SPRING_MACRO_LIBRARY);
    if (this.logger.isInfoEnabled()) {
      this.logger.info("ClasspathResourceLoader with name 'springMacro' added to configured VelocityEngine");
    }

  }

  public VelocityEngine getVelocityEngine() {
    return this.velocityEngine;
  }
}
