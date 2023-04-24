package com.nastyzera.user.service.UserService.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyzera.user.service.UserService.entity.Hotel;
import com.nastyzera.user.service.UserService.entity.Rating;
import com.nastyzera.user.service.UserService.entity.User;
import com.nastyzera.user.service.UserService.exception.ResourceNotFoundException;
import com.nastyzera.user.service.UserService.external.service.HotelService;
import com.nastyzera.user.service.UserService.external.service.RatingService;
import com.nastyzera.user.service.UserService.repository.UserRepository;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        log.info("Saving user"+'\n'+"checking if user with "+user.getId_phoneNo()+" already exists or not ");
        
        Optional<User> userPresent = userRepository.findById(user.getId_phoneNo());
        if (userPresent.isPresent()) {
            log.error("User with user id "+user.getId_phoneNo()+" already exists.");
            throw new ResourceNotFoundException("User Exist with phone no. "+user.getId_phoneNo());
        }
        log.info("User with user Id"+user.getId_phoneNo()+" already exists");
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        log.info("Fetching user with "+userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        log.info("User not found for User ID: "+userId);

        List<Rating> ratings = ratingService.getRatingsByUserId(userId).getBody();

        log.info("Ratings fetched for User Id "+userId);

        List<Rating> ratingList = ratings.parallelStream().map(rating ->{
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        log.info("Hotels mapped for Ratings  for user ID "+userId );

        user.setRatings(ratingList);
        return user;
    }

}
