package testewagner.citiesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testewagner.citiesapi.countries.Country;
import testewagner.citiesapi.repository.CountryRepository;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryResource {


    private CountryRepository repository;

    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Country> countries(){
        return repository.findAll();

    }
    @GetMapping("/page")
    //Experimente chamar GET neste endereço: http://localhost:8080/countries/page?page=0&size=10&sort=name,desc
    public Page<Country> countriesPage(Pageable page){
        return repository.findAll(page);
    }
}
