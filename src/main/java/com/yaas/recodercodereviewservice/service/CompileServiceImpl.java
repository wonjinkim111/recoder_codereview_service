package com.yaas.recodercodereviewservice.service;

import com.yaas.recodercodereviewservice.dto.CompileDto;
import com.yaas.recodercodereviewservice.repository.ICompileMapper;
import com.yaas.recodercodereviewservice.service.ICompileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompileServiceImpl implements ICompileService {
    ICompileMapper iCompileMapper;

    @Autowired
    public CompileServiceImpl(ICompileMapper iCompileMapper) {
        this.iCompileMapper = iCompileMapper;
    }

    public CompileDto getCodePath(long reviewId) {
        CompileDto compileDto = this.iCompileMapper.getCodePath(reviewId);
        return compileDto;
    }
}
