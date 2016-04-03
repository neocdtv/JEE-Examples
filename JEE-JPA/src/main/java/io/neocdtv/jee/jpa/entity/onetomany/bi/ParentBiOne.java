/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa.entity.onetomany.bi;

import io.neocdtv.jee.jpa.entity.AbstractEntity;
import static io.neocdtv.jee.jpa.entity.AbstractEntity.FIELD_NAME_VERSION;
import io.neocdtv.jee.jpa.entity.EnumClass;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = ParentBiOne.ENTITY_NAME)
@NamedQueries({
    @NamedQuery(name = ParentBiOne.QUERY_NAME_READ_ALL,
            query = "SELECT e FROM ParentBiOne e")
})
public class ParentBiOne extends AbstractEntity {
    
    public final static String ENTITY_NAME = "PARENT_BI_ONE";
    public final static String QUERY_NAME_READ_ALL = "QUERY_NAME_READ_ALL";
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
    @Enumerated(EnumType.STRING)
    private EnumClass attributeTwo;
    
    @Column
    private String attributeThree;
    
    @Column
    private Integer attributeFour;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID", nullable = false)
    private Set<ChildBiMany> children;
    
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

    public EnumClass getAttributeTwo() {
        return attributeTwo;
    }

    public void setAttributeTwo(EnumClass attributeTwo) {
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

    public Set<ChildBiMany> getChildren() {
        if (children == null) {
            children = new HashSet<>();
        }
        return children;
    }

    public void setChildren(Set<ChildBiMany> children) {
        this.children = new HashSet<>();
        for (ChildBiMany child : children) {
            addChild(child);
        }
    }

    /*
    this method is required to set the correct back reference, 
    if not set a constraint violation will occure on parent_id in the child.
    The constraint is import if data is migrated e.g. by flyway, 
    where invalid data could be written to the DB i.e. no parent_id in child.
    */
    public void addChild(final ChildBiMany child) {
        getChildren().add(child);
        if (child.getParent() != this) {
            child.setParentBE(this);
        }
    }    
}
