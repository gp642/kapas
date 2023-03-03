package com.kapas.vendor.entity;

import com.kapas.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Table(name = "vendor")
@Entity
@Getter
@Setter
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 75)
    private String firstName;

    @Column(name = "last_name", length = 75)
    private String lastName;

    @Column(name = "address", length = 175)
    private String address;

    @Column(name = "city", length = 25)
    private String city;

    @Column(name = "state", length = 25)
    private String state;

    @Column(name = "email", length = 25)
    private String email;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "vendor_img", length = 120)
    private String vendorImg;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_type_fk", nullable = false)
    private VendorType vendorType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type_fk", nullable = false)
    private IdType idType;

    @Column(name = "id_number", length = 75)
    private String idNumber;

    @Column(name = "id_img", length = 120)
    private String idImg;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modified_by", nullable = false)
    private User modifiedBy;

    @Column(name = "creation_time", insertable = false)
    private Instant creationTime;

    @Column(name = "modification_time", insertable = false)
    private Instant modificationTime;
}