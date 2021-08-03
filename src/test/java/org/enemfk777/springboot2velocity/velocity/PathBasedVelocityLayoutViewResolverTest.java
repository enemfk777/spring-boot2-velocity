package org.enemfk777.springboot2velocity.velocity;

import org.apache.struts.mock.MockServletContext;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PathBasedVelocityLayoutViewResolverTest {

  @Test
  public void embeddedVelocityLayoutViewTest() {
    ApplicationContext context = loadContext(PathBasedVelocityLayoutViewResolverSetting.class);
    PathBasedVelocityLayoutViewResolver resolver = context
        .getBean(PathBasedVelocityLayoutViewResolver.class);

    assertAll(
        () -> assertThat(resolver.buildView("/test/testView")).isInstanceOf(EmbeddedVelocityLayoutView.class),
        () -> assertThat(resolver.buildView("/sample/sampleView")).isInstanceOf(EmbeddedVelocityLayoutView.class)
        );
  }

  @Test
  public void nonExistentViewTest() throws Exception {
    ApplicationContext context = loadContext(PathBasedVelocityLayoutViewResolverSetting.class);
    PathBasedVelocityLayoutViewResolver resolver = context
        .getBean(PathBasedVelocityLayoutViewResolver.class);

    assertAll(
        () -> assertThat(resolver.buildView("/rootPathView").getClass().getSimpleName()).contains("NonExistentView"),
        () -> assertThat(resolver.buildView("/ignore/ignorePathView").getClass().getSimpleName()).contains("NonExistentView")
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
  static class PathBasedVelocityLayoutViewResolverSetting {

    @Bean
    public PathBasedVelocityLayoutViewResolver resolver() {
      PathBasedVelocityLayoutViewResolver pathBasedVelocityLayoutViewResolver = new PathBasedVelocityLayoutViewResolver();
      pathBasedVelocityLayoutViewResolver.setAcceptPaths("/test/**", "/sample/**");
      pathBasedVelocityLayoutViewResolver.setIgnorePaths("/ignore/**");
      return pathBasedVelocityLayoutViewResolver;
    }

    @Bean
    public VelocityConfigurer velocityConfigurer() {
      return new VelocityConfigurer();
    }

  }
}
