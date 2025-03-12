package com.customer.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.customer.api.dto.in.DtoCustomerIn;
import com.customer.api.dto.out.DtoCustomerListOut;
import com.customer.api.entity.Customer;

@Service
public class MapperCustomer {
	
	public List<DtoCustomerListOut> fromCustomerList(List<Customer> customers){
		List<DtoCustomerListOut> list = new ArrayList<>();
		for(Customer customer: customers) {
			list.add(new DtoCustomerListOut(
					customer.getCustomer_id(),
					customer.getName(),
					customer.getSurname(),
					customer.getRfc(),
					customer.getStatus()
					));
		}
		return list;
	}

	public Customer fromDto(DtoCustomerIn dto) {
		Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setRfc(dto.getRfc());
        customer.setMail(dto.getMail());
        customer.setPhone_number(dto.getPhone_number());
        customer.setAddress(dto.getAddress());
        customer.setUser_id(dto.getUser_id());
        customer.setRegion_id(dto.getRegion_id());
        customer.setStatus(1);
        return customer;
	}
	
	public Customer fromDto(Integer id, DtoCustomerIn dto) {
		Customer customer = fromDto(dto);
		customer.setCustomer_id(id);
		return customer;
	}
	
	
	
}
