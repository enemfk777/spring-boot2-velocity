package org.enemfk777.springboot2velocity.autoconfigure;

import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

import java.util.Map;

public class VelocityTemplateAvailabilityProvider implements TemplateAvailabilityProvider {
  public VelocityTemplateAvailabilityProvider() {
  }

  public boolean isTemplateAvailable(String view, Environment environment, ClassLoader classLoader, ResourceLoader resourceLoader) {
    if (ClassUtils.isPresent("org.apache.velocity.app.VelocityEngine", classLoader)) {
      Map<String, String> map = Binder.get(environment).bind("spring.velocity.", Bindable.of(Map.class)).get();
      String loaderPath = map.getOrDefault("resource-loader-path", "classpath:/templates/");
      String prefix = map.getOrDefault("prefix", "");
      String suffix = map.getOrDefault("suffix", ".vm");
      return resourceLoader.getResource(loaderPath + prefix + view + suffix).exists();
    } else {
      return false;
    }
  }
}
