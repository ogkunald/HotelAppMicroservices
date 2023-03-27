package com.nastyzera.user.service.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nastyzera.user.service.UserService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>
{
}