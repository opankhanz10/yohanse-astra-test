package com.hansen.astratest.datasource.repository;

import com.hansen.astratest.datasource.entity.Salaries;
import com.hansen.astratest.datasource.entity.SalariesIds;
import com.hansen.astratest.dto.SalaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, SalariesIds> {
    @Modifying
    @Query(value = "delete from salaries where emp_no = :emp_no",
            nativeQuery = true)
    void deleteAllByEmpNo(Integer emp_no);

    @Query(value ="SELECT new com.hansen.astratest.dto.SalaryDto(s.salariesId.fromDate,s.toDate, s.salary) " +
            "FROM Salaries s " +
            "WHERE s.salariesId.empNo = :empNo order by s.salariesId.fromDate ASC "
    )
    List<SalaryDto> getListSalaryByEmpNo(@Param("empNo") Integer empNo);


}
