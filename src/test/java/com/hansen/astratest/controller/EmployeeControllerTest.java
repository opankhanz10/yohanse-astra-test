package com.hansen.astratest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.dto.ApiResponse;
import com.hansen.astratest.dto.EmployeeDto;
import com.hansen.astratest.service.intface.EmployeesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    EmployeesService employeesService;

    @Test
    void getList() throws Exception {
        var result = mapper.readValue("{\"data\":{\"data\":[{\"empNo\":15,\"birthDate\":\"1980-12-03\",\"firstName\":\"Andika\",\"lastName\":\"Primawan\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":14,\"birthDate\":\"1980-12-03\",\"firstName\":\"Andri\",\"lastName\":\"Hendrawan\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":17,\"birthDate\":\"1980-12-03\",\"firstName\":\"Dhika\",\"lastName\":\"Akbar\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":11,\"birthDate\":\"1982-02-25\",\"firstName\":\"Dina\",\"lastName\":\"Suryadina\",\"gender\":\"F\",\"hireDate\":\"2022-12-01\"},{\"empNo\":19,\"birthDate\":\"1980-12-03\",\"firstName\":\"Ferdy\",\"lastName\":\"Sambo\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":12,\"birthDate\":\"1982-02-25\",\"firstName\":\"Indari\",\"lastName\":\"Atmajaya\",\"gender\":\"F\",\"hireDate\":\"2021-01-07\"},{\"empNo\":24,\"birthDate\":\"1980-12-03\",\"firstName\":\"Iriana\",\"lastName\":\"Khosim\",\"gender\":\"F\",\"hireDate\":\"2022-09-03\"},{\"empNo\":18,\"birthDate\":\"1980-12-03\",\"firstName\":\"Julius\",\"lastName\":\"Sitanggang\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":20,\"birthDate\":\"1980-12-03\",\"firstName\":\"Lehman\",\"lastName\":\"\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"},{\"empNo\":16,\"birthDate\":\"1980-12-03\",\"firstName\":\"Petra\",\"lastName\":\"Antera\",\"gender\":\"M\",\"hireDate\":\"2022-09-03\"}],\"total_data\":14,\"total_pages\":2,\"current_page\":1,\"size_of_data\":10,\"data_of\":\"1 - 10\"},\"status\":true}", ApiResponse.class);
        doReturn(result).when(employeesService).listEmployee(any(),any());
        when(employeesService.setRequestList(any(),any(),any(),any(),any()))
                .thenReturn(new EmployeeDto());

        this.mvc.perform(get("/employee/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(Boolean.TRUE)));
    }

    @Test
    void getDetail() throws Exception {
        var result = mapper.readValue("{\"data\":{\"emp_no\":10,\"birth_date\":\"1992-01-25\",\"first_name\":\"Tina\",\"last_name\":\"\",\"gender\":\"F\",\"hire_date\":\"2022-12-01\",\"salary\":[{\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\",\"salary\":7500000}],\"title\":[{\"title\":\"S.E\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"},{\"title\":\"Sarjana Ekonomi\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"MK01\",\"from_date\":\"2021-11-01\",\"to_date\":\"2023-12-01\",\"dept_name\":\"Marketing & Strategic\"}]},\"status\":true}", ApiResponse.class);
        doReturn(result).when(employeesService).getEmployee(any());

        this.mvc.perform(get("/employee/detail/8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployee() throws Exception {
        var result = mapper.readValue("{\"data\":\"Success\",\"status\":true}", ApiResponse.class);
        doReturn(result).when(employeesService).addEmployee(any());

        this.mvc.perform(post("/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}"))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployee() throws Exception {
        var result = mapper.readValue("{\"data\":\"Success\",\"status\":true}", ApiResponse.class);        doReturn(result).when(employeesService).listEmployee(any(EmployeeDto.class),any(PageRequest.class));
        when(employeesService.updateEmployee(any(),any()))
                .thenReturn(result);
        this.mvc.perform(post("/employee/update/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployee() throws Exception {
        var result = mapper.readValue("{\"data\":\"Success\",\"status\":true}", ApiResponse.class);
        doReturn(result).when(employeesService).listEmployee(any(EmployeeDto.class),any(PageRequest.class));
        when(employeesService.deleteEmployee(any()))
                .thenReturn(result);
        this.mvc.perform(post("/employee/delete/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
