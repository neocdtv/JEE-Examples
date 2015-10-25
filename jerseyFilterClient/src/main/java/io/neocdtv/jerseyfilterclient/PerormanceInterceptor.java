/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterclient;

import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.interceptor.Interceptor;

/**
 *
 * @author xix
 */

@Interceptor
@PerformanceLog
public class PerormanceInterceptor {
    
    private static final Logger logger = Logger.getLogger(PerormanceInterceptor.class.getName());
    
    @AroundInvoke
    public Object logEntry(InvocationContext ctx) throws Exception{
        final long start = System.currentTimeMillis();
        final Object proceed = ctx.proceed();
        final long end = System.currentTimeMillis();
        final long timeSpentOnCall = end - start;
        log(timeSpentOnCall);
        return proceed;
    }

    private void log(long timeSpentOnCall) {
        //logger.info(String.format("YYY - time spent on call %s", timeSpentOnCall));
    }
}
