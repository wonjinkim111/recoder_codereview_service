package com.yaas.recodercodereviewservice.service;

import com.yaas.recodercodereviewservice.dto.CompileDto;

public interface ICompileService {
    CompileDto getCodePath(long reviewId);
}
