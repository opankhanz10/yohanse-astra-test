package com.hansen.astratest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.datasource.repository.SalariesRepository;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.dto.SalaryDto;
import com.hansen.astratest.service.intface.SalariesService;
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
class SalariesServiceTest {
    @Autowired
    SalariesService salariesService;

    @MockBean
    SalariesRepository salariesRepository;

    @Autowired
    ObjectMapper mapper;

    @Test
    void addSalary() throws JsonProcessingException {
        var request = mapper.readValue("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}", RequestEmployeeDto.class);
        when(salariesRepository.saveAll(any()))
                .thenReturn(new ArrayList<>());

        salariesService.addSalary(request,123);
    }

    @Test
    void deleteSalary(){
        doNothing().when(salariesRepository).deleteAllByEmpNo(any());
        salariesService.deleteSalary(123);
    }

    @Test
    void getListSalary() throws JsonProcessingException {
        var salary = mapper.readValue("[{\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\",\"salary\":7500000}]", new TypeReference<List<SalaryDto>>() {});
        when(salariesRepository.getListSalaryByEmpNo(any()))
                .thenReturn(salary);
        var resp = salariesService.getListSalary(123);
        assertTrue(resp.size() > 0);
    }
}
