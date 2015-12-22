/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.headerparamtomap;

import io.neocdtv.genericgraph.Complex;
import io.neocdtv.genericgraph.Attribute;
import io.neocdtv.genericgraph.Simple;
import io.neocdtv.genericgraph.PathsToGraphConverter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xix
 */
public class PathsToGraphConverterTest {

    private final PathsToGraphConverter converter = new PathsToGraphConverter();
    
    @Test
    public void testPathsToGraph() {
        String[] headerParamsSplitted = "attributeOne,children.attributeFour.one,attributeTwo,children.attributeThree,children.attributeFour.two".split(",");
        final String rootNode = "rootNode";
        final Complex graph = new PathsToGraphConverter().toGraph(rootNode, headerParamsSplitted);
        assertEquals(rootNode, graph.getName());
        final Set<Attribute> rootAttributes = graph.getAttributes();
        assertEquals(3, rootAttributes.size());
        assertAttribute("attributeOne", rootAttributes, Simple.class);
        assertAttribute("attributeTwo", rootAttributes, Simple.class);
        final Complex children = assertAttribute("children", rootAttributes, Complex.class);
        final Complex childrenComplex = (Complex)children;
        final Set<Attribute> childrenAttributes = childrenComplex.getAttributes();
        assertEquals(2, childrenAttributes.size());
        assertAttribute("attributeThree", childrenAttributes, Simple.class);
        final Complex attributeFour = assertAttribute("attributeFour", childrenAttributes, Complex.class);
        
    }
    
    private <T extends Attribute> T assertAttribute(final String name, final Set<Attribute> attributes, final Class<T> clazz) {
        for (Attribute attribute : attributes) {
            if (name.equals(attribute.getName()) && attribute.getClass().equals(clazz)) {
                return (T)attribute;
            }
        }
        return null;
    }
    
    private void assertSimple(final Attribute attribute) {
        assertNotNull(attribute);
        assertEquals(Simple.class, attribute.getClass());
    }
    
    private void assertComplex(final Attribute attribute) {
        assertNotNull(attribute);
        assertEquals(Complex.class, attribute.getClass());
    }
    
    @Test
    public void testBuildFromSplitPath() {
        final String childrenattributeFourone = "children.attributeFour.one";
        String[] split = childrenattributeFourone.split("\\.");
        String result = converter.buildPathFromParts(split);
        assertEquals(childrenattributeFourone, result);
    }

    @Test
    public void testextractChildren() {
        String[] paths = "attributeOne,children.attributeFour.one,attributeTwo,children.attributeThree,children.attributeFour.two".split(",");
        final List<String> asList = (List<String>) Arrays.asList(converter.extractChildren("children", paths));

        assertTrue(asList.contains("attributeFour.one"));
        assertTrue(asList.contains("attributeFour.two"));
        assertTrue(asList.contains("attributeThree"));
        
    }
}
