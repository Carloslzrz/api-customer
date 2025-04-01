package com.customer.api.dto.in;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonInclude(Include.NON_EMPTY)
public class InfoPaginacion implements Serializable{

	private static final long serialVersionUID = 1502769119951843826L;
	
	private static final Logger log = LoggerFactory.getLogger(InfoPaginacion.class);
	
	private Integer paginaActual;
	private Boolean paginaSiguiente;
	private Boolean paginaAnterior;
	private Integer paginasTotales;
	private Integer registrosDevueltos;
	private Long registrosTotales;
	
	public InfoPaginacion() {
		super();
	}

	public InfoPaginacion(Integer paginaActual, Boolean paginaSiguiente, Boolean paginaAnterior, Integer paginasTotales,
			Integer registrosDevueltos, Long registrosTotales) {
		super();
		this.paginaActual = paginaActual;
		this.paginaSiguiente = paginaSiguiente;
		this.paginaAnterior = paginaAnterior;
		this.paginasTotales = paginasTotales;
		this.registrosDevueltos = registrosDevueltos;
		this.registrosTotales = registrosTotales;
	}
	
	public Integer getPaginaActual() {
		return paginaActual;
	}
	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}
	public Boolean getPaginaSiguiente() {
		return paginaSiguiente;
	}
	public void setPaginaSiguiente(Boolean paginaSiguiente) {
		this.paginaSiguiente = paginaSiguiente;
	}
	public Boolean getPaginaAnterior() {
		return paginaAnterior;
	}
	public void setPaginaAnterior(Boolean paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}
	public Integer getPaginasTotales() {
		return paginasTotales;
	}
	public void setPaginasTotales(Integer paginasTotales) {
		this.paginasTotales = paginasTotales;
	}
	public Integer getRegistrosDevueltos() {
		return registrosDevueltos;
	}
	public void setRegistrosDevueltos(Integer registrosDevueltos) {
		this.registrosDevueltos = registrosDevueltos;
	}
	public Long getRegistrosTotales() {
		return registrosTotales;
	}
	public void setRegistrosTotales(Long registrosTotales) {
		this.registrosTotales = registrosTotales;
	}
	
	@Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
        	log.error("Excepción atrapada al serializar objeto: {}", e.getMessage());
        	return super.toString(); 
        }
    }
	
}
