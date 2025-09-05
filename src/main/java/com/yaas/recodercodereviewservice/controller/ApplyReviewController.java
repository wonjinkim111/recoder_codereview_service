package com.yaas.recodercodereviewservice.controller;

import com.yaas.recodercodereviewservice.code.FileGet;
import com.yaas.recodercodereviewservice.code.FileSaveLinux;
import com.yaas.recodercodereviewservice.dto.ReviewDto;
import com.yaas.recodercodereviewservice.entity.Reviews;
import com.yaas.recodercodereviewservice.model.CreateReviewFileResponseModel;
import com.yaas.recodercodereviewservice.model.CreateReviewResponseModel;
import com.yaas.recodercodereviewservice.model.GetReviewResponseModel;
import com.yaas.recodercodereviewservice.service.IApplyReviewService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping({"/codereview"})
public class ApplyReviewController {
    private static final Logger log = LoggerFactory.getLogger(ApplyReviewController.class);
    IApplyReviewService iApplyReviewService;
    ModelMapper modelMapper = new ModelMapper();
    FileSaveLinux fileSave;
    FileGet fileGet;
    GetReviewResponseModel returnValue;
    ReviewDto getReviewDto;
    String filename = "";

    @Autowired
    public ApplyReviewController(IApplyReviewService iApplyReviewService, FileSaveLinux fileSave, FileGet fileGet) {
        this.iApplyReviewService = iApplyReviewService;
        this.fileSave = fileSave;
        this.fileGet = fileGet;
    }

    @PostMapping
    public ResponseEntity<CreateReviewResponseModel> applyReview(@RequestBody Reviews reviews) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        // DTO 변환
        ReviewDto reviewDto = this.modelMapper.map(reviews, ReviewDto.class);
        log.info("[applyReview] 받은 코드: {}", reviews.getReviewCode());

        // INSERT (AutoIncrement PK 생성됨)
        ReviewDto created = this.iApplyReviewService.applyReview(reviewDto);
        log.info("[applyReview] 새 reviewId={}", created.getReviewId());

        // 파일 저장 (실제 파일 생성, 파일명 리턴됨)
        String filePath = this.fileSave.setFileStore(reviews, created.getReviewId());
        log.info("[applyReview] 저장된 파일명(filePath)={}", filePath);

        // DB update (codePath 세팅)
        Map<String, Object> params = new HashMap<>();
        params.put("reviewId", created.getReviewId());
        params.put("reviewCodePath", filePath);

        int updated = this.iApplyReviewService.updateCodePath(params);
        log.info("[applyReview] 저장된 파일명(filePath)={}, update 결과={}", filePath, updated);

        // DTO에도 반영
        created.setReviewCodePath(filePath);

        CreateReviewResponseModel returnValue =
                this.modelMapper.map(created, CreateReviewResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PostMapping("/file")
    public ResponseEntity<?> applyReviewFile(@RequestParam("file") MultipartFile file,
                                             Reviews reviews) {
        try {
            ReviewDto created = iApplyReviewService.applyReview(
                    modelMapper.map(reviews, ReviewDto.class)
            );

            String storedFileName = reviews.getMenteeId() + "_" + file.getOriginalFilename();

            String codeDir = "/usr/src/recoder/";
            if (reviews.getReviewLanguage() == 1) codeDir += "c/";
            else if (reviews.getReviewLanguage() == 2) codeDir += "cpp/";
            else codeDir += "java/";

            File dest = new File(codeDir + storedFileName);
            file.transferTo(dest);

            Map<String, Object> params = new HashMap<>();
            params.put("reviewId", created.getReviewId());
            params.put("reviewCodePath", storedFileName);
            iApplyReviewService.updateCodePath(params);

            created.setReviewCodePath(storedFileName);

            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("파일 저장 실패: " + e.getMessage());
        }
    }

    @GetMapping({"/linux/{reviewId}"})
    @ResponseBody
    public ReviewDto getReview(@PathVariable long reviewId) {
        System.out.println("가져올 리뷰의 아이디는? " + reviewId);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.getReviewDto = this.iApplyReviewService.getApplyReview(reviewId);
        String getFile;
        if (this.getReviewDto.getReviewLanguage() == 0) {
            getFile = this.fileGet.getFile("/usr/src/recoder/java/" + this.getReviewDto.getReviewCodePath());
            this.getReviewDto.setReviewCode((String)null);
            this.getReviewDto.setReviewCode(getFile);
        } else if (this.getReviewDto.getReviewLanguage() == 1) {
            getFile = this.fileGet.getFile("/usr/src/recoder/c/" + this.getReviewDto.getReviewCodePath());
        	//getFile = this.fileGet.getFile("C:\\Users\\diffr\\recoder\\c" + this.getReviewDto.getReviewCodePath());
        	this.getReviewDto.setReviewCode((String)null);
            this.getReviewDto.setReviewCode(getFile);
        } else {
            getFile = this.fileGet.getFile("/usr/src/recoder/cpp/" + this.getReviewDto.getReviewCodePath());
            this.getReviewDto.setReviewCode((String)null);
            this.getReviewDto.setReviewCode(getFile);
        }

        this.returnValue = (GetReviewResponseModel)this.modelMapper.map(this.getReviewDto, GetReviewResponseModel.class);
        return this.getReviewDto;
    }

    @GetMapping({"/{reviewId}"})
    @ResponseBody
    public ReviewDto getReviewLinux(@PathVariable long reviewId) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.getReviewDto = this.iApplyReviewService.getApplyReview(reviewId);
        System.out.println(this.getReviewDto.getReviewCodePath());
        String getFile = "";
        if (this.getReviewDto.getReviewLanguage() == 0) {
        	getFile = this.fileGet.getFile("/usr/src/recoder/java/" + this.getReviewDto.getReviewCodePath());
            //getFile = this.fileGet.getFile("C:\\Users\\diffr\\recoder\\java" + this.getReviewDto.getReviewCodePath());
            log.info(String.format(">>> 자바 코드는?? %s", this.getReviewDto.getReviewCode()));
        } else if (this.getReviewDto.getReviewLanguage() == 1) {
            getFile = this.fileGet.getFile("/usr/src/recoder/c/" + this.getReviewDto.getReviewCodePath());
        	//getFile = this.fileGet.getFile("C:\\Users\\diffr\\recoder\\c" + this.getReviewDto.getReviewCodePath());
        	log.info(String.format(">>> c 코드는?? %s", this.getReviewDto.getReviewCode()));
        } else {
        	getFile = this.fileGet.getFile("/usr/src/recoder/cpp/" + this.getReviewDto.getReviewCodePath());
            //getFile = this.fileGet.getFile("C:\\Users\\diffr\\recoder\\cpp" + this.getReviewDto.getReviewCodePath());
            log.info(String.format(">>> cpp 코드는?? %s", this.getReviewDto.getReviewCode()));
        }

        this.getReviewDto.setReviewCode((String)null);
        this.getReviewDto.setReviewCode(getFile);
        this.returnValue = (GetReviewResponseModel)this.modelMapper.map(this.getReviewDto, GetReviewResponseModel.class);
        return this.getReviewDto;
    }
}
