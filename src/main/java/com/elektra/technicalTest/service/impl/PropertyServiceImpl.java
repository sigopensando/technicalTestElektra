package com.elektra.technicalTest.service.impl;

import com.elektra.technicalTest.converter.PropertyConverter;
import com.elektra.technicalTest.dto.PropertyDTO;
import com.elektra.technicalTest.entity.PropertyEntity;
import com.elektra.technicalTest.repository.PropertyRepository;
import com.elektra.technicalTest.repository.PropertyRepositoryJDBC;
import com.elektra.technicalTest.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyRepositoryJDBC propertyRepositoryJDBC;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);
        PropertyEntity propertyEntitySaved = propertyRepository.save(propertyEntity);
//        PropertyEntity propertyEntitySaved = propertyRepositoryJDBC.save(propertyEntity);
        return propertyConverter.convertEntityToDTO(propertyEntitySaved);
    }

    @Override
    public PropertyDTO getPropertyById(Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntitySaved = propertyRepository.findById(propertyId);
        PropertyEntity propertyEntityFounded = new PropertyEntity();
        if (optionalPropertyEntitySaved.isPresent()) {
            propertyEntityFounded = optionalPropertyEntitySaved.get();
            return propertyConverter.convertEntityToDTO(propertyEntityFounded);
        } else {
            return null;
        }
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
//        List<PropertyEntity> allPropertyEntities = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyEntity> allPropertyEntities = (List<PropertyEntity>) propertyRepositoryJDBC.findAll();
        List<PropertyDTO> allPropertyDTOs = new ArrayList<>();
        for (PropertyEntity propertyEntity : allPropertyEntities) {
            allPropertyDTOs.add(propertyConverter.convertEntityToDTO(propertyEntity));
        }
        return allPropertyDTOs;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
//        Optional<PropertyEntity> optionalPropertyEntity = propertyRepositoryJDBC.findById(propertyId);
        PropertyDTO propertyDTOUpdated = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            propertyEntity.setTitle(propertyDTO.getTitle());
            propertyEntity.setDescription(propertyDTO.getDescription());
            propertyEntity.setOwnerName(propertyDTO.getOwnerName());
            propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
            propertyEntity.setPrice(propertyDTO.getPrice());
            propertyEntity.setAddress(propertyDTO.getAddress());

            PropertyEntity propertyEntityUpdated = propertyRepository.save(propertyEntity);
//            PropertyEntity propertyEntityUpdated = propertyRepositoryJDBC.save(propertyEntity);
            propertyDTOUpdated = propertyConverter.convertEntityToDTO(propertyEntityUpdated);
        }

        return propertyDTOUpdated;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
//        Optional<PropertyEntity> optionalPropertyEntity = propertyRepositoryJDBC.findById(propertyId);
        PropertyDTO propertyDTOUpdated = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            propertyEntity.setDescription(propertyDTO.getDescription());

            PropertyEntity propertyEntityUpdated = propertyRepository.save(propertyEntity);
//            PropertyEntity propertyEntityUpdated = propertyRepositoryJDBC.save(propertyEntity);
            propertyDTOUpdated = propertyConverter.convertEntityToDTO(propertyEntityUpdated);
        }

        return propertyDTOUpdated;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
//        Optional<PropertyEntity> optionalPropertyEntity = propertyRepositoryJDBC.findById(propertyId);
        PropertyDTO propertyDTOUpdated = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            propertyEntity.setPrice(propertyDTO.getPrice());

            PropertyEntity propertyEntityUpdated = propertyRepository.save(propertyEntity);
//            PropertyEntity propertyEntityUpdated = propertyRepositoryJDBC.save(propertyEntity);
            propertyDTOUpdated = propertyConverter.convertEntityToDTO(propertyEntityUpdated);
        }

        return propertyDTOUpdated;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
//        propertyRepositoryJDBC.deleteById(propertyId);
    }
}
