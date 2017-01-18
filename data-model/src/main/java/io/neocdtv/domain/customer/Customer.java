/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.domain.customer;

import io.neocdtv.domain.AbstractEntity;
import static io.neocdtv.domain.customer.Organization.ENTITY_NAME;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = Customer.ENTITY_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Customer extends AbstractEntity implements ICustomer {

  final static String ENTITY_NAME = "CUSTOMER";
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

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = ENTITY_FOREIGN_KEY, nullable = true)
  private Address mainAddress;

  @Override
  public Address getMainAddress() {
    return mainAddress;
  }

  @Override
  public void setMainAddress(Address mainAddress) {
    this.mainAddress = mainAddress;
  }
}