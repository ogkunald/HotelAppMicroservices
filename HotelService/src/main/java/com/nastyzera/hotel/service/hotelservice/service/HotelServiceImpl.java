package com.nastyzera.hotel.service.hotelservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyzera.hotel.service.hotelservice.entity.Hotel;
import com.nastyzera.hotel.service.hotelservice.exception.ResourceNotFoundException;
import com.nastyzera.hotel.service.hotelservice.repository.HotelRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString().substring(0, 5);
        log.info("Creating hotel with id "+hotelId);
        if (hotelRepository.findById(hotelId).isPresent())
            throw new ResourceNotFoundException("Hotel with Hotel id " + hotelId + " exists");
        hotel.setId(hotelId);
        log.info("hotel with id "+hotelId+" Created");
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        log.info("Fetching All hotels ");
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        log.info("Fetching Hotel with Hotel id "+id);
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " NOT FOUND"));
    }

}
