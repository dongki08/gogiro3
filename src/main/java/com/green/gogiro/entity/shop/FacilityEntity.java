package com.green.gogiro.entity.shop;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_facilities")
public class FacilityEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ifacil;

    @Column(length = 10,nullable = false)
    private String facility;

    @ToString.Exclude
    @OneToMany(mappedBy = "facilityEntity",cascade = CascadeType.PERSIST)
    List<ShopFacilitiesEntity> facilitiesEntityList = new ArrayList<>();

}
