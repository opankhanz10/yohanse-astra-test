package com.hansen.astratest.service.intface;

import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import com.hansen.astratest.dto.RequestEmployeeDto;

import java.util.List;

public interface DeptEmpService {
    void addDeptEmp(RequestEmployeeDto request,Integer empNo);
    void deleteDeptEmp(Integer empNo);
    List<EmployeeRelationDetailDto> getListDetailDeptEmp(Integer empNo);
}
