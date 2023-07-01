package br.com.drop.controller;


import br.com.drop.model.entities.PersonalData;
import br.com.drop.model.entities.User;
import br.com.drop.model.exeption.BusinessRule;
import br.com.drop.repository.PersonalDateRepository;
import br.com.drop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/personal/data")
public class PersonalDataController {

    PersonalDateRepository personalDataRepository;
    UserRepository userRepository;

    public PersonalDataController(PersonalDateRepository personalDataRepository, UserRepository userRepository) {
        this.personalDataRepository = personalDataRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonalData save(@RequestBody PersonalData data){
        return personalDataRepository.save(data);
    }



    @GetMapping("/user/{user_id}")
    public PersonalData data(@PathVariable("user_id") Integer user_id){
        User usuario = userRepository.findById(user_id).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"user não encontrado" ));
        return personalDataRepository.findById(usuario.getPersonalData_data().getId()).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"dados não encontrado" ));

    }



}
