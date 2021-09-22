package org.launchcode.drinkapp.controllers;

import org.launchcode.drinkapp.models.Drink;
import org.launchcode.drinkapp.models.Rating;
import org.launchcode.drinkapp.models.User;
import org.launchcode.drinkapp.models.data.DrinkRepository;
import org.launchcode.drinkapp.models.data.RatingRepository;
import org.launchcode.drinkapp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("drinks")
public class DrinkController {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "Drink");
        List drinks = (List<Drink>) drinkRepository.findAll();
        model.addAttribute("drinks", drinks);
        return "drinks/index";
    }

//    @GetMapping("add")
//    public String displayAddDrinkForm(Model model) {
//        model.addAttribute("drink", new Drink());
//        model.addAttribute("users", userRepository.findAll());
//        model.addAttribute("rating", new Rating());
//        return "drinks/add";
//    }

    @PostMapping("add")
    public String processAddDrinkForm(@ModelAttribute @Valid Rating newRating,
                                         Errors errors, Model model, @RequestParam int user_id,  @RequestParam int drink_id) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Drink");
            return "drinks/add";
        }

        User user = userRepository.findById(user_id).orElse(new User());
        newRating.setUser(user);

        Drink employer = drinkRepository.findById(drink_id).orElse(new Drink());
        newRating.setDrink(employer);

        ratingRepository.save(newRating);

        return "redirect:";
    }

    @GetMapping("view/{drinkId}")
    public String displayViewDrink(Model model, @PathVariable int drinkId) {

        Optional<Drink> optDrink = drinkRepository.findById(drinkId);
        if (optDrink.isPresent()) {
            Drink drink = (Drink) optDrink.get();
            model.addAttribute("drink", drink);
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("rating", new Rating());
            return "drinks/view";
        } else {
            return "redirect:../";
        }
    }
}