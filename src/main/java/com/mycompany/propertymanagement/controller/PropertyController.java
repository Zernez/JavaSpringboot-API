package com.mycompany.propertymanagement.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;



@RestController
@RequestMapping("/api/v1")
public class PropertyController {

	@Value("${pms.dummy:}")
	private String dummy;

	@Value("${spring.datasource.url:}")
	private String datasource;

	@Autowired
	private PropertyService propertyService;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello";
	}

	@PostMapping("/properties")
	public ResponseEntity <PropertyDTO> SaveProperty (@RequestBody PropertyDTO propertyDTO) {
		propertyDTO = propertyService.saveProperty(propertyDTO);
		ResponseEntity <PropertyDTO> responseEntity = new ResponseEntity <> (propertyDTO, HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@GetMapping("/properties")
	public ResponseEntity<List <PropertyDTO>> getAllProperies() {

		System.out.println(dummy);
		System.out.println(datasource);

		List <PropertyDTO> propertyList = propertyService.getAllProperties();
		ResponseEntity <List <PropertyDTO>> responseEntity = new ResponseEntity <> (propertyList, HttpStatus.OK);
		
		return responseEntity;
	}

	@GetMapping("/properties/users/{userId}")
	public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable("userId") Long userId){
		List<PropertyDTO> propertyList = propertyService.getAllPropertiesForUser(userId);
		ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/properties/{propertyId}")
	public ResponseEntity <PropertyDTO> updateProperty (@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
		
		propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
		ResponseEntity <PropertyDTO> responseEntity = new ResponseEntity <> (propertyDTO, HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@PatchMapping("/properties/update-description/{propertyId}")
	public ResponseEntity <PropertyDTO> updatePropertyDescription (@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
		
		propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
		ResponseEntity <PropertyDTO> responseEntity = new ResponseEntity <> (propertyDTO, HttpStatus.OK);
		
		return responseEntity;		
	}
	
	@PatchMapping("/properties/update-price/{propertyId}")
	public ResponseEntity <PropertyDTO> updatePropertyPrice (@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
		
		propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
		ResponseEntity <PropertyDTO> responseEntity = new ResponseEntity <> (propertyDTO, HttpStatus.OK);
		
		return responseEntity;		
	}
	
	@DeleteMapping("/properties/{propertyId}")	
	public ResponseEntity <Void> deleteProperty (@PathVariable Long propertyId) {
		propertyService.deleteProperty(propertyId);
		ResponseEntity <Void> responseEntity = new ResponseEntity <> (null, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
}
