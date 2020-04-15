package com.example.stripe.utils;

import com.example.stripe.dto.CreditCardDTO;
import com.example.stripe.dto.CustomerDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StripeClient {

	StripeClient() {
		Stripe.apiKey = "sk_test_XXXXXXXXXXXXXXXXXXX";
	}

	public Token createCardToken (CreditCardDTO creditCard) throws StripeException {
		Map<String, Object> card = new HashMap<>();
		card.put("number", creditCard.getNumber());
		card.put("exp_month", creditCard.getMonth());
		card.put("exp_year", creditCard.getYear());
		card.put("cvc", creditCard.getCvc());
		Map<String, Object> params = new HashMap<>();
		params.put("card", card);
		return Token.create(params);    //Token.getId() is used as String 'token' in all other methods
	}

	public Customer createCustomer(String token, CustomerDTO customerDTO) throws StripeException {
		Map<String, Object> customerParams = new HashMap<>();
		customerParams.put("email", customerDTO.getEmail());
		customerParams.put("name", customerDTO.getName());
		customerParams.put("phone", customerDTO.getPhone());
		customerParams.put("source", token);
		return Customer.create(customerParams);
	}

	public List<Customer> getAllCustomers() throws StripeException {
		Map<String, Object> customerParams = new HashMap<>();
		customerParams.put("limit", "10");
		return Customer.list(customerParams).getData();
	}

	public Charge chargeNewCard(String token, double amount) throws StripeException {
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", (int)(amount * 100));    //IMPORTANT: amount should be provided in cents. Minimum amount is 50 cents
		chargeParams.put("currency", "USD");
		chargeParams.put("source", token);
		return Charge.create(chargeParams);
	}

	public Charge chargeExistingCustomer(String customerId, int amount) throws StripeException {
		String sourceCard = getCustomer(customerId).getDefaultSource();
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", (int)(amount * 100));
		chargeParams.put("currency", "USD");
		chargeParams.put("customer", customerId);
		chargeParams.put("source", sourceCard);
		return Charge.create(chargeParams);
	}

	public Refund createRefund(String chargeId) throws StripeException {
		Map<String, Object> params = new HashMap<>();
		params.put("charge", chargeId);
		return Refund.create(params);
	}

	public Balance getBalance() throws StripeException {
		return Balance.retrieve();
	}

	public boolean deleteCustomer(String customerId) throws StripeException {
		Customer customer = getCustomer(customerId);
		return customer.delete().getDeleted();
	}

	public Customer getCustomer(String id) throws StripeException {
		return Customer.retrieve(id);
	}

}
