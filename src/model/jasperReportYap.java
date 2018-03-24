/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.javaConnect;
import java.sql.Connection;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
  public void raporla(String path, String sql){
 
      try {
            JasperDesign jd=JRXmlLoader.load(path);
            JRDesignQuery quer=new JRDesignQuery();
            quer.setText(sql);
        jd.setQuery(quer);
        JasperReport jr=JasperCompileManager.compileReport(jd);
 JasperPrint jp=JasperFillManager.fillReport(jr, null, conn);
      
            JasperViewer.viewReport(jp);
        
        } catch (JRException ex) {
            Logger.getLogger(jasperReportYap.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
    
}
