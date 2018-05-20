/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.toedter.calendar.JDateChooser;
import controller.javaConnect;
import diu.swe.habib.JPanelSlider.JPanelSlider;
import entities.AtamaTablo;
import entities.GirisTablo;
import entities.Izindurum;
import entities.KimlikTablo;
import entities.Malihaklar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.PopupActionListener;

/**
 *
 * @author Ruhi ÇELİK
 */
public class kisiGuncelle extends javax.swing.JFrame {
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
    ImageIcon ic=null; 
     private ImageIcon format=null;
  private String path=null;
   byte [] personimage=null;
    String kimlik_Pattern="^[0-9]{11}$";
   String email_Pattern="^[a-zA-Z0-9]{1-20}@[a-zA-Z]{2-3}$";
   String k;
   public kisiGuncelle() {
        initComponents();
     PopupActionListener pal=new PopupActionListener(popupMenu);
kesMenu.addActionListener(pal);
yapistirMenu.addActionListener(pal);
kopyalaMenu.addActionListener(pal);
        emf=javax.persistence.Persistence.createEntityManagerFactory("personelOtamasyonPU");
em=emf.createEntityManager();
kt=new KimlikTablo();
at=new AtamaTablo();
mh=new Malihaklar();
id=new Izindurum();
gt=new GirisTablo();
conn=javaConnect.ConnectDb();
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        sicil=javaConnect.sicil;
        doldur(sicil);
    }

    private void doldur(int sicil){
doldurKimlik(sicil);
doldurAtama(sicil);
doldurMali(sicil);
doldurIzin(sicil);
doldurGiris(sicil);
    }
    private void doldurKimlik(int sicil){     
    try{
    TypedQuery<KimlikTablo> sorgu=em.createNamedQuery("KimlikTablo.findBySicil",KimlikTablo.class).setParameter("sicil", sicil);
jTextField33.setText(sorgu.getSingleResult().getSicil().toString());
jTextField15.setText(sorgu.getSingleResult().getTelefon());
jTextField11.setText(sorgu.getSingleResult().getAdres());
jTextField12.setText(sorgu.getSingleResult().getCinsiyet());
jTextField16.setText(sorgu.getSingleResult().getMedenihal());
jTextField13.setText(sorgu.getSingleResult().getDogumtarihi());
jTextField14.setText(sorgu.getSingleResult().getDogumyeri());
jTextField1.setText(sorgu.getSingleResult().getTCKimlik());   
}catch(Exception e){
}    }
    private void doldurMali(int sicil){
    try{
    TypedQuery<Malihaklar> sorgu=em.createNamedQuery("Malihaklar.findBySicil",Malihaklar.class).setParameter("sicil", sicil);
    jTextField30.setText(sorgu.getSingleResult().getKademe().toString());
    jTextField21.setText(sorgu.getSingleResult().getDerece().toString());
    jTextField29.setText(sorgu.getSingleResult().getGosterge().toString());
    jTextField28.setText(sorgu.getSingleResult().getEkgosterge().toString());
    Boolean aile=sorgu.getSingleResult().getAileyardimi();
    if (aile) jTextField32.setText("Var");
    else jTextField33.setText("Yok");
    jTextField34.setText(sorgu.getSingleResult().getCocuk().toString());
    jTextField35.setText(sorgu.getSingleResult().getSendika());
    jTextField24.setText(sorgu.getSingleResult().getYabancidil());
    jTextField25.setText(sorgu.getSingleResult().getDilderece());
    jTextField27.setText(sorgu.getSingleResult().getMezuniyet());
    jTextField26.setText(sorgu.getSingleResult().getMezuniyetBolum());
    jTextField40.setText(sorgu.getSingleResult().getMaas());
}catch(Exception e){
}
}
public void doldurIzin(int sicil){
    try{
    TypedQuery<Izindurum> sorgu=em.createNamedQuery("Izindurum.findBySicil",Izindurum.class).setParameter("sicil", sicil);
    jTextField19.setText(sorgu.getSingleResult().getIzinhakedis().toString());
    
   
    jTextField17.setText(sorgu.getSingleResult().getKalanyillikizin().toString());
    String yol=sorgu.getSingleResult().getYolizni().toString();
    jTextField31.setText(yol);
       
    jTextField18.setText(sorgu.getSingleResult().getRapor().toString());
    jTextField20.setText(sorgu.getSingleResult().getMazeretizni().toString());
    jTextField36.setText(sorgu.getSingleResult().getOzursuz().toString());
           
}catch(Exception e){
}
}
public void doldurGiris(int sicil){
 try{
    TypedQuery<GirisTablo> sorgu=em.createNamedQuery("GirisTablo.findBySicil", GirisTablo.class).setParameter("sicil", sicil);
    jTextField37.setText(sorgu.getSingleResult().getKullaniciAdi());
    jTextField38.setText(sorgu.getSingleResult().getSifre());
      Icon resim=new ImageIcon(sorgu.getSingleResult().getImage());
      jLabel1.setIcon(resim);
      jTextField39.setText(sorgu.getSingleResult().getYetki());
}catch(Exception e){
}
}
    public void doldurAtama(int sicil){
    try{
    TypedQuery<AtamaTablo> sorgu=em.createNamedQuery("AtamaTablo.findBySicil",AtamaTablo.class).setParameter("sicil", sicil);
    jTextField22.setText(sorgu.getSingleResult().getName());
    jTextField10.setText(sorgu.getSingleResult().getSirname());
    jTextField3.setText(sorgu.getSingleResult().getAtamaTarihi());
    jTextField4.setText(sorgu.getSingleResult().getHizmetSinifi());
    jTextField6.setText(sorgu.getSingleResult().getBirim());
    jTextField7.setText(sorgu.getSingleResult().getUnvan());
    jTextField8.setText(sorgu.getSingleResult().getGorev());
    jTextField9.setText(sorgu.getSingleResult().getGorevsekli());
    jTextField2.setText(sorgu.getSingleResult().getAtamasekli());
    jTextField5.setText(sorgu.getSingleResult().getMemuriyetbaslama()); 
    jTextField23.setText(sorgu.getSingleResult().getLojmanYararlanmaSure().toString());
    }catch(Exception e){
        
    }
}
//    private boolean kimlik_format(){
//    Pattern kimlikPat=Pattern.compile(kimlik_Pattern);
//    Matcher regexMatcher=kimlikPat.matcher(jTextField51.getText());
//    if(!regexMatcher.matches()){
//        jTextField51.setBackground(Color.red);
//        JOptionPane.showMessageDialog(null, "TC Kimlik numarası 11 haneli rakamlardan oluşmalı");
//    return false;
//    }else return true;
//}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        popupMenu = new javax.swing.JPopupMenu();
        kesMenu = new javax.swing.JMenuItem();
        kopyalaMenu = new javax.swing.JMenuItem();
        yapistirMenu = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jTextField35 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jButton40 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jButton41 = new javax.swing.JButton();

        kesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/cut.png"))); // NOI18N
        kesMenu.setText("kes");
        popupMenu.add(kesMenu);

        kopyalaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/copying_and_distribution.png"))); // NOI18N
        kopyalaMenu.setText("kopyala");
        popupMenu.add(kopyalaMenu);

        yapistirMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/paste_plain.png"))); // NOI18N
        yapistirMenu.setText("yapıştır");
        popupMenu.add(yapistirMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setBackground(new java.awt.Color(255, 255, 153));

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton18.setText("Resmi değiştir");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel2.setText("Sicil");

        jLabel3.setText("Şifre");

        jLabel4.setText("Kullanıcı Adı");

        jLabel8.setText("Yetki");

        jTextField33.setEditable(false);
        jTextField33.setComponentPopupMenu(popupMenu);
        jTextField33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField33ActionPerformed(evt);
            }
        });

        jTextField37.setEditable(false);
        jTextField37.setComponentPopupMenu(popupMenu);

        jTextField38.setEditable(false);
        jTextField38.setComponentPopupMenu(popupMenu);
        jTextField38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField38ActionPerformed(evt);
            }
        });

        jTextField39.setEditable(false);
        jTextField39.setComponentPopupMenu(popupMenu);

        jButton27.setText("Değiştir");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton37.setText("Değiştir");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setText("Değiştir");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setText("Değiştir");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField39)
                    .addComponent(jTextField33)
                    .addComponent(jTextField38)
                    .addComponent(jTextField37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton18)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDesktopPane1)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton38))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton39))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel28.setText("İsim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel28, gridBagConstraints);

        jLabel27.setText("Soyisim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel27, gridBagConstraints);

        jLabel12.setText("Atama Tarihi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel12, gridBagConstraints);

        jLabel11.setText("Hizmet Sınıfı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel11, gridBagConstraints);

        jLabel37.setText("Memuriyete Basşlama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel37, gridBagConstraints);

        jLabel13.setText("Birim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel5.setText("Ünvan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Görevi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Görev Şekli");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel29.setText("Atama Şekli");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel29, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setColumns(10);
        jTextField1.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        jPanel2.add(jTextField1, gridBagConstraints);

        jTextField2.setEditable(false);
        jTextField2.setColumns(10);
        jTextField2.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        jPanel2.add(jTextField2, gridBagConstraints);

        jTextField3.setEditable(false);
        jTextField3.setColumns(10);
        jTextField3.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jTextField3, gridBagConstraints);

        jTextField4.setEditable(false);
        jTextField4.setColumns(10);
        jTextField4.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jTextField4, gridBagConstraints);

        jTextField5.setEditable(false);
        jTextField5.setColumns(10);
        jTextField5.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel2.add(jTextField5, gridBagConstraints);

        jTextField6.setEditable(false);
        jTextField6.setColumns(10);
        jTextField6.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel2.add(jTextField6, gridBagConstraints);

        jTextField7.setEditable(false);
        jTextField7.setColumns(10);
        jTextField7.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        jPanel2.add(jTextField7, gridBagConstraints);

        jTextField8.setEditable(false);
        jTextField8.setColumns(10);
        jTextField8.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        jPanel2.add(jTextField8, gridBagConstraints);

        jTextField9.setEditable(false);
        jTextField9.setColumns(10);
        jTextField9.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        jPanel2.add(jTextField9, gridBagConstraints);

        jTextField10.setEditable(false);
        jTextField10.setColumns(10);
        jTextField10.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jTextField10, gridBagConstraints);

        jButton1.setText("değiştir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jButton1, gridBagConstraints);

        jButton2.setText("değiştir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jButton2, gridBagConstraints);

        jButton3.setText("değiştir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jButton3, gridBagConstraints);

        jButton4.setText("değiştir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jButton4, gridBagConstraints);

        jButton5.setText("değiştir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        jPanel2.add(jButton5, gridBagConstraints);

        jButton6.setText("değiştir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        jPanel2.add(jButton6, gridBagConstraints);

        jButton7.setText("değiştir");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        jPanel2.add(jButton7, gridBagConstraints);

        jButton8.setText("değiştir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        jPanel2.add(jButton8, gridBagConstraints);

        jButton9.setText("değiştir");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        jPanel2.add(jButton9, gridBagConstraints);

        jButton10.setText("değiştir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        jPanel2.add(jButton10, gridBagConstraints);

        jLabel19.setText("Tc Kimlik");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel19, gridBagConstraints);

        jLabel14.setText("Adres");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel14, gridBagConstraints);

        jLabel15.setText("Cinsiyet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel15, gridBagConstraints);

        jLabel17.setText("Doğum Tarihi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel17, gridBagConstraints);

        jLabel16.setText("Doğum Yeri");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel16, gridBagConstraints);

        jLabel18.setText("Telefon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel18, gridBagConstraints);

        jLabel20.setText("Medeni Hal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel20, gridBagConstraints);

        jTextField11.setEditable(false);
        jTextField11.setColumns(10);
        jTextField11.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        jPanel2.add(jTextField11, gridBagConstraints);

        jTextField12.setEditable(false);
        jTextField12.setColumns(10);
        jTextField12.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        jPanel2.add(jTextField12, gridBagConstraints);

        jTextField13.setEditable(false);
        jTextField13.setColumns(10);
        jTextField13.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        jPanel2.add(jTextField13, gridBagConstraints);

        jTextField14.setEditable(false);
        jTextField14.setColumns(10);
        jTextField14.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        jPanel2.add(jTextField14, gridBagConstraints);

        jTextField15.setEditable(false);
        jTextField15.setColumns(10);
        jTextField15.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        jPanel2.add(jTextField15, gridBagConstraints);

        jTextField16.setEditable(false);
        jTextField16.setColumns(10);
        jTextField16.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 18;
        jPanel2.add(jTextField16, gridBagConstraints);

        jTextField17.setEditable(false);
        jTextField17.setColumns(10);
        jTextField17.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jTextField17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jSeparator1, gridBagConstraints);

        jButton11.setText("değiştir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        jPanel2.add(jButton11, gridBagConstraints);

        jButton12.setText("değiştir");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 14;
        jPanel2.add(jButton12, gridBagConstraints);

        jButton13.setText("değiştir");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 15;
        jPanel2.add(jButton13, gridBagConstraints);

        jButton14.setText("değiştir");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 17;
        jPanel2.add(jButton14, gridBagConstraints);

        jButton15.setText("değiştir");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 16;
        jPanel2.add(jButton15, gridBagConstraints);

        jButton16.setText("değiştir");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 18;
        jPanel2.add(jButton16, gridBagConstraints);

        jButton17.setText("değiştir");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        jPanel2.add(jButton17, gridBagConstraints);

        jLabel30.setText("izin Hakediş");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel30, gridBagConstraints);

        jLabel31.setText("Kalan Yıllık İzin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel31, gridBagConstraints);

        jLabel32.setText("Kullanılan Mazeret İzin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel32, gridBagConstraints);

        jLabel33.setText("Kullanılan Rapor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel33, gridBagConstraints);

        jLabel35.setText("Özürsüz işe gelmeme");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel35, gridBagConstraints);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel2.add(jSeparator2, gridBagConstraints);

        jTextField18.setEditable(false);
        jTextField18.setColumns(10);
        jTextField18.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jTextField18, gridBagConstraints);

        jTextField19.setEditable(false);
        jTextField19.setColumns(10);
        jTextField19.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jTextField19, gridBagConstraints);

        jTextField20.setEditable(false);
        jTextField20.setColumns(10);
        jTextField20.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jTextField20, gridBagConstraints);

        jTextField21.setEditable(false);
        jTextField21.setColumns(10);
        jTextField21.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        jPanel2.add(jTextField21, gridBagConstraints);

        jTextField22.setEditable(false);
        jTextField22.setColumns(10);
        jTextField22.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jTextField22, gridBagConstraints);

        jLabel47.setText("Derece");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel47, gridBagConstraints);

        jLabel48.setText("Kademe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel48, gridBagConstraints);

        jLabel49.setText("Gösterge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel49, gridBagConstraints);

        jLabel50.setText("Ek Gösterge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel50, gridBagConstraints);

        jLabel9.setText("Mezuniyet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setText("Bölüm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel10, gridBagConstraints);

        jLabel22.setText("Dil Derece");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel22, gridBagConstraints);

        jLabel23.setText("Yabancı Dil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel23, gridBagConstraints);

        jLabel24.setText("Sendika");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel24, gridBagConstraints);

        jLabel21.setText("Çocuk Sayısı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel21, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jSeparator3, gridBagConstraints);

        jTextField24.setEditable(false);
        jTextField24.setColumns(10);
        jTextField24.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        jPanel2.add(jTextField24, gridBagConstraints);

        jTextField25.setEditable(false);
        jTextField25.setColumns(10);
        jTextField25.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 13;
        jPanel2.add(jTextField25, gridBagConstraints);

        jTextField26.setEditable(false);
        jTextField26.setColumns(10);
        jTextField26.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        jPanel2.add(jTextField26, gridBagConstraints);

        jTextField27.setEditable(false);
        jTextField27.setColumns(10);
        jTextField27.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 11;
        jPanel2.add(jTextField27, gridBagConstraints);

        jTextField28.setEditable(false);
        jTextField28.setColumns(10);
        jTextField28.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        jPanel2.add(jTextField28, gridBagConstraints);

        jTextField29.setEditable(false);
        jTextField29.setColumns(10);
        jTextField29.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 9;
        jPanel2.add(jTextField29, gridBagConstraints);

        jTextField30.setEditable(false);
        jTextField30.setColumns(10);
        jTextField30.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        jPanel2.add(jTextField30, gridBagConstraints);

        jTextField31.setEditable(false);
        jTextField31.setColumns(10);
        jTextField31.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        jPanel2.add(jTextField31, gridBagConstraints);

        jTextField32.setEditable(false);
        jTextField32.setColumns(10);
        jTextField32.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        jPanel2.add(jTextField32, gridBagConstraints);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel2.add(jSeparator4, gridBagConstraints);

        jButton19.setText("değiştir");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jButton19, gridBagConstraints);

        jButton20.setText("değiştir");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jButton20, gridBagConstraints);

        jButton21.setText("değiştir");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jButton21, gridBagConstraints);

        jButton22.setText("değiştir");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        jPanel2.add(jButton22, gridBagConstraints);

        jButton23.setText("değiştir");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        jPanel2.add(jButton23, gridBagConstraints);

        jButton24.setText("değiştir");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        jPanel2.add(jButton24, gridBagConstraints);

        jButton25.setText("değiştir");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 18;
        jPanel2.add(jButton25, gridBagConstraints);

        jButton26.setText("değiştir");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jButton26, gridBagConstraints);

        jButton28.setText("değiştir");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 14;
        jPanel2.add(jButton28, gridBagConstraints);

        jButton29.setText("değiştir");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 13;
        jPanel2.add(jButton29, gridBagConstraints);

        jButton30.setText("değiştir");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 12;
        jPanel2.add(jButton30, gridBagConstraints);

        jButton31.setText("değiştir");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 11;
        jPanel2.add(jButton31, gridBagConstraints);

        jButton32.setText("değiştir");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 10;
        jPanel2.add(jButton32, gridBagConstraints);

        jButton33.setText("değiştir");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 9;
        jPanel2.add(jButton33, gridBagConstraints);

        jLabel25.setText("Aile Yardımı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel25, gridBagConstraints);

        jTextField34.setEditable(false);
        jTextField34.setColumns(10);
        jTextField34.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 17;
        jPanel2.add(jTextField34, gridBagConstraints);

        jButton34.setText("değiştir");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 17;
        jPanel2.add(jButton34, gridBagConstraints);

        jTextField35.setEditable(false);
        jTextField35.setColumns(10);
        jTextField35.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 15;
        jPanel2.add(jTextField35, gridBagConstraints);

        jButton35.setText("değiştir");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 15;
        jPanel2.add(jButton35, gridBagConstraints);

        jLabel34.setText("Yol izni kullandı");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel34, gridBagConstraints);

        jTextField36.setEditable(false);
        jTextField36.setColumns(10);
        jTextField36.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        jPanel2.add(jTextField36, gridBagConstraints);

        jButton36.setText("değiştir");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        jPanel2.add(jButton36, gridBagConstraints);

        jLabel26.setText("Maaş Tertibi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel26, gridBagConstraints);

        jTextField40.setEditable(false);
        jTextField40.setColumns(10);
        jTextField40.setComponentPopupMenu(popupMenu);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 16;
        jPanel2.add(jTextField40, gridBagConstraints);

        jButton40.setText("değiştir");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 16;
        jPanel2.add(jButton40, gridBagConstraints);

        jLabel36.setText("Lojmanda Faydalanma");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel36, gridBagConstraints);

        jTextField23.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jTextField23, gridBagConstraints);

        jButton41.setText("değiştir");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        jPanel2.add(jButton41, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField33ActionPerformed

    private void jTextField38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38ActionPerformed
private void degistir(Object t){
    try{
em.getTransaction().begin();
 em.merge(t);
 em.getTransaction().commit();
}catch(Exception e){
    em.getTransaction().rollback();
}doldur(sicil);
}
    
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
at=em.find(AtamaTablo.class, sicil);
at.setName(name);
        degistir(at);      
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
 at=em.find(AtamaTablo.class, sicil);  
 at.setSirname(name);
        degistir(at);       // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
at=em.find(AtamaTablo.class, sicil); 
        
     JFrame p=new JFrame();
     p.setSize(300, 100);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JDateChooser chooser=new JDateChooser();
        chooser.setDateFormatString("dd.MM.yyyy");
        JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         at=em.find(AtamaTablo.class, sicil); 
        at.setAtamaTarihi(((JTextField)chooser.getDateEditor().getUiComponent()).getText());
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(chooser,gbc);
        gbc.gridx=1;
        
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from hizmetsinifi";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     at=em.find(AtamaTablo.class, sicil); 
        at.setHizmetSinifi(k);
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

 
        

// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from birim";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     at=em.find(AtamaTablo.class, sicil); 
        at.setBirim(k);
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 at=em.find(AtamaTablo.class, sicil); 
        
     JFrame p=new JFrame();
     p.setSize(300, 100);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JDateChooser chooser=new JDateChooser();
        chooser.setDateFormatString("dd.MM.yyyy");
        JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
         at=em.find(AtamaTablo.class, sicil); 
        at.setMemuriyetbaslama(((JTextField)chooser.getDateEditor().getUiComponent()).getText());
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(chooser,gbc);
        gbc.gridx=1;
        
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from unvan";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     at=em.find(AtamaTablo.class, sicil); 
        at.setUnvan(k);
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from gorev";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     at=em.find(AtamaTablo.class, sicil); 
        at.setGorev(k);
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from gorevsekli";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     at=em.find(AtamaTablo.class, sicil); 
        at.setGorevsekli(k);
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from atamasekli";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     at=em.find(AtamaTablo.class, sicil); 
        at.setAtamasekli(k);
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
mh=em.find(Malihaklar.class, sicil);
mh.setDerece(Integer.parseInt(name));
        degistir(mh);      

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
 String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
mh=em.find(Malihaklar.class, sicil);
mh.setKademe(Integer.parseInt(name));
        degistir(mh);  
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
 String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
mh=em.find(Malihaklar.class, sicil);
mh.setGosterge(Integer.parseInt(name));
        degistir(mh);  
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
 String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
mh=em.find(Malihaklar.class, sicil);
mh.setEkgosterge(Integer.parseInt(name));
        degistir(mh);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String[] liste={"İlkokul","Ortaokul","Lise ve Dengi", "Önlisans", "Lisans", "Yüksek Lisans"};
  for (int i=0;i<liste.length;i++){
     kombo.addItem(liste[i]);
  }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     mh=em.find(Malihaklar.class, sicil); 
        mh.setMezuniyet(k);
        degistir(mh);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
       p.setLocationRelativeTo(null);
        p.setVisible(true);
 
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
 String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
mh=em.find(Malihaklar.class, sicil);
mh.setMezuniyetBolum(name);
        degistir(mh);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String[] liste={"Yok", "A", "B", "C", "D", "E"};
  for (int i=0;i<liste.length;i++){
     kombo.addItem(liste[i]);
  }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     mh=em.find(Malihaklar.class, sicil); 
        mh.setDilderece(k);
        degistir(mh);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
     
        
   
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from yabancidil";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     mh=em.find(Malihaklar.class, sicil); 
        mh.setYabancidil(k);
        degistir(mh);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String sql="select * from sendika";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(2);
             kombo.addItem(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     mh=em.find(Malihaklar.class, sicil); 
        mh.setSendika(k);
        degistir(mh);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
mh=em.find(Malihaklar.class, sicil);
mh.setCocuk(Integer.parseInt(name));
        degistir(mh);      

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String[] liste={"Yok", "Var"};
  for (int i=0;i<liste.length;i++){
     kombo.addItem(liste[i]);
  }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
            Boolean d;
            if (k.equalsIgnoreCase("var"))d=true;
            else d=false;
             mh=em.find(Malihaklar.class, sicil); 
        mh.setAileyardimi(d);
        degistir(mh);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
       String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
id=em.find(Izindurum.class, sicil);
id.setKalanyillikizin(Integer.parseInt(name));
        degistir(id);      
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
id=em.find(Izindurum.class, sicil);
id.setRapor(Integer.parseInt(name));
        degistir(id); 
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     kombo.addItem(20);
     kombo.addItem(30);
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
             id=em.find(Izindurum.class, sicil); 
        id.setIzinhakedis(Integer.parseInt(kombo.getSelectedItem().toString()));
        degistir(id);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
       
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
id=em.find(Izindurum.class, sicil);
id.setMazeretizni(Integer.parseInt(name));
        degistir(id); 

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
id=em.find(Izindurum.class, sicil);
id.setOzursuz(Integer.parseInt(name));
        degistir(id); 

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String[] liste={"kullanmadı","2 Gün Kullandı","4 gün kullandı"};
  for (int i=0;i<liste.length;i++){
     kombo.addItem(liste[i]);
  }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
            int d;
            if (kombo.getSelectedIndex()==0)d=0;
            else if (kombo.getSelectedIndex()==1)d=2;
            else d=4;
             id=em.find(Izindurum.class, sicil); 
        id.setYolizni(d);
        degistir(id);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
kt=em.find(KimlikTablo.class, sicil);
kt.setAdres(name);
        degistir(kt);  
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
kt=em.find(KimlikTablo.class, sicil);
kt.setDogumyeri(name);
        degistir(kt);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
String name=JOptionPane.showInputDialog(null,"Değiştirmek istediğiniz yeni degeri giriniz:");
kt=em.find(KimlikTablo.class, sicil);
kt.setTelefon(name);
        degistir(kt);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
 kt=em.find(KimlikTablo.class, sicil); 
        
     JFrame p=new JFrame();
     p.setSize(300, 100);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JDateChooser chooser=new JDateChooser();
        chooser.setDateFormatString("dd.MM.yyyy");
        JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
         
        kt.setDogumtarihi(((JTextField)chooser.getDateEditor().getUiComponent()).getText());
        degistir(kt);
        p.dispose();
         }
     });
        
        p.add(chooser,gbc);
        gbc.gridx=1;
        
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
        
 
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       JFrame p=new JFrame();
     p.setSize(400, 100);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
       JTextField field=new JTextField();
       field.setColumns(20);
       p.add(field,gbc);
       gbc.gridx=1;
       JButton b1=new JButton("Değiştir");
       p.add(b1,gbc);
       p.setLocationRelativeTo(null);
       p.setVisible(true);
       b1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { 
           Pattern kimlikPat=Pattern.compile(kimlik_Pattern);
           Matcher regexMatcher=kimlikPat.matcher(field.getText());
           if(!regexMatcher.matches()){
        field.setBackground(Color.red);
        JOptionPane.showMessageDialog(null, "TC Kimlik numarası 11 haneli rakamlardan oluşmalı");
           }else
    kt.setTCKimlik(field.getText());
               degistir(kt);
           p.dispose();
           }
       });
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String[] liste={"Erkek","Bayan"};
  for (int i=0;i<liste.length;i++){
     kombo.addItem(liste[i]);
  }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     kt=em.find(KimlikTablo.class, sicil); 
        kt.setCinsiyet(k);
        degistir(kt);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
      
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String[] liste={"Bekar", "evli", "boşanmış"};
  for (int i=0;i<liste.length;i++){
     kombo.addItem(liste[i]);
  }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     kt=em.find(KimlikTablo.class, sicil); 
        kt.setMedenihal(k);
        degistir(kt);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
             
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
JFrame jf=new JFrame();
jf.setSize(400, 100);
jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
JButton b1=new JButton("Dosyadan Fotoğraf Yükle");
JButton b2=new JButton("Kameradan Fotoğraf Yükle");
GridBagLayout gbl=new GridBagLayout();
jf.setLayout(gbl);
GridBagConstraints gbc=new GridBagConstraints();
gbc.gridx=0;
gbc.gridy=0;
jf.add(b1,gbc);
gbc.gridx=5;
jf.add(b2,gbc);
jf.setVisible(true);
b1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
         but4();
 jf.dispose();
    }
});
b2.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
  kameraPencere();  
 jf.dispose();
    }
});        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed
private void kameraPencere(){
    JFrame fr=new JFrame();
    fr.setSize(600,300);
    fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    BorderLayout bl=new BorderLayout();
    fr.setLayout(bl); 
    
    JDesktopPane pane=new JDesktopPane();
    pane.setSize(350, 220);
    Webcam webcam=Webcam.getDefault();             
 webcam.setViewSize(WebcamResolution.VGA.getSize());
 WebcamPanel wp=new WebcamPanel(webcam);
 wp.setFPSDisplayed(true);
 wp.setDisplayDebugInfo(true);
 wp.setImageSizeDisplayed(true);
 wp.setMirrored(true);
 wp.setSize(pane.getSize());
 wp.setVisible(true);
pane.add(wp);
pane.setVisible(true);
 fr.add(pane);
 
     JButton b5=new JButton("Kaydet");
 b5.setSize(30, 15);
 
     fr.add(b5,BorderLayout.SOUTH);
       
    b5.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        kameraKaydet();
        fr.dispose();
        resimKaydet();
        }
    });
    fr.setLocationRelativeTo(null);
    fr.setVisible(true);
}
private void kameraKaydet(){
    try {
        
            Webcam webcam=Webcam.getDefault();
            BufferedImage image=webcam.getImage();
            ImageIO.write(resizeImage(image, 10), "PNG", new File("test.png"));
            webcam.close();
            ic = new ImageIcon("test.png");
            jLabel1.setIcon(ic);
            File images=new File("test.png");
            FileInputStream fis=new FileInputStream(images);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            byte [] buf=new byte[1024];
            for (int readNum;(readNum=fis.read(buf))!=-1; )
            {
                baos.write(buf,0,readNum);
            }
            personimage =baos.toByteArray();
           } catch (IOException ex) {
            Logger.getLogger(eklemeSihirbazı.class.getName()).log(Level.SEVERE, null, ex);
        }

}
private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
    int IMG_WIDTH = 120;
    int IMG_CLAHEIGHT = 120;
    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_CLAHEIGHT,
        type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_CLAHEIGHT, null);
    g.dispose();
    return resizedImage;
  }   
private void but4(){
    JFrame jd=new JFrame();
jd.setSize(800, 100);
jd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
GridBagLayout gbl=new GridBagLayout();
jd.setLayout(gbl);
JTextField tf=new JTextField(30);
GridBagConstraints gbc=new GridBagConstraints();
gbc.gridx=0;
gbc.gridy=0;
jd.add(tf,gbc);
gbc.gridx=2;
JButton b3=new JButton("Resmin bulunduğu Klasörü gösterin");
JButton b4=new JButton("Kaydet");

jd.add(b3,gbc);
gbc.gridx=4;
jd.add(b4);
b4.setVisible(false);
jd.setVisible(true);
b3.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
     b4.setVisible(true);
        
     JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        path=f.getAbsolutePath();
        tf.setText(path);
        Icon fi=new ImageIcon(path);
        jLabel1.setIcon(fi);
at1();        
     }
 });
b4.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        resimKaydet();
        jd.dispose();
        }
    });
}
private void resimKaydet(){
    try{
          gt=em.find(GirisTablo.class, sicil);  
          gt.setImage(personimage);
           
        degistir(gt);
          JOptionPane.showMessageDialog(null, "resim güncellendi, Güncellenmiş resmi görmek için uygulamayı yeniden çalıştırmalısınız");
            
            dispose();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
}
private void at1(){
    try{
            File image=new File(path);
            FileInputStream fis=new FileInputStream(image);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            byte [] buf=new byte[1024];
            for (int readNum;(readNum=fis.read(buf))!=-1; )
            {
                baos.write(buf,0,readNum);
            }
            personimage =baos.toByteArray();
        }catch(IOException e){
            
        }

}
    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
String k=JOptionPane.showInputDialog(null,"Yeni Şifreyi giriniz:");
gt=em.find(GirisTablo.class, sicil);
gt.setSifre(k);
        degistir(gt);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
String k=JOptionPane.showInputDialog(null,"Yeni Kullanıcı adını giriniz:");
gt=em.find(GirisTablo.class, sicil);
gt.setKullaniciAdi(k);
        degistir(gt);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
JOptionPane.showMessageDialog(null, "Sicil numarası değiştirmek tehlikeli olacağından, kişiyi silip tüm bilgileri yeniden girmelisiniz");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
     String[] liste={"Kullanıcı", "Yönetici"};
  for (int i=0;i<liste.length;i++){
     kombo.addItem(liste[i]);
  }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     gt=em.find(GirisTablo.class, sicil); 
        gt.setYetki(k);
        degistir(gt);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JComboBox kombo=new JComboBox();
             kombo.addItem("Özel Bütçe");
             kombo.addItem("Döner Sermaye");
     
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String k=kombo.getSelectedItem().toString();
                     mh=em.find(Malihaklar.class, sicil); 
        mh.setMaas(k);
        degistir(mh);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
 JFrame p=new JFrame();
     p.setSize(300, 400);   
     GridBagLayout bl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
     p.setLayout(bl);
     JTextField kombo=new JTextField();
     String sql="select lojmanYararlanmaSure from atamaTablo";
     try{
         ps=conn.prepareStatement(sql);
         rs=ps.executeQuery();
         while(rs.next()){
             String deger=rs.getString(1);
             kombo.setText(deger);
         }
     }catch(Exception e){}
     finally{
         try{
          rs.close();
          ps.close();
         }catch(Exception e){}
     }
     JButton b1=new JButton("Değiştir");
        b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             int k=Integer.parseInt(kombo.getText());
                     at=em.find(AtamaTablo.class, sicil); 
        at.setLojmanYararlanmaSure(k);
        degistir(at);
        p.dispose();
         }
     });
        
        p.add(kombo,gbc);
        gbc.gridx=1;
        p.add(b1,gbc);
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
yoneticiEkrani.yenile();
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(kisiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kisiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kisiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kisiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kisiGuncelle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
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
    private javax.swing.JTextField jTextField25;
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
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JMenuItem kesMenu;
    private javax.swing.JMenuItem kopyalaMenu;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem yapistirMenu;
    // End of variables declaration//GEN-END:variables
}
