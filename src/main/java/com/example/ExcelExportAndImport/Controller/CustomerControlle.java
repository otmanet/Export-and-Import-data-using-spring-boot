package com.example.ExcelExportAndImport.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.example.ExcelExportAndImport.Excel.CustomerExcelExport;

import com.example.ExcelExportAndImport.model.Customer;
import com.example.ExcelExportAndImport.service.ServiceCustomer;

import org.apache.logging.log4j.message.Message;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        // send to excelImpl clwass
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public String importExcelFile(@RequestParam("file") MultipartFile files, Model model)
            throws IOException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {
                    Customer customer = new Customer();
                    XSSFRow row = worksheet.getRow(index);
                    Integer id = (int) row.getCell(0).getNumericCellValue();
                    customer.setId(id);
                    customer.setFirstName(row.getCell(1).getStringCellValue());
                    customer.setLastName(row.getCell(2).getStringCellValue());
                    customer.setCin(row.getCell(3).getStringCellValue());

                    serviceCustomer.save(customer);
                }
            }
            return "redirect:/";
        } catch (IOException e) {

            return "Error";
        }

    }
}
