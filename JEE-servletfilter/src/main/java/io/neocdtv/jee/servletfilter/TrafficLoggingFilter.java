package io.neocdtv.jee.servletfilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xix
 */
@WebFilter(filterName = "NewFilter", urlPatterns = {"/*"})
public class TrafficLoggingFilter implements Filter {
  private static final Logger LOGGER = Logger.getLogger(TrafficLoggingFilter.class.getName());
  private FilterConfig filterConfig = null;

  public TrafficLoggingFilter() {
  }

  private void logRequest(final HttpServletRequest httpServletRequest)
          throws IOException, ServletException {
    logRequestLine(httpServletRequest);
    logRequestHeaders(httpServletRequest);
    //logRequestEntity(httpServletRequest);
  }

  private void logRequestLine(final HttpServletRequest httpServletRequest) {

    LOGGER.info(httpServletRequest.getMethod());
    LOGGER.info(httpServletRequest.getRequestURL().toString());
  }

  private void logRequestHeaders(final HttpServletRequest httpServletRequest) {
    final Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      final String header = headerNames.nextElement();
      final String value = httpServletRequest.getHeader(header);
      LOGGER.info(header + ", " + value);
    }
  }

  private void logResponse(final HttpServletResponse httpServletResponse, Writer writer)
          throws IOException, ServletException {
    logResponseLine(httpServletResponse);
    logResponseHeaders(httpServletResponse);

  }

  private void logResponseLine(final HttpServletResponse httpServletResponse) {
    LOGGER.info(httpServletResponse.getStatus() + "");
  }

  private void logResponseHeaders(final HttpServletResponse httpServletResponse) {
    final Collection<String> headerNames = httpServletResponse.getHeaderNames();
    for (final String header : headerNames) {
      final String value = httpServletResponse.getHeader(header);
      LOGGER.info(header + ", " + value);
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);

    logRequest(httpServletRequest);

    chain.doFilter(httpServletRequest, wrapper);
    logResponse(wrapper, null);
    if (wrapper.getContentType() != null) {
      String originalContent = wrapper.getResponseContent();
      if (originalContent!= null) {
        CharArrayWriter caw = new CharArrayWriter();
        caw.write(originalContent);
        LOGGER.info(originalContent);
        httpServletResponse.getWriter().write(caw.toString());
      }
      CharArrayWriter caw = new CharArrayWriter();
      caw.write(originalContent);
      httpServletResponse.getWriter().write(caw.toString());

    }

  }

  /**
   * Return the filter configuration object for this filter.
   *
   * @return
   */
  public FilterConfig getFilterConfig() {
    return (this.filterConfig);
  }

  /**
   * Set the filter configuration object for this filter.
   *
   * @param filterConfig The filter configuration object
   */
  public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  /**
   * Destroy method for this filter
   */
  @Override
  public void destroy() {
  }

  /**
   * Init method for this filter
   *
   * @param filterConfig
   */
  @Override
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  public void log(String msg) {
    filterConfig.getServletContext().log(msg);
  }
}
