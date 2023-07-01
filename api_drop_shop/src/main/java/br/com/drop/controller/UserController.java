package br.com.drop.controller;

import br.com.drop.model.dto.FinalizedAccount;
import br.com.drop.model.dto.UserDTO;
import br.com.drop.model.entities.User;
import br.com.drop.model.exeption.BusinessRule;
import br.com.drop.repository.UserRepository;
import br.com.drop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody UserDTO userDTO){
        return  userService.save(userDTO);

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


    @PatchMapping("/{user_id}/deactivate/account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivateUser(@PathVariable Integer user_id){
        userService.deactivateAccount(user_id);

    }







}
