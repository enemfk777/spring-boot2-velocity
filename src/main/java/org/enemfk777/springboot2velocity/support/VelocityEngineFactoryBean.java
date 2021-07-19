package org.enemfk777.springboot2velocity.support;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;

import java.io.IOException;

public class VelocityEngineFactoryBean extends VelocityEngineFactory implements FactoryBean<VelocityEngine>, InitializingBean, ResourceLoaderAware {
  private VelocityEngine velocityEngine;

  public VelocityEngineFactoryBean() {
  }

  public void afterPropertiesSet() throws IOException, VelocityException {
    this.velocityEngine = this.createVelocityEngine();
  }

  public VelocityEngine getObject() {
    return this.velocityEngine;
  }

  public Class<? extends VelocityEngine> getObjectType() {
    return VelocityEngine.class;
  }

  public boolean isSingleton() {
    return true;
  }
}
