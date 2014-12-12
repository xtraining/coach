package com.coach.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.coach.resolver.SysSessionResolver;

@Component
public class RemoveWebSessionScheduler {
	private static final Logger log = LoggerFactory.getLogger(RemoveWebSessionScheduler.class);
	 @Autowired private SysSessionResolver sessionResolver;
		@Scheduled(fixedRate = 60 * 1000)
		void removeWebSession() {
			sessionResolver.removeInvalidSession();
		}
}
