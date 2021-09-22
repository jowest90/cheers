package org.launchcode.drinkapp.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;

    @Size(min = 1,max = 5, message = "rating must be between 1-5")
    private int rating;

    public Rating() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Rating(User user, Drink drink) {
        super();
        this.user = user;
        this.drink = drink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
