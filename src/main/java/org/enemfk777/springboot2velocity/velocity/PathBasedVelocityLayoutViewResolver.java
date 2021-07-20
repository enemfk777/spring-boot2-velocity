package org.enemfk777.springboot2velocity.velocity;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class PathBasedVelocityLayoutViewResolver extends VelocityLayoutViewResolver {

  private static final String PATH_SEPARATOR = "/";

  private final List<String> acceptPaths = new ArrayList<>();
  private final List<String> ignorePaths = new ArrayList<>();
  private final AntPathMatcher pathMatcher = new AntPathMatcher();

  public void setAcceptPaths(String... acceptPaths) {
    this.acceptPaths.addAll(Arrays.asList(acceptPaths));
  }

  public void setIgnorePaths(String... ignorePaths) {
    this.ignorePaths.addAll(Arrays.asList(ignorePaths));
  }

  @Override
  protected AbstractUrlBasedView buildView(String viewName) throws Exception {
    String viewNameStartWithSeparator = makeViewNameStartWithSeparator(viewName);
    boolean isAcceptPath = acceptPaths.stream()
        .anyMatch(path -> pathMatcher.match(path, viewNameStartWithSeparator));
    boolean isIgnorePath = ignorePaths.stream()
        .anyMatch(path -> pathMatcher.match(path, viewNameStartWithSeparator));

    if(isAcceptPath && !isIgnorePath) {
      return super.buildView(viewNameStartWithSeparator);
    }

    return new NonExistentView();
  }

  private String makeViewNameStartWithSeparator(String viewName) {
    return viewName.startsWith(PATH_SEPARATOR) ? viewName : PATH_SEPARATOR + viewName;
  }

  private static class NonExistentView extends AbstractUrlBasedView {
    @Override
    protected boolean isUrlRequired() {
      return false;
    }

    @Override
    public boolean checkResource(Locale locale) throws Exception {
      return false;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
      // 호출되지 않을 영역
    }
  }
}
