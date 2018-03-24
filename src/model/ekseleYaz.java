/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ruhi ÇELİK
 */
public class ekseleYaz {
    
    public static void writeToExcel(JTable table, String pat) throws FileNotFoundException, IOException{
     new WorkbookFactory();
     Workbook wb=new XSSFWorkbook();
     Sheet seet=wb.createSheet();
     Row row=seet.createRow(2);
        TableModel tm=table.getModel();
     Row baslik=seet.createRow(0);
        for(int head=0;head<tm.getColumnCount();head++){
            baslik.createCell(head).setCellValue(tm.getColumnName(head));
        }
        for(int rows=0;rows<tm.getRowCount();rows++){
            for(int cols=0;cols<table.getColumnCount();cols++){
                row.createCell(cols).setCellValue(tm.getValueAt(rows, cols).toString());
            }
                row=seet.createRow(rows+3);
            }
            wb.write(new FileOutputStream(pat));
        }
    }

