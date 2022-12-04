package com.hansen.astratest.datasource.repository;

import com.hansen.astratest.datasource.entity.Departements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementsRepository extends JpaRepository<Departements,String> {
    @Query(value ="SELECT dp FROM Departements dp WHERE 1=1 AND " +
            "(:deptName IS NULL OR dp.deptName LIKE '%:deptName%') " +
            "order by dp.deptName ASC")
    List<Departements> getListDepartement(String deptName);
}
