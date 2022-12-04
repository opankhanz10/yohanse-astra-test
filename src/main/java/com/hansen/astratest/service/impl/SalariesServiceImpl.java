package com.hansen.astratest.service.impl;

import com.hansen.astratest.datasource.entity.Salaries;
import com.hansen.astratest.datasource.entity.SalariesIds;
import com.hansen.astratest.datasource.repository.SalariesRepository;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.dto.SalaryDto;
import com.hansen.astratest.service.intface.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class SalariesServiceImpl implements SalariesService {
    @Autowired
    SalariesRepository salariesRepository;
    @Override
    public void addSalary(RequestEmployeeDto request, Integer empNo) {
        var salaries = new ArrayList<Salaries>();
        request.getSalary().forEach(salaryDto -> {
            var ids = new SalariesIds(empNo,salaryDto.getFromDate());
            var salary = new Salaries(ids,salaryDto.getSalary(),salaryDto.getToDate());
            salaries.add(salary);
        });
        salariesRepository.saveAll(salaries);
    }

    @Override
    public void deleteSalary(Integer empNo) {
        salariesRepository.deleteAllByEmpNo(empNo);
    }

    @Override
    public List<SalaryDto> getListSalary(Integer empNo) {
        return salariesRepository.getListSalaryByEmpNo(empNo);
    }
}
