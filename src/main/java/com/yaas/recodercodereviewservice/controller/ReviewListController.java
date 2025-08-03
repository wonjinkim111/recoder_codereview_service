package com.yaas.recodercodereviewservice.controller;

import com.yaas.recodercodereviewservice.dto.MenteeListDto;
import com.yaas.recodercodereviewservice.dto.MenteeReviewListDto;
import com.yaas.recodercodereviewservice.dto.ReviewListDto;
import com.yaas.recodercodereviewservice.service.IReviewListService;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.reflect.TypeToken;

@CrossOrigin
@RestController
@RequestMapping({"/codereview"})
public class ReviewListController {
    private static final Logger log = LoggerFactory.getLogger(ReviewListController.class);
    IReviewListService iReviewListService;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ReviewListController(IReviewListService iReviewListService) {
        this.iReviewListService = iReviewListService;
    }

    @GetMapping({"/mentees"})
    @ResponseBody
    public MenteeListDto getAllMentees(@RequestParam Map<String, Long> menteesMap) {
        MenteeListDto mentees = this.iReviewListService.getAllMentees(menteesMap);
        return mentees;
    }

    @GetMapping({"/list/{roomId}"})
    @ResponseBody
    public List<ReviewListDto> getAllReviews(@RequestParam long roomId) {
        int cnt = 0;
        List<ReviewListDto> returnValue = new ArrayList();
        List<ReviewListDto> reviews = this.iReviewListService.getAllReviews(roomId);
        if (reviews != null && !reviews.isEmpty()) {
            for(int i = 0; i < reviews.size(); ++i) {
                if (((ReviewListDto)reviews.get(i)).getReviewCodePath() == null) {
                    this.iReviewListService.deleteReviewAuto(((ReviewListDto)reviews.get(i)).getReviewId());
                    ++cnt;
                }
            }

            reviews = this.iReviewListService.getAllReviews(roomId);
            Type listType = (new TypeToken<List<ReviewListDto>>() {
            }).getType();
            returnValue = (List)(new ModelMapper()).map(reviews, listType);
            log.info(">>> Returning " + returnValue.size() + " review list");
            return returnValue;
        } else {
            return returnValue;
        }
    }

    @GetMapping({"/mentee/{menteeId}"})
    @ResponseBody
    public List<MenteeReviewListDto> getAllMenteeReviews(@RequestParam long menteeId) {
        int cnt = 0;
        List<MenteeReviewListDto> returnValue = new ArrayList();
        List<MenteeReviewListDto> reviews = this.iReviewListService.getAllMenteeReviews(menteeId);
        if (reviews != null && !reviews.isEmpty()) {
            for(int i = 0; i < reviews.size(); ++i) {
                if (((MenteeReviewListDto)reviews.get(i)).getReviewCodePath() == null) {
                    this.iReviewListService.deleteReviewAuto(((MenteeReviewListDto)reviews.get(i)).getReviewId());
                    ++cnt;
                }
            }

            reviews = this.iReviewListService.getAllMenteeReviews(menteeId);
            Type listType = (new TypeToken<List<MenteeReviewListDto>>() {
            }).getType();
            returnValue = (List)(new ModelMapper()).map(reviews, listType);
            log.info(">>> Returning " + returnValue.size() + " review list");
            return returnValue;
        } else {
            return returnValue;
        }
    }
}
