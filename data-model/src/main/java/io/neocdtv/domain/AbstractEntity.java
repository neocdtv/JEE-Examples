/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.domain;

import java.io.Serializable;

/**
 *
 * @author xix
 */
public abstract class AbstractEntity implements Serializable {

  public final static String FIELD_NAME_VERSION = "VERSION";
  public final static String FIELD_NAME_ID = "ID";
}
