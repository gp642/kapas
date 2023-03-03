package com.kapas.vendor.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String mobile;

    private String address;

    private String state;

    private String city;

    private String vendorType;

    private String idType;

    private String idNumber;
}
