package com.customer.api.dto.out;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.customer.api.dto.in.UsuarioResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_EMPTY)
public class AuthAPIResponse {
	
	public static final Logger log = LoggerFactory.getLogger(AuthAPIResponse.class);
	
	private String token;
	private String fechaHora;
	private List<String> detalles;
	private List<UsuarioResponse> usuarios;
	
	public AuthAPIResponse() {
		super();
	}

	public AuthAPIResponse(String token, String fechaHora, List<String> detalles) {
		super();
		this.token = token;
		this.fechaHora = fechaHora;
		this.detalles = detalles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<String> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<String> detalles) {
		this.detalles = detalles;
	}
	
	public List<UsuarioResponse> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioResponse> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
        	log.error("Excepción atrapada al serializar objeto: {}",  e.getMessage());
        	return super.toString(); 
        }
    }

}
