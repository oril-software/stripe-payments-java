package com.example.stripe.dto;

public class CreditCardDTO {

	private String number;
	private int month;
	private int year;
	private String cvc;

	public CreditCardDTO() {
	}

	public CreditCardDTO(String number, int month, int year, String cvc) {
		this.number = number;
		this.month = month;
		this.year = year;
		this.cvc = cvc;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
}
