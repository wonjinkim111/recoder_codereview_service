package com.yaas.recodercodereviewservice.service;

import com.yaas.recodercodereviewservice.dto.MenteeListDto;
import com.yaas.recodercodereviewservice.dto.MenteeReviewListDto;
import com.yaas.recodercodereviewservice.dto.ReviewListDto;
import java.util.List;
import java.util.Map;

public interface IReviewListService {
    MenteeListDto getAllMentees(Map<String, Long> mentees);

    List<ReviewListDto> getAllReviews(long roomId);

    List<MenteeReviewListDto> getAllMenteeReviews(long menteeId);

    int deleteReviewAuto(long reviewId);
}
