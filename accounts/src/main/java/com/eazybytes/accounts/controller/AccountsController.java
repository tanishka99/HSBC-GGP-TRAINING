/**
 * 
 */
package com.eazybytes.accounts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.AccountServiceConfig;
import com.eazybytes.accounts.feignclients.CardsFeignClient;
import com.eazybytes.accounts.feignclients.LoansFeignClient;
import com.eazybytes.accounts.model.Accounts;
import com.eazybytes.accounts.model.Cards;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.model.CustomerDetails;
import com.eazybytes.accounts.model.Loans;
import com.eazybytes.accounts.model.Properties;
import com.eazybytes.accounts.repository.AccountsRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class AccountsController {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private CardsFeignClient cardsFeignClient;

	@Autowired
	private LoansFeignClient loansFeignClient;

	@Autowired
	private AccountServiceConfig accountsConfig;
	
	@GetMapping("/sayHello")
	public String sayhello() {
		return "Helloworld";
	}

	@GetMapping("/accounts/properties")
	public Properties getPropertyDetails() {
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
				accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
//		String jsonStr = ow.writeValueAsString(properties);
		return properties;
	}

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		accountsRepository.findAll().forEach(c -> System.out.println(c));

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

		if (accounts != null) {
			
			return accounts;
		} else {
			return null;
		}

	}

	@PostMapping("/getCustomerDetails")
	//@CircuitBreaker(name = "customerDetailsInAccountsService", fallbackMethod = "myCustomerDetailsFallback")
	@Retry(name="retrygetCustomerDetails", fallbackMethod="retryFallBackForCustomerDetails")
	public CustomerDetails getCustomerDetails(@RequestBody Customer customer) {

		CustomerDetails customerDetails = new CustomerDetails();

		// get customer acc details
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

		// make http req to /mycards --> localhost:9000/myCards
		List<Cards> cards = cardsFeignClient.getCardsDetails(customer);

		// make http req to /myLoans --> localhost:8090/myLoans
		List<Loans> loans = loansFeignClient.getLoansDetails(customer);

		customerDetails.setAccount(accounts);
		customerDetails.setCards(cards);
		customerDetails.setLoans(loans);

		return customerDetails;

	}
	
	private CustomerDetails retryFallBackForCustomerDetails(Customer customer, RuntimeException E) {
		
		CustomerDetails customerDetails = new CustomerDetails();

		// get customer acc details
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

		// make http req to /myLoans --> localhost:8090/myLoans
		List<Loans> loans = loansFeignClient.getLoansDetails(customer);

		customerDetails.setAccount(accounts);
		customerDetails.setLoans(loans);

		return customerDetails;

	}
		

	private CustomerDetails myCustomerDetailsFallback(Customer customer, Throwable t) {

		CustomerDetails customerDetails = new CustomerDetails();

		// get customer acc details
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

		// make http req to /myLoans --> localhost:8090/myLoans
		List<Loans> loans = loansFeignClient.getLoansDetails(customer);

		customerDetails.setAccount(accounts);
		customerDetails.setLoans(loans);

		return customerDetails;

	}

}
