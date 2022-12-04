package com.hansen.astratest.datasource.repository;

import com.hansen.astratest.datasource.entity.DeptEmp;
import com.hansen.astratest.datasource.entity.DeptRelationIds;
import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptRelationIds> {
    @Modifying
    @Query(value = "delete from dept_emp where emp_no = :emp_no",
            nativeQuery = true)
    void deleteAllByEmpNo(Integer emp_no);

    @Query(value ="SELECT new com.hansen.astratest.dto.EmployeeRelationDetailDto(dm.deptEmpId.deptNo,d.deptName, dm.fromDate,dm.toDate) " +
            "FROM DeptEmp dm " +
            "INNER JOIN Departements d on dm.deptEmpId.deptNo = d.deptNo " +
            "WHERE dm.deptEmpId.empNo = :empNo order by dm.fromDate ASC "
    )
    List<EmployeeRelationDetailDto> getListDeptEmpByEmpNo(@Param("empNo") Integer empNo);
}
