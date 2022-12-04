package com.hansen.astratest.service.impl;

import com.hansen.astratest.datasource.entity.Titles;
import com.hansen.astratest.datasource.entity.TitlesIds;
import com.hansen.astratest.datasource.repository.TitlesRepository;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.dto.TitleDto;
import com.hansen.astratest.service.intface.TitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class TitlesServiceImpl implements TitlesService {
    @Autowired
    TitlesRepository titlesRepository;

    @Override
    public void addTitle(RequestEmployeeDto request,Integer empNo) {
        var titles = new ArrayList<Titles>();
        request.getTitle().forEach(titleDto -> {
            var ids = new TitlesIds(empNo,titleDto.getTitle(),titleDto.getFromDate());
            var title = new Titles(ids,titleDto.getToDate());
            titles.add(title);
        });
        titlesRepository.saveAll(titles);
    }

    @Override
    public void deleteTitle(Integer empNo) {
        titlesRepository.deleteAllByEmpNo(empNo);
    }

    @Override
    public List<TitleDto> getListTitle(Integer empNo) {
        return titlesRepository.getListTitleByEmpNo(empNo);
    }
}
