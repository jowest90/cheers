package org.launchcode.drinkapp.models.data;

import org.launchcode.drinkapp.models.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RatingRepository extends CrudRepository<Rating, Integer> {
}