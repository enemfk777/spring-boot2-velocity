package org.enemfk777.springboot2velocity.support;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public abstract class VelocityEngineUtils {
  public VelocityEngineUtils() {
  }

  public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, Map<String, Object> model, Writer writer) throws VelocityException {
    VelocityContext velocityContext = new VelocityContext(model);
    velocityEngine.mergeTemplate(templateLocation, velocityContext, writer);
  }

  public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, String encoding, Map<String, Object> model, Writer writer) throws VelocityException {
    VelocityContext velocityContext = new VelocityContext(model);
    velocityEngine.mergeTemplate(templateLocation, encoding, velocityContext, writer);
  }

  public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, Map<String, Object> model) throws VelocityException {
    StringWriter result = new StringWriter();
    mergeTemplate(velocityEngine, templateLocation, model, result);
    return result.toString();
  }

  public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, String encoding, Map<String, Object> model) throws VelocityException {
    StringWriter result = new StringWriter();
    mergeTemplate(velocityEngine, templateLocation, encoding, model, result);
    return result.toString();
  }
}
