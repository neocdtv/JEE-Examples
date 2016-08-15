/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server.model;

import io.neocdtv.jarxrs.server.model.FirstEnum;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author xix
 */
public class PojoFirst {

  @NotNull
  @Pattern(regexp = "[0-9]{12}")
  private String firstName;
  @Size(min = 10, max = 12)
  private String lastName;
  private PojoSecond pojoSecond;

  private FirstEnum firstEnum;
  private SecondEnum secondEnum;
  @Min(2)
  @Max(8)
  private Integer age;
  @Size(min = 10, max = 12)
  private List<String> liste;
  
  private Date date;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public PojoSecond getPojoSecond() {
    return pojoSecond;
  }

  public void setPojoSecond(PojoSecond pojoSecond) {
    this.pojoSecond = pojoSecond;
  }

  public FirstEnum getFirstEnum() {
    return firstEnum;
  }

  public void setFirstEnum(FirstEnum firstEnum) {
    this.firstEnum = firstEnum;
  }

  public SecondEnum getSecondEnum() {
    return secondEnum;
  }

  public void setSecondEnum(SecondEnum secondEnum) {
    this.secondEnum = secondEnum;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public List<String> getListe() {
    return liste;
  }

  public void setListe(List<String> liste) {
    this.liste = liste;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
