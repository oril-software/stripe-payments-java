package com.example.stripe.controllers;

import com.example.stripe.dto.CreditCardDTO;
import com.example.stripe.dto.CustomerDTO;
import com.example.stripe.utils.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private StripeClient stripeClient;

	@Autowired
	PaymentController(StripeClient stripeClient) {
		this.stripeClient = stripeClient;
	}

	@PostMapping("/token")
	public Token createToken(@RequestBody CreditCardDTO creditCardDTO) throws StripeException {
		return stripeClient.createCardToken(creditCardDTO);
	}

	@PostMapping("/charge")
	public ResponseEntity<Charge> chargeCard(@RequestParam("amount") int amount, HttpServletRequest request) throws StripeException {
		String stripeToken = request.getHeader("token");
		return ResponseEntity.ok(stripeClient.chargeNewCard(stripeToken, amount));
	}

	@PostMapping("/charge/customers/{customerId}")
	public ResponseEntity<Charge> chargeExistingCustomer(@PathVariable("customerId") String customerId, @RequestParam("amount") int amount) throws StripeException {
		return ResponseEntity.ok(stripeClient.chargeExistingCustomer(customerId, amount));
	}

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO, HttpServletRequest request) throws StripeException {
		String stripeToken = request.getHeader("token");
		return ResponseEntity.ok(stripeClient.createCustomer(stripeToken, customerDTO));
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Boolean> deleteCustomer(@PathVariable("customerId") String customerId) throws StripeException {
		return ResponseEntity.ok(stripeClient.deleteCustomer(customerId));
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws StripeException {
		return ResponseEntity.ok(stripeClient.getAllCustomers());
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") String customerId) throws StripeException {
		return ResponseEntity.ok(stripeClient.getCustomer(customerId));
	}

	@PostMapping("/refund/{chargeId}")
	public ResponseEntity<Refund> createRefund(@PathVariable("chargeId") String chargeId) throws StripeException {
		return ResponseEntity.ok(stripeClient.createRefund(chargeId));
	}

	@GetMapping("/balance")
	public ResponseEntity<Balance> getBalance() throws StripeException {
		return ResponseEntity.ok(stripeClient.getBalance());
	}

}
