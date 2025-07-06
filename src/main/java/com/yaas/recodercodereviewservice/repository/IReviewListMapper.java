package com.yaas.recodercodereviewservice.repository;

import com.yaas.recodercodereviewservice.dto.MenteeListDto;
import com.yaas.recodercodereviewservice.dto.MenteeReviewListDto;
import com.yaas.recodercodereviewservice.dto.ReviewListDto;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IReviewListMapper {
    MenteeListDto getAllMentees(Map<String, Long> mentees);

    List<ReviewListDto> getAllReviews(long roomId);

    List<MenteeReviewListDto> getAllMenteeReviews(long menteeId);

    int deleteReviewAuto(long reviewId);
}
