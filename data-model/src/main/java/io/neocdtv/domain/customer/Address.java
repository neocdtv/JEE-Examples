/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.domain.customer;

import io.neocdtv.domain.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author xix
 */
@Entity
@Table(name = Address.ENTITY_NAME)
public class Address extends AbstractEntity implements Serializable {

  final static String ENTITY_NAME = "ADDRESS";
  private final static String ENTITY_GEN_NAME = ENTITY_NAME + "_GEN";
  private final static String ENTITY_SEQ_NAME = ENTITY_NAME + "_SEQ";
  
  @Id
  @Column(name = AbstractEntity.FIELD_NAME_ID)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GEN_NAME)
  @SequenceGenerator(name = ENTITY_GEN_NAME, sequenceName = ENTITY_SEQ_NAME, allocationSize = 10)
  private Long id;

  @Version
  @Column(name = FIELD_NAME_VERSION)
  private Long version;

  @Size(min = 5, max = 100)
  private String street;
  @Min(1)
  @Max(10)
  private int houseNumber;
  private String city;
  private Country country;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public int getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(int houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
}
