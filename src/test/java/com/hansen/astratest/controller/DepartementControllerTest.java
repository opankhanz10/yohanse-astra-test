package com.hansen.astratest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.dto.ApiResponse;
import com.hansen.astratest.service.intface.DepartementsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DepartementControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;
    @MockBean
    DepartementsService departementsService;

    @Test
    void getList() throws Exception {
        var result = mapper.readValue("{\"data\":[{\"dept_no\":\"AC01\",\"dept_name\":\"Accounting\"},{\"dept_no\":\"BD01\",\"dept_name\":\"Bussiness Development\"},{\"dept_no\":\"FN01\",\"dept_name\":\"Finance\"},{\"dept_no\":\"IT01\",\"dept_name\":\"Information Technology\"},{\"dept_no\":\"MK01\",\"dept_name\":\"Marketing & Strategic\"}],\"status\":true}", ApiResponse.class);
        doReturn(result).when(departementsService).getListDepartement(any());

        this.mvc.perform(get("/departement/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
