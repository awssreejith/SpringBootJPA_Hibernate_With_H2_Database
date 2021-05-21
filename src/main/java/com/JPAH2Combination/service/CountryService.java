package com.JPAH2Combination.service;


import com.JPAH2Combination.model.WebModel.Language;
import com.JPAH2Combination.model.WebModel.Country;
import com.JPAH2Combination.model.WebModel.ResponseModelFromWeb;
import com.JPAH2Combination.repository.CountryRepository;
import com.JPAH2Combination.repository.LanguageRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LanguageRespository languageRespository;

    @Value("${app.country.url}")
    private String appUrl;

    private List<com.JPAH2Combination.model.DBModel.Country> countries = new ArrayList<>();

    public List<com.JPAH2Combination.model.DBModel.Country> getAllCountries()
    {
        return countries;
    }

    private static Integer langCount = 0;

    @PostConstruct
    public void populateCountriesIntoDatabase()
    {
        deleteAllFromDB();
        ResponseModelFromWeb responseModel = crawlAndGetCountries();
        AtomicInteger count = new AtomicInteger();

        Set<com.JPAH2Combination.model.DBModel.Language> langList = new HashSet<>();

        responseModel.getCountryList().forEach(element ->{
            count.addAndGet(1);
            com.JPAH2Combination.model.DBModel.Country dbModel = new com.JPAH2Combination.model.DBModel.Country();
            dbModel.setCountryName(element.getName());
            dbModel.setCountryID("CNT_"+count);
           // dbModel.setPopulation(element.getPopulation());
            List<String> countryCode = Arrays.asList(element.getCallingCodes());
            StringBuilder builder = new StringBuilder();
            for(String cal:countryCode)
            {
                builder.append(cal+" ");
            }
           // dbModel.setCallingCodes(builder.toString());
            dbModel.setCapital(element.getCapital());

            List<String> timeZones = Arrays.asList(element.getTimezones());
            StringBuilder builder2 = new StringBuilder();
            String primaryLanguage = getPrimaryLanguage(Arrays.asList(element.getLanguages()));

            for(String tz:timeZones)
            {
                builder2.append(tz+" ");
            }
           // dbModel.setTimeZones(builder2.toString());
            com.JPAH2Combination.model.DBModel.Language theLang = createLanguageModel(primaryLanguage);
            dbModel.setLanguageSpoken(theLang);
            langList.add(theLang);
            //First lets save the parent table[ i.e language] because country table depends on language table
            languageRespository.save(theLang);
            countryRepository.save(dbModel);
            countries.add(dbModel);
        } );

    }

    public com.JPAH2Combination.model.DBModel.Country getCountry(String country)
    {
        com.JPAH2Combination.model.DBModel.Country cont = countryRepository.findCountryByCountryName(country);
        return(cont);
    }

    public void deleteAllFromDB()
    {
        countryRepository.deleteAll();
        countries.clear();

    }

    private ResponseModelFromWeb crawlAndGetCountries()
    {
        Country[] countries = null;
        try {
            countries = restTemplate.getForObject(appUrl, Country[].class);
        }
        catch(HttpServerErrorException| HttpClientErrorException hex)
        {
            System.out.println("Http exception:- "+hex);
        }
        catch(RestClientException rex)
        {
            System.out.println("Rest client exception:- "+rex);
        }
        ResponseModelFromWeb responseModel = new ResponseModelFromWeb();
        responseModel.setCountryList(Arrays.asList(countries));
        return responseModel;
    }

    private String getPrimaryLanguage(List<Language> langArray)
    {

        String lang = "NIL";
        for (Language language:langArray)
        {
            lang = language.getName();
            break;
        }
        return lang;
    }

    private  void populalateLanguageTables(Set<com.JPAH2Combination.model.DBModel.Language> langList)
    {
       // AtomicInteger count = new AtomicInteger();
        langList.forEach(ele -> {
            languageRespository.save(ele);
        });
    }

    private com.JPAH2Combination.model.DBModel.Language createLanguageModel(String language)
    {

        com.JPAH2Combination.model.DBModel.Language lang = new com.JPAH2Combination.model.DBModel.Language();
        langCount+=1;
        lang.setLanguageId("LANG_"+langCount);
        lang.setLanguageName(language);
        return lang;
    }

    public void deleteFromDBForACountry(String country) {
        countryRepository.removeCountryByCountryName(country);
    }
}
