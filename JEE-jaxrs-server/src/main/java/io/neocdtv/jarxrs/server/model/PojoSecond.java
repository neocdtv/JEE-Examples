/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server.model;

import javax.validation.constraints.Size;

/**
 *
 * @author xix
 */
public class PojoSecond {
    
    @Size(min = 10, max = 12)
    private String thirdName;

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
    
    

}
