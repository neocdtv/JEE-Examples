/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa.entity.onetomany.uni;

import io.neocdtv.jee.jpa.entity.*;
import static io.neocdtv.jee.jpa.entity.AbstractEntity.FIELD_NAME_VERSION;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author xix
 */

@Entity
@Table(name = ParentUniOne.ENTITY_NAME)
@NamedQueries({
    @NamedQuery(name = ParentUniOne.QUERY_NAME_READ_ALL,
            query = "SELECT e FROM ParentUniOne e")
})
public class ParentUniOne extends AbstractEntity {
    
    public final static String ENTITY_NAME = "PARENT_UNI_ONE";
    public final static String QUERY_NAME_READ_ALL = "PARENT_UNI_ONE_QUERY_NAME_READ_ALL";
    private final static String ENTITY_GEN_NAME = ENTITY_NAME + "_GEN";
    private final static String ENTITY_SEQ_NAME = ENTITY_NAME + "_SEQ";
    private final static String FIELD_NAME_ATTRIBUTE_ONE = "ATTRIBUTE_ONE";
    
    @Id
    @Column(name = AbstractEntity.FIELD_NAME_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_GEN_NAME)
    @SequenceGenerator(name = ENTITY_GEN_NAME, sequenceName = ENTITY_SEQ_NAME, allocationSize = 10)
    private Long id;

    @Version
    @Column(name = FIELD_NAME_VERSION)
    private Long version;

    @Column(name = FIELD_NAME_ATTRIBUTE_ONE)
    private String attributeOne;
    
    @Column
    private String attributeTwo;
    
    @Column
    private String attributeThree;
    
    @Column
    private Integer attributeFour;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PARENT_ID", nullable = true)
    private Set<ChildUniMany> children;
    
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

    public Integer getAttributeFour() {
        return attributeFour;
    }

    public void setAttributeFour(Integer attributeFour) {
        this.attributeFour = attributeFour;
    }

    public Set<ChildUniMany> getChildren() {
        if (children == null) {
            children = new HashSet<>();
        }
        return children;
    }

    public void addChild(final ChildUniMany childUniMany) {
        getChildren().add(childUniMany);
    }
}