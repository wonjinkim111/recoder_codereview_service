package com.yaas.recodercodereviewservice.service;

import com.yaas.recodercodereviewservice.dto.ReviewDto;
import java.util.Map;

public interface IApplyReviewService {
    ReviewDto applyReview(ReviewDto reviewDto);

    int updateCodePath(Map<Object, Object> updateCodePathMap);

    ReviewDto getApplyReview(long reviewId);
}
