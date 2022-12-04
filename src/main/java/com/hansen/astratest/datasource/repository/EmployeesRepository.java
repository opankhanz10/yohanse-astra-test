package com.hansen.astratest.datasource.repository;

import com.hansen.astratest.datasource.entity.Employees;
import com.hansen.astratest.dto.EmployeeDetailDto;
import com.hansen.astratest.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
    Employees findByEmpNo(Integer empNo);

    @Query(value ="SELECT new com.hansen.astratest.dto.EmployeeDto(e.empNo, e.birthDate, e.firstName, e.lastName, e.gender, e.hireDate) " +
            "FROM Employees e WHERE 1=1 " +
            "AND (:#{#employeeDto.search} IS NULL OR (UPPER(e.firstName) LIKE %:#{#employeeDto.search}% OR UPPER(e.lastName) LIKE %:#{#employeeDto.search}%)) " +
            "AND (:#{#employeeDto.birthDateFromStr} IS NULL OR e.birthDate >= :#{#employeeDto.birthDateFrom}) " +
            "AND (:#{#employeeDto.birthDateToStr} IS NULL OR e.birthDate <= :#{#employeeDto.birthDateTo}) " +
            "AND (:#{#employeeDto.hireDateFromStr} IS NULL OR e.hireDate >= :#{#employeeDto.hireDateFrom}) " +
            "AND (:#{#employeeDto.hirehDateToStr} IS NULL OR e.hireDate <= :#{#employeeDto.hirehDateTo}) " +
            "ORDER BY e.firstName ASC"
    )
    Page<EmployeeDto> getListEmployee(@Param("employeeDto") EmployeeDto employeeDto, PageRequest page);

    @Query(value ="SELECT new com.hansen.astratest.dto.EmployeeDetailDto(e) " +
            "FROM Employees e WHERE e.empNo = :empNo"
    )
    EmployeeDetailDto getDetailEmployee(@Param("empNo") Integer empNo);
}
