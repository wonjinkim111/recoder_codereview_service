package com.yaas.recodercodereviewservice.service;

import com.yaas.recodercodereviewservice.client.UsersService;
import com.yaas.recodercodereviewservice.dto.ReviewDto;
import com.yaas.recodercodereviewservice.entity.Reviews;
import com.yaas.recodercodereviewservice.repository.IApplyReviewMapper;
import com.yaas.recodercodereviewservice.service.IApplyReviewService;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyReviewServiceImpl implements IApplyReviewService {
    private static final Logger log = LoggerFactory.getLogger(ApplyReviewServiceImpl.class);
    IApplyReviewMapper iApplyReviewMapper;
    UsersService usersService;

    @Autowired
    public ApplyReviewServiceImpl(IApplyReviewMapper iApplyReviewMapper, UsersService usersService) {
        this.iApplyReviewMapper = iApplyReviewMapper;
        this.usersService = usersService;
    }
    
    @Override
    public ReviewDto applyReview(ReviewDto reviewDto) {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Reviews entity = mm.map(reviewDto, Reviews.class);

        // INSERT
        iApplyReviewMapper.applyReview(entity);

        // INSERT 후 reviewId 확인 (useGeneratedKeys 로 들어와야 함)
        log.info("[applyReviewService] INSERT된 reviewId={}", entity.getReviewId());

        ReviewDto returnValue = mm.map(entity, ReviewDto.class);

        // (유저 서비스 연동 부분은 그대로)
        log.info("유저 >>> Before calling users microservice");
        usersService.enrollReview(returnValue.getMenteeId(), returnValue.getRoomId());
        log.info("유저 >>> After calling users microservice");

        return returnValue;
    }

    public int updateCodePath(Map<Object, Object> updateCodePathMap) {
        int updateCodePath = this.iApplyReviewMapper.updateCodePath(updateCodePathMap);
        return updateCodePath;
    }

    public ReviewDto getApplyReview(long reviewId) {
        ReviewDto getApplyReview = this.iApplyReviewMapper.getApplyReview(reviewId);
        return getApplyReview;
    }
}
