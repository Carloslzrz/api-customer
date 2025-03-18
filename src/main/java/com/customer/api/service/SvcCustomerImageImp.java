package com.customer.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.api.dto.in.DtoCustomerImageIn;
import com.customer.api.entity.CustomerImage;
import com.customer.api.repository.RepoCustomerImage;
import com.customer.common.dto.ApiResponse;
import com.customer.exception.ApiException;
import com.customer.exception.DBAccessException;

@Service
public class SvcCustomerImageImp implements SvcCustomerImage {
	
	@Autowired
	RepoCustomerImage repo;
	
	@Value("${app.upload.dir}")
	private String uploadDir;

	@Override
	public ResponseEntity<ApiResponse> uploadCustomerImage(DtoCustomerImageIn in) {
		try {
			// Eliminar el prefijo "data:image/png;base64," si existe
			if (in.getImage().startsWith("data:image")) {
			int commaIndex = in.getImage().indexOf(",");
				if (commaIndex != -1) {
					in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}

			
			// Decodifica la cadena Base64 a bytes
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());

			// Genera un nombre único para la imagen (se asume extensión PNG)
			String fileName = UUID.randomUUID().toString() + ".png";

			// Construye la ruta completa donde se guardará la imagen
			Path imagePath = Paths.get(uploadDir, "img", "customer", fileName);
		    
			// Asegurarse de que el directorio exista
			Files.createDirectories(imagePath.getParent());

			// Escribir el archivo en el sistema de archivos
			Files.write(imagePath, imageBytes);
			
			CustomerImage customerImage = repo.findByCustomer_id(in.getCustomer_id());
			if(customerImage == null) {

				// Crear la entidad CustomerImage y guardar la URL en la base de datos
				customerImage = new CustomerImage();
				customerImage.setCustomer_id(in.getCustomer_id());
				customerImage.setImage("/" + uploadDir + "/img/customer/" + fileName);
				customerImage.setStatus(1); 

				// Guardar la ruta de la imagen
				repo.save(customerImage);
			}else {
				customerImage.setImage("/" + uploadDir + "/img/customer/" + fileName);
				repo.save(customerImage);
			}
			
			return new ResponseEntity<>(new ApiResponse("La imagen del cliente ha sido actualizada"), HttpStatus.OK);
		}catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}

	}

}
