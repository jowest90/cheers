package org.launchcode.drinkapp.controllers;

import org.launchcode.drinkapp.models.Drink;
import org.launchcode.drinkapp.models.data.DrinkRepository;
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

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "Drink");
        List drinks = (List<Drink>) drinkRepository.findAll();
        model.addAttribute("drinks", drinks);
        return "drinks/index";
    }

    @GetMapping("add")
    public String displayAddDrinkForm(Model model) {
        model.addAttribute(new Drink());
        return "drinks/add";
    }

    @PostMapping("add")
    public String processAddDrinkForm(@ModelAttribute @Valid Drink newDrink,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Drink");
            return "drinks/add";
        }

        drinkRepository.save(newDrink);

        return "redirect:";
    }

    @GetMapping("view/{drinkId}")
    public String displayViewDrink(Model model, @PathVariable int drinkId) {

        Optional<Drink> optDrink = drinkRepository.findById(drinkId);
        if (optDrink.isPresent()) {
            Drink drink = (Drink) optDrink.get();
            model.addAttribute("drink", drink);
            return "drinks/view";
        } else {
            return "redirect:../";
        }
    }
}