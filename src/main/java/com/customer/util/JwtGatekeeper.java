package com.customer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.customer.api.dto.in.AuthRequest;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class JwtGatekeeper {
	
	private static final Logger log = LoggerFactory.getLogger(JwtGatekeeper.class);
	

	private String jwtToken;
	private Instant expirationTime;
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition jwtAvailable = lock.newCondition();

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public String getValidJwt(AuthRequest request) throws InterruptedException {
		lock.lock();
		try {
			if (!isJwtValid()) {
				log.info("Renovando JWT para operaciones que requieren autenticación/autorización en el sistema");
				Executors.newSingleThreadExecutor().submit(() -> eventPublisher.publishEvent(new JwtFetchEvent(this, request)));
				jwtAvailable.await();
			}
			return jwtToken;
		} finally {
			lock.unlock();
		}
	}

	public void updateJwt(String token, Instant expiresAt) {
		lock.lock();
		try {
			this.jwtToken = token;
			this.expirationTime = expiresAt;
			log.info("Liberando bloqueo de peticiones");
			jwtAvailable.signalAll();
		} finally {
			lock.unlock();
		}
	}

	private boolean isJwtValid() {
		return jwtToken != null && expirationTime != null && Instant.now().isBefore(expirationTime);
	}
}


