package com.hansen.astratest.service.impl;

import com.hansen.astratest.datasource.entity.DeptEmp;
import com.hansen.astratest.datasource.entity.DeptRelationIds;
import com.hansen.astratest.datasource.repository.DeptEmpRepository;
import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.service.intface.DepartementsService;
import com.hansen.astratest.service.intface.DeptEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class DeptEmpServiceImpl implements DeptEmpService {

    @Autowired
    DeptEmpRepository deptEmpRepository;

    @Autowired
    DepartementsService departementsService;

    @Override
    public void addDeptEmp(RequestEmployeeDto request,Integer empNo) {
        var deptEmpls = new ArrayList<DeptEmp>();
        request.getEmployeeDepartement().forEach(employeeRelationDto -> {
            departementsService.checkDepartement(employeeRelationDto.getDeptNo());
            var ids = new DeptRelationIds(employeeRelationDto.getDeptNo(),empNo);
            var deptEmp = new DeptEmp(ids,employeeRelationDto.getFromDate(),employeeRelationDto.getToDate());
            deptEmpls.add(deptEmp);
        });
        deptEmpRepository.saveAll(deptEmpls);
    }

    @Override
    public void deleteDeptEmp(Integer empNo) {
        deptEmpRepository.deleteAllByEmpNo(empNo);
    }

    @Override
    public List<EmployeeRelationDetailDto> getListDetailDeptEmp(Integer empNo) {
        return deptEmpRepository.getListDeptEmpByEmpNo(empNo);
    }
}
