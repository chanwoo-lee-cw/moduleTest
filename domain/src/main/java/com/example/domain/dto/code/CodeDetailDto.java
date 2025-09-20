package com.example.domain.dto.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
public class CodeDetailDto {
    String code;
    String codeName;
    String codeGroup;
}
