package com.hansen.astratest.service.impl;

import com.hansen.astratest.datasource.entity.DeptManager;
import com.hansen.astratest.datasource.entity.DeptRelationIds;
import com.hansen.astratest.datasource.repository.DeptManagerRepository;
import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.service.intface.DepartementsService;
import com.hansen.astratest.service.intface.DeptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class DeptManagerServiceImpl implements DeptManagerService {
    @Autowired
    DeptManagerRepository deptManagerRepository;

    @Autowired
    DepartementsService departementsService;

    @Override
    public void addDeptManager(RequestEmployeeDto request,Integer empNo) {
        var deptManagers = new ArrayList<DeptManager>();
        request.getManagerDepartement().forEach(managerDto -> {
            departementsService.checkDepartement(managerDto.getDeptNo());
            var ids = new DeptRelationIds(managerDto.getDeptNo(),empNo);
            var deptManager = new DeptManager(ids,managerDto.getFromDate(),managerDto.getToDate());
            deptManagers.add(deptManager);
        });
        deptManagerRepository.saveAll(deptManagers);
    }

    @Override
    public void deleteDeptManager(Integer empNo) {
        deptManagerRepository.deleteAllByEmpNo(empNo);
    }

    @Override
    public List<EmployeeRelationDetailDto> getListDetailDeptManager(Integer empNo) {
        return deptManagerRepository.getListManagerByEmpNo(empNo);
    }
}
