package com.nastyzera.hotel.service.hotelservice.service;

import java.util.List;

import com.nastyzera.hotel.service.hotelservice.entity.Hotel;

public interface HotelService {
    Hotel create(Hotel hotel);


    List<Hotel> getAll();


    Hotel getHotelById(String id);
}
