package com.example.ExcelExportAndImport.service;

import java.util.List;

import com.example.ExcelExportAndImport.Repository.CustomerRepository;
import com.example.ExcelExportAndImport.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
