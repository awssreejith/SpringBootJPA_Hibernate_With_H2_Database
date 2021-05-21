package com.JPAH2Combination.model.WebModel;

import java.util.List;

public class ResponseModelFromWeb {

    public List<com.JPAH2Combination.model.WebModel.Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<com.JPAH2Combination.model.WebModel.Country> countryList) {
        this.countryList = countryList;
    }

    List<Country> countryList;
}
