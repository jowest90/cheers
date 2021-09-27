package org.launchcode.drinkapp.controllers;


import org.launchcode.drinkapp.models.DrinkData;
import org.launchcode.drinkapp.models.data.DrinkRepository;
import org.launchcode.drinkapp.models.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import static org.launchcode.drinkapp.controllers.ListController.columnChoices;


@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private DrinkRepository drinkRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Drink> drinks;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            drinks = drinkRepository.findAll();
        } else {
            drinks = DrinkData.findByColumnAndValue(searchType, searchTerm, drinkRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Drinks with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("drinks", drinks);

        return "search";
    }
}