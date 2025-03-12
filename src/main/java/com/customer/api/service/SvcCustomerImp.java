package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.api.dto.in.DtoCustomerIn;
import com.customer.api.dto.out.DtoCustomerListOut;
import com.customer.api.dto.out.DtoCustomerOut;
import com.customer.api.entity.Customer;
import com.customer.api.repository.RepoCustomer;
import com.customer.common.dto.ApiResponse;
import com.customer.common.mapper.MapperCustomer;
import com.customer.exception.ApiException;
import com.customer.exception.DBAccessException;

@Service
public class SvcCustomerImp implements SvcCustomer{
	
	@Autowired
	RepoCustomer repo;
	
	@Autowired
	MapperCustomer mapper;

	@Override
	public ResponseEntity<List<DtoCustomerListOut>> getCustomers() {
		try {
			List<Customer> customers = repo.findAll();
			return new ResponseEntity<>(mapper.fromCustomerList(customers), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<DtoCustomerOut> getCustomer(Integer id) {
		try {
			validateCustomerId(id);
			// getCustomer
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> createCustomer(DtoCustomerIn in) {
		try {
			Customer customer = mapper.fromDto(in);
			repo.save(customer);
			return new ResponseEntity<>(new ApiResponse("El cliente ha sido registrado"), HttpStatus.CREATED);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_customer_rfc"))
				throw new ApiException(HttpStatus.CONFLICT, "El rfc del cliente ya está registrado");
			if (e.getLocalizedMessage().contains("ux_customer_mail"))
				throw new ApiException(HttpStatus.CONFLICT, "El mail del cliente ya está registrado");
			if (e.getLocalizedMessage().contains("fk_customer_region"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de región no existe");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateCustomer(Integer id, DtoCustomerIn in) {
		try {
			validateCustomerId(id);
			Customer customer = mapper.fromDto(id, in);
			repo.save(customer);
			return new ResponseEntity<>(new ApiResponse("El cliente ha sido actualizado"), HttpStatus.OK);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_customer_rfc"))
				throw new ApiException(HttpStatus.CONFLICT, "El rfc del cliente ya está registrado");
			if (e.getLocalizedMessage().contains("ux_customer_mail"))
				throw new ApiException(HttpStatus.CONFLICT, "El mail del cliente ya está registrado");
			if (e.getLocalizedMessage().contains("fk_customer_region"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de región no existe");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> enableCustomer(Integer id) {
		try {
			validateCustomerId(id);
			Customer customer = repo.findById(id).get();
			customer.setStatus(1);
			repo.save(customer);
			return new ResponseEntity<>(new ApiResponse("El cliente ha sido activado"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> disableCustomer(Integer id) {
		try {
			validateCustomerId(id);
			Customer customer = repo.findById(id).get();
			customer.setStatus(0);
			repo.save(customer);
			return new ResponseEntity<>(new ApiResponse("El cliente ha sido desactivado"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	private void validateCustomerId(Integer id) {
		try {
			if(repo.findById(id).isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del cliente no existe");
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

}
