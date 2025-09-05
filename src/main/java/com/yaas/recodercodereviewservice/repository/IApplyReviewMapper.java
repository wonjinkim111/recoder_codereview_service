package com.yaas.recodercodereviewservice.repository;

import com.yaas.recodercodereviewservice.dto.ReviewDto;
import com.yaas.recodercodereviewservice.entity.Reviews;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IApplyReviewMapper {
    int applyReview(Reviews reviews);

    int updateCodePath(Map<String, Object> updateCodePathMap);

    ReviewDto getApplyReview(long reviewId);
}
