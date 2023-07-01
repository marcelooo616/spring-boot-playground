package br.com.drop.service.impl;

import br.com.drop.model.dto.UserDTO;
import br.com.drop.model.entities.Address;
import br.com.drop.model.entities.Contact;
import br.com.drop.model.entities.PersonalData;
import br.com.drop.model.entities.User;
import br.com.drop.repository.AddresRepository;
import br.com.drop.repository.ContactRepository;
import br.com.drop.repository.PersonalDateRepository;
import br.com.drop.repository.UserRepository;
import br.com.drop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final AddresRepository addresRepository;
    private final PersonalDateRepository personalDateRepository;
    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public User save(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        Address address = addresRepository.save(saveAddress(user));
        PersonalData personalData = personalDateRepository.save(savePersonalData(user, address));
        Contact contact = contactRepository.save(saveContact(user));
        return user;
    }

    @Override
    public Optional<Address> getFullAddress(Integer address_id) {
        return addresRepository.findById(address_id);
    }

    private Address saveAddress(User user_id){
        Address address = new Address();
        address.setCep("");
        address.setCity("");
        address.setComplement("");
        address.setDistrict("");
        address.setNation("");
        address.setResidential_number("");
        address.setStreet("");
        address.setState("");
        address.setUser(user_id);
        return address;

    }

    private PersonalData savePersonalData(User user, Address address){
        PersonalData personalData = new PersonalData();
        personalData.setCompleted_name("");
        personalData.setBirthday("");
        personalData.setGender("");
        personalData.setCpf("");
        personalData.setWhatsapp("");
        personalData.setEmail(user.getEmail());
        personalData.setUser(user);
        personalData.setAddress(address);
        return personalData;

    }

    private Contact saveContact(User user){
        Contact contact = new Contact();
        contact.setUser_id(user);
        contact.setType("Email");
        contact.setValue(user.getEmail());
        contact.setPreferential(true);
        contact.setObservation("");
        return contact;
    }


}
