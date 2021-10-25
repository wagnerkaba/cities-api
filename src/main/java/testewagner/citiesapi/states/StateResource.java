package testewagner.citiesapi.states;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateResource {

    private final StateRepository stateRepository;

    public StateResource(final StateRepository repository){
        this.stateRepository = repository;
    }

    @GetMapping
    public List<State> states(){
        return stateRepository.findAll();
    }
}
