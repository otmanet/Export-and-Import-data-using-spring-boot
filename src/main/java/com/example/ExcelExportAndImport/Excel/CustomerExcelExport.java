package com.example.ExcelExportAndImport.Excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.ExcelExportAndImport.model.Customer;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class CustomerExcelExport extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // define excel file name to be exported
        response.addHeader("Content-Disposition", "attachment;fileName=Customers.xls");

        // read data provided by controller
        @SuppressWarnings("unchecked")
        List<Customer> list = (List<Customer>) model.get("list");

        // create one sheet
        Sheet sheet = workbook.createSheet("Customer");

        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("FIRSTNAME");
        row0.createCell(2).setCellValue("LASTNAME");
        row0.createCell(3).setCellValue("CIN");

        // create row1 onwards from List<T>
        int rowNum = 1;
        for (Customer cus : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cus.getId());
            row.createCell(1).setCellValue(cus.getFirstName());
            row.createCell(2).setCellValue(cus.getLastName());
            row.createCell(3).setCellValue(cus.getCin());
        }
    }

}
