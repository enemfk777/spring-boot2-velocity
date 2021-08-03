package org.enemfk777.springboot2velocity.velocity;

import org.apache.struts.mock.MockServletContext;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class EmbeddedVelocityLayoutViewResolverTest {

  @Test
  public void embeddedViewWithToolboxConfig() throws Exception {
    ApplicationContext context = loadContext(LayoutSettingConfig.class);
    EmbeddedVelocityLayoutViewResolver resolver = context
        .getBean(EmbeddedVelocityLayoutViewResolver.class);
    Object viewClass = ReflectionTestUtils.getField(resolver, "viewClass");
    Object layoutUrl = ReflectionTestUtils.getField(resolver, "layoutUrl");
    Object layoutKey = ReflectionTestUtils.getField(resolver, "layoutKey");
    Object screenContentKey = ReflectionTestUtils.getField(resolver, "screenContentKey");
    assertAll(
        () -> assertEquals(EmbeddedVelocityLayoutView.class, viewClass),
        () -> assertEquals(layoutUrl, "/sample.vm"),
        () -> assertEquals(layoutKey, "layoutKey"),
        () -> assertEquals(screenContentKey, "screenContent")
    );
  }

  private ApplicationContext loadContext(Class<?> config) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.setServletContext(new MockServletContext());
    context.register(config);
    context.refresh();
    return context;
  }

  @Configuration
  static class LayoutSettingConfig {

    @Bean
    public EmbeddedVelocityLayoutViewResolver resolver() {
      EmbeddedVelocityLayoutViewResolver embeddedVelocityLayoutViewResolver = new EmbeddedVelocityLayoutViewResolver();
      embeddedVelocityLayoutViewResolver.setLayoutUrl("/sample.vm");
      embeddedVelocityLayoutViewResolver.setLayoutKey("layoutKey");
      embeddedVelocityLayoutViewResolver.setScreenContentKey("screenContent");
      return embeddedVelocityLayoutViewResolver;
    }

    @Bean
    public VelocityConfigurer velocityConfigurer() {
      return new VelocityConfigurer();
    }

  }
}
