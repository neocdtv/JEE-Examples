/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jndi;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author xix
 */
@ApplicationScoped
public class ManualJndi {

  //private final static Logger LOGGER = Logger.getLogger(ManualJndi.class.getName());
  private static final Logger LOGGER = LoggerFactory.getLogger(ManualJndi.class.getName());
  private static final String JNDI_VALUE = "java:global/simple/value";

  public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
    LOGGER.info("bla blup");
    //org.slf4j.LoggerFactory.getLogger(io.neocdtv.jee.jndi.ManualJndi.class).info("bla blup");
    LOGGER.info("post constructing cdi bean");
    try {
      final InitialContext initialContext = new InitialContext();
      final String value = (String) initialContext.lookup(JNDI_VALUE);
      LOGGER.info( "JNDI retrieved value " + value);
    } catch (NamingException ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
  }

  @PostConstruct
  public void postConstruct() {
    try {
      LOGGER.info("post constructing cdi bean");
      try {
        final InitialContext initialContext = new InitialContext();
        initialContext.rebind(JNDI_VALUE, "1234");
      } catch (NamingException ex) {
        LOGGER.error(ex.getMessage(), ex);
      }
      ClassPool pool = ClassPool.getDefault();
      ClassClassPath ccpath = new ClassClassPath(Dummy.class);
      pool.insertClassPath(ccpath);
      //CtClass ctClass = pool.get("io.neocdtv.jee.jndi.Dummy");
      Enumeration<URL> e = this.getClass().getClassLoader().getResources("");
      while (e.hasMoreElements()) {
        LOGGER.info("ClassLoader Resource: " + e.nextElement());
      }
    } catch (IOException ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
  }
}
