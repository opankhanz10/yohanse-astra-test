package com.hansen.astratest.service.intface;

import com.hansen.astratest.dto.ApiResponse;

public interface DepartementsService {
    ApiResponse getListDepartement(String deptName);
    void checkDepartement(String deptNo);
}
