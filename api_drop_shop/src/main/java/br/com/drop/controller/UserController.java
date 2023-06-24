package br.com.drop.controller;

import br.com.drop.model.entities.User;
import br.com.drop.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }


    @GetMapping("/all")
    public List<User> showAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{user_id}")
    public User searchForId(@PathVariable("user_id") Integer user_id){
        return userRepository.findById(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    }

    @GetMapping("/filtered/search")
    public List<User> filteredSearch(User filter){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        return userRepository.findAll(example);

    }

    @PutMapping("/{user_id}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User update(@PathVariable Integer user_id, @RequestBody User user){
        return userRepository.findById(user_id)
                .map(update_user -> {
                    user.setId(update_user.getId());
                    userRepository.save(user);
                    return update_user;
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Update failed, user not found or does not exist "));

    }


    @DeleteMapping("/{user_id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer user_id){
        userRepository.findById(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deletion failed, user not found or does not exist "));
    }




}
