package br.com.drop.controller;


import br.com.drop.model.entities.Contact;
import br.com.drop.model.entities.User;
import br.com.drop.model.exeption.BusinessRule;
import br.com.drop.repository.ContactRepository;
import br.com.drop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user/contact")
public class ContactController {

    ContactRepository contactRepository;
    UserRepository userRepository;

    public ContactController(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    public Contact insert(@RequestBody Contact contact){
        return contactRepository.save(contact);

    }

    @GetMapping("/all")
    public List<Contact> showAll(){
        return contactRepository.findAll();
    }

    @GetMapping("/{user_id}")
    public List<Contact> searchForId(@PathVariable("user_id") Integer user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"user não encontrado" ));
        return contactRepository.findByUserId(user_id);
    }

    @DeleteMapping("/{contact_id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Integer contact_id){
        contactRepository.findById(contact_id)
                .map(contact -> {
                    contactRepository.delete(contact);
                    return contact;
                }).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND, "Deletion failed, user not found or does not exist "));

    }


    @PutMapping("/{contact_id}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  Contact update(@PathVariable Integer contact_id, @RequestBody Contact contact){
        return contactRepository.findById(contact_id)
                .map(update_contact -> {
                    contact.setId(update_contact.getId());
                    contactRepository.save(contact);
                    return update_contact;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Update failed, user not found or does not exist "));

    }


}
