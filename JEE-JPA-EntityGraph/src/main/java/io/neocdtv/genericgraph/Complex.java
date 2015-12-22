/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.genericgraph;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xix
 */
public class Complex extends Attribute{
    private Set<Attribute> attributes;

    public Set<Attribute> getAttributes() {
        if (attributes == null) {
            attributes = new HashSet<>();
        }
        return attributes;
    }

    public void add(final Attribute attribute) {
        getAttributes().add(attribute);
    }
    
    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    } 
}
