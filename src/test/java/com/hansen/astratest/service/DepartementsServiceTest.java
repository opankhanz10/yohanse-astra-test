package com.hansen.astratest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.datasource.entity.Departements;
import com.hansen.astratest.datasource.repository.DepartementsRepository;
import com.hansen.astratest.exception.DataNotFound;
import com.hansen.astratest.service.intface.DepartementsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartementsServiceTest {
    @MockBean
    DepartementsRepository departementsRepository;

    @Autowired
    DepartementsService departementsService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void getListDepartement() throws JsonProcessingException {
        var listDept = mapper.readValue("[{\"dept_no\":\"AC01\",\"dept_name\":\"Accounting\"},{\"dept_no\":\"BD01\",\"dept_name\":\"Bussiness Development\"},{\"dept_no\":\"FN01\",\"dept_name\":\"Finance\"},{\"dept_no\":\"IT01\",\"dept_name\":\"Information Technology\"},{\"dept_no\":\"MK01\",\"dept_name\":\"Marketing & Strategic\"}]", new TypeReference<List<Departements>>() {});
        when(departementsRepository.getListDepartement(any()))
                .thenReturn(listDept)
                .thenReturn(new ArrayList<>());
        var resp = departementsService.getListDepartement(null);
        assertTrue(resp.getStatus());

        assertThrows(DataNotFound.class,()->{departementsService.getListDepartement(null);});
    }

    @Test
    void checkDepartement() throws JsonProcessingException {
        var dept = mapper.readValue("{\"dept_no\":\"AC01\",\"dept_name\":\"Accounting\"}", Departements.class);
        when(departementsRepository.findById(any()))
                .thenReturn(Optional.of(dept))
                .thenReturn(Optional.empty());
        departementsService.checkDepartement("IT01");
        assertThrows(DataNotFound.class,() ->{departementsService.checkDepartement("AS01");} );
    }
}
