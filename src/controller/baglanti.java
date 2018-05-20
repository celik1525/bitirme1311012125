/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Ruhi ÇELİK
 */
public class baglanti {
    static PreparedStatement ps=null;
    static Connection conn=null;
    static ResultSet rs=null;
    
public static ResultSet yap(String sql){
    try{
        Class.forName("org.sqlite.JDBC");
        conn=DriverManager.getConnection("jdbc:sqlite:c:\\tasarım1311012125\\tasarim.sqlite");
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery(sql);
        
    }catch(Exception e)
    {}
//    finally{
//        try{
//      rs.close();
//      ps.close();
//        }catch(Exception e)
//    {}
//    }
    return rs;
}
    public static Connection ConnectDb() {
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn=DriverManager.getConnection("jdbc:sqlite:c:\\tasarım1311012125\\tasarim.sqlite");
        return conn;
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }            
    }



}
