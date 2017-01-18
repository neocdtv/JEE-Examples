/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.eclipselink.entitygraph;

import java.util.Arrays;
import java.util.HashSet;
import javax.persistence.EntityGraph;
import org.eclipse.persistence.internal.jpa.EntityGraphImpl;

/**
 *
 * @author xix
 */
public class PathsToEntityGraphConverter {

  private static final String PROPERTY_SEPARATOR = ".";
  private static final String PROPERT_SEPARATOR_REGEX = "\\" + PROPERTY_SEPARATOR;
  private static final String MULTIPLE_PROPERTY_REGEX = String.format(".*%s.*", PROPERT_SEPARATOR_REGEX);
  private static final String MULTIPLE_PROPERTY_WITH_PARENT_TO_REPLACE = "%s" + PROPERTY_SEPARATOR + ".*";

  public <T> EntityGraph<T> toGraph(EntityGraphImpl<T> entityGraph, final String[] paths) {

    splitRecursive(entityGraph, paths);
    return entityGraph;
  }

  <T> void splitRecursive(final EntityGraphImpl<T> parent, final String[] paths) {
    // TODO: try - change data structure to iterator and remove markPathAsProcessed
    for (int i = 0; i < paths.length; i++) {
      if (!paths[i].isEmpty()) {
        if (isSimpleProperty(paths[i])) {
          parent.addAttributeNodes(paths[i]);
          markPathAsProcessed(paths, i);
        } else {
          // complex means !prmitiv && !wrapper
          final String[] split = paths[i].split(PROPERT_SEPARATOR_REGEX);
          final String parentName = split[0];

          final EntityGraphImpl<?> subGraph = (EntityGraphImpl) parent.addSubgraph(parentName);
          final String[] childrenPaths = removeParentFromPaths(parentName, paths);
          splitRecursive(subGraph, childrenPaths);
        }
      }
    }
  }

  private void markPathAsProcessed(final String[] paths, int i) {
    paths[i] = ""; // TODO: improve
  }

  // TODO: this looks a little bit to complex try to simplify, this should only remove the first part i.e. parent of the selector parent.child.child
  public String[] removeParentFromPaths(final String parentName, final String[] paths) {
    HashSet<String> children = new HashSet<>();
    String multiplePathRegex = String.format(MULTIPLE_PROPERTY_WITH_PARENT_TO_REPLACE, parentName);
    for (int i = 0; i < paths.length; i++) {
      if (paths[i].matches(multiplePathRegex)) {
        final String[] split = paths[i].split(PROPERT_SEPARATOR_REGEX);
        markPathAsProcessed(paths, i);
        final String buildFromSplitPath = buildPathFromParts(Arrays.copyOfRange(split, 1, split.length));
        children.add(buildFromSplitPath);
      }
    }
    return children.toArray(new String[children.size()]);
  }

  public String buildPathFromParts(final String[] split) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < split.length; i++) {
      final String current = split[i];
      if (i == 0) {
        builder.append(current);
      } else {
        builder.append(PROPERTY_SEPARATOR);
        builder.append(current);
      }
    }
    return builder.toString();
  }

  public boolean isSimpleProperty(String path) {
    return !path.matches(MULTIPLE_PROPERTY_REGEX); // TODO: improve: path.split(".").length > 1
  }
}
