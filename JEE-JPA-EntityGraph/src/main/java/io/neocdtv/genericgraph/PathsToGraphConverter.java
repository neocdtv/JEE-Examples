/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.genericgraph;

import io.neocdtv.genericgraph.Complex;
import io.neocdtv.genericgraph.Simple;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author xix
 */
public class PathsToGraphConverter {

    public Complex toGraph(final String parentName, final String[] paths) {
        final Complex complex = new Complex();
        complex.setName(parentName);
        splitRecursive(complex, paths);
        return complex;
    }

    public void splitRecursive(final Complex parent, final String[] paths) {
        for (int i = 0; i < paths.length; i++) {
            if (!paths[i].isEmpty()) {
                if (isSimple(paths[i])) {
                    final Simple simple = new Simple();
                    simple.setName(paths[i]);
                    parent.add(simple);
                    markPathAsProcessed(paths, i);
                } else {
                    final String[] split = paths[i].split("\\.");
                    final String complexAttributeName = split[0];
                    final Complex complex = new Complex();
                    complex.setName(complexAttributeName);
                    parent.add(complex);
                    final String[] extractChildren = extractChildren(complexAttributeName, paths);
                    splitRecursive(complex, extractChildren);
                }
            }
        }
    }

    private void markPathAsProcessed(final String[] paths, int i) {
        paths[i] = "";
    }

    public String[] extractChildren(final String parentName, final String[] paths) {
        HashSet<String> children = new HashSet<>();
        String regex = String.format("%s\\..*", parentName);
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].matches(regex)) {
                final String[] split = paths[i].split("\\.");
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
                builder.append(".");
                builder.append(current);
            }
        }
        return builder.toString();
    }

    public boolean isSimple(String path) {
        return !path.matches(".*\\..*");
    }
}
