package com.customer.api.dto.in;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@AuthRequestConstraint
public class AuthRequest {
	
	private static final Logger log = LoggerFactory.getLogger(AuthRequest.class);
	
	private String nombreUsuario;
	
	@Email
	private String correo;
	
	@NotBlank
	private String contrasena;
	
	public AuthRequest() {
		super();		
	}

	public AuthRequest(String nombreUsuario, String correo, String contrasena) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.contrasena = contrasena;
	}
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	@Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
        	log.error("Excepción atrapada al serializar objeto: {} ", e.getMessage());
        	return super.toString(); 
        }
    }

}
