package com.nastyzera.user.service.UserService.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID_phone", length = 10)
    @Pattern(regexp = "^\\d{10}$")
    private String id_phoneNo;

    @Column(name = "NAME", length = 20)
    private String name;

    @Email
    @Column(name = "EMAIL")

    private String email;
    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();


}