package com.rapidapi.covid19statistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "covid19_states")
public class CovidStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "keyId")
	private String keyId;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@Column(name = "province")
	private String province;

	@Column(name = "confirmed")
	private int confirmed;

	@Column(name = "deaths")
	private int deaths;

	@Column(name = "recovered")
	private int recovered;

	@Column(name = "lastUpdate")
	private Date lastUpdate;

	public CovidStatus() {
	}

	public CovidStatus(String keyId, String country, String city, String province, int confirmed, int deaths, int recovered, Date lastUpdate) {
		this.keyId = keyId;
		this.country = country;
		this.city = city;
		this.province = province;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.lastUpdate = lastUpdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
