/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.javaConnect;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ruhi ÇELİK
 */
public class ikincilTabloEkran extends JFrame{
    Connection conn=null;
ResultSet rs=null;
PreparedStatement ps=null;
    GridBagLayout gb;
    GridBagConstraints gbc;
    JPanel p, secimPano;
    JLabel id,deger;
    JTextField idTF, degerTF;
    JButton ekleBt,guncelleBt,silBt;
    JTable tablo;
    private String isim;
public ikincilTabloEkran(){
    
    conn=javaConnect.ConnectDb();
    gb=new GridBagLayout();
    gbc=new GridBagConstraints();
    gbc.gridx=0;
    gbc.gridy=0;
    this.setSize(400, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    p=new JPanel();
    p.setLayout(gb);
    idTF.setEditable(false);
    idTF.setColumns(20);
    degerTF.setColumns(20);
    ekleBt=new JButton("EKLE");
    ekleBt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) { 
        ekle();
        }
    });
    guncelleBt=new JButton("GÜNCELLE");
    guncelleBt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            guncelle();
        }
    });
    silBt=new JButton("SİL");
    silBt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        sil();    
        }
    });
tablo=new JTable();
tablo.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        TabloGez();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
tablo.addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent evt) {
         if(evt.getKeyCode()==KeyEvent.VK_UP||evt.getKeyCode()==KeyEvent.VK_DOWN){

        TabloGez();
         }
        }
    });

p.add(id,gbc);
gbc.gridy=1;
p.add(deger,gbc);
gbc.gridx=1;
gbc.gridy=0;
p.add(idTF,gbc);
gbc.gridy=1;
p.add(degerTF,gbc);
gbc.gridx=2;
gbc.gridy=0;
p.add(ekleBt,gbc);
gbc.gridy=1;
p.add(guncelleBt,gbc);
gbc.gridy=2;
p.add(silBt,gbc);
gbc.gridx=3;
gbc.gridy=0;
p.add(tablo,gbc);
this.add(p);
this.setVisible(true);
}   
public void TabloGez(){
    int Row=tablo.getSelectedRow();
    String tableClick=(tablo.getModel().getValueAt(Row, 0).toString());
     try{   
    String sql="select * from ? where id=?";
    
    ps=conn.prepareStatement(sql);
    ps.setString(1, isim);
    ps.setString(2,tableClick);
    rs=ps.executeQuery();
    if(rs.next()){
       idTF.setText(rs.getString(1));
       degerTF.setText(rs.getString(2));
    }
    }catch(Exception e)
{
    }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
}
    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

public void ekle(){
String sql="INSERT İNTO ? (deger) VALUES (?)";
 try{ps=conn.prepareStatement(sql);
    ps.setString(1, isim);
    ps.setString(2, degerTF.getText());
        ps.execute();
        JOptionPane.showMessageDialog(null, "EKLENDİ");
    }catch(Exception e){}
    finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }
updateTable();
}
public void sil(){
    int p=JOptionPane.showConfirmDialog(null,"Silmek istediğinizden eminmisiniz","SİL",JOptionPane.YES_NO_OPTION);
        if (p==0){
         
    String sql="DELETE FROM ? WHERE id=?";
    try{ps=conn.prepareStatement(sql);
    ps.setString(1, isim);
    ps.setString(2, idTF.getText());
        ps.execute();
        JOptionPane.showMessageDialog(null, "SİLİNDİ");
    }catch(Exception e){}
    finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }
updateTable();}
}
public void guncelle(){
    String sql="UPDATE ? SET deger=? WHERE id=?";
    try{ps=conn.prepareStatement(sql);
    ps.setString(1, isim);
    ps.setString(2, degerTF.getText());
    ps.setString(3, idTF.getText());
        ps.execute();
        JOptionPane.showMessageDialog(null, "GÜNCELLENDİ");
    }catch(Exception e){}
    finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }
updateTable();
}

public void updateTable(){
    String sql="select * from ?";
    try{ps=conn.prepareStatement(sql);
    ps.setString(1, isim);
        rs=ps.executeQuery();
        tablo.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(Exception e){}
    finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }
}


}
