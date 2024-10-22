package com.javguerra.services;

import com.javguerra.entities.Customer;
import com.javguerra.repositories.CustomerRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public long countCustomers() {
        return customerRepository.count();
    }

    @Override
    public Optional<Customer> findACustomerById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer saveACustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void removeACustomerById(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void removeAllCustomers() {
        customerRepository.deleteAll();
    }

}