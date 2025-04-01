package com.customer.api.dto.in;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthRequestValidator implements ConstraintValidator<AuthRequestConstraint, AuthRequest>{

	@Override
	public boolean isValid(AuthRequest request, ConstraintValidatorContext context) {
		return StringUtils.hasLength(request.getCorreo()) || StringUtils.hasLength(request.getNombreUsuario());
	}

}
