/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.domain;

import static io.neocdtv.domain.AbstractEntity.FIELD_NAME_VERSION;
import static io.neocdtv.domain.Offer.ENTITY_NAME;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

/**
 *
 * @author xix
 */
@Entity
@Table(name = Car.ENTITY_NAME)
public class Car extends AbstractEntity implements Serializable {

  public final static String ENTITY_NAME = "CAR";
  private final static String ENTITY_GEN_NAME = ENTITY_NAME + "_GEN";
  private final static String ENTITY_SEQ_NAME = ENTITY_NAME + "_SEQ";
  private final static String ENTITY_FOREIGN_KEY = ENTITY_NAME + "_" + AbstractEntity.FIELD_NAME_ID;

  @Id
  @Column(name = AbstractEntity.FIELD_NAME_ID)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GEN_NAME)
  @SequenceGenerator(name = ENTITY_GEN_NAME, sequenceName = ENTITY_SEQ_NAME, allocationSize = 10)
  private Long id;

  @Version
  @Column(name = FIELD_NAME_VERSION)
  private Long version;

  @Pattern(regexp = "[0-9]{12}")
  private String model;
  

  public String getModelCode() {
    return model;
  }

  public void setModelCode(String modelCode) {
    this.model = modelCode;
  }
  
  @OneToMany
  @JoinColumn(name = ENTITY_FOREIGN_KEY, nullable = true)
  private List<Feature> features;
}
