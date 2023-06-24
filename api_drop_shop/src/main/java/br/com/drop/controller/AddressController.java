package br.com.drop.controller;

import br.com.drop.model.entities.Address;
import br.com.drop.model.entities.User;
import br.com.drop.repository.AddresRepository;
import br.com.drop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user/address")
public class AddressController {

    AddresRepository addresRepository;
    UserRepository userRepository;

    public AddressController(AddresRepository addresRepository, UserRepository userRepository) {
        this.addresRepository = addresRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Address save(@RequestBody Address address){
        return addresRepository.save(address);
    }

    @GetMapping("/all")
    public List<Address> showAll(){
        return addresRepository.findAll();
    }

    @GetMapping("/find/{user_id}")
    public Address searchForId(@PathVariable("user_id") Integer user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found" ));
        return addresRepository.findById(user.getPersonalData_data().getAddress().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"address not found" ));
    }

    @PutMapping("/{user_id}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Address address (@PathVariable Integer user_id, @RequestBody Address address){

        User user = userRepository.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found" ));

        return addresRepository.findById(user.getPersonalData_data().getAddress().getId())
                .map(update_address -> {
                    address.setId(update_address.getId());
                    addresRepository.save(address);
                    return update_address;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Update failed, user not found or does not exist "));
    }
}
