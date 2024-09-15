package com.trackAgile.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CountriesInfo {

    @Id
    private String id;
    private String village;
    private String mandal;
    private String district;
    private String pkg;
    private String state;
    private String country;
    private String pincode;
}