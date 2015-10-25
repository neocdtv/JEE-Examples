/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.timer;

import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author xix
 */

@Singleton
public class EJBTimer {
    private static final Logger LOGGER = Logger.getLogger(EJBTimer.class.getName());
    
    @Schedule(hour = "*", minute = "*", second = "*/5")
    public void scheduled() {
        LOGGER.info("automatic timer - scheduled timer call");
    }
}
