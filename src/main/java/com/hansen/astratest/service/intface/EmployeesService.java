package com.hansen.astratest.service.intface;

import com.hansen.astratest.dto.ApiResponse;
import com.hansen.astratest.dto.EmployeeDto;
import com.hansen.astratest.dto.RequestEmployeeDto;
import org.springframework.data.domain.PageRequest;


public interface EmployeesService {
    ApiResponse addEmployee(RequestEmployeeDto request);
    ApiResponse updateEmployee(RequestEmployeeDto request, Integer empNo);
    ApiResponse deleteEmployee(Integer empNo);
    ApiResponse getEmployee(Integer empNo);
    ApiResponse listEmployee(EmployeeDto request, PageRequest page);
    EmployeeDto setRequestList(String search, String birthDateFrom,String birthDateTo,String hireDateFrom,String hireDateTo);
}
