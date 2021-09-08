package org.launchcode.drinkapp.models.data;

import org.launchcode.drinkapp.models.Drink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DrinkRepository extends CrudRepository<Drink, Integer> {
//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    public User findByEmail(String email);
}