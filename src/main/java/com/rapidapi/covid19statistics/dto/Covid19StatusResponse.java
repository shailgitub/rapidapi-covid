package com.rapidapi.covid19statistics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"error", "statusCode", "message", "data"})
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Covid19StatusResponse {
    @JsonProperty("error")
    private boolean error;
    @JsonProperty("statusCode")
    private int statusCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private List<Data> data = null;

    public Covid19StatusResponse() {
        super();
    }

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
    
}
