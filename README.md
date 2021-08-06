# spring-boot2-velocity
Spring Boot Velocity Module For 2.x

## Usage

### Requires

* Java 8
* Spring Boot 2.x
* velocity 1.7 or under
* velocity-tools 2.0 or under

### Dependency

```xml
<dependencies>
  ...
  <dependency>
      <groupId>io.github.enemfk777</groupId>
      <artifactId>spring-boot2-velocity</artifactId>
      <version>0.0.2-SNAPSHOT</version>
  </dependency>
  ...
</dependencies>

<repositories>
    <repository>
        <id>sonatype-snapshot</id>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
        <snapshots>
          <enabled>true</enabled>
          <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>

```

### EmbeddedVelocityLayoutViewResolver
```java
  @Bean
  public EmbeddedVelocityLayoutViewResolver embeddedVelocityLayoutViewResolver() {
    EmbeddedVelocityLayoutViewResolver velocityLayoutViewResolver = new EmbeddedVelocityLayoutViewResolver();
    velocityLayoutViewResolver.setOrder(0);
    velocityLayoutViewResolver.setLayoutKey("firstLayout");
    velocityLayoutViewResolver.setScreenContentKey("body");
    velocityLayoutViewResolver.setLayoutUrl("/firstLayout.vm");
    this.velocityProperties.applyToViewResolver(velocityLayoutViewResolver);
    return velocityLayoutViewResolver;
  }
```

### PathBasedVelocityLayoutViewResolver

```java
  @Bean
  public EmbeddedVelocityLayoutViewResolver firstLayoutVelocityViewResolver() {
    PathBasedVelocityLayoutViewResolver velocityLayoutViewResolver = new PathBasedVelocityLayoutViewResolver();
    velocityLayoutViewResolver.setOrder(0);
    velocityLayoutViewResolver.setAcceptPaths("/first/**", "/third/**");
    velocityLayoutViewResolver.setLayoutKey("firstLayout");
    velocityLayoutViewResolver.setScreenContentKey("body");
    velocityLayoutViewResolver.setLayoutUrl("/firstLayout.vm");
    this.velocityProperties.applyToViewResolver(velocityLayoutViewResolver);
    return velocityLayoutViewResolver;
  }

```
