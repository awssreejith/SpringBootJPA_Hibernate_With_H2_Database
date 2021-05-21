package com.JPAH2Combination.repository;

import com.JPAH2Combination.model.DBModel.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CountryRepository extends JpaRepository<Country, String> {

    //Custom queries below
    //0) query a specific country[Using Native query]
    @Query("SELECT a FROM Country a WHERE COUNTRYNAME = ?1")
    Country findCountryByCountryName(String countryName);

    //For any operation that includes a table update, we have to provide @Transactional and @Modifying
    @Transactional
    @Modifying
    @Query("DELETE FROM Country a WHERE COUNTRYNAME = ?1")
    void removeCountryByCountryName(String countryName);

    }
