package com.yaas.recodercodereviewservice.service;

import com.yaas.recodercodereviewservice.client.UsersService;
import com.yaas.recodercodereviewservice.dto.MenteeListDto;
import com.yaas.recodercodereviewservice.dto.MenteeReviewListDto;
import com.yaas.recodercodereviewservice.dto.ReviewListDto;
import com.yaas.recodercodereviewservice.model.UmenteeNicknameModel;
import com.yaas.recodercodereviewservice.model.UmentorNicknameModel;
import com.yaas.recodercodereviewservice.repository.IReviewListMapper;
import com.yaas.recodercodereviewservice.service.IReviewListService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ReviewListServiceImpl implements IReviewListService {
    private static final Logger log = LoggerFactory.getLogger(ReviewListServiceImpl.class);
    IReviewListMapper iReviewListMapper;
    UsersService usersService;

    @Autowired
    public ReviewListServiceImpl(IReviewListMapper iReviewListMapper, UsersService usersService) {
        this.iReviewListMapper = iReviewListMapper;
        this.usersService = usersService;
    }

    public List<ReviewListDto> getAllReviews(@PathVariable long roomId) {
        List<ReviewListDto> reviewListDtos = this.iReviewListMapper.getAllReviews(roomId);
        if (reviewListDtos == null) {
            log.info(String.format("not exists %s", roomId));
            return new ArrayList();
        } else {
            log.info("유저 >>> Before calling users microservice");

            for(int i = 0; i < reviewListDtos.size(); ++i) {
                UmenteeNicknameModel umenteeNicknameModel = this.usersService.getMenteeNickname(((ReviewListDto)reviewListDtos.get(i)).getMenteeId());
                ((ReviewListDto)reviewListDtos.get(i)).setMenteeNickname(umenteeNicknameModel.getMenteeNickname());
            }

            log.info("유저 >>> After calling users microservice");
            return reviewListDtos;
        }
    }

    public MenteeListDto getAllMentees(Map<String, Long> mentees) {
        MenteeListDto menteeListDtos = this.iReviewListMapper.getAllMentees(mentees);
        System.out.println("멘티 리스트 서비스 탔고!!!! review count 데이터는? " + menteeListDtos.getReviewCount());
        if (menteeListDtos == null) {
            log.info(String.format("not exists %s", mentees));
        }

        return menteeListDtos;
    }

    public List<MenteeReviewListDto> getAllMenteeReviews(@PathVariable long menteeId) {
        List<MenteeReviewListDto> reviewListDtos = this.iReviewListMapper.getAllMenteeReviews(menteeId);
        System.out.println("리뷰 리스트 서비스 탔고!!!! 데이터는? " + ((MenteeReviewListDto)reviewListDtos.get(0)).getMenteeId());
        if (reviewListDtos == null) {
            log.info(String.format("not exists %s", menteeId));
            return new ArrayList();
        } else {
            log.info("유저 >>> Before calling users microservice");

            for(int i = 0; i < reviewListDtos.size(); ++i) {
                UmentorNicknameModel umentorNicknameModel = this.usersService.getMentorNickname(((MenteeReviewListDto)reviewListDtos.get(i)).getMentorId());
                ((MenteeReviewListDto)reviewListDtos.get(i)).setMentorNickname(umentorNicknameModel.getMentorNickname());
            }

            log.info("유저 >>> After calling users microservice");
            return reviewListDtos;
        }
    }

    public int deleteReviewAuto(long reviewId) {
        int deleteReviewAuto = this.iReviewListMapper.deleteReviewAuto(reviewId);
        return deleteReviewAuto;
    }
}
