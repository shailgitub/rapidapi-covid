package com.rapidapi.covid19statistics.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.rapidapi.covid19statistics.ConfigRestTemplate;
import com.rapidapi.covid19statistics.dto.Covid19StatusResponse;
import com.rapidapi.covid19statistics.entity.CovidStatus;
import com.rapidapi.covid19statistics.repo.CovidStatusPaginationRepository;
import com.rapidapi.covid19statistics.repo.CovidStatusRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Log
@Service
public class CovidStatusApiClientService {
    @Value("${covidstatusapi.total.url}")
    String covidTotalStatusApiUrl;
    @Value("${covidstatusapi.states.url}")
    String covidAllStateStatusApiUrl;
    @Value("${covidstatusapi.host.name}")
    String apiHostName;
    @Value("${covidstatusapi.host.value}")
    String apiHostValue;
    @Value("${covidstatusapi.key.name}")
    String apiKeyName;
    @Value("${covidstatusapi.key.value}")
    String apiKeyValue;

    @Autowired
    ConfigRestTemplate restTemplate;
    @Autowired
    CovidStatusPaginationRepository covidStatusPaginationRepository;
    @Autowired
    CovidStatusRepository covidStatusRepository;

    public ResponseEntity<String> consumeCovidApiTotalStatus() {
        log.info("consumeCovidApiTotalStatus() started in CovidStatusApiClientService");
        HttpEntity<String> request = null;
        ResponseEntity<String> response = null;
        String result = null;
        try {
            URI uri;
            uri = new URI(covidTotalStatusApiUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set(apiKeyName, apiKeyValue);
            headers.set(apiHostName, apiHostValue);
            headers.setContentType(MediaType.APPLICATION_JSON);
            request = new HttpEntity<String>(headers);
            final HttpEntity<String> entity = new HttpEntity<String>(headers);
            response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            System.out.println("response" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<String> consumeCovidApiAllStatesStatus() {
        log.info("consumeCovidApiAllStatesStatus() started in CovidStatusApiClientService");
        ResponseEntity<String> response = null;
        try {
            response = this.getCovidAllStatesStatusResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<CovidStatus> consumeCovidApiForPagination() {
        log.info("consumeCovidApiForPagination() started in CovidStatusApiClientService");
        HttpEntity<String> request = null;
        ResponseEntity<CovidStatus> response = null;
        String result = null;
        try {
            URI uri;
            //uri = new URI(covidTotalStatusApiUrl);
            uri = new URI(covidAllStateStatusApiUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set(apiKeyName, apiKeyValue);
            headers.set(apiHostName, apiHostValue);
            headers.setContentType(MediaType.APPLICATION_JSON);
            request = new HttpEntity<String>(headers);
            final HttpEntity<String> entity = new HttpEntity<String>(headers);
            response = restTemplate.exchange(uri, HttpMethod.GET, entity, CovidStatus.class);
            System.out.println("response" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<CovidStatus>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<String> getCovidAllStatesStatusResponse() {
        log.info("getCovidAllStatesStatusResponse() started in CovidStatusApiClientService");
        HttpEntity<String> request = null;
        ResponseEntity<String> response = null;
        String result = null;
        try {
            URI uri;
            uri = new URI(covidAllStateStatusApiUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set(apiKeyName, apiKeyValue);
            headers.set(apiHostName, apiHostValue);
            headers.setContentType(MediaType.APPLICATION_JSON);
            request = new HttpEntity<String>(headers);
            final HttpEntity<String> entity = new HttpEntity<String>(headers);
            response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            System.out.println("response" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
    }

    public Object convertJSON2POJO() throws JsonProcessingException {
        log.info("convertJSON2POJO() started in CovidStatusApiClientService");
        ResponseEntity<String> response = null;
        Object covid19StatusResponseObj = null;
        String result = null;
        try {
            response = getCovidAllStatesStatusResponse();
            System.out.println("response" + response);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            covid19StatusResponseObj = mapper.readValue(response.getBody(), Covid19StatusResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return covid19StatusResponseObj;
    }

    public List<CovidStatus> getCovidStatsByPages(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("getCovidStatsByPages() started in CovidStatusApiClientService");
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<CovidStatus> pagedResult = covidStatusPaginationRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<CovidStatus>();
        }
    }

    public List<CovidStatus> getAllStatsBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        return covidStatusRepository.getDataBetweenStartAndEndDates(startDate, endDate);
    }

}
