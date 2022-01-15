package com.example.ExcelExportAndImport.Controller;

import java.util.List;

import com.example.ExcelExportAndImport.Excel.CustomerExcelExport;
import com.example.ExcelExportAndImport.model.Customer;
import com.example.ExcelExportAndImport.service.ServiceCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerControlle {

    @Autowired
    private ServiceCustomer serviceCustomer;

    @GetMapping("/")
    public String GetCustomers(Model model) {
        List<Customer> customers = serviceCustomer.findAllCustomers();
        model.addAttribute("customers", customers);
        return "index";
    }

    @GetMapping("/excelExport")
    public ModelAndView exportToExcel() {
        ModelAndView mav = new ModelAndView();
        mav.setView(new CustomerExcelExport());
        // read data from DB
        List<Customer> list = serviceCustomer.findAllCustomers();
        // send to excelImpl class
        mav.addObject("list", list);
        return mav;
    }

}
