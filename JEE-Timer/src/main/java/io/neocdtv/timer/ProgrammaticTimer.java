/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.timer;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;

/**
 *
 * @author xix
 */

@Startup // if @Startup not added, postconstruct won't be executed
@Singleton
public class ProgrammaticTimer {
    private static final Logger LOGGER = Logger.getLogger(ProgrammaticTimer.class.getName());

    @Resource
    javax.ejb.TimerService timerService;
    
    @PostConstruct 
    public void schedule() {
        LOGGER.info("programmatic timer - scheduling... - @PostConstruct works apparently only with the @Startup annotation");
        final TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("this can also be an object");
        final ScheduleExpression scheduleExpression = new ScheduleExpression();
        scheduleExpression.second("*/1");
        scheduleExpression.minute("*");
        scheduleExpression.hour("*");
        timerService.createCalendarTimer(scheduleExpression, timerConfig);
    }
    
    @Timeout
    public void handleTimeout(Timer timer) {
        LOGGER.info(String.format("programmatic timer - scheduled timer call, with info '%s'", (String)timer.getInfo()));
    }
}
