package com.nastyzera.rating.service.RatingService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyzera.rating.service.RatingService.entity.Rating;
import com.nastyzera.rating.service.RatingService.repository.RatingRepository;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class RatingServiceImpl implements RatingService {


    @Autowired
    private RatingRepository repository;

    @Override
    public Rating create(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());
        log.info("Ratings saved for  "+rating.getHotelId()+" for user ID"+rating.getUserId());
        return repository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        log.info("Fetching all Ratings");
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        log.info("Fetching all Ratings by user ID "+userId);
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        log.info("Fetching all Ratings by hotel ID "+hotelId);
        return repository.findByHotelId(hotelId);
    }
}
