package com.elektra.technicalTest.controller;

import com.elektra.technicalTest.dto.PropertyDTO;
import com.elektra.technicalTest.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping()
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTORequest) {
        PropertyDTO propertyDTOResponse = propertyService.saveProperty(propertyDTORequest);
        return new ResponseEntity<>(propertyDTOResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propertyDTOsResponse = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyDTOsResponse, HttpStatus.OK);
    }

    @GetMapping("{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long propertyId) {
        PropertyDTO propertyDTOResponse = propertyService.getPropertyById(propertyId);
        if(propertyDTOResponse == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(propertyDTOResponse, HttpStatus.OK);
    }

    @PutMapping("{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTORequest,
                                                      @PathVariable Long propertyId) {
        PropertyDTO propertyDTOResponse = propertyService.updateProperty(propertyDTORequest, propertyId);
        return new ResponseEntity<>(propertyDTOResponse, HttpStatus.OK);
    }

    @PatchMapping("update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTORequest,
                                                                 @PathVariable Long propertyId) {
        PropertyDTO propertyDTOResponse = propertyService.updatePropertyDescription(propertyDTORequest, propertyId);
        return new ResponseEntity<>(propertyDTOResponse, HttpStatus.OK);
    }

    @PatchMapping("update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTORequest,
                                                           @PathVariable Long propertyId) {
        PropertyDTO propertyDTOResponse = propertyService.updatePropertyPrice(propertyDTORequest, propertyId);
        return new ResponseEntity<>(propertyDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId) {
        PropertyDTO propertyDTOResponse = propertyService.getPropertyById(propertyId);
        if(propertyDTOResponse == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
