package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.entity.Customer;
import com.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	// need to inject the customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		// get customer from dao
		List<Customer> customers = customerService.getCustomers();
		System.out.println(customers);

		// add the customers to the model
		theModel.addAttribute("customers", customers);
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model themodel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		themodel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		// save the customer using our service

		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")

	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from service
		Customer theCustomer = customerService.getCustomer(theId);

		// set customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);

		// send over to our form

		return "customer-form";

	}

	@GetMapping("/showFormForDelete")

	public String showFormForDelete(@RequestParam("customerId") int theId) {

		//delete the customer
		customerService.deleteCustomer(theId);
		
	return "redirect:/customer/list";
//	return "redirect:/customer/list";

	}

}
