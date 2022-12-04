package com.hansen.astratest.service.impl;

import com.hansen.astratest.datasource.repository.DepartementsRepository;
import com.hansen.astratest.dto.ApiResponse;
import com.hansen.astratest.exception.DataNotFound;
import com.hansen.astratest.service.intface.DepartementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class DepartementsServiceImpl implements DepartementsService {

    @Autowired
    DepartementsRepository departementsRepository;

    @Override
    public ApiResponse getListDepartement(String deptName) {
        var depts = departementsRepository.getListDepartement(deptName);
        if(depts.isEmpty()){
            throw new DataNotFound("List Data departement not found");
        }
        return new ApiResponse(depts,true);
    }

    @Override
    public void checkDepartement(String deptNo) {
        if(!departementsRepository.findById(deptNo).isPresent()){
            throw new DataNotFound("Data departement number "+deptNo+" not found");
        }
    }
}
