package com.hansen.astratest.service.intface;

import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import com.hansen.astratest.dto.RequestEmployeeDto;

import java.util.List;

public interface DeptManagerService {
    void addDeptManager(RequestEmployeeDto request, Integer empNo);
    void deleteDeptManager(Integer empNo);
    List<EmployeeRelationDetailDto> getListDetailDeptManager(Integer empNo);
}
