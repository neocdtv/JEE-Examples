package io.neocdtv.webapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
public class ServletFilter implements Filter {

  private static final Logger LOGGER = Logger.getLogger(ServletFilter.class.getName());
  private FilterConfig filterConfig = null;

  public ServletFilter() {
  }

  private void doBeforeProcessing(ServletRequest request, ServletResponse response)
          throws IOException, ServletException {
    LOGGER.info("ServletFilter.doBeforeProcessing");
  }

  private void doAfterProcessing(ServletRequest request, ServletResponse response)
          throws IOException, ServletException {
    LOGGER.info("ServletFilter.doAfterProcessing");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain)
          throws IOException, ServletException {

    LOGGER.info("ServletFilter.doFilter");
    doBeforeProcessing(request, response);
    chain.doFilter(request, response);
    doAfterProcessing(request, response);

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
