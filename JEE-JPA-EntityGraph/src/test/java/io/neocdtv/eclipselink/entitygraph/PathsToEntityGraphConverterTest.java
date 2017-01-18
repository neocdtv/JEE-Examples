/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.eclipselink.entitygraph;

import javax.persistence.EntityGraph;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xix
 */
public class PathsToEntityGraphConverterTest {
  
  @Test
  public void removeParentFromPath() {
    String parentName = "";
    String[] paths = {"parent.childOne.childTwo", "parent.childOne"};
    PathsToEntityGraphConverter instance = new PathsToEntityGraphConverter();
    String[] expResult = {"childOne.childTwo", "childOne"};
    String[] result = instance.removeParentFromPaths(parentName, paths);
    assertArrayEquals(expResult, result);
  }
}
