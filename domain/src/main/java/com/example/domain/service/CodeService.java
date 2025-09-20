package com.example.domain.service;

import com.example.domain.dto.code.CodeDetailDto;
import com.example.domain.entity.code.Code;
import com.example.domain.repository.code.CodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeDetailDto findByCode(String code) {
        Code entity = codeRepository.findByCode(code).orElseThrow();


        return new CodeDetailDto(
                entity.getCode(),
                entity.getCodeName(),
                entity.getCodeGroup()
        );
    }
}
