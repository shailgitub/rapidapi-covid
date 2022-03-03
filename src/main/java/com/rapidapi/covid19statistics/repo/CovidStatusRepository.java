package com.rapidapi.covid19statistics.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidapi.covid19statistics.entity.CovidStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CovidStatusRepository extends JpaRepository<CovidStatus, Long> {
    @Query(nativeQuery = true, value="select * from covid19_states c19s where c19s.last_update between :startDate and :endDate")
    public List<CovidStatus> getDataBetweenStartAndEndDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
