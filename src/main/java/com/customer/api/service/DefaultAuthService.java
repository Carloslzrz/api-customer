package com.customer.api.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.customer.api.dto.in.AuthRequest;
import com.customer.api.dto.out.AuthAPIResponse;
import com.customer.util.JwtFetchEvent;
import com.customer.util.JwtGatekeeper;
import com.customer.util.JwtUtils;

@Service
public class DefaultAuthService implements SvcAuth {

	private static final Logger log = LoggerFactory.getLogger(DefaultAuthService.class);

	private static final String TOKEN_FETCH_FAILED = "El token no fue poblado correctamente. No se puede instruir la petición hacia servicio de consulta usuarios";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.auth.url}")
	private String apiAuthUrl;

	@Autowired
	private JwtGatekeeper jwtGatekeeper;

	private static final String authEndpoint = "/login";
	private static final String getUsuarios = "/usuario";

	@EventListener
	public void fetchAndUpdateJwt(JwtFetchEvent event){
		String newJwt = solicitaToken(event.getRequest());

		if(newJwt == null || newJwt.isBlank()) {
			jwtGatekeeper.updateJwt(null, null);
			return;
		}

		String jwtPayload = newJwt.replace("Bearer ", "");
		Instant expiration = JwtUtils.extractExpiration(jwtPayload);
		jwtGatekeeper.updateJwt(newJwt, expiration);
	}

	@Override
	public String solicitaToken(AuthRequest tokenRequest) { 
		log.info("Obteniendo JWT para autenticación de peticiones en el sistema...");
		String token = null;
		ResponseEntity<AuthAPIResponse> responseAuth = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> requestEntity = new HttpEntity<>(tokenRequest.toString(), headers);

		try {
			responseAuth = restTemplate.exchange(this.apiAuthUrl + authEndpoint, HttpMethod.POST, requestEntity, AuthAPIResponse.class);
			token = new String(responseAuth.getBody().getToken());
		} catch (RestClientException | NullPointerException e) {
			log.error("Error en la peticion para obtener el web token: {}", e.getMessage());
			log.error("--------STACKTRACE--------", e.fillInStackTrace());
			log.error("--------STACKTRACE--------");
			return null;
		} 

		log.info("... Token recuperado");
		return token; 
	}


	@Override
	public AuthAPIResponse consultaUsuarios(AuthRequest request) throws InterruptedException {

		ResponseEntity<AuthAPIResponse> responseGetUsuarios = null;
		String tokenConsulta = jwtGatekeeper.getValidJwt(request);

		if(tokenConsulta == null || tokenConsulta.isBlank()) {
			log.error(TOKEN_FETCH_FAILED);
			return new AuthAPIResponse();
		}

		try {
			log.info("Instruyendo consulta de usuarios");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Accept", "*/*");
			headers.set("Connection", "keep-alive");
			headers.set("Accept-Encoding", "gzip,deflate,br");
			headers.setBearerAuth(tokenConsulta);

			responseGetUsuarios = restTemplate.exchange(this.apiAuthUrl + getUsuarios, HttpMethod.GET, new HttpEntity<Object>(headers), AuthAPIResponse.class);

			log.info("Response: {}", responseGetUsuarios.getBody());

		}catch(RestClientException ex) {
			log.info("Excepción atrapada en envío de la solicitud. Error: {}", ex.getMessage());
			AuthAPIResponse response = new AuthAPIResponse();
			response.setDetalles(Arrays.asList(ex.getMessage()));
			return response;
		}

		return responseGetUsuarios.getBody();
	}

}
