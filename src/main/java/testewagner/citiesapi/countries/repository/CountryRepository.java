package testewagner.citiesapi.countries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testewagner.citiesapi.countries.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
