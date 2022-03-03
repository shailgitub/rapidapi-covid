package com.rapidapi.covid19statistics.controller;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapidapi.covid19statistics.dto.Covid19StatusResponse;
import com.rapidapi.covid19statistics.services.CovidStatusApiClientService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rapidapi.covid19statistics.entity.CovidStatus;
import com.rapidapi.covid19statistics.repo.CovidStatusRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/apicovid")
@Log
public class CovidStatusController {

    @Autowired
    CovidStatusRepository covidStatusRepository;
    @Autowired
    CovidStatusApiClientService covidStatusApiClientService;
	/*
	 * @Autowired CovidStatusService covidStatusService;
	 */

    Covid19StatusResponse covid19StatusResponse;

    public Covid19StatusResponse getCovid19StatusResponse() {
        return covid19StatusResponse;
    }

    public void setCovid19StatusResponse(Covid19StatusResponse covid19StatusResponse) {
        this.covid19StatusResponse = covid19StatusResponse;
    }

    @PostMapping("/addstatus")
    public ResponseEntity<CovidStatus> addCovid19Status(@RequestBody CovidStatus covidStatus) {
        try {
            CovidStatus _covidStatus = covidStatusRepository.save(new CovidStatus(covidStatus.getKeyId(),
                    covidStatus.getCountry(), covidStatus.getCity(),
                    covidStatus.getProvince(), covidStatus.getConfirmed(),
                    covidStatus.getDeaths(), covidStatus.getRecovered(), covidStatus.getLastUpdate()));
            return new ResponseEntity<>(_covidStatus, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/saveallstats")
    public ResponseEntity<String> addCovid19StatusResponse() {
        try {
            // ArrayList<Covid19Stats> covid19StatsList = new ArrayList<>(covid19StatusResponse.getData().get(0).getCovid19Stats());
           // Covid19StatusResponse covid19StatusResponse = null;
            //ResponseEntity<Covid19StatusResponse> covid19StatusResponse = covidStatusApiClientService.getCovidAllStatesStatus();
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            covid19StatusResponse = mapper.readValue(covidStatusApiClientService.getCovidAllStatesStatusResponse().getBody(), Covid19StatusResponse.class);

            for (int index = 0; index < covid19StatusResponse.getData().get(0).getCovid19Stats().size(); index++) {
                CovidStatus _covidStatus = covidStatusRepository.save(new CovidStatus(
                        covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getKeyId(),
                        covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getCountry(),
                        (null!=covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getCity()) ? covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getCity().toString() : null,
                        covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getProvince(),
                        covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getConfirmed(),
                        covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getDeaths(),
                        (null!=covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getRecovered()) ? Integer.parseInt(covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getRecovered().toString()) : 0,
                        covid19StatusResponse.getData().get(0).getCovid19Stats().get(index).getLastUpdate()));
            }
            log.info("Data saved");
            return new ResponseEntity<>("Data Saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllStats")
    public ResponseEntity<List<CovidStatus>> getAllStates(@RequestParam(required = false) String title) {
        try {
            List<CovidStatus> covidStatusList = new ArrayList<CovidStatus>();
            covidStatusRepository.findAll().forEach(covidStatusList::add);
            if (covidStatusList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(covidStatusList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStatsById/{id}")
    public ResponseEntity<CovidStatus> getTutorialById(@PathVariable("id") long id) {
        Optional<CovidStatus> covidStatusData = covidStatusRepository.findById(id);
        if (covidStatusData.isPresent()) {
            return new ResponseEntity<>(covidStatusData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Fetch data for USA or all Status
    @GetMapping("/apiallstates")
    public ResponseEntity<String> getAllStatusFromCovidApi() {
        try {
            return covidStatusApiClientService.consumeCovidApiAllStatesStatus();
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Fetch data for the entire world
    @GetMapping("/total")
    public ResponseEntity<String> getTotalStatusFromCovidApi() {
        if (true) {
            return covidStatusApiClientService.consumeCovidApiTotalStatus();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/json2pojo")
    public Object jsonResponse2JavaPojo() {
        try {
            return covidStatusApiClientService.convertJSON2POJO();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping
    public ResponseEntity<List<CovidStatus>> getCovidStatsPagination(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<CovidStatus> list = covidStatusApiClientService.getCovidStatsByPages(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<CovidStatus>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/getallBwDates/{startDate}/{endDate}")
    public List<CovidStatus> getData_between(@PathVariable(value = "startDate")
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                             @PathVariable(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return covidStatusApiClientService.getAllStatsBetweenTwoDates(startDate, endDate);
    }
}
