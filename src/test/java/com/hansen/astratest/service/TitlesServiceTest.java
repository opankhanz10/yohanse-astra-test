package com.hansen.astratest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansen.astratest.datasource.repository.TitlesRepository;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.dto.TitleDto;
import com.hansen.astratest.service.intface.TitlesService;
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
class TitlesServiceTest {
    @MockBean
    TitlesRepository titlesRepository;

    @Autowired
    TitlesService titlesService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void addTitle() throws JsonProcessingException {
        var request = mapper.readValue("{\"birth_date\":\"1980-12-03\",\"first_name\":\"Danang\",\"last_name\":\"Khosim\",\"gender\":\"F\",\"hire_date\":\"2022-09-03\",\"salary\":[{\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\",\"salary\":6850000}],\"employee_departement\":[],\"manager_departement\":[{\"dept_no\":\"IT01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}],\"title\":[{\"title\":\"FN01\",\"from_date\":\"2022-10-03\",\"to_date\":\"2022-10-03\"}]}", RequestEmployeeDto.class);
        when(titlesRepository.saveAll(any()))
                .thenReturn(new ArrayList<>());

        titlesService.addTitle(request,123);
    }

    @Test
    void deleteTitle(){
        doNothing().when(titlesRepository).deleteAllByEmpNo(any());
        titlesService.deleteTitle(123);
    }

    @Test
    void getListTitle() throws JsonProcessingException {
        var title = mapper.readValue("[{\"title\":\"S.E\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"},{\"title\":\"Sarjana Ekonomi\",\"from_date\":\"2022-11-01\",\"to_date\":\"2023-11-01\"}]", new TypeReference<List<TitleDto>>() {});
        when(titlesRepository.getListTitleByEmpNo(any()))
                .thenReturn(title);
        var resp = titlesService.getListTitle(123);
        assertTrue(resp.size() > 0);
    }
}
