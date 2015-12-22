/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.eclipselink.entitygraph;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.Subgraph;
import org.apache.commons.beanutils.PropertyUtils;
import org.eclipse.persistence.internal.jpa.AttributeNodeImpl;
import org.eclipse.persistence.internal.jpa.EntityGraphImpl;

/**
 *
 * @author Krzysztof Wolf
 */
public class CopyPartialEntities {

    private static final String VERSION = "version";
    private static final String ID = "id";
    
    public <T> T copy(final T copyFrom, final EntityGraphImpl<T> entityGraph) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final T copyTo = (T)copyFrom.getClass().newInstance();
        copyRecursive(copyFrom, copyTo, entityGraph);
        return copyTo;
    }

    void copyRecursive(final Object copyFrom, final Object copyTo, final EntityGraphImpl entityGraph) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        copyDefaultAttributes(copyFrom, copyTo);
        final List<AttributeNodeImpl> attributeNodes = entityGraph.getAttributeNodes();
        for (int i = 0; i < attributeNodes.size(); i++) {
            final AttributeNodeImpl node = attributeNodes.get(i);
            if (node != null) {
                if (isSimpleNode(node)) {
                    handleSimpleNode(node, copyFrom, copyTo);
                } else {
                    final String attributeName = node.getAttributeName();
                    final Object property = PropertyUtils.getProperty(copyFrom, attributeName);
                    final Object newProperty = property.getClass().newInstance();
                    final Map<Class, Subgraph> subgraphs = node.getSubgraphs();
                    if (isCollectionProperty(newProperty)) {
                        handleCollectionNode(property, newProperty, subgraphs, copyTo, attributeName);
                    } else {
                        final EntityGraphImpl subgraph = (EntityGraphImpl) subgraphs.get(property.getClass());
                        copyRecursive(property, newProperty, subgraph);
                    }
                }
            }
        }
    }

    private void handleCollectionNode(final Object property, final Object newProperty, final Map<Class, Subgraph> subgraphs, final Object copyTo, final String attributeName) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        final Collection propertyAsCollection = (Collection)property;
        final Collection newPropertyAsCollection = (Collection)newProperty;
        for (Object elementFromCollection : propertyAsCollection) {
            final EntityGraphImpl subgraph = (EntityGraphImpl) subgraphs.get(elementFromCollection.getClass());
            final Object newElementForCollection = subgraph.getClassType().newInstance();
            newPropertyAsCollection.add(newElementForCollection);
            copyRecursive(elementFromCollection, newElementForCollection, subgraph);
        }
        PropertyUtils.setProperty(copyTo, attributeName, newPropertyAsCollection);
    }

    private void handleSimpleNode(final AttributeNodeImpl node, final Object copyFrom, final Object copyTo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String attributeName = node.getAttributeName();
        final Object property = PropertyUtils.getProperty(copyFrom, attributeName);
        PropertyUtils.setProperty(copyTo, attributeName, property);
    }

    private boolean isSimpleNode(final AttributeNodeImpl node) {
        final Map subgraphs = node.getSubgraphs();
        return subgraphs == null || subgraphs.isEmpty();
    }

    private boolean isCollectionProperty(final Object property) {
        return property instanceof Collection; // || property instanceof Map;
    }

    private void copyDefaultAttributes(final Object copyFrom, final Object copyTo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object id = PropertyUtils.getProperty(copyFrom, ID);
        PropertyUtils.setProperty(copyTo, ID, id);
        
        final Object version = PropertyUtils.getProperty(copyFrom, VERSION);
        PropertyUtils.setProperty(copyTo, VERSION, version);
    }
}
