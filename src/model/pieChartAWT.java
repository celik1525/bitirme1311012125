/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.javaConnect;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Ruhi ÇELİK
 */
public class pieChartAWT extends ApplicationFrame{
   JFreeChart pieChart;
   Connection conn=null;
ResultSet rs=null;
PreparedStatement ps=null; 
 String sq;   
 String baslik;   
public pieChartAWT(String title, String sql) {
       super(title);
   sq=sql;
   baslik=title;
       conn=javaConnect.ConnectDb();
    
    setContentPane(createDemoPanel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
   
private PieDataset createDataset(String s ) {
    String sql=s;  
    DefaultPieDataset dataset = new DefaultPieDataset( );
    try{
        ps=conn.prepareStatement(sql);
    rs=ps.executeQuery();

    while (rs.next()){
final String grup=rs.getString(1);
final int rakam=rs.getInt(2);
dataset.setValue(grup, rakam);
    
    }}catch(Exception e){
               
    }finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }  
//    dataset.setValue( "IPhone 5s" , new Double( 20 ) );  
//      dataset.setValue( "SamSung Grand" , new Double( 20 ) );   
//      dataset.setValue( "MotoG" , new Double( 40 ) );    
//      dataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
      return dataset;         
   }
   
 private  JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         baslik,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         true);

      return chart;
   }
   
   public JPanel createDemoPanel( ) {
      pieChart = createChart(createDataset(sq));  
      return new ChartPanel( pieChart ); 
   }


public void jpegYap(){
    try {
        File barChar=new File("Piechart.jpeg");
        //ChartUtilities.saveChartAsJPEG(BarChartAWT, barChart, 640, 480);
        ChartUtilities.saveChartAsJPEG(barChar, pieChart, 640, 480);
    } catch (IOException ex) {
        Logger.getLogger(BarChartAWT.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
