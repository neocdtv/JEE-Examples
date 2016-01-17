/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa.entity.onetomany.uni;

import io.neocdtv.jee.jpa.entity.*;
import static io.neocdtv.jee.jpa.entity.AbstractEntity.FIELD_NAME_VERSION;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author xix
 */

@Entity
@Table(name = ChildUniMany.ENTITY_NAME)
public class ChildUniMany extends AbstractEntity implements Serializable {
    
    public final static String ENTITY_NAME = "CHILD_UNI_MANY";
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
    
    @Column
    private String attributeOne;

    @Column
    private String attributeTwo;

    @Column
    private String attributeThree;

    @Column
    private String attributeFour;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public String getAttributeOne() {
        return attributeOne;
    }

    public void setAttributeOne(String attributeOne) {
        this.attributeOne = attributeOne;
    }

    public String getAttributeTwo() {
        return attributeTwo;
    }

    public void setAttributeTwo(String attributeTwo) {
        this.attributeTwo = attributeTwo;
    }

    public String getAttributeThree() {
        return attributeThree;
    }

    public void setAttributeThree(String attributeThree) {
        this.attributeThree = attributeThree;
    }

    public String getAttributeFour() {
        return attributeFour;
    }

    public void setAttributeFour(String attributeFour) {
        this.attributeFour = attributeFour;
    }
}
