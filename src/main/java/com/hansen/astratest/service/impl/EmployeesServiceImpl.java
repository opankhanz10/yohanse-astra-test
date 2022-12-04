package com.hansen.astratest.service.impl;

import com.hansen.astratest.datasource.entity.Employees;
import com.hansen.astratest.datasource.repository.EmployeesRepository;
import com.hansen.astratest.dto.ApiResponse;
import com.hansen.astratest.dto.EmployeeDto;
import com.hansen.astratest.dto.PaginationResponse;
import com.hansen.astratest.dto.RequestEmployeeDto;
import com.hansen.astratest.exception.DataNotFound;
import com.hansen.astratest.exception.SystemUnavailableException;
import com.hansen.astratest.service.intface.*;
import com.hansen.astratest.utils.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class EmployeesServiceImpl implements EmployeesService {
    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    DeptManagerService deptManagerService;

    @Autowired
    DeptEmpService deptEmpService;

    @Autowired
    SalariesService salariesService;

    @Autowired
    TitlesService titlesService;

    @Override
    public ApiResponse addEmployee(RequestEmployeeDto request) {
        var employee = new Employees();
        saveDetails(request,employee);
        return new ApiResponse(Constant.SUCCESS, true);
    }

    @Override
    public ApiResponse updateEmployee(RequestEmployeeDto request, Integer empNo) {
        try{
            checkExistEmployee(empNo);
            var employee = employeesRepository.findByEmpNo(empNo);
            saveDetails(request,employee);
        }catch (DataNotFound dx){
            throw new DataNotFound(dx.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new SystemUnavailableException();
        }
        return new ApiResponse(Constant.SUCCESS, true);
    }

    @Override
    public ApiResponse deleteEmployee(Integer empNo) {
        try{
            checkExistEmployee(empNo);
            employeesRepository.deleteById(empNo);
            deptEmpService.deleteDeptEmp(empNo);
            deptManagerService.deleteDeptManager(empNo);
            salariesService.deleteSalary(empNo);
            titlesService.deleteTitle(empNo);
        }catch (DataNotFound dx){
            throw new DataNotFound(dx.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new SystemUnavailableException();
        }
        return new ApiResponse(Constant.SUCCESS, true);
    }

    @Override
    public ApiResponse getEmployee(Integer empNo) {
        var empDetail = employeesRepository.getDetailEmployee(empNo);
        if(empDetail == null){
            throw new DataNotFound("Data employee id "+empNo+" not found");
        }
        empDetail.setEmployeeDepartement(deptEmpService.getListDetailDeptEmp(empNo));
        empDetail.setManagerDepartement(deptManagerService.getListDetailDeptManager(empNo));
        empDetail.setSalary(salariesService.getListSalary(empNo));
        empDetail.setTitle(titlesService.getListTitle(empNo));
        return new ApiResponse(empDetail, true);
    }

    @Override
    public ApiResponse listEmployee(EmployeeDto request, PageRequest page) {
        var response = new PaginationResponse();
        var result = employeesRepository.getListEmployee(request, page);
        getResult(result, response);
        return new ApiResponse(response,true);
    }

    @Override
    public EmployeeDto setRequestList(String search, String birthDateFrom, String birthDateTo, String hireDateFrom, String hireDateTo) {
        var employeeDto = new EmployeeDto();
        try{
            var formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][dd-MM-yyyy]");
            employeeDto = new EmployeeDto(search == null || search.isEmpty() ? null : search.toUpperCase(),
                    birthDateFrom == null || birthDateFrom.isEmpty() ? null : LocalDate.parse(birthDateFrom,formatter),
                    birthDateTo == null || birthDateTo.isEmpty() ? null : LocalDate.parse(birthDateTo,formatter),
                    hireDateFrom == null || hireDateFrom.isEmpty() ? null : LocalDate.parse(hireDateFrom,formatter),
                    hireDateTo == null || hireDateTo.isEmpty() ? null : LocalDate.parse(hireDateTo,formatter));
        }catch (Exception ex){
            ex.printStackTrace();
            throw new SystemUnavailableException();
        }
        return employeeDto;
    }

    private void checkExistEmployee(Integer empNo){
        var employee = employeesRepository.findById(empNo);
        if(!employee.isPresent()){
            throw new DataNotFound("Employee Number "+empNo+" not found");
        }
    }

    private void saveDetails(RequestEmployeeDto request, Employees employee){
        try {
            BeanUtils.copyProperties(request,employee);
            employeesRepository.save(employee);
            titlesService.addTitle(request, employee.getEmpNo());
            salariesService.addSalary(request, employee.getEmpNo());
            deptEmpService.addDeptEmp(request, employee.getEmpNo());
            deptManagerService.addDeptManager(request, employee.getEmpNo());
        }catch (DataNotFound ex){
            throw new DataNotFound(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new SystemUnavailableException();
        }
    }

    private PaginationResponse getResult(Page<?> result, PaginationResponse response){

        if(result.isEmpty()){
            throw new DataNotFound(Constant.DATA_LIST_EMPLOYEE_NOT_FOUND);
        }
        else{
            addDataPaging(response, result.getContent(), result);
        }
        return response;
    }

    private void addDataPaging(PaginationResponse response, List<?> list, Page<?> page){
        response.setData(list);
        response.setTotalData(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber()+1);
        response.setSizeOfData(page.getNumberOfElements());
        var firstData = 1 + ((page.getNumber()) * page.getSize());
        var lastData = (long) (page.getNumber() + 1) * page.getSize();

        if (lastData > page.getTotalElements()) {
            lastData = page.getTotalElements();
        }
        response.setDataOf(firstData + " - " + lastData);
    }
}
