package com.javguerra.service;

import com.javguerra.model.Customer;
import com.javguerra.repository.CustomerRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void saveAll(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    @Override
    public void deleteById(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

}