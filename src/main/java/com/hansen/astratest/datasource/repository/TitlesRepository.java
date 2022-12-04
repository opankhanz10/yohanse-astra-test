package com.hansen.astratest.datasource.repository;

import com.hansen.astratest.datasource.entity.Titles;
import com.hansen.astratest.datasource.entity.TitlesIds;
import com.hansen.astratest.dto.TitleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitlesRepository extends JpaRepository<Titles, TitlesIds> {
    @Modifying
    @Query(value = "delete from titles where emp_no = :emp_no",
            nativeQuery = true)
    void deleteAllByEmpNo(Integer emp_no);

    @Query(value ="SELECT new com.hansen.astratest.dto.TitleDto(s.titlesId.title,s.titlesId.fromDate, s.toDate) " +
            "FROM Titles s " +
            "WHERE s.titlesId.empNo = :empNo order by s.titlesId.fromDate ASC "
    )
    List<TitleDto> getListTitleByEmpNo(@Param("empNo") Integer empNo);
}
