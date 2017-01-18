/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import io.neocdtv.domain.customer.Address;
import io.neocdtv.domain.customer.Country;
import io.neocdtv.domain.customer.Customer;
import io.neocdtv.domain.customer.Person;
import javax.persistence.EntityManager;
import org.junit.Test;

public class ExternalDBFirst extends ExternalDBTest {

  @Override
  public void setUp() {

  }

  @Test
  public void test() {
    final Customer parent = createPerson();
    final EntityManager entityManager = getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(parent);
    entityManager.getTransaction().commit();

    entityManager.getTransaction().begin();
    entityManager.getTransaction().commit();
  }

  private Customer createPerson() {
    final Person person = new Person();
    person.setFirstName("Krzysztof");
    person.setLastName("Wolf");
    person.setMainAddress(createAddress());
    return person;
  }
  
  private Address createAddress() {
    final Address address = new Address();
    address.setCity("Munich");
    address.setCountry(Country.DE);
    address.setHouseNumber(10);
    address.setStreet("Gmunder Str.");
    return address;
  }
}
