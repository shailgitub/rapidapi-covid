package com.rapidapi.covid19statistics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lastChecked", "covid19States" })
@ToString
@AllArgsConstructor
public class Data {
    @JsonProperty("lastChecked")
    public Date lastChecked;
    @JsonProperty("covid19Stats")
    public ArrayList<Covid19Stats> covid19Stats;

    public Data() {
        super();
    }

	public Date getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(Date lastChecked) {
		this.lastChecked = lastChecked;
	}

	public ArrayList<Covid19Stats> getCovid19Stats() {
		return covid19Stats;
	}

	public void setCovid19Stats(ArrayList<Covid19Stats> covid19Stats) {
		this.covid19Stats = covid19Stats;
	}
    
}
