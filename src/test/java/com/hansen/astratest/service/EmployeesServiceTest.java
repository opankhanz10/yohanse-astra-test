package com.hansen.astratest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.datasource.entity.Employees;
import com.hansen.astratest.datasource.repository.EmployeesRepository;
import com.hansen.astratest.dto.*;
import com.hansen.astratest.exception.DataNotFound;
import com.hansen.astratest.exception.SystemUnavailableException;
import com.hansen.astratest.service.intface.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeesServiceTest {
    @MockBean
    EmployeesRepository employeesRepository;

    @MockBean
    DeptManagerService deptManagerService;

    @MockBean
    DeptEmpService deptEmpService;

    @MockBean
    SalariesService salariesService;

    @MockBean
    TitlesService titlesService;

    @Autowired
    EmployeesService employeesService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void addEmployee() throws JsonProcessingException {
        var request = mapper.readValue("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}", RequestEmployeeDto.class);
        when(employeesRepository.save(any())).thenReturn(new Employees());
        doNothing().when(titlesService).addTitle(any(),any());
        doNothing().when(salariesService).addSalary(any(),any());
        doNothing().when(deptEmpService).addDeptEmp(any(),any());
        doNothing().when(deptManagerService).addDeptManager(any(),any());

        var resp = employeesService.addEmployee(request);
        assertTrue(resp.getStatus());

        doThrow(DataNotFound.class).when(deptEmpService).addDeptEmp(any(),any());
        assertThrows(DataNotFound.class,()->{employeesService.addEmployee(request);});

        doThrow(NullPointerException.class).when(deptEmpService).addDeptEmp(any(),any());
        assertThrows(SystemUnavailableException.class,()->{employeesService.addEmployee(request);});


    }

    @Test
    void updateEmployee() throws JsonProcessingException {
        var request = mapper.readValue("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}", RequestEmployeeDto.class);
        when(employeesRepository.save(any())).thenReturn(new Employees());
        doNothing().when(titlesService).addTitle(any(),any());
        doNothing().when(salariesService).addSalary(any(),any());
        doNothing().when(deptEmpService).addDeptEmp(any(),any());
        doNothing().when(deptManagerService).addDeptManager(any(),any());
        var employees = mapper.readValue("{\"emp_no\":10,\"birth_date\":\"1992-01-25\",\"first_name\":\"Tina\",\"last_name\":\"\",\"gender\":\"F\",\"hire_date\":\"2022-12-01\"}", Employees.class);
        when(employeesRepository.findById(any()))
                .thenReturn(Optional.of(employees))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(employees));
        when(employeesRepository.findByEmpNo(any())).thenReturn(employees);
        var resp = employeesService.updateEmployee(request,123);
        assertTrue(resp.getStatus());

        assertThrows(DataNotFound.class,()->{employeesService.updateEmployee(request,222);});

        doThrow(NullPointerException.class).when(deptEmpService).addDeptEmp(any(),any());
        assertThrows(SystemUnavailableException.class,()->{employeesService.addEmployee(request);});

    }

    @Test
    void deleteEmployee() throws JsonProcessingException {
        var employees = mapper.readValue("{\"emp_no\":10,\"birth_date\":\"1992-01-25\",\"first_name\":\"Tina\",\"last_name\":\"\",\"gender\":\"F\",\"hire_date\":\"2022-12-01\"}", Employees.class);
        when(employeesRepository.findById(any()))
                .thenReturn(Optional.of(employees))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(employees));
        doNothing().when(employeesRepository).deleteById(any());
        doNothing().when(deptEmpService).deleteDeptEmp(any());
        doNothing().when(deptManagerService).deleteDeptManager(any());
        doNothing().when(salariesService).deleteSalary(any());
        doNothing().when(titlesService).deleteTitle(any());

        var resp = employeesService.deleteEmployee(123);
        assertTrue(resp.getStatus());

        assertThrows(DataNotFound.class,()->{employeesService.deleteEmployee(222);});

        doThrow(NullPointerException.class).when(deptEmpService).deleteDeptEmp(any());
        assertThrows(SystemUnavailableException.class,()->{employeesService.deleteEmployee(233);});
    }

    @Test
    void getEmployee() throws JsonProcessingException {
        var empDetail = mapper.readValue("{\"data\":{\"emp_no\":10,\"birth_date\":\"1992-01-25\",\"first_name\":\"Tina\",\"last_name\":\"\",\"gender\":\"F\",\"hire_date\":\"2022-12-01\",\"salary\":[{\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\",\"salary\":7500000}],\"title\":[{\"title\":\"S.E\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"},{\"title\":\"Sarjana Ekonomi\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"MK01\",\"from_date\":\"2021-11-01\",\"to_date\":\"2023-12-01\",\"dept_name\":\"Marketing & Strategic\"}]},\"status\":true}", EmployeeDetailDto.class);
        when(employeesRepository.getDetailEmployee(any()))
                .thenReturn(empDetail)
                .thenReturn(null);
        var employeeRelationDetailDtos = mapper.readValue("[{\"dept_no\":\"MK01\",\"from_date\":\"2021-11-01\",\"to_date\":\"2023-12-01\",\"dept_name\":\"Marketing & Strategic\"}]", new TypeReference<List<EmployeeRelationDetailDto>>() {});
        when(deptEmpService.getListDetailDeptEmp(any()))
                .thenReturn(employeeRelationDetailDtos);
        when(deptManagerService.getListDetailDeptManager(any()))
                .thenReturn(employeeRelationDetailDtos);

        var salary = mapper.readValue("[{\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\",\"salary\":7500000}]", new TypeReference<List<SalaryDto>>() {});
        when(salariesService.getListSalary(any()))
                .thenReturn(salary);

        var title = mapper.readValue("[{\"title\":\"S.E\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"},{\"title\":\"Sarjana Ekonomi\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"}]", new TypeReference<List<TitleDto>>() {});
        when(titlesService.getListTitle(any()))
                .thenReturn(title);

        var resp = employeesService.getEmployee(123);
        assertTrue(resp.getStatus());

        assertThrows(DataNotFound.class,()->{employeesService.getEmployee(222);});
    }

    @Test
    void listEmployee() throws JsonProcessingException {
        var employess = mapper.readValue("[{\"empNo\":15,\"birthDate\":\"1980-12-03\",\"firstName\":\"Andika\",\"lastName\":\"Primawan\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":14,\"birthDate\":\"1980-12-03\",\"firstName\":\"Andri\",\"lastName\":\"Hendrawan\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":17,\"birthDate\":\"1980-12-03\",\"firstName\":\"Dhika\",\"lastName\":\"Akbar\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"}]", new TypeReference<List<EmployeeDto>>() {});
        when(employeesRepository.getListEmployee(any(),any()))
                .thenReturn(new PageImpl<>(employess));
        var page = PageRequest.of(0,10);
        var resp = employeesService.listEmployee(new EmployeeDto(),page);
        assertTrue(resp.getStatus());
    }

    @Test
    void setRequestList(){
        var resp = employeesService.setRequestList("lia","2022-01-20","2022-01-20","2022-01-20","2022-01-20");
        assertTrue(resp.getSearch().equalsIgnoreCase("lia"));

        resp = employeesService.setRequestList("","","","","");
        assertNull(resp.getSearch());

        resp = employeesService.setRequestList(null,null,null,null,null);
        assertNull(resp.getSearch());

        assertThrows(SystemUnavailableException.class,()->{
            employeesService.setRequestList("lia","22-01-20","2022-01-20","2022-01-20","2022-01-20");
        });
    }
}
