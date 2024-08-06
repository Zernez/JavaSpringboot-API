package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String ownerName;
    @NotNull(message = "Owner email is mandatory")
    @NotEmpty(message = "Owner email cannot be empty")
    @Size(min = 1, max = 60, message = "Owner email should be between 1 to 60 characters")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String houseNo;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
