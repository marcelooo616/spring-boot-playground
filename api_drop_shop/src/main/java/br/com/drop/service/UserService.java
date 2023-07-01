package br.com.drop.service;

import br.com.drop.model.dto.UserDTO;
import br.com.drop.model.entities.Address;
import br.com.drop.model.entities.User;

import java.util.Optional;

public interface UserService {

    User save(UserDTO userDTO);
    Optional<Address> getFullAddress(Integer address_id);
    void deactivateAccount(Integer user_id);
}
