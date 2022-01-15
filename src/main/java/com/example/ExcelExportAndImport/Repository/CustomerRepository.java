package com.example.ExcelExportAndImport.Repository;

import com.example.ExcelExportAndImport.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
