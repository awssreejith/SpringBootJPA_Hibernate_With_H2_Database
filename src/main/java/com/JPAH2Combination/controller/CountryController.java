package com.JPAH2Combination.controller;

import com.JPAH2Combination.model.DBModel.Country;
import com.JPAH2Combination.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries/get-all")
    public ResponseEntity<List<Country>> getCountries()
    {
        List<Country> resp = countryService.getAllCountries();
        return  ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PutMapping("/countries/delete-all")
    public ResponseEntity<String> deleteAllCountries()
    {
        countryService.deleteAllFromDB();
        return ResponseEntity.status(HttpStatus.OK).body("Purge Completed");
    }

    @PutMapping("/countries/delete-all/{countryName}")
    public ResponseEntity<String> deleteAllCountries(@PathVariable(value="countryName") String country)
    {
        countryService.deleteFromDBForACountry(country);
        return ResponseEntity.status(HttpStatus.OK).body("Purge Completed");
    }

    @PutMapping("/countries/populate-all")
    public ResponseEntity<List<Country>> populateAllCountries()
    {
        countryService.populateCountriesIntoDatabase();
        List<Country> resp = countryService.getAllCountries();
        return  ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/countries/get/{countryName}")
    public ResponseEntity<Country> getCountry(@PathVariable(value="countryName") String country)
    {
        ResponseEntity resp ;
        Optional<Country> cont = null;
        try {
            cont = Optional.of(countryService.getCountry(country));
            resp = ResponseEntity.status(HttpStatus.OK).body(cont.get());
        }
        catch (NullPointerException nex)
        {
            System.out.println("No country found");
            resp = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return resp;
    }

}
