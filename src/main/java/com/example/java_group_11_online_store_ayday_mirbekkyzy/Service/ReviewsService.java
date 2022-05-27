package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.ReviewsDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Reviews;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception.UserNotFoundException;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ReviewsRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@AllArgsConstructor
@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final UserRepository userRepository;

    public void addReview(String email, ReviewsDTO reviewsDTO) {
        var userOp = userRepository.findUserByEmail(email);
        if (userOp.isPresent()) {
            reviewsRepository.save(Reviews.builder()
                    .user(userOp.get())
                    .review(reviewsDTO.getReview())
                    .date(LocalDateTime.now())
                    .build());
        } else {
            throw new UserNotFoundException("User not found!!!",email);
        }
    }

    public Page<ReviewsDTO> getReview(Pageable pageable) {
        var review = reviewsRepository.findAll(pageable);
        return review.map(ReviewsDTO::from);
    }
}
