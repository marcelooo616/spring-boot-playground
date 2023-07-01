package br.com.drop.controller;

import br.com.drop.model.dto.UserDTO;
import br.com.drop.model.entities.User;
import br.com.drop.model.exeption.BusinessRule;
import br.com.drop.repository.UserRepository;
import br.com.drop.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserRepository userRepository;
    UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/insertDTO")
    public User save(@RequestBody UserDTO userDTO){
        User user = userService.save(userDTO);
        return user;
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
                .orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND, "User not found"));

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
    }).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND, "Update failed, user not found or does not exist "));

    }


    @DeleteMapping("/{user_id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer user_id){
        userRepository.findById(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                }).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND, "Deletion failed, user not found or does not exist "));
    }




}
