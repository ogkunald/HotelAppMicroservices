package com.nastyzera.hotel.service.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nastyzera.hotel.service.hotelservice.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String> {
    
}
