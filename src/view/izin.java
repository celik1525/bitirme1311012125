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
public class izin extends javax.swing.JFrame {

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
 

    public izin() {
        initComponents();
PopupActionListener pal=new PopupActionListener(popupMenu);
kesMenu.addActionListener(pal);
yapistirMenu.addActionListener(pal);
kopyalaMenu.addActionListener(pal);
adresTB.setComponentPopupMenu(popupMenu);
        eklebut.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    sicil=javaConnect.sicil;
        jLabel5.setText(String.valueOf(sicil));
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
            izinturKombo.addItem(name);
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
       siraTF.setText(rs.getString(1));
       izinturKombo.getModel().setSelectedItem(rs.getString(3));
       adresTB.setText(rs.getString(6));
       ilKombo.getModel().setSelectedItem(rs.getString(7));
       sureTF.setText(rs.getString(9));
       int k=rs.getInt(8);
       yolizniTF.setText(String.valueOf(k));
       if (k==0)yolCB.setSelected(false);
       else yolCB.setSelected(true);
       beginDC.setDate(dateFormat.parse(rs.getString(4)));
       endDC.setDate(dateFormat.parse(rs.getString(5)));  
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
c.setTime(beginDC.getDate()); 
int ek=0;
String k=sureTF.getText();
if (k.equals(""))ek=0;
else
ek=Integer.parseInt(k);
c.add(Calendar.DATE, ek);
endDC.setDate(c.getTime());
    }
    private void fillCombo(){
    String sql="SELECT isim FROM ilTablo";
    try{
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        while (rs.next()){
            String name=rs.getString(1);
            ilKombo.addItem(name);
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
    public void updateTable2(){
    try{
        String sql="Select * from izindurum where sicil=? ";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,sicil);
        rs=ps.executeQuery();
       // jTable1.setModel(DbUtils.resultSetToTableModel(rs));
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
     seneTF.setText(String.valueOf(year));
     
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
        Soyisim_TF.setText(rs.getString(2));
        Sicil_TF.setText(rs.getString(3));
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        eklebut = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        izinturKombo = new javax.swing.JComboBox();
        seneTF = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        adresTB = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        sureTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        endDC = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        ilKombo = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        siraTF = new javax.swing.JLabel();
        beginDC = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        yolizniTF = new javax.swing.JTextField();
        yolCB = new javax.swing.JCheckBox();
        gucelleBut = new javax.swing.JButton();
        popupMenu = new javax.swing.JPopupMenu();
        kesMenu = new javax.swing.JMenuItem();
        kopyalaMenu = new javax.swing.JMenuItem();
        yapistirMenu = new javax.swing.JMenuItem();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        kombotab = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
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

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eklebut.setText("KAYDET");
        eklebut.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        eklebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eklebutActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(eklebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 210, 120));

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Alanları Doldurunuz"));

        izinturKombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                izinturKomboPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        seneTF.setText("      ");
        seneTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Süre");

        adresTB.setColumns(20);
        adresTB.setRows(5);
        jScrollPane3.setViewportView(adresTB);

        jLabel25.setText("Kayıt Sıra");

        sureTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sureTFKeyReleased(evt);
            }
        });

        jLabel15.setText("Gün");

        endDC.setDateFormatString("dd.MM.yyyy");
        endDC.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                endDCComponentRemoved(evt);
            }
        });
        endDC.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                endDCHierarchyChanged(evt);
            }
        });
        endDC.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                endDCAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                endDCAncestorRemoved(evt);
            }
        });
        endDC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                endDCFocusLost(evt);
            }
        });
        endDC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                endDCMouseReleased(evt);
            }
        });
        endDC.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                endDCComponentHidden(evt);
            }
        });
        endDC.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                endDCİnputMethodTextChanged(evt);
            }
        });

        jLabel24.setText("Bitiş");

        jLabel19.setText("İzin İli");

        jLabel22.setText("Başlangıç");

        ilKombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ilKomboPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel27.setText("Senesi");

        siraTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        beginDC.setDateFormatString("dd.MM.yyyy");
        beginDC.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                beginDCComponentAdded(evt);
            }
        });
        beginDC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                beginDCMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                beginDCMouseReleased(evt);
            }
        });
        beginDC.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                beginDCComponentShown(evt);
            }
        });
        beginDC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                beginDCKeyReleased(evt);
            }
        });
        beginDC.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                beginDCVetoableChange(evt);
            }
        });

        jLabel18.setText("İzin Adresi");

        jLabel23.setText("İzin Türü");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        yolizniTF.setEditable(false);
        yolizniTF.setText("0");

        yolCB.setText("yol izni istiyor");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(yolCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(yolizniTF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yolCB)
                    .addComponent(yolizniTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel16)
                    .addComponent(jLabel24)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel25))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(endDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(sureTF, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15))
                            .addComponent(beginDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(izinturKombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(siraTF, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ilKombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izinturKombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(beginDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(sureTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(endDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ilKombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(seneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(siraTF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel25)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog1.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        gucelleBut.setText("Güncelle");
        gucelleBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gucelleButActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(gucelleBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 210, 120));

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
                .addGap(0, 102, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("KULLANILAN İZİNLER");

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

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/recycle.png"))); // NOI18N
        jButton5.setText("Güncelle");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/add.png"))); // NOI18N
        jButton1.setText("Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/delete.png"))); // NOI18N
        jButton4.setText("Sil");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(45, 45, 45))
        );

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/page_white_acrobat.png"))); // NOI18N
        jButton14.setText("PDF Aktar(2)");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/export_excel.png"))); // NOI18N
        jButton10.setText("Excele Gönder(1.)");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

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

        kullanilanLCD.setGlowColor(new java.awt.Color(255, 255, 51));
        kullanilanLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.STANDARD_GREEN_LCD);
        kullanilanLCD.setLcdDecimals(0);
        kullanilanLCD.setLcdUnitString("GÜN");

        mazeretLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.BLUE2_LCD);
        mazeretLCD.setLcdDecimals(0);
        mazeretLCD.setLcdUnitString("GÜN");

        raporLCD.setDigitalFont(true);
        raporLCD.setFocusCycleRoot(true);
        raporLCD.setGlowing(true);
        raporLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.YELLOW_LCD);
        raporLCD.setLcdDecimals(0);
        raporLCD.setLcdText("Rapor");
        raporLCD.setLcdTextScrolling(true);
        raporLCD.setLcdUnitString("GÜN");
        displaySingle5.add(raporLCD);
        raporLCD.setBounds(0, 0, 128, 48);

        ozursuzLCD.setDigitalFont(true);
        ozursuzLCD.setLcdColor(eu.hansolo.steelseries.tools.LcdColor.REDDARKRED_LCD);
        ozursuzLCD.setLcdDecimals(0);
        ozursuzLCD.setLcdUnitString("GÜN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ozursuzLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displaySingle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mazeretLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(KalanLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hakedisLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kullanilanLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addComponent(hakedisLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kullanilanLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(KalanLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mazeretLCD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(displaySingle5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ozursuzLCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        radial2.setBorder(javax.swing.BorderFactory.createTitledBorder("Kalan Yıllık İzin"));
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
        radial2.setTitle("Kalan İzin");
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(kombotab, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radial2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 93, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(radial2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton14)
                                    .addComponent(kombotab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(105, 105, 105))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void izinturKomboPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_izinturKomboPopupMenuWillBecomeInvisible

        // TODO add your handling code here:
    }//GEN-LAST:event_izinturKomboPopupMenuWillBecomeInvisible

    private void beginDCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beginDCMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_beginDCMouseExited

    private void beginDCMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beginDCMouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_beginDCMouseReleased

    private void beginDCComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_beginDCComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_beginDCComponentShown

    private void beginDCComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_beginDCComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_beginDCComponentAdded

    private void beginDCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beginDCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_beginDCKeyReleased

    private void beginDCVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_beginDCVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_beginDCVetoableChange

    private void sureTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sureTFKeyReleased
        gunTopla();

        // TODO add your handling code here:
    }//GEN-LAST:event_sureTFKeyReleased

    private void endDCMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endDCMouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_endDCMouseReleased

    private void endDCComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_endDCComponentHidden

        // TODO add your handling code here:
    }//GEN-LAST:event_endDCComponentHidden

    private void endDCComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_endDCComponentRemoved

        // TODO add your handling code here:
    }//GEN-LAST:event_endDCComponentRemoved

    private void endDCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_endDCFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_endDCFocusLost

    private void endDCHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_endDCHierarchyChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_endDCHierarchyChanged

    private void endDCİnputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_endDCİnputMethodTextChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_endDCİnputMethodTextChanged

    private void endDCAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_endDCAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_endDCAncestorAdded

    private void endDCAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_endDCAncestorRemoved

        // TODO add your handling code here:
    }//GEN-LAST:event_endDCAncestorRemoved

    private void ilKomboPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ilKomboPopupMenuWillBecomeInvisible

        // TODO add your handling code here:
    }//GEN-LAST:event_ilKomboPopupMenuWillBecomeInvisible

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

    private void kombotabPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_kombotabPopupMenuWillBecomeInvisible
        updateTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_kombotabPopupMenuWillBecomeInvisible
private void ekle(){
        muhtelifIzinKontrol();

        if (izinOnay){
            try{
                ig.setBegin(((JTextField) beginDC.getDateEditor().getUiComponent()).getText());
                ig.setEnd(((JTextField) endDC.getDateEditor().getUiComponent()).getText());
                ig.setIzinadresi(adresTB.getText());
                ig.setIzinili(ilKombo.getSelectedItem().toString());
                ig.setIzinsekli(izinturKombo.getSelectedItem().toString());
                ig.setSenesi(yil);
                ig.setSicil(sicil);
                ig.setSure(Integer.parseInt(sureTF.getText()));
                ig.setYolizni(Integer.parseInt(yolizniTF.getText()));
                em.getTransaction().begin();
                em.persist(ig);
                em.getTransaction().commit();

                JOptionPane.showMessageDialog(null,"İZİN GİRİŞİ KAYDEDİLDİ");

            }catch(Exception e){}
            updateTable();
            updateIzinDurum();
            doldur(sicil);
        }else JOptionPane.showMessageDialog(null, "Tekrar kontrol edin");
}

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
temizle();
        eklebut.setVisible(true);
gucelleBut.setVisible(false);
        jDialog1.setSize(700,500);
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
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
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int p=JOptionPane.showConfirmDialog(null,"Silmek istediğinizden eminmisiniz","SİL",JOptionPane.YES_NO_OPTION);
        if (p==0){
            try{
                String sql="delete from izingiris where sira=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1, siraTF.getText());
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
private void guncelle(){
           try{
            String value1=siraTF.getText();
            int value2=sicil;
            String value3=izinturKombo.getSelectedItem().toString();
            String value4=((JTextField)beginDC.getDateEditor().getUiComponent()).getText();
            String value5=((JTextField)endDC.getDateEditor().getUiComponent()).getText();
            String value6=adresTB.getText();
            String value7=ilKombo.getSelectedItem().toString();
            String value8=sureTF.getText();

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
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
eklebut.setVisible(false);
gucelleBut.setVisible(true);
        jDialog1.setSize(700,500);
        jDialog1.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            pdfYap.pdfYapp(jTable2, "izinliste.pdf");

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"izinliste.pdf");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "dosya bulunamadı");
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(izinEkran.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed
private void temizle(){
    adresTB.setText("");
sureTF.setText("");
ilKombo.setSelectedIndex(0);
        curDate();
        beginDC.setDate(null);
        endDC.setDate(null);
}
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
      int sure=0;  
      if(sureTF.getText()==null)
          sure=0;else
      sure=Integer.parseInt(sureTF.getText());
      
        int ek=0;
        switch(izinturKombo.getSelectedItem().toString()){
            case "Yıllık İzin": if (sure>sen){
                JOptionPane.showMessageDialog(null,
                    "İstenilenIzinsuresi kalan hakkınızdan fazla");
                izinOnay=false;
            } else if (yolCB.isSelected()&& yolizni==true){
              JOptionPane.showMessageDialog(null,
                    "Bir sene içinde bir kere yol izni kullanabilirsiniz");
                izinOnay=false;  
            } else if (yolCB.isSelected()&& sure<10){
                JOptionPane.showMessageDialog(null,
                    "Yol izni için senelik izin 10 günden aşağı olamaz");
                izinOnay=false; 
            } else if (yolCB.isSelected()&& ilKombo.getSelectedItem().toString().equalsIgnoreCase("ISPARTA")){
                JOptionPane.showMessageDialog(null,
                    "Yol izni için izin ili farklı bir il olmalı");
                izinOnay=false;
            } else if(yolCB.isSelected() && yolizni==false && sure>9 && !ilKombo.getSelectedItem().toString().equalsIgnoreCase("ISPARTA"))
            {
                izinOnay=true;
                int mesafe=mesafe(ilKombo.getSelectedItem().toString());
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
    yolizniTF.setText(String.valueOf(ek));
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

    private void eklebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eklebutActionPerformed
 ekle();
 jDialog1.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_eklebutActionPerformed

    private void gucelleButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gucelleButActionPerformed
guncelle();
jDialog1.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_gucelleButActionPerformed

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
            java.util.logging.Logger.getLogger(izin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(izin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(izin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(izin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new izin().setVisible(true);
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
    private javax.swing.JTextArea adresTB;
    private com.toedter.calendar.JDateChooser beginDC;
    private javax.swing.JTextField butce_TF;
    private eu.hansolo.steelseries.gauges.DisplaySingle displaySingle5;
    private javax.swing.JButton eklebut;
    private com.toedter.calendar.JDateChooser endDC;
    private javax.swing.JButton gucelleBut;
    private eu.hansolo.steelseries.gauges.DisplaySingle hakedisLCD;
    private javax.swing.JComboBox<String> ilKombo;
    private javax.swing.JLabel imajLabel;
    private javax.swing.JTextField isim_TF;
    private javax.swing.JComboBox izinturKombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenuItem kesMenu;
    private javax.swing.JComboBox<String> kombotab;
    private javax.swing.JMenuItem kopyalaMenu;
    private eu.hansolo.steelseries.gauges.DisplaySingle kullanilanLCD;
    private eu.hansolo.steelseries.gauges.DisplaySingle mazeretLCD;
    private eu.hansolo.steelseries.gauges.DisplaySingle ozursuzLCD;
    private model.panel panel1;
    private javax.swing.JPopupMenu popupMenu;
    private eu.hansolo.steelseries.gauges.Radial radial2;
    private eu.hansolo.steelseries.gauges.DisplaySingle raporLCD;
    private javax.swing.JLabel seneTF;
    private javax.swing.JLabel siraTF;
    private javax.swing.JTextField sureTF;
    private javax.swing.JTextField telefon_TF;
    private javax.swing.JMenuItem yapistirMenu;
    private javax.swing.JCheckBox yolCB;
    private javax.swing.JTextField yolizniTF;
    // End of variables declaration//GEN-END:variables
}
