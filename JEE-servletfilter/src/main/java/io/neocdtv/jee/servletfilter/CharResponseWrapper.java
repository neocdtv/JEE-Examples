/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.servletfilter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author xix
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {

  protected CharArrayWriter charWriter;
  protected PrintWriter writer;
  protected boolean getOutputStreamCalled;
  protected boolean getWriterCalled;

  public CharResponseWrapper(HttpServletResponse response) {
    super(response);

    charWriter = new CharArrayWriter();
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    if (getWriterCalled) {
      throw new IllegalStateException("getWriter already called");
    }

    getOutputStreamCalled = true;
    return super.getOutputStream();
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    if (writer != null) {
      return writer;
    }
    if (getOutputStreamCalled) {
      throw new IllegalStateException("getOutputStream already called");
    }
    getWriterCalled = true;
    writer = new PrintWriter(charWriter);
    return writer;
  }

  public String getResponseContent() {
    String s = null;

    if (writer != null) {
      s = charWriter.toString();
    }
    return s;
  }
}
