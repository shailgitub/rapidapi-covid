package com.rapidapi.covid19statistics.repo;

import com.rapidapi.covid19statistics.entity.CovidStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidStatusPaginationRepository extends PagingAndSortingRepository<CovidStatus, Long> {

}
