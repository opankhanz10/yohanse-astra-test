package com.hansen.astratest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.datasource.repository.DeptManagerRepository;
import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.service.intface.DepartementsService;
import com.hansen.astratest.service.intface.DeptManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class DeptManagerServiceTest {
    @Autowired
    DeptManagerService deptManagerService;

    @MockBean
    DepartementsService departementsService;

    @MockBean
    DeptManagerRepository deptManagerRepository;

    @Autowired
    ObjectMapper mapper;

    @Test
    void addDeptManager() throws JsonProcessingException {
        var request = mapper.readValue("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}", RequestEmployeeDto.class);
        doNothing().when(departementsService).checkDepartement(any());
        when(deptManagerRepository.saveAll(any()))
                .thenReturn(new ArrayList<>());
        deptManagerService.addDeptManager(request,123);
    }

    @Test
    void deleteDeptManager(){
        doNothing().when(deptManagerRepository).deleteAllByEmpNo(any());
        deptManagerService.deleteDeptManager(123);
    }

    @Test
    void getListDetailDeptManager() throws JsonProcessingException {
        var details = mapper.readValue("[{\"dept_no\":\"MK01\",\"from_date\":\"2021-11-01\",\"to_date\":\"2023-12-01\",\"dept_name\":\"Marketing & Strategic\"}]", new TypeReference<List<EmployeeRelationDetailDto>>() {});
        when(deptManagerRepository.getListManagerByEmpNo(any()))
                .thenReturn(details);
        var resp = deptManagerService.getListDetailDeptManager(123);
        assertTrue(resp.size() > 0);
    }
}
