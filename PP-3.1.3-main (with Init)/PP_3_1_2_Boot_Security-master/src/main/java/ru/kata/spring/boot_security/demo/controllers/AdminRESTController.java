package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.NoSuchException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRESTController {

    private UserService userService;

    @Autowired
    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> showAllUsers() {
        List<User> allUsers = userService.showUsers();
        return allUsers;
    }

    @GetMapping("/{id}")
    public User getUserbyID(@PathVariable long id) {
        User user = userService.showById(id);
        if (user == null) {
            throw new NoSuchException("Пользователя с id " + id + "не существует");
        }
        return user;
    }

    @PostMapping()
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
        //затем в постмане в body пишем json с данными нового юзера
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
        //затем в постмане в body пишем json с измененными данными
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable long id) {
        User user = userService.showById(id);
        if(user==null) {
            throw new NoSuchException("Пользователя с id" + id + "не существует");
        }
        userService.delete(id);
        return "Пользователь с id" + id + "был удален";
        //затем в постмане в url пишем номер id, а body должно быть пустым
    }



}
