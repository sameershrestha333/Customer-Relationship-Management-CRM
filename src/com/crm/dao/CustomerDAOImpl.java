package com.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crm.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override

	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currenctSession = sessionFactory.getCurrentSession();

		// create query
		Query<Customer> query = currenctSession.createQuery("from Customer order by lastName", Customer.class);

		// execute query and get result list

		List<Customer> customers = query.getResultList();

		// return the result lists.

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// save the customer ... finally
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//delete object with primary key
		
		Query query=currentSession.createQuery("delete from Customer where id=:theCustomerId");
		query.setParameter("theCustomerId", theId);
		
		query.executeUpdate();
		//delete the customer..finally
	//	currentSession.delete(theId);
	}

}
