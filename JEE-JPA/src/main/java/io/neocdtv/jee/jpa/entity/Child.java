/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa.entity;

import static io.neocdtv.jee.jpa.entity.AbstractEntity.FIELD_NAME_VERSION;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author xix
 */

@Entity
@Table(name = Child.ENTITY_NAME)
public class Child extends AbstractEntity implements Serializable {
    
    public final static String ENTITY_NAME = "CHILD";
    private final static String ENTITY_GEN_NAME = ENTITY_NAME + "_GEN";
    private final static String ENTITY_SEQ_NAME = ENTITY_NAME + "_SEQ";
    private final static String FIELD_NAME_STRING_VALUE = "STRING_VALUE";
    
    @Id
    @Column(name = AbstractEntity.FIELD_NAME_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GEN_NAME)
    @SequenceGenerator(name = ENTITY_GEN_NAME, sequenceName = ENTITY_SEQ_NAME, allocationSize = 10)
    private Long id;
    
    @Version
    @Column(name = FIELD_NAME_VERSION)
    private Long version;
    
    @Column(name = FIELD_NAME_STRING_VALUE)
    private String stringValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Parent parent; 
    
    
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

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Parent getParentBE() {
        return parent;
    }

    public void setParentBE(Parent parentBE) {
        this.parent = parentBE;
        if (!parentBE.getChildren().contains(this)) {
            parentBE.getChildren().add(this);
        }
    }
}