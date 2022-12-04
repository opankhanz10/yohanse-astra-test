package com.hansen.astratest.controller;

import com.hansen.astratest.dto.ApiResponse;
import com.hansen.astratest.service.intface.DepartementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("departement")
public class DepartementController {
    @Autowired
    DepartementsService departementsService;
    @GetMapping("list")
    public ApiResponse getList(@RequestParam(name = "dept_name", required = false) String deptName){
        return departementsService.getListDepartement(deptName);
    }
}
