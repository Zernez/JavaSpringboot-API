package com.mycompany.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;



//Makes the instance of a class in singleton
@Service
public class PropertyServiceImpl implements PropertyService {

	@Value("${pms.dummy:}")
	private String dummy;

	@Value("${spring.datasource.url:}")
	private String datasource;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private PropertyConverter propertyConverter;

	//Factory or Prototype OOP pattern as multiple implementation of the same interface
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
    	
    	PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
    	pe = propertyRepository.save(pe);
    	propertyDTO = propertyConverter.convertEntitytoDTO(pe);
    	
		return propertyDTO;
	}
    
    @Override
    public List<PropertyDTO> getAllProperties() {

		System.out.println("Service is provided in " + dummy);

		List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
    	List<PropertyDTO> propList = new ArrayList<>();
    	
    	for (PropertyEntity pe : listOfProps) {
    		PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
    		propList.add(dto);
    	}
		return propList;
	}

	@Override
	public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
		
		PropertyDTO dto = null;
		Optional <PropertyEntity> optEn = propertyRepository.findById(propertyId);
		
		if (optEn.isPresent()) {
			PropertyEntity pe = optEn.get();
	    	pe.setTitle(propertyDTO.getTitle());
	    	pe.setDescription(propertyDTO.getDescription());
	    	pe.setAddress(propertyDTO.getAddress());
	    	pe.setPrice(propertyDTO.getPrice());
	    	dto = propertyConverter.convertEntitytoDTO(pe);
	    	
	    	propertyRepository.save(pe);
		}
		
		return dto;
	}

	@Override
	public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
		
		PropertyDTO dto = null;
		Optional <PropertyEntity> optEn = propertyRepository.findById(propertyId);
		
		if (optEn.isPresent()) {
			PropertyEntity pe = optEn.get();
	    	pe.setDescription(propertyDTO.getDescription());
	    	dto = propertyConverter.convertEntitytoDTO(pe);
	    	
	    	propertyRepository.save(pe);
		}
		
		return dto;
	}

	@Override
	public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
		
		PropertyDTO dto = null;
		Optional <PropertyEntity> optEn = propertyRepository.findById(propertyId);
		
		if (optEn.isPresent()) {
			PropertyEntity pe = optEn.get();
	    	pe.setPrice(propertyDTO.getPrice());
	    	dto = propertyConverter.convertEntitytoDTO(pe);
	    	
	    	propertyRepository.save(pe);
		}
		
		return dto;
	}

	@Override
	public void deleteProperty(Long propertyId) {
		propertyRepository.deleteById(propertyId);
		
	}
}
