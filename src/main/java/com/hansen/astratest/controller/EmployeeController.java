package com.hansen.astratest.controller;

import com.hansen.astratest.dto.ApiResponse;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.service.intface.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeesService employeesService;

    @GetMapping("list")
    public ApiResponse getList(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "birth-date-from", required = false) String birthDateFrom,
            @RequestParam(value = "birth-date-to", required = false) String birthDateTo,
            @RequestParam(value = "hire-date-from", required = false) String hireDateFrom,
            @RequestParam(value = "hire-date-to", required = false) String hireDateTo,
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "page-size", defaultValue = "10", required = false) int size
    ){
        var pageable = PageRequest.of(page-1, size, Sort.by("firstName").ascending().and(Sort.by("lastName").ascending()));
        return employeesService.listEmployee(employeesService.setRequestList(search,birthDateFrom,birthDateTo,hireDateFrom,hireDateTo),pageable);
    }

    @GetMapping("detail/{empNo}")
    public ApiResponse getDetail(
            @PathVariable("empNo") Integer empNo
    ){
        return employeesService.getEmployee(empNo);
    }

    @PostMapping("add")
    public ApiResponse addEmployee(
            @Valid @RequestBody RequestEmployeeDto request
    ){
        return employeesService.addEmployee(request);
    }
    @PostMapping("update/{empNo}")
    public ApiResponse updateEmployee(
            @PathVariable("empNo") Integer empNo,
            @Valid @RequestBody RequestEmployeeDto request
    ){
        return employeesService.updateEmployee(request,empNo);
    }

    @PostMapping("delete/{empNo}")
    public ApiResponse deleteEmployee(
            @PathVariable("empNo") Integer empNo
    ){
        return employeesService.deleteEmployee(empNo);
    }
}
