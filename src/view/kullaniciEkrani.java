package view;

import controller.javaConnect;
import entities.AtamaTablo;
import entities.GirisTablo;
import entities.Izindurum;
import entities.Izingiris;
import entities.KimlikTablo;
import entities.Malihaklar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ruhi ÇELİK
 */
public class kullaniciEkrani extends javax.swing.JFrame {
int sicil=0;
    EntityManager em=null;
EntityManagerFactory emf=null;
    KimlikTablo kt; 
    AtamaTablo at;
    Malihaklar mh;
    Izindurum id;
    GirisTablo gt;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement ps=null;
    String kimlik_Pattern="^[0-9]{11}$";
   String email_Pattern="^[a-zA-Z0-9]{1-20}@[a-zA-Z]{2-3}$";
public kullaniciEkrani() {
        initComponents();
emf=javax.persistence.Persistence.createEntityManagerFactory("personelOtamasyonPU");
em=emf.createEntityManager();
 sicil=javaConnect.sicil;
kt=new KimlikTablo();
at=new AtamaTablo();
mh=new Malihaklar();
id=new Izindurum();
gt=new GirisTablo();
conn=javaConnect.ConnectDb();
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
     //   this.setContentPane(MasaUstu);
     doldurKimlik();
     doldurMali();
     doldurAtama();
     doldurIzin();
     doldurizinTablo();
     doldurGiris();
     doldurYakinAile();
     doldurMalBildirim();
     doldurGorev();
}
public void doldurKimlik(){
    try{
    TypedQuery<KimlikTablo> sorgu=em.createNamedQuery("KimlikTablo.findBySicil",KimlikTablo.class).setParameter("sicil", sicil);
    jTextField1.setText(sorgu.getSingleResult().getSicil().toString());
jTextField2.setText(sorgu.getSingleResult().getTelefon());
jTextArea1.setText(sorgu.getSingleResult().getAdres());
jTextField4.setText(sorgu.getSingleResult().getCinsiyet());
jTextField5.setText(sorgu.getSingleResult().getMedenihal());
jTextField6.setText(sorgu.getSingleResult().getDogumtarihi());
jTextField7.setText(sorgu.getSingleResult().getDogumyeri());
jTextField8.setText(sorgu.getSingleResult().getTCKimlik());   
}catch(Exception e){
}
}
public void doldurAtama(){
    try{
    TypedQuery<AtamaTablo> sorgu=em.createNamedQuery("AtamaTablo.findBySicil",AtamaTablo.class).setParameter("sicil", sicil);
    jTextField10.setText(sorgu.getSingleResult().getSicil().toString());
    jTextField43.setText(jTextField10.getText());
    jTextField3.setText(sorgu.getSingleResult().getName());
    jTextField9.setText(sorgu.getSingleResult().getSirname());
    jTextField11.setText(sorgu.getSingleResult().getAtamaTarihi());
    jTextField12.setText(sorgu.getSingleResult().getHizmetSinifi());
    jTextField13.setText(sorgu.getSingleResult().getBirim());
    jTextField14.setText(sorgu.getSingleResult().getUnvan());
    jTextField15.setText(sorgu.getSingleResult().getGorev());
    jTextField16.setText(sorgu.getSingleResult().getGorevsekli());
    jTextField17.setText(sorgu.getSingleResult().getAtamasekli());
    jTextField18.setText(sorgu.getSingleResult().getMemuriyetbaslama()); 
    }catch(Exception e){
        
    }
}
public void doldurMali(){
    try{
    TypedQuery<Malihaklar> sorgu=em.createNamedQuery("Malihaklar.findBySicil",Malihaklar.class).setParameter("sicil", sicil);
    jTextField19.setText(sorgu.getSingleResult().getKademe().toString());
    jTextField20.setText(sorgu.getSingleResult().getDerece().toString());
    jTextField21.setText(sorgu.getSingleResult().getGosterge().toString());
    jTextField22.setText(sorgu.getSingleResult().getEkgosterge().toString());
    Boolean aile=sorgu.getSingleResult().getAileyardimi();
    if (aile) jTextField23.setText("Var");
    else jTextField23.setText("Yok");
    jTextField24.setText(sorgu.getSingleResult().getCocuk().toString());
    
    
    jTextField26.setText(sorgu.getSingleResult().getSendika());
    jTextField27.setText(sorgu.getSingleResult().getYabancidil());
    jTextField28.setText(sorgu.getSingleResult().getDilderece());
    jTextField29.setText(sorgu.getSingleResult().getMezuniyet());
    jTextField30.setText(sorgu.getSingleResult().getMezuniyetBolum());
}catch(Exception e){
}
}
public void doldurIzin(){
    try{
    TypedQuery<Izindurum> sorgu=em.createNamedQuery("Izindurum.findBySicil",Izindurum.class).setParameter("sicil", sicil);
    jTextField31.setText(sorgu.getSingleResult().getIzinhakedis().toString());
    jTextField32.setText(sorgu.getSingleResult().getDevirizin().toString());
    jTextField33.setText(sorgu.getSingleResult().getKullanılanizin().toString());
    jTextField34.setText(sorgu.getSingleResult().getKalanyillikizin().toString());
    jTextField35.setText(sorgu.getSingleResult().getYolizni().toString());
    jTextField36.setText(sorgu.getSingleResult().getRapor().toString());
    jTextField37.setText(sorgu.getSingleResult().getMazeretizni().toString());
    jTextField38.setText(sorgu.getSingleResult().getOzursuz().toString());
    jTextField39.setText(sorgu.getSingleResult().getIdariizin().toString());       
}catch(Exception e){
}
}
public void doldurGiris(){
 try{
    TypedQuery<GirisTablo> sorgu=em.createNamedQuery("GirisTablo.findBySicil", GirisTablo.class).setParameter("sicil", sicil);
    jTextField40.setText(sorgu.getSingleResult().getKullaniciAdi());
    jTextField41.setText(sorgu.getSingleResult().getSifre());
      Icon resim=new ImageIcon(sorgu.getSingleResult().getImage());
      imajLabel.setIcon(resim);
}catch(Exception e){
}
}
public void doldurizinTablo(){
    String sql="Select * from izingiris where sicil=?";
    try{
        ps=conn.prepareStatement(sql);
        ps.setInt(1, sicil);
        rs=ps.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(Exception e){        
    }finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
}
public void doldurYakinAile(){
    jTextField52.setText(jTextField10.getText());
    String sql="Select * from yakinTablo where sicil=?";
    try{
        ps=conn.prepareStatement(sql);
        ps.setInt(1, sicil);
        rs=ps.executeQuery();
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(Exception e){}
    finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
}
public void doldurMalBildirim(){
malTablodoldur();
      
}
public void malTablodoldur(){
    String sql="select * from malBildirim where sicil=?";
    try{ ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    rs=ps.executeQuery();
    jTable3.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(Exception e){}
    finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
}
public void doldurGorev(){
    String sql="select * from job where sicil=?";
    try{ ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    rs=ps.executeQuery();
    jTable4.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(Exception e){}
    finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        personelOtamasyonPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("personelOtamasyonPU").createEntityManager();
        yakinaileQuery = java.beans.Beans.isDesignTime() ? null : personelOtamasyonPUEntityManager.createQuery("SELECT y FROM Yakinaile y");
        yakinaileList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : yakinaileQuery.getResultList();
        yakinaileQuery1 = java.beans.Beans.isDesignTime() ? null : personelOtamasyonPUEntityManager.createQuery("SELECT y FROM Yakinaile y");
        yakinaileList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : yakinaileQuery1.getResultList();
        yakinaileQuery2 = java.beans.Beans.isDesignTime() ? null : personelOtamasyonPUEntityManager.createQuery("SELECT y FROM Yakinaile y");
        yakinaileList2 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : yakinaileQuery2.getResultList();
        yakinTabloQuery = java.beans.Beans.isDesignTime() ? null : personelOtamasyonPUEntityManager.createQuery("SELECT y FROM YakinTablo y");
        yakinTabloList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : yakinTabloQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        imajLabel = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        kimlikPanel = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField52 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel9.setText("isim");

        jLabel10.setText("Soyisim");

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 204));

        imajLabel.setBackground(new java.awt.Color(204, 204, 255));

        jDesktopPane1.setLayer(imajLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imajLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imajLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField3.setEditable(false);

        jTextField9.setEditable(false);

        jLabel12.setText("Sicil");

        jTextField10.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(32, 238, 238));

        kimlikPanel.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        kimlikPanel.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("Atama Tarihi");
        jPanel4.add(jLabel13, new java.awt.GridBagConstraints());

        jLabel14.setText("Hizmet Sınıfı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel4.add(jLabel14, gridBagConstraints);

        jLabel15.setText("Birimi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel4.add(jLabel15, gridBagConstraints);

        jLabel16.setText("Ünvanı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel4.add(jLabel16, gridBagConstraints);

        jLabel17.setText("Görev");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel4.add(jLabel17, gridBagConstraints);

        jLabel18.setText("görev Şekli");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel4.add(jLabel18, gridBagConstraints);

        jLabel19.setText("Atama Şekli");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel4.add(jLabel19, gridBagConstraints);

        jLabel20.setText("Memuriyete Alınma Tarihi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        jPanel4.add(jLabel20, gridBagConstraints);

        jTextField11.setEditable(false);
        jTextField11.setColumns(20);
        jPanel4.add(jTextField11, new java.awt.GridBagConstraints());

        jTextField12.setEditable(false);
        jTextField12.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel4.add(jTextField12, gridBagConstraints);

        jTextField13.setEditable(false);
        jTextField13.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel4.add(jTextField13, gridBagConstraints);

        jTextField14.setEditable(false);
        jTextField14.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel4.add(jTextField14, gridBagConstraints);

        jTextField15.setEditable(false);
        jTextField15.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel4.add(jTextField15, gridBagConstraints);

        jTextField16.setEditable(false);
        jTextField16.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel4.add(jTextField16, gridBagConstraints);

        jTextField17.setEditable(false);
        jTextField17.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        jPanel4.add(jTextField17, gridBagConstraints);

        jTextField18.setEditable(false);
        jTextField18.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        jPanel4.add(jTextField18, gridBagConstraints);

        kimlikPanel.addTab("Atama Bilgileri", jPanel4);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel33.setText("İzin Hakediş");
        jPanel5.add(jLabel33, new java.awt.GridBagConstraints());

        jLabel34.setText("Devir İzin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel5.add(jLabel34, gridBagConstraints);

        jLabel35.setText("Kullanılan İzin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel5.add(jLabel35, gridBagConstraints);

        jLabel36.setText("Kalan Yıllık İzin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel5.add(jLabel36, gridBagConstraints);

        jLabel37.setText("Kullanılan Yol İzni");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel5.add(jLabel37, gridBagConstraints);

        jLabel38.setText("Sağlık raporu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel5.add(jLabel38, gridBagConstraints);

        jLabel39.setText("Mazeret İzni");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel5.add(jLabel39, gridBagConstraints);

        jLabel40.setText("Özürsüz İşe Gelmeme");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        jPanel5.add(jLabel40, gridBagConstraints);

        jLabel41.setText("İdari İzin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        jPanel5.add(jLabel41, gridBagConstraints);

        jTextField31.setEditable(false);
        jTextField31.setColumns(5);
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField31, new java.awt.GridBagConstraints());

        jTextField32.setEditable(false);
        jTextField32.setColumns(5);
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel5.add(jTextField32, gridBagConstraints);

        jTextField33.setEditable(false);
        jTextField33.setColumns(5);
        jTextField33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField33ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel5.add(jTextField33, gridBagConstraints);

        jTextField34.setEditable(false);
        jTextField34.setColumns(5);
        jTextField34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField34ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel5.add(jTextField34, gridBagConstraints);

        jTextField35.setEditable(false);
        jTextField35.setColumns(5);
        jTextField35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField35ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel5.add(jTextField35, gridBagConstraints);

        jTextField36.setEditable(false);
        jTextField36.setColumns(5);
        jTextField36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField36ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel5.add(jTextField36, gridBagConstraints);

        jTextField37.setEditable(false);
        jTextField37.setColumns(5);
        jTextField37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField37ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        jPanel5.add(jTextField37, gridBagConstraints);

        jTextField38.setEditable(false);
        jTextField38.setColumns(5);
        jTextField38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField38ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        jPanel5.add(jTextField38, gridBagConstraints);

        jTextField39.setEditable(false);
        jTextField39.setColumns(5);
        jTextField39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField39ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        jPanel5.add(jTextField39, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 10;
        jPanel5.add(jScrollPane2, gridBagConstraints);

        kimlikPanel.addTab("İzin Bilgileri", jPanel5);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel22.setText("Kademe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel22, gridBagConstraints);

        jLabel23.setText("Gösterge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel23, gridBagConstraints);

        jLabel24.setText("Ek Gösterge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel24, gridBagConstraints);

        jLabel25.setText("Aile Yardımı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel25, gridBagConstraints);

        jLabel26.setText("Çocuk Sayısı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel26, gridBagConstraints);

        jLabel28.setText("Sendika");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel28, gridBagConstraints);

        jLabel29.setText("Yabancı Dil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel29, gridBagConstraints);

        jLabel30.setText("Dil Derece");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel30, gridBagConstraints);

        jLabel31.setText("Mezuniyet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel31, gridBagConstraints);

        jLabel32.setText("Derece");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel32, gridBagConstraints);

        jTextField19.setEditable(false);
        jTextField19.setColumns(10);
        jPanel6.add(jTextField19, new java.awt.GridBagConstraints());

        jTextField20.setEditable(false);
        jTextField20.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel6.add(jTextField20, gridBagConstraints);

        jTextField21.setEditable(false);
        jTextField21.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel6.add(jTextField21, gridBagConstraints);

        jTextField22.setEditable(false);
        jTextField22.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel6.add(jTextField22, gridBagConstraints);

        jTextField23.setEditable(false);
        jTextField23.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel6.add(jTextField23, gridBagConstraints);

        jTextField24.setEditable(false);
        jTextField24.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel6.add(jTextField24, gridBagConstraints);

        jTextField26.setEditable(false);
        jTextField26.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        jPanel6.add(jTextField26, gridBagConstraints);

        jTextField27.setEditable(false);
        jTextField27.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        jPanel6.add(jTextField27, gridBagConstraints);

        jTextField28.setEditable(false);
        jTextField28.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        jPanel6.add(jTextField28, gridBagConstraints);

        jTextField29.setEditable(false);
        jTextField29.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        jPanel6.add(jTextField29, gridBagConstraints);

        jLabel21.setText("Mezun Olduğu Bölüm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        jPanel6.add(jLabel21, gridBagConstraints);

        jTextField30.setEditable(false);
        jTextField30.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        jPanel6.add(jTextField30, gridBagConstraints);

        kimlikPanel.addTab("Mali Haklar", jPanel6);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel42.setText("Kullanıcı Adı");
        jPanel7.add(jLabel42, new java.awt.GridBagConstraints());

        jLabel43.setText("Parola");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel7.add(jLabel43, gridBagConstraints);

        jTextField40.setEditable(false);
        jTextField40.setColumns(20);
        jPanel7.add(jTextField40, new java.awt.GridBagConstraints());

        jTextField41.setEditable(false);
        jTextField41.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel7.add(jTextField41, gridBagConstraints);

        jButton10.setText("Şifre Değiştir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel7.add(jButton10, gridBagConstraints);

        kimlikPanel.addTab("Giriş Bilgileri", jPanel7);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 7;
        jPanel8.add(jScrollPane3, gridBagConstraints);

        jLabel51.setText("id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel8.add(jLabel51, gridBagConstraints);

        jLabel52.setText("isim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel8.add(jLabel52, gridBagConstraints);

        jLabel53.setText("Soyisim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel8.add(jLabel53, gridBagConstraints);

        jLabel54.setText("TC Kimlik");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel8.add(jLabel54, gridBagConstraints);

        jLabel55.setText("Doğum Tarihi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel8.add(jLabel55, gridBagConstraints);

        jLabel56.setText("Yakınlık");
        jPanel8.add(jLabel56, new java.awt.GridBagConstraints());

        jLabel57.setText("Sicil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel8.add(jLabel57, gridBagConstraints);

        jTextField44.setEditable(false);
        jTextField44.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel8.add(jTextField44, gridBagConstraints);

        jTextField46.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel8.add(jTextField46, gridBagConstraints);

        jTextField50.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel8.add(jTextField50, gridBagConstraints);

        jTextField51.setColumns(11);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel8.add(jTextField51, gridBagConstraints);

        jDateChooser2.setDateFormatString("dd.MM.yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel8.add(jDateChooser2, gridBagConstraints);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Kendisi", "Eşi", "Çocuğu", "Annesi", "Babası", "diğer" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel8.add(jComboBox2, gridBagConstraints);

        jTextField52.setEditable(false);
        jTextField52.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel8.add(jTextField52, gridBagConstraints);

        jButton6.setText("Temizle");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel8.add(jButton6, gridBagConstraints);

        jButton7.setText("Güncelle");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel8.add(jButton7, gridBagConstraints);

        jButton8.setText("Sil");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel8.add(jButton8, gridBagConstraints);

        jButton9.setText("Ekle");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel8.add(jButton9, gridBagConstraints);

        kimlikPanel.addTab("Yakın Aile Bilgileri", jPanel8);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable3KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 11;
        jPanel9.add(jScrollPane4, gridBagConstraints);

        jLabel11.setText("sicil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel11, gridBagConstraints);

        jLabel44.setText("id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel44, gridBagConstraints);

        jLabel45.setText("Cinsi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel45, gridBagConstraints);

        jLabel46.setText("Açıklana");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel46, gridBagConstraints);

        jLabel47.setText("Edinme Tarihi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel47, gridBagConstraints);

        jLabel48.setText("Değeri");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel48, gridBagConstraints);

        jLabel49.setText("Sahibinin İsmi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel49, gridBagConstraints);

        jLabel50.setText("Sahibinin Soyismi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel9.add(jLabel50, gridBagConstraints);

        jButton2.setText("Temizle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(jButton2, gridBagConstraints);

        jButton3.setText("Ekle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(jButton3, gridBagConstraints);

        jButton4.setText("Güncelle");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(jButton4, gridBagConstraints);

        jButton5.setText("Sil");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(jButton5, gridBagConstraints);

        jTextField42.setEditable(false);
        jTextField42.setColumns(20);
        jPanel9.add(jTextField42, new java.awt.GridBagConstraints());

        jTextField43.setEditable(false);
        jTextField43.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel9.add(jTextField43, gridBagConstraints);

        jTextField45.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel9.add(jTextField45, gridBagConstraints);

        jTextField47.setColumns(20);
        jTextField47.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField47KeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel9.add(jTextField47, gridBagConstraints);

        jTextField48.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        jPanel9.add(jTextField48, gridBagConstraints);

        jTextField49.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        jPanel9.add(jTextField49, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Taşınmaz", "Kooperatif", "Taşıt", "Taşınır", "Banka Hesabı", "Altın", "Borç" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(jComboBox1, gridBagConstraints);

        jDateChooser1.setDateFormatString("dd.MM.yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(jDateChooser1, gridBagConstraints);

        kimlikPanel.addTab("Mal Bildirim", jPanel9);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Sicil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Telefon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Adres");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Cinsiyet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Medeni Hal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Doğum Tarihi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Doğum Yeri");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel7, gridBagConstraints);

        jLabel8.setText("TC Kimlik");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel8, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setColumns(20);
        jPanel3.add(jTextField1, new java.awt.GridBagConstraints());

        jTextField2.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel3.add(jTextField2, gridBagConstraints);

        jTextField4.setEditable(false);
        jTextField4.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel3.add(jTextField4, gridBagConstraints);

        jTextField5.setEditable(false);
        jTextField5.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jTextField5, gridBagConstraints);

        jTextField6.setEditable(false);
        jTextField6.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel3.add(jTextField6, gridBagConstraints);

        jTextField7.setEditable(false);
        jTextField7.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        jPanel3.add(jTextField7, gridBagConstraints);

        jTextField8.setEditable(false);
        jTextField8.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        jPanel3.add(jTextField8, gridBagConstraints);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jButton1.setText("Guncelle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel3.add(jButton1, gridBagConstraints);

        kimlikPanel.addTab("Kimlik Bilgileri", jPanel3);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel58.setText("Sıra");
        jPanel10.add(jLabel58, new java.awt.GridBagConstraints());

        jLabel59.setText("Sicil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel10.add(jLabel59, gridBagConstraints);

        jLabel60.setText("Başlangıç");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel10.add(jLabel60, gridBagConstraints);

        jLabel61.setText("Bitiş");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel10.add(jLabel61, gridBagConstraints);

        jLabel62.setText("Süre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel10.add(jLabel62, gridBagConstraints);

        jLabel63.setText("Birim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel10.add(jLabel63, gridBagConstraints);

        jLabel64.setText("Görev");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel10.add(jLabel64, gridBagConstraints);

        jLabel65.setText("Görev Şekli");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        jPanel10.add(jLabel65, gridBagConstraints);

        jTextField53.setEditable(false);
        jTextField53.setColumns(20);
        jPanel10.add(jTextField53, new java.awt.GridBagConstraints());

        jTextField54.setEditable(false);
        jTextField54.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel10.add(jTextField54, gridBagConstraints);

        jTextField55.setEditable(false);
        jTextField55.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel10.add(jTextField55, gridBagConstraints);

        jTextField56.setEditable(false);
        jTextField56.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel10.add(jTextField56, gridBagConstraints);

        jTextField57.setEditable(false);
        jTextField57.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel10.add(jTextField57, gridBagConstraints);

        jTextField58.setEditable(false);
        jTextField58.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel10.add(jTextField58, gridBagConstraints);

        jTextField59.setEditable(false);
        jTextField59.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        jPanel10.add(jTextField59, gridBagConstraints);

        jTextField60.setEditable(false);
        jTextField60.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        jPanel10.add(jTextField60, gridBagConstraints);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable4KeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 10;
        jPanel10.add(jScrollPane5, gridBagConstraints);

        kimlikPanel.addTab("Görevlendirme", jPanel10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kimlikPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kimlikPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
kt=em.find(KimlikTablo.class, sicil);
        kt.setAdres(jTextArea1.getText());
kt.setTelefon(jTextField2.getText());
try{
em.getTransaction().begin();
 em.merge(kt);
 em.getTransaction().commit();
}catch(Exception e){
    em.getTransaction().rollback();
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jTextField33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField33ActionPerformed

    private void jTextField34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField34ActionPerformed

    private void jTextField35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField35ActionPerformed

    private void jTextField36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField36ActionPerformed

    private void jTextField37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField37ActionPerformed

    private void jTextField38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38ActionPerformed

    private void jTextField39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField39ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
 

        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated
public void tabloGez(String num){
try{String sql="select * from malBildirim where id='"+num+"'";
ps=conn.prepareStatement(sql);
rs=ps.executeQuery();
if(rs.next()){

jTextField42.setText(rs.getString(1));
jTextField43.setText(rs.getString(2));
jComboBox1.setSelectedItem(rs.getString(3));
jTextField45.setText(rs.getString(4));

jTextField47.setText(rs.getString(6));
jTextField48.setText(rs.getString(7));
jTextField49.setText(rs.getString(8));
jDateChooser1.setDate(rs.getDate(5));
}
    
} catch(Exception e){}
finally{
    try{
        rs.close();
        ps.close();
    }catch(Exception e){}
}
    
}
    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
int Row=jTable3.getSelectedRow();
    String tableClick=(jTable3.getModel().getValueAt(Row, 0).toString());
        tabloGez(tableClick);
// TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyReleased
 if(evt.getKeyCode()==KeyEvent.VK_UP||evt.getKeyCode()==KeyEvent.VK_DOWN){
int Row=jTable3.getSelectedRow();
    String tableClick=(jTable3.getModel().getValueAt(Row, 0).toString());
       tabloGez(tableClick);
 }
    }//GEN-LAST:event_jTable3KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try{
        String sql="insert into malBildirim (sicil,cinsi,aciklama,edinmeTarih,deger,name,sirname) values(?,?,?,?,?,?,?)";
       ps=conn.prepareStatement(sql);
       ps.setString(1, jTextField43.getText());
       ps.setString(2, jComboBox1.getSelectedItem().toString());
       ps.setString(3, jTextField45.getText());
       ps.setString(4, ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText());
       ps.setString(5, jTextField47.getText());
       ps.setString(6, jTextField48.getText());
       ps.setString(7, jTextField49.getText());
       ps.executeUpdate();
    JOptionPane.showMessageDialog(null, "EKLENDİ");
malTablodoldur();
    temizle();
       }catch(Exception e){}
       finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
public void temizle(){
    jTextField42.setText("");
    jTextField45.setText("");
    jTextField47.setText("");
    jTextField48.setText("");
    jTextField49.setText("");
    jComboBox1.setSelectedItem("");
    jDateChooser1.cleanup();
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       temizle();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 int p=JOptionPane.showConfirmDialog(null,"Silmek istediğinizden emin misiniz?","SİL",JOptionPane.YES_NO_OPTION);
        if (p==0){
        String sql="delete from malBildirim where id=?";
try{
    ps=conn.prepareStatement(sql);
    ps.setString(1, jTextField42.getText());
    ps.execute();
    JOptionPane.showMessageDialog(null, "SİLİNDİ");
}catch(Exception e){
    
}finally{        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
malTablodoldur();
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 String value1=jTextField42.getText();
 String value2=jTextField43.getText();
 String value3=jComboBox1.getSelectedItem().toString();
 String value4=jTextField45.getText();
 String value5=((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
 String value6=jTextField47.getText();
 String value7=jTextField48.getText();
 String value8=jTextField49.getText();
 String sql="update malBildirim set id='"+value1+"', sicil='"+value2+
        "', cinsi='"+value3+"', aciklama='"+value4+"' ,edinmeTarih=?, deger=?,name=?,sirname=? where id='"+value1+"'";
        try{
        ps=conn.prepareStatement(sql);
        ps.setString(1, value5);
        ps.setString(2, value6);
        ps.setString(3, value7);
        ps.setString(4, value8);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "GÜNCELLENDİ");
        temizle();
        }catch(Exception e){}finally{        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
malTablodoldur();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
int Row=jTable2.getSelectedRow();
 String tableClick=(jTable2.getModel().getValueAt(Row, 0).toString());
       tabloGez2(tableClick); 
// TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
 if(evt.getKeyCode()==KeyEvent.VK_UP||evt.getKeyCode()==KeyEvent.VK_DOWN){
int Row=jTable2.getSelectedRow();
    String tableClick=(jTable2.getModel().getValueAt(Row, 0).toString());
tabloGez2(tableClick); 
 } // TODO add your handling code here:
    }//GEN-LAST:event_jTable2KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 temizle2();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
if(kimlik_format()){
        String sql="insert into yakinTablo (name,sirname,tcKimlik,birthdate,yakinlik,sicil) values (?,?,?,?,?,?) ";
try{    
 ps=conn.prepareStatement(sql);
 ps.setString(1, jTextField46.getText());
 ps.setString(2, jTextField50.getText());
 ps.setString(3, jTextField51.getText());
 ps.setString(4, ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText());
 ps.setString(5, jComboBox2.getSelectedItem().toString());
 ps.setString(6, jTextField10.getText());
 ps.execute();
 JOptionPane.showMessageDialog(null,"EKLENDİ");
 doldurYakinAile();
 temizle2();
}catch(Exception e){}
finally{
    try{
     ps.close();
     rs.close();
    }catch(Exception e){}
}}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
try{ String yakinlik=jComboBox2.getSelectedItem().toString();
     String isim=jTextField46.getText();
     String soyisim=jTextField50.getText();
     String kimlik=jTextField51.getText();
     String tarih=((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText();
     
     String sql="update yakinTablo set yakinlik='"+yakinlik+"', name='"+isim+"', sirname='"+soyisim+"', tcKimlik='"+kimlik+"', birthdate='"+tarih+"' where sicil='"+sicil+"'";
     ps=conn.prepareStatement(sql);
     ps.execute();
      JOptionPane.showMessageDialog(null, "GÜNCELLENDİ");
}catch(Exception e){}
finally{
    try{
        rs.close();
        ps.close();
    }catch(Exception e){}
}
doldurYakinAile();
temizle2();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
 int p=JOptionPane.showConfirmDialog(null,"Silmek istediğinizden emin misiniz?","SİL",JOptionPane.YES_NO_OPTION);
        if (p==0){
        String sql="delete from yakinTablo where id=?";
try{
    ps=conn.prepareStatement(sql);
    ps.setString(1, jTextField44.getText());
    ps.execute();
    JOptionPane.showMessageDialog(null, "SİLİNDİ");
}catch(Exception e){
    
}finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){}
}doldurYakinAile();
temizle2();
        }
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
 int row=jTable4.getSelectedRow();
 int tableClick=Integer.parseInt((jTable1.getModel().getValueAt(row, 0)));
        tabloGez3(tableClick);
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyReleased
 if(evt.getKeyCode()==KeyEvent.VK_UP||evt.getKeyCode()==KeyEvent.VK_DOWN){
int row=jTable4.getSelectedRow();
 int tableClick=Integer.parseInt((jTable1.getModel().getValueAt(row, 0)));
        tabloGez3(tableClick);}        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4KeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
String k=JOptionPane.showInputDialog(null,"yeni Şifrenizi giriniz:");
gt=em.find(GirisTablo.class, sicil);
gt.setSifre(k);
try{
em.getTransaction().begin();
 em.merge(gt);
 em.getTransaction().commit();
 JOptionPane.showMessageDialog(null, "Şifreniz Başarılı bir şekilde değiştirildi");
 
}catch(Exception e){
    em.getTransaction().rollback();
}
jTextField41.setText(gt.getSifre());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField47KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField47KeyTyped
         char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)))
        {
            evt.consume();
            getToolkit().beep();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField47KeyTyped
public void tabloGez2(String num){
    try{
        String sql="select * from yakinTablo where id=?";
    ps=conn.prepareStatement(sql);
    ps.setString(1, num);
rs=ps.executeQuery();
if(rs.next()){
jComboBox2.setSelectedItem(rs.getString(6));
jTextField44.setText(rs.getString(1));
jTextField46.setText(rs.getString(2));
jTextField50.setText(rs.getString(3));
jTextField51.setText(rs.getString(4));
jDateChooser2.setDate(rs.getDate(5));
}}catch(Exception e){}
    finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
} 
public void temizle2(){
    jTextField46.setText("");
    jTextField50.setText("");
    jTextField51.setText("");
    jComboBox2.setSelectedItem("");
    jDateChooser2.cleanup();
}   
public void tabloGez3(int num){
        try{
        String sql="select * from job where id=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1, num);
rs=ps.executeQuery();
if(rs.next()){
jTextField53.setText(rs.getString(1));
jTextField54.setText(rs.getString(2));
jTextField55.setText(rs.getString(3));
jTextField56.setText(rs.getString(4));
jTextField57.setText(rs.getString(5));
jTextField58.setText(rs.getString(6));
jTextField59.setText(rs.getString(7));
jTextField60.setText(rs.getString(8));
}
        }catch(Exception e){}
    finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
}
private boolean kimlik_format(){
    Pattern kimlikPat=Pattern.compile(kimlik_Pattern);
    Matcher regexMatcher=kimlikPat.matcher(jTextField51.getText());
    if(!regexMatcher.matches()){
        jTextField51.setBackground(Color.red);
        JOptionPane.showMessageDialog(null, "TC Kimlik numarası 11 haneli rakamlardan oluşmalı");
    return false;
    }else return true;
}
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(kullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kullaniciEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imajLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTabbedPane kimlikPanel;
    private javax.persistence.EntityManager personelOtamasyonPUEntityManager;
    private java.util.List<entities.YakinTablo> yakinTabloList;
    private javax.persistence.Query yakinTabloQuery;
    private java.util.List<entities.Yakinaile> yakinaileList;
    private java.util.List<entities.Yakinaile> yakinaileList1;
    private java.util.List<entities.Yakinaile> yakinaileList2;
    private javax.persistence.Query yakinaileQuery;
    private javax.persistence.Query yakinaileQuery1;
    private javax.persistence.Query yakinaileQuery2;
    // End of variables declaration//GEN-END:variables
}
