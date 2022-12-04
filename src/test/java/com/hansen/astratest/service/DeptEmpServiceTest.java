package com.hansen.astratest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.datasource.repository.DeptEmpRepository;
import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.service.intface.DepartementsService;
import com.hansen.astratest.service.intface.DeptEmpService;
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
class DeptEmpServiceTest {
    @MockBean
    DeptEmpRepository deptEmpRepository;

    @MockBean
    DepartementsService departementsService;

    @Autowired
    DeptEmpService deptEmpService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void addDeptEmp() throws JsonProcessingException {
        var request = mapper.readValue("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}", RequestEmployeeDto.class);
        doNothing().when(departementsService).checkDepartement(any());
        when(deptEmpRepository.saveAll(any()))
                .thenReturn(new ArrayList<>());

        deptEmpService.addDeptEmp(request,123);
    }

    @Test
    void deleteDeptEmp(){
        doNothing().when(deptEmpRepository).deleteAllByEmpNo(any());
        deptEmpService.deleteDeptEmp(123);
    }

    @Test
    void getListDetailDeptEmp() throws JsonProcessingException {
        var details = mapper.readValue("[{\"dept_no\":\"MK01\",\"from_date\":\"2021-11-01\",\"to_date\":\"2023-12-01\",\"dept_name\":\"Marketing & Strategic\"}]", new TypeReference<List<EmployeeRelationDetailDto>>() {});
        when(deptEmpRepository.getListDeptEmpByEmpNo(any()))
                .thenReturn(details);
        var resp = deptEmpService.getListDetailDeptEmp(123);
        assertTrue(resp.size() > 0);
    }
}
