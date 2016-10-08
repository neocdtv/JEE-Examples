package io.neocdtv.webapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author xix
 */
public class ServletFilter2 implements Filter {

  private static final Logger LOGGER = Logger.getLogger(ServletFilter2.class.getName());
  private FilterConfig filterConfig = null;

  public ServletFilter2() {
  }

  private void filterRequest(ServletRequest request)
          throws IOException, ServletException {
    LOGGER.info("ServletFilter2.filterRequest");
  }

  private void filterResponse(ServletResponse response)
          throws IOException, ServletException {
    LOGGER.info("ServletFilter2.filterResponse");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain)
          throws IOException, ServletException {

    filterRequest(request);
    executeNextFilter(chain, request, response);
    filterResponse(response);

  }

  private void executeNextFilter(FilterChain chain, ServletRequest request, ServletResponse response) throws IOException, ServletException {
    LOGGER.info("ServletFilter2.executeNextFilter");
    chain.doFilter(request, response);
  }

  public FilterConfig getFilterConfig() {
    return (this.filterConfig);
  }

  public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }
}
