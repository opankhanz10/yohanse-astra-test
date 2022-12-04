package com.hansen.astratest.datasource.repository;

import com.hansen.astratest.datasource.entity.DeptManager;
import com.hansen.astratest.datasource.entity.DeptRelationIds;
import com.hansen.astratest.dto.EmployeeRelationDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptRelationIds> {
    @Modifying
    @Query(value = "delete from dept_manager where emp_no = :emp_no",
            nativeQuery = true)
    void deleteAllByEmpNo(Integer emp_no);

    @Query(value ="SELECT new com.hansen.astratest.dto.EmployeeRelationDetailDto(dm.deptManagerId.deptNo,d.deptName, dm.fromDate,dm.toDate) " +
            "FROM DeptManager dm " +
            "INNER JOIN Departements d on dm.deptManagerId.deptNo = d.deptNo " +
            "WHERE dm.deptManagerId.empNo = :empNo order by dm.fromDate ASC "
    )
    List<EmployeeRelationDetailDto> getListManagerByEmpNo(@Param("empNo") Integer empNo);
}
