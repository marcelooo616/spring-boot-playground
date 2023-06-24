package br.com.drop.controller;


import br.com.drop.model.entities.Rating;
import br.com.drop.repository.RatingRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @PostMapping("/post")
    public Rating save(@RequestBody Rating rating){
        return ratingRepository.save(rating);
    }

    @GetMapping("/show/all")
    public List<Rating> showAll(){
        return ratingRepository.findAll();
    }
}
