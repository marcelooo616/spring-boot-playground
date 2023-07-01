package br.com.drop.service;

import br.com.drop.model.dto.RatingDTO;
import br.com.drop.model.entities.Rating;

public interface ProductService {

    Rating postReview(RatingDTO dto);
}
