package br.com.drop.service.impl;

import br.com.drop.model.dto.RatingDTO;
import br.com.drop.model.entities.Product;
import br.com.drop.model.entities.Rating;
import br.com.drop.model.exeption.BusinessRule;
import br.com.drop.repository.ProductRepository;
import br.com.drop.repository.RatingRepository;
import br.com.drop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;

    @Override
    public Rating postReview(RatingDTO dto) {

        Product product = productRepository.findById(dto.getProduct_id()).orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND, "Product not fount."));

        Rating rating = new Rating();
        rating.setProduct_note(dto.getProduct_note());
        rating.setProduct(product);
        rating.setEvaluation_date(LocalDate.now());
        rating.setReview(dto.getReview());
        ratingRepository.save(rating);
        return rating;
    }
}
