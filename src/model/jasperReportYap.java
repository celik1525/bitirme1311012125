/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.javaConnect;
import it.businesslogic.ireport.connection.JRXMLDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ruhi ÇELİK
 */
public class jasperReportYap {
Connection conn=null;
    public jasperReportYap() {
       conn=javaConnect.ConnectDb();
          
    
    }
  public void raporla(String path, String sql) throws IOException{
 
      try {
            JasperDesign jd=JRXmlLoader.load(path);
            JRDesignQuery quer=new JRDesignQuery();
            quer.setText(sql);
        jd.setQuery(quer);
        JasperReport jr=JasperCompileManager.compileReport(jd);
 JasperPrint jp=JasperFillManager.fillReport(jr, null, conn);
 JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report.pdf");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                        exporter.exportReport();
  Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+"report.pdf");
//        JasperViewer.viewReport(jp);
//         JRPdfExporter pdfExporter = new JRPdfExporter();
//            pdfExporter.setExporterInput(new SimpleExporterInput(jp));
//            ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
//            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
//            pdfExporter.exportReport();
//// 
         //  response.setContentType("application/pdf");
           // response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
//            //response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
// 
//            //OutputStream responseOutputStream = response.getOutputStream();
//            //responseOutputStream.write(pdfReportStream.toByteArray());
//            //responseOutputStream.close();
//            //pdfReportStream.close();
//            //logger.info("Completed Successfully: ");
        } catch (JRException ex) {
            Logger.getLogger(jasperReportYap.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    public void pdf(){
        String reportFileName ="D:\\iReport-3.0.0\\test_report.jasper";
     String outFileName    ="D:\\iReport-3.0.0\\report.pdf";
     String xmlFileName    ="D:\\test.xml";
     String recordPath     ="/ROOT/BARKOD";
     JRXMLDataSource jrxmlds = new JRXMLDataSource(xmlFileName,recordPath);
     HashMap hm = new HashMap();
     try 
     {
 
         JasperPrint  print =  JasperFillManager.fillReport(reportFileName,hm,jrxmlds);
 
 
         JRExporter  exporter= new JRPdfExporter();
 
 
         exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,outFileName);
         exporter.setParameter(JRExporterParameter.JASPER_PRINT,print);
         exporter.exportReport();
         System.out.println("File Created: "+ outFileName);
 
    } catch (JRException ex) 
     {
        ex.printStackTrace();
        System.exit(1);
     } catch(Exception ex)
     {
       ex.printStackTrace();
       System.exit(1);
     }
 
   }
    }
    

