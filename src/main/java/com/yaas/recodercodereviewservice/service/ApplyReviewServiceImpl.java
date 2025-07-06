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

    public ReviewDto applyReview(ReviewDto reviewDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Reviews reviewEntity = (Reviews)modelMapper.map(reviewDto, Reviews.class);
        System.out.println("review 테이블 생성");
        this.iApplyReviewMapper.applyReview(reviewEntity);
        ReviewDto returnValue = (ReviewDto)modelMapper.map(reviewEntity, ReviewDto.class);
        log.info("유저 >>> Before calling users microservice");
        int updateMenteeRoomId = this.usersService.enrollReview(returnValue.getMenteeId(), returnValue.getRoomId());
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
