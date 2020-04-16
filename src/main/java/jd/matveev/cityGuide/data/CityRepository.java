package jd.matveev.cityGuide.data;

import jd.matveev.cityGuide.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    public List<City> findByCityName(String cityName);
}
