package com.airlines.flyaway.listeners;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.airlines.flyaway.threads.WithdrawTicketsThread;

public class WithdrawTicketsListener implements ServletContextListener {
	
	private ScheduledExecutorService scheduler;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable command = new WithdrawTicketsThread();
        // Delay 10 Minute to first execution
        long initialDelay = 2;
        TimeUnit unit = TimeUnit.MINUTES;
        // period the period between successive executions
        long period = 60;// 60 Minute!
 
        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		scheduler.shutdown();

	}

}
