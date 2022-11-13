package com.newjeans.quickboard.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DepartmentSaveReqDto {
    private Long departmentId;

    @Builder
    public DepartmentSaveReqDto(Long departmentId) {
        this.departmentId = departmentId;
    }
}
