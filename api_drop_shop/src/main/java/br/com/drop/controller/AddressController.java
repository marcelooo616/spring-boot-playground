package br.com.drop.controller;

import br.com.drop.model.dto.AddressDTO;
import br.com.drop.model.entities.Address;
import br.com.drop.model.entities.User;
import br.com.drop.model.exeption.BusinessRule;
import br.com.drop.repository.AddresRepository;
import br.com.drop.repository.UserRepository;
import br.com.drop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/address")
public class AddressController {

    AddresRepository addresRepository;
    UserRepository userRepository;
    UserService userService;

    public AddressController(AddresRepository addresRepository, UserRepository userRepository, UserService userService) {
        this.addresRepository = addresRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Address save(@RequestBody Address address){
        return addresRepository.save(address);
    }


    @GetMapping("/all")
    public List<AddressDTO> showAll(){
        List<Address> listAddress = addresRepository.findAll();

        if(CollectionUtils.isEmpty(listAddress)){
            return Collections.emptyList();
        }

        return listAddress.stream().map(
                address -> AddressDTO
                    .builder()
                        .addres_id(address.getId())
                        .street(address.getStreet())
                        .residential_number(address.getResidential_number())
                        .complement(address.getComplement())
                        .district(address.getDistrict())
                        .city(address.getCity())
                        .state(address.getState())
                        .cep(address.getCep())
                        .nation(address.getNation())
                        .user_id(address.getUser().getId())
                .build()
        ).collect(Collectors.toList());
    }


    @GetMapping("/find/{user_id}")
    public AddressDTO getById(@PathVariable Integer user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found" ));
        return userService.getFullAddress(user.getPersonalData_data().getAddress().getId())
                .map( a -> convertToDTO(a))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found" ));
    }

    private AddressDTO convertToDTO(Address address){
        return AddressDTO
                .builder()
                .addres_id(address.getId())
                .street(address.getStreet())
                .residential_number(address.getResidential_number())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .city(address.getCity())
                .state(address.getState())
                .cep(address.getCep())
                .nation(address.getNation())
                .user_id(address.getUser().getId())
                .build();
    }



    @PutMapping("/{user_id}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Address address (@PathVariable Integer user_id, @RequestBody Address address){

        User user = userRepository.findById(user_id).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"user not found" ));

        return addresRepository.findById(user.getPersonalData_data().getAddress().getId())
                .map(update_address -> {
                    address.setId(update_address.getId());
                    addresRepository.save(address);
                    return update_address;
                }).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND, "Update failed, user not found or does not exist "));
    }
}
