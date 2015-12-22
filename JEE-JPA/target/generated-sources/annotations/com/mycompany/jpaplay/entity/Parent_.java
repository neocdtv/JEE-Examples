package com.mycompany.jpaplay.entity;

import com.mycompany.jpaplay.entity.Child;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20150330-rNA", date="2015-11-06T09:30:36")
@StaticMetamodel(Parent.class)
public class Parent_ { 

    public static volatile SingularAttribute<Parent, String> stringValue;
    public static volatile SetAttribute<Parent, Child> children;
    public static volatile SingularAttribute<Parent, Long> id;
    public static volatile SingularAttribute<Parent, Long> version;

}