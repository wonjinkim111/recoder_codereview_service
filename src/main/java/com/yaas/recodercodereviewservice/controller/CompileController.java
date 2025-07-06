package com.yaas.recodercodereviewservice.controller;

import com.yaas.recodercodereviewservice.code.CompileProcess;
import com.yaas.recodercodereviewservice.code.CompileProcess2;
import com.yaas.recodercodereviewservice.code.CompileProcess3;
import com.yaas.recodercodereviewservice.dto.CompileDto;
import com.yaas.recodercodereviewservice.service.ICompileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/codereview1"})
public class CompileController {
    private static final Logger log = LoggerFactory.getLogger(CompileController.class);
    ICompileService iCompileService;
    CompileProcess compileProcess;
    CompileProcess2 compileProcess2;
    CompileProcess3 compileProcess3;

    @Autowired
    public CompileController(ICompileService iCompileService, CompileProcess compileProcess, CompileProcess2 compileProcess2, CompileProcess3 compileProcess3) {
        this.iCompileService = iCompileService;
        this.compileProcess = compileProcess;
        this.compileProcess2 = compileProcess2;
        this.compileProcess3 = compileProcess3;
    }

    @GetMapping({"/compile/{reviewId}"})
    @ResponseBody
    public String getResult(@PathVariable long reviewId) {
        System.out.println("컴파일 컨트롤러 탔다!! ");
        CompileDto compileDto = this.iCompileService.getCodePath(reviewId);
        CompileProcess var10000 = this.compileProcess;
        String result = CompileProcess.execute(compileDto.getReviewCodePath());
        System.out.println("컨트롤러 와서 출력하기 >>>>");
        System.out.println(result);
        return result;
    }

    @GetMapping({"/compile2"})
    @ResponseBody
    public String getComment2() {
        System.out.println("컴파일 컨트롤러 2 탔다!! ");
        CompileProcess2 var10000 = this.compileProcess2;
        String successResult = CompileProcess2.execute("test.java");
        System.out.println("컨트롤러 와서 출력하기 >>>>");
        System.out.println(successResult);
        return successResult;
    }

    @GetMapping({"/compile3/{codePath}"})
    @ResponseBody
    public String getComment2(@PathVariable String codePath) {
        System.out.println("컴파일 컨트롤러 3 탔다!! ");
        CompileProcess3 var10000 = this.compileProcess3;
        String successResult = CompileProcess3.execute(codePath);
        System.out.println("컨트롤러 와서 출력하기 >>>>");
        System.out.println(successResult);
        return successResult;
    }
}
