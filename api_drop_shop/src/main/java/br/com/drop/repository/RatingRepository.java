package br.com.drop.repository;

import br.com.drop.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
