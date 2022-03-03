package com.rapidapi.covid19statistics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "keyId","city", "province", "country", "lastUpdate","confirmed","deaths","recovered" })
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Covid19Stats {

    @JsonProperty("city")
    public Object city;
    @JsonProperty("province")
    public String province;
    @JsonProperty("country")
    public String country;
    @JsonProperty("lastUpdate")
    public Date lastUpdate;
    @JsonProperty("keyId")
    public String keyId;
    @JsonProperty("confirmed")
    public int confirmed;
    @JsonProperty("deaths")
    public int deaths;
    @JsonProperty("recovered")
    public Object recovered;

    public Covid19Stats() {
        super();
    }


}
