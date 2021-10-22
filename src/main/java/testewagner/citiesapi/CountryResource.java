package testewagner.citiesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testewagner.citiesapi.countries.Country;
import testewagner.citiesapi.repository.CountryRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getv1/{id}")
    public Country getV1(@PathVariable long id){
        Optional<Country> optional = repository.findById(id);
        return optional.get();
    }


    //Este método é uma evolução do getV1. Se o get não encontrar
    //nenhum resultado, ele retorna uma resposta 404 (not found)
    @GetMapping("/getv2/{id}")
    public ResponseEntity getV2(@PathVariable long id){
        Optional<Country> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
