/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.DocumentException;
import controller.javaConnect;
import entities.Izindurum;
import entities.Izingiris;
import eu.hansolo.steelseries.tools.LcdColor;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.PopupActionListener;
import model.ekseleYaz;
import model.pdfYap;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ruhi ÇELİK
 */
public class izinEkran extends javax.swing.JFrame {
int yil=0;
EntityManager em=null;
    EntityManagerFactory emf=null;
int sicil=0; 
Izindurum id;
Izingiris ig;
    DateFormat df;
   Connection conn=null;
ResultSet rs=null;
PreparedStatement ps=null;
    DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
     private Icon format=null;
 byte [] personimage=null;
 int hakedis,kullanilan,kalan,rapor,mazeret,ozursuz=0;
    public izinEkran() {
        initComponents();
PopupActionListener pal=new PopupActionListener(popupMenu);
kesMenu.addActionListener(pal);
yapistirMenu.addActionListener(pal);
kopyalaMenu.addActionListener(pal);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    sicil=javaConnect.sicil;
        sicil_txtbox.setText(String.valueOf(sicil));
        conn=javaConnect.ConnectDb();
        emf=javax.persistence.Persistence.createEntityManagerFactory("personelOtamasyonPU");
        em=emf.createEntityManager();
   df=new SimpleDateFormat("dd.MM.yyyy");
        curDate();
id=new Izindurum();        
    ig=new Izingiris();
    doldur(sicil);
        updateTable();
        updateTable2();
        updateIzinDurum();
        fillCombo();
        fillCombo1();
 Date d=new Date();
 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    

    
    private void fillCombo1(){
    try{
        String sql="select * from izinsekli";
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        while (rs.next()){
            String name=rs.getString("deger");
            kombo_ekle.addItem(name);
        }
    }catch(Exception e){
        
    }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
    }
private void TabloGez() {
        int Row=jTable2.getSelectedRow();
    String tableClick=(jTable2.getModel().getValueAt(Row, 0).toString());
        
    try{   
    String sql="select * from izingiris where sira=?";
    ps=conn.prepareStatement(sql);
    ps.setString(1,tableClick);
    rs=ps.executeQuery();
    if(rs.next()){
       siraLabel_txt.setText(rs.getString(1));
       sicil_txtbox.setText(rs.getString(2));
       kombo_ekle.getModel().setSelectedItem(rs.getString(3));
       adres_ekle.setText(rs.getString(6));
       kombo_il_ekle.getModel().setSelectedItem(rs.getString(7));
       sure_ekle.setText(rs.getString(9));
       int k=rs.getInt(8);
       sure_ekle.setText(String.valueOf(k));
       if (k==0)check_box_ekle.setSelected(false);
       else check_box_ekle.setSelected(true);
       date_begin_ekle.setDate(dateFormat.parse(rs.getString(4)));
       date_end_ekle.setDate(dateFormat.parse(rs.getString(5)));  
       seneTF.setText(rs.getString(10));
       
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
private void gunTopla(){
           Calendar c = Calendar.getInstance(); 
c.setTime(date_begin_ekle.getDate()); 
int ek=0;
String k=sure_ekle.getText();
if (k.equals(""))ek=0;
else
ek=Integer.parseInt(k);
c.add(Calendar.DATE, ek);
date_end_ekle.setDate(c.getTime());
    }
    private void fillCombo(){
    String sql="SELECT isim FROM ilTablo";
    try{
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        while (rs.next()){
            String name=rs.getString(1);
            kombo_il_ekle.addItem(name);
        }
    }catch(Exception e){}
    finally{
    try{
    rs.close();
    ps.close();
    }catch(Exception e){}
    
    }
    }
private void fillLCD(){
    hakedisLCD.setLcdValue(hakedis);
    ozursuzLCD.setLcdValue(ozursuz);
    KalanLCD.setLcdValue(kalan);
    kullanilanLCD.setLcdValue(kullanilan);
    raporLCD.setLcdValue(rapor);
    mazeretLCD.setLcdValue(mazeret);
    radial2.setMaxValue(hakedis);
    radial2.setValue(kalan);
    if (kalan<5){
        radial2.setGlowVisible(true);
    }if (kalan<3)
        radial2.setGlowPulsating(true);
    if (kalan<2)
    { radial2.setLcdColor(LcdColor.RED_LCD);
    radial2.setLedBlinking(true);}
    int yuzde=kalan*100/hakedis;
    panelBasla(yuzde);
    
}
public void panelBasla(int yuzde){
                new Thread(new Runnable()  {
    @Override
    public void run(){
        for(int num=1;num <= yuzde;num++){
            try{
                panel1.guncelle(num);
                panel1.repaint();
                jProgressBar1.setValue(num);
    
                Thread.sleep(50);
            }catch(InterruptedException e){
                
            }
        }
    }
}).start();

    }
    public void updateTable2(){
    try{
        String sql="Select * from izindurum where sicil=? ";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,sicil);
        rs=ps.executeQuery();
    //    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    if (rs.next()){
        hakedis=rs.getInt(2);
        kullanilan=rs.getInt(4);
        kalan=rs.getInt(5);
        rapor=rs.getInt(7);
        mazeret=rs.getInt(8);
        ozursuz=rs.getInt(9);
    }
    fillLCD();
    
    }   catch(Exception e){
        
    }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
}
   public void updateTable(){
    try{
        
        String sql1="Select * from izingiris where sicil=? and senesi=? ";
        String sql2="Select * from izingiris where sicil=? ";
        if (kombotab.getSelectedIndex()==0)
        {ps=conn.prepareStatement(sql1);
        ps.setInt(1,sicil);
        ps.setInt(2,yil);
        }else{
            ps=conn.prepareStatement(sql2);
        ps.setInt(1,sicil);
        }
        rs=ps.executeQuery();
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        curDate();
        
    }   catch(Exception e){
        
    }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
}
    private void curDate(){
    Calendar cal=new GregorianCalendar();
    int month=cal.get(Calendar.MONTH);
    int year=cal.get(Calendar.YEAR);
    int day=cal.get(Calendar.DAY_OF_MONTH);
    yil=year;
      int hour=cal.get(Calendar.HOUR);
    int second=cal.get(Calendar.SECOND);
    int minute=cal.get(Calendar.MINUTE);
    
}
public void doldur(int sic){
    String sql="select a.name, a.sirname, a.sicil, a.unvan, k.TCKimlik, k.telefon, m.derece, m.kademe, i.kalanyillikizin,"
            + " m.maas, g.image from atamaTablo a,KimlikTablo k, malihaklar m, izindurum i, girisTablo g where a.sicil=k.sicil and k.sicil=m.sicil and "
            + "m.sicil=i.sicil and i.sicil=g.sicil and g.sicil=? ";
    
    try{ps=conn.prepareStatement(sql);
    ps.setInt(1, sic);
    rs=ps.executeQuery();
    if (rs.next()){
        isim_TF.setText(rs.getString(1));
        isim_txt.setText(rs.getString(1));
        Soyisim_TF.setText(rs.getString(2));
        sirname_txt.setText(rs.getString(2));
        Sicil_TF.setText(rs.getString(3));
        sicil_txtbox.setText(rs.getString(3));
        Unvan_TF.setText(rs.getString(4));
        TCkimlik_TF.setText(rs.getString(5));
        telefon_TF.setText(rs.getString(6));
        Derece_TF.setText(rs.getString(7));
        Kademe_TF.setText(rs.getString(8));
        KalanIzin_TF.setText(rs.getString(9));
        butce_TF.setText(rs.getString(10));
        byte [] imagedata=rs.getBytes(11);
    format=new ImageIcon(imagedata);
    imajLabel.setIcon(format);
    }
        
    }catch(Exception e){    }
finally{
    try{
        ps.close();
        rs.close();
    }catch(Exception e){    }
}
}    
 
 
//private void tablo(int num){
//    
//    try{
//        int tableClick=num;
//        sicil=tableClick; // seçili kişinin üzerinde işlem yapılabilmesi için
//TypedQuery<KimlikTablo> query =em.createNamedQuery("KimlikTablo.findBySicil", KimlikTablo.class).setParameter("sicil",tableClick );
//TypedQuery<Malihaklar> sor =em.createNamedQuery("Malihaklar.findBySicil", Malihaklar.class).setParameter("sicil",tableClick );
//TypedQuery<Izindurum> sorg =em.createNamedQuery("Izindurum.findBySicil", Izindurum.class).setParameter("sicil",tableClick );
//TypedQuery<AtamaTablo> sorgu=em.createNamedQuery("AtamaTablo.findBySicil",AtamaTablo.class).setParameter("sicil",tableClick);
//TypedQuery<GirisTablo> so=em.createNamedQuery("GirisTablo.findBySicil",GirisTablo.class).setParameter("sicil",tableClick);
//Icon resim=new ImageIcon(so.getSingleResult().getImage());
//jLabel5.setText(String.valueOf(sicil));
//imajLabel.setIcon(resim);
//String isim=sorgu.getSingleResult().getName();
//isim_TF.setText(isim);
//jLabel4.setText(isim);
//Soyisim_TF.setText(sorgu.getSingleResult().getSirname());
//jLabel21.setText(sorgu.getSingleResult().getSirname());
//Unvan_TF.setText(sorgu.getSingleResult().getUnvan());
//Sicil_TF.setText(sorgu.getSingleResult().getSicil().toString());
//TCkimlik_TF.setText(query.getSingleResult().getTCKimlik());
// telefon_TF.setText(query.getSingleResult().getTelefon());
// Derece_TF.setText(sor.getSingleResult().getDerece().toString());
// Kademe_TF.setText(sor.getSingleResult().getKademe().toString());
// KalanIzin_TF.setText(sorg.getSingleResult().getKalanyillikizin().toString());
//
//  
//    
//    } catch(Exception e){
//         }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        kesMenu = new javax.swing.JMenuItem();
        kopyalaMenu = new javax.swing.JMenuItem();
        yapistirMenu = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        hakedisLCD = new eu.hansolo.steelseries.gauges.DisplaySingle();
        KalanLCD = new eu.hansolo.steelseries.gauges.DisplaySingle();
        kullanilanLCD = new eu.hansolo.steelseries.gauges.DisplaySingle();
        mazeretLCD = new eu.hansolo.steelseries.gauges.DisplaySingle();
        displaySingle5 = new eu.hansolo.steelseries.gauges.DisplaySingle();
        raporLCD = new eu.hansolo.steelseries.gauges.DisplaySingle();
        ozursuzLCD = new eu.hansolo.steelseries.gauges.DisplaySingle();
        radial2 = new eu.hansolo.steelseries.gauges.Radial();
        jProgressBar1 = new javax.swing.JProgressBar();
        panel1 = new model.panel();
        jButton2 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        adres_ekle = new javax.swing.JTextArea();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        kombo_ekle = new javax.swing.JComboBox();
        date_begin_ekle = new com.toedter.calendar.JDateChooser();
        sure_ekle = new javax.swing.JTextField();
        date_end_ekle = new com.toedter.calendar.JDateChooser();
        jLabel49 = new javax.swing.JLabel();
        siraLabel_txt = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        seneTF = new javax.swing.JLabel();
        kombo_il_ekle = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        label_yol_ekle = new javax.swing.JTextField();
        check_box_ekle = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        isim_txt = new javax.swing.JLabel();
        sicil_txtbox = new javax.swing.JLabel();
        sirname_txt = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TCkimlik_TF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefon_TF = new javax.swing.JTextField();
        Kademe_TF = new javax.swing.JTextField();
        Soyisim_TF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Sicil_TF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        isim_TF = new javax.swing.JTextField();
        Unvan_TF = new javax.swing.JTextField();
        KalanIzin_TF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Derece_TF = new javax.swing.JTextField();
        butce_TF = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        imajLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kombotab = new javax.swing.JComboBox<>();

        kesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/cut.png"))); // NOI18N
        kesMenu.setText("kes");
        popupMenu.add(kesMenu);

        kopyalaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/copying_and_distribution.png"))); // NOI18N
        kopyalaMenu.setText("kopyala");
        popupMenu.add(kopyalaMenu);

        yapistirMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/paste_plain.png"))); // NOI18N
        yapistirMenu.setText("yapıştır");
        popupMenu.add(yapistirMenu);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("İzin Hakedis");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Kullanılan İzin");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Kalan İzin");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Mazeret İzin");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Rapor");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Ozursuz");

        hakedisLCD.setLcdDecimals(0);
        hakedisLCD.setLcdUnitString("GÜN");

        KalanLCD.setDigitalFont(true);
        KalanLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.GREEN_LCD);
        KalanLCD.setLcdDecimals(0);
        KalanLCD.setLcdUnitString("GÜN");

        kullanilanLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.BLACK_LCD);
        kullanilanLCD.setLcdDecimals(0);
        kullanilanLCD.setLcdUnitString("GÜN");

        mazeretLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.BLUE2_LCD);
        mazeretLCD.setLcdDecimals(0);
        mazeretLCD.setLcdUnitString("GÜN");

        raporLCD.setDigitalFont(true);
        raporLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.YELLOW_LCD);
        raporLCD.setLcdDecimals(0);
        raporLCD.setLcdUnitString("GÜN");
        displaySingle5.add(raporLCD);
        raporLCD.setBounds(0, 0, 128, 48);

        ozursuzLCD.setDigitalFont(true);
        ozursuzLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.REDDARKRED_LCD);
        ozursuzLCD.setLcdDecimals(0);
        ozursuzLCD.setLcdUnitString("GÜN");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ozursuzLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displaySingle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mazeretLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(KalanLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hakedisLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kullanilanLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addComponent(hakedisLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kullanilanLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(KalanLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mazeretLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(displaySingle5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ozursuzLCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        radial2.setArea3DEffectVisible(true);
        radial2.setAreasVisible(true);
        radial2.setBackgroundColor(eu.hansolo.steelseries.tools.BackgroundColor.BEIGE);
        radial2.setCustomTickmarkLabelsEnabled(true);
        radial2.setDigitalFont(true);
        radial2.setGaugeType(eu.hansolo.steelseries.tools.GaugeType.TYPE2);
        radial2.setGlowing(true);
        radial2.setHighlightArea(true);
        radial2.setHighlightSection(true);
        radial2.setLcdUnitString("Gün");
        radial2.setLcdUnitStringVisible(true);
        radial2.setLedColor(eu.hansolo.steelseries.tools.LedColor.GREEN_LED);
        radial2.setMaxMeasuredValueVisible(true);
        radial2.setMaxValue(20.0);
        radial2.setTitleAndUnitFontEnabled(true);
        radial2.setTrackVisible(true);
        radial2.setUnitString("Gün");
        radial2.setUserLedBlinking(true);

        jProgressBar1.setMaximum(20);
        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(radial2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jDialog1Layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                            .addComponent(jButton2))
                        .addComponent(radial2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "İzin Giriş", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel40.setText("Gün");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel41.setText("Süre");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel42.setText("İzin Adresi");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel43.setText("İzin İli");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel46.setText("Başlangıç");

        adres_ekle.setColumns(20);
        adres_ekle.setRows(5);
        jScrollPane4.setViewportView(adres_ekle);

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setText("İzin Türü");

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel48.setText("Bitiş");

        kombo_ekle.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                kombo_eklePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        date_begin_ekle.setDateFormatString("dd.MM.yyyy");
        date_begin_ekle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                date_begin_ekleMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                date_begin_ekleMouseReleased(evt);
            }
        });
        date_begin_ekle.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                date_begin_ekleComponentShown(evt);
            }
        });
        date_begin_ekle.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                date_begin_ekleComponentAdded(evt);
            }
        });
        date_begin_ekle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date_begin_ekleKeyReleased(evt);
            }
        });
        date_begin_ekle.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                date_begin_ekleVetoableChange(evt);
            }
        });

        sure_ekle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sure_ekleKeyReleased(evt);
            }
        });

        date_end_ekle.setDateFormatString("dd.MM.yyyy");
        date_end_ekle.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                date_end_ekleComponentRemoved(evt);
            }
        });
        date_end_ekle.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                date_end_ekleHierarchyChanged(evt);
            }
        });
        date_end_ekle.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                date_end_ekleAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                date_end_ekleAncestorRemoved(evt);
            }
        });
        date_end_ekle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                date_end_ekleFocusLost(evt);
            }
        });
        date_end_ekle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                date_end_ekleMouseReleased(evt);
            }
        });
        date_end_ekle.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                date_end_ekleComponentHidden(evt);
            }
        });
        date_end_ekle.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                date_end_ekleİnputMethodTextChanged(evt);
            }
        });

        jLabel49.setText("Kayıt Sıra");

        siraLabel_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel51.setText("Senesi");

        seneTF.setText("      ");

        kombo_il_ekle.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                kombo_il_eklePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label_yol_ekle.setEditable(false);
        label_yol_ekle.setText("0");

        check_box_ekle.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        check_box_ekle.setText("yol izni istiyor");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(check_box_ekle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_yol_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_box_ekle)
                    .addComponent(label_yol_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton3.setBackground(new java.awt.Color(51, 255, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/accept.png"))); // NOI18N
        jButton3.setText("KAYDET");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel37.setText("Sicil");

        jLabel44.setText("isim ");

        isim_txt.setText(" ");
        isim_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 0), null, null));

        sicil_txtbox.setText(" ");
        sicil_txtbox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 0), null, null));

        sirname_txt.setText(" ");
        sirname_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 0), null, null));

        jLabel38.setText("Soyisim");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(sicil_txtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addComponent(isim_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(sirname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sicil_txtbox)
                    .addComponent(jLabel37))
                .addGap(3, 3, 3)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(isim_txt)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sirname_txt)
                    .addComponent(jLabel38))
                .addContainerGap())
        );

        jButton7.setText("Guncelle");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel47)
                                .addComponent(jLabel42)
                                .addComponent(jLabel43))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel41)
                                .addComponent(jLabel46)
                                .addComponent(jLabel48)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(date_end_ekle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(kombo_ekle, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(date_begin_ekle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kombo_il_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(sure_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel40)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel49)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(siraLabel_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel51)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(seneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kombo_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(date_begin_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sure_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel40)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(date_end_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel43)
                                .addComponent(kombo_il_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel49))
                            .addComponent(siraLabel_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buttons", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        jButton4.setText("Sil");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Güncelle");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/export_excel.png"))); // NOI18N
        jButton9.setText("Excele Gönder(2.)");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/export_excel.png"))); // NOI18N
        jButton10.setText("Excele Gönder(1.)");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/page_white_acrobat.png"))); // NOI18N
        jButton13.setText("PDF Aktar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/page_white_acrobat.png"))); // NOI18N
        jButton14.setText("PDF Aktar(2)");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton6.setText("Ayrıntılı Durum");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(23, 23, 23)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton14))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Seçili Kişi Bilgileri"));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("TC Kimlik");

        TCkimlik_TF.setEditable(false);
        TCkimlik_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ünvan");

        telefon_TF.setEditable(false);
        telefon_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Kademe_TF.setEditable(false);
        Kademe_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Soyisim_TF.setEditable(false);
        Soyisim_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Sicil");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Derece");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Soyisim");

        Sicil_TF.setEditable(false);
        Sicil_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Telefon");

        isim_TF.setEditable(false);
        isim_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Unvan_TF.setEditable(false);
        Unvan_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        KalanIzin_TF.setEditable(false);
        KalanIzin_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("İsim");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Kademe");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Kalan İzin");

        Derece_TF.setEditable(false);
        Derece_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        butce_TF.setEditable(false);
        butce_TF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Maaş Tertibi");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Soyisim_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(isim_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Sicil_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Unvan_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(TCkimlik_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(telefon_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Derece_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Kademe_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(KalanIzin_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(butce_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(isim_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(Soyisim_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(Sicil_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(Unvan_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(TCkimlik_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(telefon_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(Derece_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(Kademe_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(KalanIzin_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addComponent(butce_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 0));

        imajLabel.setBackground(new java.awt.Color(255, 204, 255));

        jDesktopPane2.setLayer(imajLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imajLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addComponent(imajLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("KULLANILAN İZİNLER");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("İZİN DURUM");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        kombotab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BU YIL", "TÜMÜ" }));
        kombotab.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                kombotabPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(548, 548, 548)
                .addComponent(kombotab, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 92, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(387, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(150, 150, 150))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kombotab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int p=JOptionPane.showConfirmDialog(null,"Silmek istediğinizden eminmisiniz","SİL",JOptionPane.YES_NO_OPTION);
        if (p==0){
            try{
                String sql="delete from izingiris where sira=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1, siraLabel_txt.getText());
                ps.execute();
                JOptionPane.showMessageDialog(null, "sİLİNDİ");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"tablodan silmek isteğiniz veriyi seçiniz"+e.toString());
            }finally{
                try{
                    rs.close();
                    ps.close();
                } catch(Exception e){

                }
            }
            updateTable();
            updateIzinDurum();
            doldur(sicil);
        }
    }//GEN-LAST:event_jButton4ActionPerformed
private void updateIzinDurum() {
            int idari = 0, ozursuz = 0, mazeret = 0, rapor = 0, kullanilanYizin = 0, kalanYizin=0,yolizni=0;
            boolean yol = false;
    try{ 
        String sql="SELECT izinsekli, SUM(sure), SUM(yolizni) FROM izingiris where sicil=? and senesi=? and izinsekli=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    ps.setInt(2,yil);
    ps.setString(3,"Yıllık İzin");
    rs=ps.executeQuery();
    if(rs.next()){
        kullanilanYizin=rs.getInt("SUM(sure)");
        yolizni=rs.getInt(3);
    }
     }catch(Exception e){
          
        }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
      try{
          String sql="SELECT izinsekli, SUM(sure) FROM izingiris where sicil=? and senesi=? and izinsekli=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    ps.setInt(2,yil);
    ps.setString(3,"Mazeret İzni");
    rs=ps.executeQuery();
    if(rs.next()){
        mazeret=rs.getInt("SUM(sure)");
    }   
      }   catch(Exception e){
          
      } finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }  
    try{
          String sql="SELECT izinsekli, SUM(sure) FROM izingiris where sicil=? and senesi=? and izinsekli=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    ps.setInt(2,yil);
    ps.setString(3,"idari izin");
    rs=ps.executeQuery();
    if(rs.next()){
        idari=rs.getInt("SUM(sure)");
    }   
      }   catch(Exception e){
          
      } finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    } 
    try{
          String sql="SELECT izinsekli, SUM(sure) FROM izingiris where sicil=? and senesi=? and izinsekli=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    ps.setInt(2,yil);
    ps.setString(3,"Sağlık Raporu");
    rs=ps.executeQuery();
    if(rs.next()){
        rapor=rs.getInt("SUM(sure)");
    }   
      }   catch(Exception e){
          
      }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }  
    try{
String sql="SELECT izinsekli, SUM(sure) FROM izingiris where sicil=? and senesi=? and izinsekli=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    ps.setInt(2,yil);
    ps.setString(3,"Özürsüz");
    rs=ps.executeQuery();
    if(rs.next()){
        ozursuz=rs.getInt("SUM(sure)");
    }   
      }   catch(Exception e){
          } finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
try{
    int hakedis = 0, devir=0;
    String sql="SELECT izinhakedis, devirizin, yolizni FROM izindurum where sicil=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1,sicil);
    rs=ps.executeQuery();
    
    if(rs.next()){
         hakedis=rs.getInt("izinhakedis");
         devir=rs.getInt("devirizin");
    }
    kalanYizin=hakedis+devir-kullanilanYizin;
    if(yolizni>0)yol=true;
    else yol=false;
}catch(Exception e){
    
}
  finally{
    try{
            rs.close();
            ps.close();
            }
catch(Exception e){
    
}
} 
String sql1="UPDATE izindurum set sicil=?, kullanılanizin=?, kalanyillikizin=?, rapor=?, mazeretizni=?, ozursuz=?, idariizin=?, yolizni=? where sicil=?";
    
    try{  
ps=conn.prepareStatement(sql1);  
ps.setInt(1, sicil);    
ps.setString(2,String.valueOf(kullanilanYizin));
ps.setString(3,String.valueOf(kalanYizin));
 ps.setString(4,String.valueOf(rapor));
 ps.setString(5,String.valueOf(mazeret));
 ps.setString(6,String.valueOf(ozursuz));
 ps.setString(7,String.valueOf(idari));
 ps.setBoolean(8,yol);
 
 ps.setInt(9, sicil);
 ps.execute();
 JOptionPane.showMessageDialog(null,"İzin Durum Güncellendi");
updateTable();
updateTable2();
    }catch(Exception e){
    
}   finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
    
}
private void guncelle(){
     try{
            String value1=siraLabel_txt.getText();
            String value2=sicil_txtbox.getText();
            String value3=kombo_ekle.getSelectedItem().toString();
            String value4=((JTextField)date_begin_ekle.getDateEditor().getUiComponent()).getText();
            String value5=((JTextField)date_end_ekle.getDateEditor().getUiComponent()).getText();
            String value6=adres_ekle.getText();
            String value7=kombo_il_ekle.getSelectedItem().toString();
            String value8=sure_ekle.getText();

            String sql="update izingiris set sira='"+value1+"', sicil='"+value2+
            "', izinsekli='"+value3+"', begin='"+value4+"',end='"+
            value5+"',izinadresi='"+value6+"', izinili='"+value7+"', sure='"+value8+
            "' where sira='"+value1+"'";
            ps=conn.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "GÜNCELLENDİ");

        }catch(Exception e){

        }finally{
            try{
                rs.close();
                ps.close();
            } catch(Exception e){

            }
        }temizle();
        updateTable();
        updateTable2();
        updateIzinDurum();
        doldur(sicil);
}

private void ekle(){
            muhtelifIzinKontrol();

        if (izinOnay){
            try{
                ig.setBegin(((JTextField) date_begin_ekle.getDateEditor().getUiComponent()).getText());
                ig.setEnd(((JTextField) date_end_ekle.getDateEditor().getUiComponent()).getText());
                ig.setIzinadresi(adres_ekle.getText());
                ig.setIzinili(kombo_il_ekle.getSelectedItem().toString());
                ig.setIzinsekli(kombo_ekle.getSelectedItem().toString());
                ig.setSenesi(yil);
                ig.setSicil(sicil);
                ig.setSure(Integer.parseInt(sure_ekle.getText()));
                ig.setYolizni(Integer.parseInt(label_yol_ekle.getText()));
                em.getTransaction().begin();
            em.persist(ig);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null,"İZİN GİRİŞİ KAYDEDİLDİ");

            }catch(Exception e){}
//            String sql="insert into izingiris (sicil, izinsekli,begin,end,izinadresi,izinili,sure,senesi) values (?,?,?,?,?,?,?,?)";
//            try{
//                ps=conn.prepareStatement(sql);
//                ps.setString(1,jLabel5.getText());
//                ps.setString(2,jComboBox1.getSelectedItem().toString());
//                ps.setString(3,((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText());
//                ps.setString(4,((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText());
//                ps.setString(5, jTextArea1.getText());
//                ps.setString(6, jComboBox2.getSelectedItem().toString());
//                ps.setString(7, jTextField1.getText());
//                ps.setString(8, jLabel19.getText());
//                ps.execute();
//                JOptionPane.showMessageDialog(null, "EKLENDİ");
//                updateTable();
//            }catch(Exception x){
//
//            }finally{
//                try{
//                    rs.close();
//                    ps.close();
//                } catch(Exception e){
//
//                }
//            }
        updateTable();
            updateIzinDurum();
            doldur(sicil);
            temizle();
        }else JOptionPane.showMessageDialog(null, "Tekrar kontrol edin");
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
jDialog2.setSize(700,500);
        jDialog2.setVisible(true);
temizle();
jButton3.setVisible(true);
jButton7.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
jDialog2.setSize(700,500);
        jDialog2.setVisible(true);
       jButton3.setVisible(false);
jButton7.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
private void temizle(){
    adres_ekle.setText("");
sure_ekle.setText("");
kombo_ekle.setSelectedIndex(0);
        curDate();
        date_begin_ekle.setDate(null);
        date_end_ekle.setDate(null);
}

    private void kombotabPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_kombotabPopupMenuWillBecomeInvisible
 updateTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_kombotabPopupMenuWillBecomeInvisible

    
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
 TabloGez();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
         if(evt.getKeyCode()==KeyEvent.VK_UP||evt.getKeyCode()==KeyEvent.VK_DOWN){

        TabloGez();
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            ekseleYaz.writeToExcel(jTable1, "izinDurum.xlsx");

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"izinDurum.xlsx");
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(lojmanEkran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            ekseleYaz.writeToExcel(jTable2, "izinDurum2.xlsx");

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"izinDurum2.xlsx");
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(lojmanEkran.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            pdfYap.pdfYapp(jTable1, "izindurum.pdf");
            
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"izindurum.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(izinEkran.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
     try {
            pdfYap.pdfYapp(jTable2, "izinliste.pdf");

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"izinliste.pdf");
     } catch (FileNotFoundException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(izinEkran.class.getName()).log(Level.SEVERE, null, ex);
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void kombo_eklePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_kombo_eklePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_kombo_eklePopupMenuWillBecomeInvisible

    private void date_begin_ekleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_begin_ekleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_date_begin_ekleMouseExited

    private void date_begin_ekleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_begin_ekleMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_begin_ekleMouseReleased

    private void date_begin_ekleComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_date_begin_ekleComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_date_begin_ekleComponentShown

    private void date_begin_ekleComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_date_begin_ekleComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_date_begin_ekleComponentAdded

    private void date_begin_ekleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_begin_ekleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_begin_ekleKeyReleased

    private void date_begin_ekleVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_date_begin_ekleVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_date_begin_ekleVetoableChange

    private void sure_ekleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sure_ekleKeyReleased
gunTopla();
        // TODO add your handling code here:
    }//GEN-LAST:event_sure_ekleKeyReleased

    private void date_end_ekleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_end_ekleMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleMouseReleased

    private void date_end_ekleComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_date_end_ekleComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleComponentHidden

    private void date_end_ekleComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_date_end_ekleComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleComponentRemoved

    private void date_end_ekleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_date_end_ekleFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleFocusLost

    private void date_end_ekleHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_date_end_ekleHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleHierarchyChanged

    private void date_end_ekleİnputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_date_end_ekleİnputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleİnputMethodTextChanged

    private void date_end_ekleAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_date_end_ekleAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleAncestorAdded

    private void date_end_ekleAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_date_end_ekleAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_date_end_ekleAncestorRemoved

    private void kombo_il_eklePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_kombo_il_eklePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_kombo_il_eklePopupMenuWillBecomeInvisible

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
ekle();
jDialog2.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
guncelle();
jDialog2.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
jDialog1.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
jDialog1.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(izinEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(izinEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(izinEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(izinEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new izinEkran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Derece_TF;
    private javax.swing.JTextField Kademe_TF;
    private javax.swing.JTextField KalanIzin_TF;
    private eu.hansolo.steelseries.gauges.DisplaySingle KalanLCD;
    private javax.swing.JTextField Sicil_TF;
    private javax.swing.JTextField Soyisim_TF;
    private javax.swing.JTextField TCkimlik_TF;
    private javax.swing.JTextField Unvan_TF;
    private javax.swing.JTextArea adres_ekle;
    private javax.swing.JTextField butce_TF;
    private javax.swing.JCheckBox check_box_ekle;
    private com.toedter.calendar.JDateChooser date_begin_ekle;
    private com.toedter.calendar.JDateChooser date_end_ekle;
    private eu.hansolo.steelseries.gauges.DisplaySingle displaySingle5;
    private eu.hansolo.steelseries.gauges.DisplaySingle hakedisLCD;
    private javax.swing.JLabel imajLabel;
    private javax.swing.JTextField isim_TF;
    private javax.swing.JLabel isim_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenuItem kesMenu;
    private javax.swing.JComboBox kombo_ekle;
    private javax.swing.JComboBox<String> kombo_il_ekle;
    private javax.swing.JComboBox<String> kombotab;
    private javax.swing.JMenuItem kopyalaMenu;
    private eu.hansolo.steelseries.gauges.DisplaySingle kullanilanLCD;
    private javax.swing.JTextField label_yol_ekle;
    private eu.hansolo.steelseries.gauges.DisplaySingle mazeretLCD;
    private eu.hansolo.steelseries.gauges.DisplaySingle ozursuzLCD;
    private model.panel panel1;
    private javax.swing.JPopupMenu popupMenu;
    private eu.hansolo.steelseries.gauges.Radial radial2;
    private eu.hansolo.steelseries.gauges.DisplaySingle raporLCD;
    private javax.swing.JLabel seneTF;
    private javax.swing.JLabel sicil_txtbox;
    private javax.swing.JLabel siraLabel_txt;
    private javax.swing.JLabel sirname_txt;
    private javax.swing.JTextField sure_ekle;
    private javax.swing.JTextField telefon_TF;
    private javax.swing.JMenuItem yapistirMenu;
    // End of variables declaration//GEN-END:variables
private void muhtelifIzinKontrol(){
        int maz=0, sen=0, ozursuz=0,  rapor=0 ;
        boolean yolizni=false;
        String sql="SELECT * FROM izindurum where sicil=?";
        try{
            ps=conn.prepareStatement(sql);
            ps.setString(1, jLabel5.getText());
            rs=ps.executeQuery();
            if (rs.next()){
              maz=rs.getInt("mazeretizni");
              sen=rs.getInt("kalanyillikizin");
              ozursuz=rs.getInt("ozursuz");
              rapor=rs.getInt("rapor");            
              yolizni=rs.getBoolean("yolizni");
            }
            
        }catch(Exception e){
            
        }   finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }}
        int sure=Integer.parseInt(sure_ekle.getText());
      int ek=0;
        switch(kombo_ekle.getSelectedItem().toString()){
            case "Yıllık İzin": if (sure>sen){
                JOptionPane.showMessageDialog(null,
                    "İstenilenIzinsuresi kalan hakkınızdan fazla");
                izinOnay=false;
            } else if (check_box_ekle.isSelected()&& yolizni==true){
              JOptionPane.showMessageDialog(null,
                    "Bir sene içinde bir kere yol izni kullanabilirsiniz");
                izinOnay=false;  
            } else if (check_box_ekle.isSelected()&& sure<10){
                JOptionPane.showMessageDialog(null,
                    "Yol izni için senelik izin 10 günden aşağı olamaz");
                izinOnay=false; 
            } else if (check_box_ekle.isSelected()&& kombo_il_ekle.getSelectedItem().toString().equalsIgnoreCase("ISPARTA")){
                JOptionPane.showMessageDialog(null,
                    "Yol izni için izin ili farklı bir il olmalı");
                izinOnay=false;
            } else if(check_box_ekle.isSelected() && yolizni==false && sure>9 && !kombo_il_ekle.getSelectedItem().toString().equalsIgnoreCase("ISPARTA"))
            {
                izinOnay=true;
                int mesafe=mesafe(kombo_il_ekle.getSelectedItem().toString());
                if (mesafe<700)
               ek=2;     
                else ek=4;
                sureEkle(ek);
                izinOnay=true;
                break;
            } else{
                izinOnay=true; break;}
            case "Mazeret İzni": if (sure>10-maz ){
              JOptionPane.showMessageDialog(null,"kalan mazeret izni hakkıınız isteğinizden az");
            izinOnay=false;
            }   else izinOnay=true; break;
            case "Sağlık Raporu": if (rapor+sure>7)
                JOptionPane.showMessageDialog(null,"Bu yıl için kullanılan rapor sayısı 7 günü geçti");
               izinOnay=true;break;
            case "Babalık İzni":  if (sure>10){
                izinOnay=false;            
                JOptionPane.showMessageDialog(null, "Babalık İzni en fazla 10 gün olabilir");
            }else izinOnay=true;break;
            case "Annelik İzni": if (sure >120){ izinOnay=false;            
                JOptionPane.showMessageDialog(null, "Annelik İzni en fazla 120 gün olabilir");
            }else izinOnay=true;break;
            case "Ölüm İzni": if (sure >7){ izinOnay=false;            
                JOptionPane.showMessageDialog(null, "Ölüm İzni en fazla 7 gün olabilir");
            }else izinOnay=true;break;
            case "Düğün İzni": if (sure >7){ izinOnay=false;            
                JOptionPane.showMessageDialog(null, "Düğün İzni en fazla 7 gün olabilir");
            }else izinOnay=true;break;
            case "Özürsüz": if (sure >=10)        
                JOptionPane.showMessageDialog(null, "Gerekli İşlem başlatılabilir");
            izinOnay=true;break;
            default: izinOnay=true;break;                
        }        
              }
    
boolean izinOnay=false;
private void sureEkle(int ek){
    sure_ekle.setText(String.valueOf(ek));
    gunTopla();
}
private int mesafe(String yer){
    String sql="select * from ilmesafe where iladi=?";
    int k=0;
    try{
        ps=conn.prepareStatement(sql);
        ps.setString(1, yer);
        rs=ps.executeQuery();
        if(rs.next())
        {
             k=rs.getInt("ISPARTA");
        }
    }catch(Exception e){
        
    }finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
    return k;
}
        

}
