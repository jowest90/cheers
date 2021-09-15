package org.launchcode.drinkapp.controllers;

import org.launchcode.drinkapp.models.Drink;
import org.launchcode.drinkapp.models.User;
import org.launchcode.drinkapp.models.data.DrinkRepository;
import org.launchcode.drinkapp.models.data.UserRepository;
import org.launchcode.drinkapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DrinkRepository drinkRepo;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/settings")
    public String settings(){return "settings";}

    @GetMapping("/addDrink")
    public String addDrink(Model model){
        model.addAttribute("drink", new Drink());
        return "addDrink";
    }

    @PostMapping("/process_drink")
    public String processDrink(Drink drink) {
        drinkRepo.save(drink);
        return userPage();
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        userRepo.save(user);
        return index();
    }
/*
    @GetMapping("/settings/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") int id, Model model) {

        // get employee from the service
//        User employee = UserService.getEmployeeById(id);
        Optional<User> optional = UserRepository.findById(id);
        User employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

 */

    @PutMapping("/update")
    public User update(@RequestBody User user) {

        return userRepo.save(user);
    }

    @GetMapping("/users")
    public String userPage() {
//        List<User> listUsers = (List<User>) userRepo.findAll();
//        model.addAttribute("listUsers", listUsers);

        return "UserPage";
    }
}
