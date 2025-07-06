package com.yaas.recodercodereviewservice.repository;

import com.yaas.recodercodereviewservice.dto.CompileDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ICompileMapper {
    CompileDto getCodePath(long reviewId);
}
