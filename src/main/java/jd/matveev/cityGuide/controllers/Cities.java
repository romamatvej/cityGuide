package jd.matveev.cityGuide.controllers;

import jd.matveev.cityGuide.data.CityRepository;
import jd.matveev.cityGuide.dto.CityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jd.matveev.cityGuide.domain.City;

import java.util.List;

@RestController
public class Cities {

    @Autowired
    private CityRepository data;

    @GetMapping("/cities")
    public CityList allCities() {
        CityList cityList = new CityList();
        data.findAll().forEach(p -> cityList.getListOfCities().add(p));
        return cityList;
    }

    @PostMapping(value = "/cities")
    public void newCity(@RequestBody City city) {
        data.save(city);
    }

    public Cities() {
    }
}
