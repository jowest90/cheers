package org.launchcode.drinkapp.controllers;
import org.launchcode.drinkapp.models.DrinkData;
import org.launchcode.drinkapp.models.data.DrinkRepository;
import org.launchcode.drinkapp.models.data.RatingRepository;
import org.launchcode.drinkapp.models.data.UserRepository;
import org.launchcode.drinkapp.models.Drink;
import org.launchcode.drinkapp.models.Rating;
import org.launchcode.drinkapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("drink", "Drink");
        columnChoices.put("rating", "Rating");

    }

    @RequestMapping("")
    public String list(Model model) {
        List drinks = (List<Drink>) drinkRepository.findAll();
        model.addAttribute("drinks", drinks);

        List ratings = (List<Rating>) ratingRepository.findAll();
        model.addAttribute("ratings", ratings);

        return "list";
    }

    @RequestMapping(value = "drinks")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        List drinks;
        if (column.toLowerCase().equals("all")){
            drinks = (List<Drink>) drinkRepository.findAll();
            model.addAttribute("drinks", drinks);
            model.addAttribute("title", "All Drinks");
        } else {
            drinks = DrinkData.findByColumnAndValue(column, value, drinkRepository.findAll());
            model.addAttribute("title", "Drinks with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("drinks", drinks);

        return "list-drinks";
    }
}