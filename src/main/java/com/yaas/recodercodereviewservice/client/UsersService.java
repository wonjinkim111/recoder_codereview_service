package com.yaas.recodercodereviewservice.client;

import com.yaas.recodercodereviewservice.model.UmenteeNicknameModel;
import com.yaas.recodercodereviewservice.model.UmentorNicknameModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:10000", name = "users-service")
public interface UsersService {
    @GetMapping({"/users/mentee/nickname"})
    UmenteeNicknameModel getMenteeNickname(@RequestParam long menteeId);

    @GetMapping({"/users/mentor/nickname"})
    UmentorNicknameModel getMentorNickname(@RequestParam long mentorId);

    @PostMapping({"/users/mentee/enroll"})
    int enrollReview(@RequestParam long menteeId, @RequestParam long roomId);
}
