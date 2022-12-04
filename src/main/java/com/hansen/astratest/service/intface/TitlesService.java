package com.hansen.astratest.service.intface;

import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.dto.TitleDto;

import java.util.List;

public interface TitlesService {
    void addTitle(RequestEmployeeDto request,Integer empNo);
    void deleteTitle(Integer empNo);
    List<TitleDto> getListTitle(Integer empNo);
}
