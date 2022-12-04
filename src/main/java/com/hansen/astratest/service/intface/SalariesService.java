package com.hansen.astratest.service.intface;

import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.dto.SalaryDto;

import java.util.List;

public interface SalariesService {
    void addSalary(RequestEmployeeDto request,Integer empNo);
    void deleteSalary(Integer empNo);
    List<SalaryDto> getListSalary(Integer empNo);
}
