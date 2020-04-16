package jd.matveev.cityGuide.dto;

import jd.matveev.cityGuide.domain.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CityList {

    List<City> listOfCities = new ArrayList<City>();

    public List<City> getListOfCities() {
        return listOfCities;
    }

    public void setListOfCities(List<City> listOfCities) {
        this.listOfCities = listOfCities;
    }

    public CityList(List<City> listOfCities) {
        this.listOfCities = listOfCities;
    }

    public CityList() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityList cityList = (CityList) o;
        return Objects.equals(listOfCities, cityList.listOfCities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfCities);
    }
}
