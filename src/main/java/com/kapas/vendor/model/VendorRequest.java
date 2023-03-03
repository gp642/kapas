package com.kapas.vendor.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VendorRequest {

    @NotBlank(message = "First Name is mandatory")
    @Size(min = 5, max = 75, message = "First Name must be between 5 and 75 characters")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 5, max = 75, message = "Name must be between 5 and 75 characters")
    private String lastName;

    @NotBlank(message = "Mobile is mandatory")
    @Size(min = 10, max = 15, message = "Mobile must be between 10 and 15 characters")
    private String mobile;

    @NotBlank(message = "Address is mandatory")
    @Size(min = 5, max = 175, message = "Address must be between 5 and 175 characters")
    private String address;

    @NotBlank(message = "State is mandatory")
    @Size(min = 5, max = 25, message = "State must be between 5 and 25 characters")
    private String state;

    @NotBlank(message = "City is mandatory")
    @Size(min = 5, max = 25, message = "City must be between 5 and 25 characters")
    private String city;

    @NotBlank(message = "Vendor Type is mandatory")
    @Size(min = 5, max = 25, message = "Vendor Type must be between 5 and 25 characters")
    private String vendorType;

    @NotBlank(message = "Id Proof Type is mandatory")
    @Size(min = 5, max = 25, message = "Id Proof Type must be between 5 and 25 characters")
    private String idType;

    @NotBlank(message = "Id Proof is mandatory")
    @Size(min = 5, max = 75, message = "Id Proof must be between 5 and 75 characters")
    private String idNumber;

}
