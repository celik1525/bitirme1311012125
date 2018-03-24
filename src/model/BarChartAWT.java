/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.javaConnect;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Ruhi ÇELİK
 */
public class BarChartAWT extends ApplicationFrame{
    JFreeChart barChart;
   Connection conn=null;
ResultSet rs=null;
PreparedStatement ps=null;
    
    public BarChartAWT(String title, String chartTitle, String sql){
    super(title);
    conn=javaConnect.ConnectDb();
    barChart=ChartFactory.createBarChart(chartTitle, "Kategori", "Sayı", createDataset(sql),PlotOrientation.VERTICAL,true,true,false);
    ChartPanel chartPanel=new ChartPanel(barChart);
    chartPanel.setPreferredSize(new Dimension(560, 367));
    setContentPane(chartPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}    

private CategoryDataset createDataset(String s){
    String sql=s;
    final DefaultCategoryDataset dataset=new DefaultCategoryDataset();
    try{
            ps=conn.prepareStatement(sql);
    rs=ps.executeQuery();

    while (rs.next()){
final String grup=rs.getString(1);
final int rakam=rs.getInt(2);
    dataset.addValue(rakam, grup, "Kategori");
    }}catch(Exception e){
        
    }finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }
//    final String fiat="Fiat";
//    final String sayi="numara";
//    final String audi="Audi";
//    final DefaultCategoryDataset dataset=new DefaultCategoryDataset();
//    dataset.addValue(1.0, fiat, sayi);
//    dataset.addValue(2.0, fiat, sayi);
//    dataset.addValue(5.0, audi, sayi);
//    dataset.addValue(3.0, audi, sayi);
    
    return dataset;
}
public void jpegYap(){
    try {
        File barChar=new File("Barchart.jpeg");
        //ChartUtilities.saveChartAsJPEG(BarChartAWT, barChart, 640, 480);
        ChartUtilities.saveChartAsJPEG(barChar, barChart, 640, 480);
    } catch (IOException ex) {
        Logger.getLogger(BarChartAWT.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
