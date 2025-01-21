package com.danilo.minipicpay.entities;

import com.danilo.minipicpay.entities.enums.Gender;
import com.danilo.minipicpay.entities.enums.UserStatus;
import com.danilo.minipicpay.entities.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Column(unique = true)
    private String document;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    private String nationality;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @NotNull
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}