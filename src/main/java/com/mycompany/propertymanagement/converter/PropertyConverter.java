package com.mycompany.propertymanagement.converter;

import org.springframework.stereotype.Component;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;

@Component
public class PropertyConverter {
	
	public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO) {
		
    	PropertyEntity pe = new PropertyEntity();
    	pe.setTitle(propertyDTO.getTitle());
    	pe.setDescription(propertyDTO.getDescription());
    	pe.setAddress(propertyDTO.getAddress());
    	pe.setPrice(propertyDTO.getPrice());
    	
    	return pe;
	}
	
	public PropertyDTO convertEntitytoDTO(PropertyEntity propertyEntity) {
		
    	PropertyDTO propertyDTO = new PropertyDTO();
    	propertyDTO.setId(propertyEntity.getId());
    	propertyDTO.setTitle(propertyEntity.getTitle());
    	propertyDTO.setDescription(propertyEntity.getDescription());
    	propertyDTO.setAddress(propertyEntity.getAddress());
    	propertyDTO.setPrice(propertyEntity.getPrice());
    	
    	return propertyDTO;
	}

}
