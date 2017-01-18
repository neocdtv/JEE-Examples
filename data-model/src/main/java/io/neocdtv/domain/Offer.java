/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.domain;

import static io.neocdtv.domain.AbstractEntity.FIELD_NAME_VERSION;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author xix
 */
@Entity
@Table(name = Offer.ENTITY_NAME)
public class Offer extends AbstractEntity implements Serializable {

  public final static String ENTITY_NAME = "OFFER";
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

  
  private String dummy;
  
  @OneToOne
  @JoinColumn(name = ENTITY_FOREIGN_KEY, nullable = true)
  private Car car;
  
  // TODO: how to solve:
  /*
  @OneToOne
  @JoinColumn(name = ENTITY_FOREIGN_KEY, nullable = true)
  private Customer owner;

  @OneToMany
  @JoinColumn(name = ENTITY_FOREIGN_KEY, nullable = true)  
  private List<Customer> contactPersons;
*/
}
