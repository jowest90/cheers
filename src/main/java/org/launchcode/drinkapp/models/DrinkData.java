package org.launchcode.drinkapp.models;

import java.util.ArrayList;

public class DrinkData {

    public static ArrayList<Drink> findByColumnAndValue(String column, String value, Iterable<Drink> allDrinks) {

        ArrayList<Drink> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Drink>) allDrinks;
        }

        if (column.equals("all")){
            results = findByValue(value, allDrinks);
            return results;
        }
        for (Drink drink : allDrinks) {

            String aValue = getFieldValue(drink, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(drink);
            }
        }

        return results;
    }

    public static String getFieldValue(Drink drink, String fieldName){
        String theValue ="";
        if (fieldName.equals("name")){
            theValue = drink.getName();
        } else if (fieldName.equals("brand")){
            theValue = drink.getBrand().toString();
        } else if (fieldName.equals("type")){
            theValue = drink.getType().toString();
        }
        return theValue;
    }

    public static ArrayList<Drink> findByValue(String value, Iterable<Drink> allDrinks) {
        String lower_val = value.toLowerCase();

        ArrayList<Drink> results = new ArrayList<>();

        for (Drink drink : allDrinks) {

            if (drink.getName().toLowerCase().contains(lower_val)) {
                results.add(drink);
            } else if (drink.getBrand().toString().toLowerCase().contains(lower_val)) {
                results.add(drink);
            } else if (drink.getType().toString().toLowerCase().contains(lower_val)) {
                results.add(drink);
            } else if (drink.toString().toLowerCase().contains(lower_val)) {
                results.add(drink);
            }
        }
        return results;
    }
}