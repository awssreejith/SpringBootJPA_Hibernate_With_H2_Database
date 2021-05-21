package com.JPAH2Combination.model.DBModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Country")
public class Country {

    @Id
    @Column(name="CountryID")
    private String countryID;

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Column(name="COUNTRYNAME")
    private String countryName;

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


    @Column(name="Capital")
    private String capital;

    public Language getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(Language languageSpoken) {
        this.languageSpoken = languageSpoken;
    }


    //we have to give the @JsonIgnoreProperties. Else lazy loading will throw nullpointer exception
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LANGUAGESPOKEN" , nullable = false)
    private Language languageSpoken;


    public Country() {
    }

    @Override
    public String toString() {
        return "Country [id=" + countryID + ", Name=" + countryName +   ", Language=" + languageSpoken+"]";
    }
}
