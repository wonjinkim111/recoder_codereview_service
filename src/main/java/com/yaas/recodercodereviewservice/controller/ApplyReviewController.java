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
        ReviewDto reviewDto = (ReviewDto)this.modelMapper.map(reviews, ReviewDto.class);
        System.out.println("받은 코드 " + reviews.getReviewCode());
        ReviewDto createReviewDto = this.iApplyReviewService.applyReview(reviewDto);
        String filePath = this.fileSave.setFileStore(reviews, createReviewDto.getReviewId());
        Map<Object, Object> updateCodePathMap = new HashMap();
        updateCodePathMap.put("reviewCodePath", filePath);
        updateCodePathMap.put("reviewId", createReviewDto.getReviewId());
        this.iApplyReviewService.updateCodePath(updateCodePathMap);
        createReviewDto.setReviewCodePath(filePath);
        CreateReviewResponseModel returnValue = (CreateReviewResponseModel)this.modelMapper.map(createReviewDto, CreateReviewResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PostMapping({"/file"})
    public ResponseEntity<CreateReviewFileResponseModel> applyReviewFile(@ModelAttribute Reviews reviews, @RequestParam("file") MultipartFile file) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReviewDto reviewDto = (ReviewDto)this.modelMapper.map(reviews, ReviewDto.class);

        try {
            this.filename = file.getOriginalFilename();
            System.out.println("파일은? " + file + " >> 파일이름은? " + this.filename);
            String codePath;
            if (reviews.getReviewLanguage() == 0) {
                codePath = "/usr/src/recoder/java/";
                file.transferTo(new File(codePath + reviews.getMenteeId() + "_" + this.filename));
            } else if (reviews.getReviewLanguage() == 1) {
                codePath = "/usr/src/recoder/c/";
                file.transferTo(new File(codePath + reviews.getMenteeId() + "_" + this.filename));
            } else {
                codePath = "/usr/src/recoder/cpp/";
                file.transferTo(new File(codePath + reviews.getMenteeId() + "_" + this.filename));
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        reviewDto.setReviewCodePath(this.filename);
        ReviewDto createReviewDto = this.iApplyReviewService.applyReview(reviewDto);
        CreateReviewFileResponseModel returnValue = (CreateReviewFileResponseModel)this.modelMapper.map(createReviewDto, CreateReviewFileResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping({"/linux/{reviewId}"})
    @ResponseBody
    public ReviewDto getReview(@RequestParam long reviewId) {
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
    public ReviewDto getReviewLinux(@RequestParam long reviewId) {
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
        	getFile = this.fileGet.getFile("/usr/src/recoder/java/" + this.getReviewDto.getReviewCodePath());
            //getFile = this.fileGet.getFile("C:\\Users\\diffr\\recoder\\cpp" + this.getReviewDto.getReviewCodePath());
            log.info(String.format(">>> cpp 코드는?? %s", this.getReviewDto.getReviewCode()));
        }

        this.getReviewDto.setReviewCode((String)null);
        this.getReviewDto.setReviewCode(getFile);
        this.returnValue = (GetReviewResponseModel)this.modelMapper.map(this.getReviewDto, GetReviewResponseModel.class);
        return this.getReviewDto;
    }
}
