/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import javafx.scene.transform.Rotate;
import javax.swing.JTable;


/**
 *
 * @author Ruhi ÇELİK
 */
public class pdfYap {
 public static void pdfYapp(JTable tablo, String pat) throws FileNotFoundException, DocumentException{
    Document document = new Document(); 
 PdfWriter writer; 

 writer = PdfWriter.getInstance(document, new FileOutputStream(pat)); 
writer.setLinearPageMode();

//Rotate rotation=new Rotate();
  
//writer.setPageEvent( rotation);
document.open();
document.add(new Paragraph("-----------------------------"));
   //  boolean add = document.add((Element) new Date());

//document.newPage();

 PdfContentByte cb = writer.getDirectContent(); 
 cb.saveState(); 
 PdfTemplate tp = cb.createTemplate(500, 500); 
 Graphics2D g2; 

 g2 = tp.createGraphicsShapes(500, 500); 
 tablo.print(g2); 
 g2.dispose(); 
 cb.addTemplate(tp, 30, 300); 
 cb.restoreState(); 

 document.close(); 
 
 
 }
}
