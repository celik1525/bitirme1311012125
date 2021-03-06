/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.DocumentException;
import controller.javaConnect;
import entities.AtamaTablo;
import entities.KimlikTablo;
import entities.Malihaklar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.ekseleYaz;
import model.pdfYap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ruhi ÇELİK
 */
public class lojmanEkran extends javax.swing.JFrame {

    Connection conn=null;
ResultSet rs=null;
PreparedStatement ps=null;
    EntityManager em=null;
    EntityManagerFactory emf=null;
int sicil=0;    
int buyil=0;
    AtamaTablo at;
    KimlikTablo kt;
    Malihaklar mh;
    DefaultListModel model1=new DefaultListModel();
    DefaultListModel model2=new DefaultListModel();
    DefaultTableModel tabloModel;
    int year=0;
    public lojmanEkran() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
conn=javaConnect.ConnectDb();
sicil=javaConnect.sicil;
emf=javax.persistence.Persistence.createEntityManagerFactory("personelOtamasyonPU");
        em=emf.createEntityManager();
        createColumns();
        sort();
          at=new AtamaTablo();
          kt=new KimlikTablo();
          mh=new Malihaklar();
    fillList();
jList1.setModel(model1);
        jList2.setModel(model2);
        jTable1.setModel(tabloModel);
        curDate();
        
    
    }
    private void sort(){
        TableRowSorter<DefaultTableModel> sorter=new TableRowSorter<DefaultTableModel>(tabloModel);
        jTable1.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys=new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
    }
    private void createColumns(){
    tabloModel=    (DefaultTableModel) jTable1.getModel();
    tabloModel.addColumn("sicil");
    tabloModel.addColumn("isim");
    tabloModel.addColumn("Soyisim");
    tabloModel.addColumn("görevinde Başarılı(+10)");
    tabloModel.addColumn("Kıdem yıl(+(5xyıl))");
    tabloModel.addColumn("Lojmandan faydalanma(-(3xyıl))");
    tabloModel.addColumn("Evli ise(+6)");
    tabloModel.addColumn("Çocuk(+(3xÇocuk))en fazla 2");
    tabloModel.addColumn("bakmakla yükümlü(+1)");
    tabloModel.addColumn("beklediği yıl (+1x her yıl)");
    tabloModel.addColumn("konut varsa(-15/-10)");
    tabloModel.addColumn("PUAN");
    
    }
    
    private void populate(String sicil, String name, String sirname, String basarili, String kidemYil,
            String LojFaydalanma, String evli, String cocuk, String bakmak, String bekleme, String konut, int puan ){
        
        String[] rowData={sicil ,name, sirname,"+"+ basarili,"+"+kidemYil, "-"+LojFaydalanma,"+"+ evli,"+"+ cocuk,"+"+ bakmak, "+"+bekleme,"-"+ konut, String.valueOf(puan) };
        tabloModel.addRow(rowData);
              
    }
    private void filter(String query){
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(tabloModel);
        jTable1.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    private void fillList(){
     try{
        String sql="select * from atamaTablo";
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()){
            String name=rs.getString("name");
            String sirname=rs.getString("sirname");
            String sic=rs.getString("sicil");
            String topla=sic+"- "+name+" "+ sirname;
            model1.addElement(topla);
            
        }
        
    }catch(Exception x){
        
    }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
            
    }
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

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seçim Tablosu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        jButton2.setBackground(new java.awt.Color(255, 51, 102));
        jButton2.setText("<< Çıkar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setText("Ekle >>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/table.png"))); // NOI18N
        jButton3.setText("Tablo Oluştur");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/printer.png"))); // NOI18N
        jButton4.setText("yazdır");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Personel Listesi");

        jLabel3.setText("Seçim Listesi");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/export_excel.png"))); // NOI18N
        jButton5.setText("Excele Gönder");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/page_white_acrobat.png"))); // NOI18N
        jButton12.setText("PDF Aktar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(171, 171, 171))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jButton12))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)))
                .addGap(11, 11, 11))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "puan tablosu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 204))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setText("Filtre");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(364, 364, 364))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 String secim=jList1.getSelectedValue().toString();
 model2.addElement(secim);
 model1.remove(jList1.getSelectedIndex());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 String secim=jList2.getSelectedValue().toString();
 model1.addElement(secim);
 model2.remove(jList2.getSelectedIndex());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
private int sicilIyi(int sic){
    int k=0;
    try{
        String sql="select * from odulTablo where sicil=? ";
        ps=conn.prepareStatement(sql);
        ps.setInt(1, sic);
        rs=ps.executeQuery();
        if(rs.next()){
            k=10;
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
int getYear(Date date1, Date date2){
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
    Integer.parseInt(sdf.format(date1));
   int t=Integer.parseInt(sdf.format(date2));
   int y=Integer.parseInt(sdf.format(date1));
   
   return y-t;
}
private int yilPuan(){
    SimpleDateFormat df=new SimpleDateFormat("dd.MM.yyyy");
Date f=new Date();
Date k=new Date();
    try{
    f = df.parse(at.getMemuriyetbaslama());
        year=getYear(k, f);
       

  
}catch(ParseException p){
    p.printStackTrace();
}    
    return year*5;
}
private int lojmanYarar(){
    int k=at.getLojmanYararlanmaSure();
    return k;
}
private int evli(){
    
    String j=kt.getMedenihal();
    if (j.equalsIgnoreCase("evli"))
    {  return 6;}
    else {
    return 0;
} }
private int cocuk(){
    int sayi=mh.getCocuk();
    if (sayi>2)
        sayi=2;
    return sayi;
}
private int bekleYil(){
    int k=at.getLojmanYararlanmaSure();
     int p=year-k;
     if (p<0)p=0;
    return p;
}
private int bakmakla(int num){
int sayi=mh.getCocuk();
String j=kt.getMedenihal();
    if (j.equalsIgnoreCase("evli"))
    sayi++;
String sql="select count(name) from yakinTablo where sicil=?";
int f=0;
try{
    ps=conn.prepareStatement(sql);
    ps.setInt(1, num);
     f=rs.getInt(1);
}catch(Exception e){}
finally{
    try{
       rs.close();
       ps.close();
    }catch(Exception e){}
}
if (f<0)
    f=0;
        return f;
}

private int konutVar(int num){
    
    int y=0;
    String sql="select * from malBildirim where sicil=?";
    try{
        ps=conn.prepareStatement(sql);
        ps.setInt(1, num);
        rs=ps.executeQuery();
        if(rs.next()){
            String g=rs.getString("aciklama");
            if(g.equalsIgnoreCase("konut")){
                
                    int p=JOptionPane.showConfirmDialog(null,"Konut şehir içinde mi","SİL",JOptionPane.YES_NO_OPTION);
        if (p==0){
           y=15;
            }else y=10;
        }
    }}catch(Exception e){}
    finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }
    
    return y;
}
private void tabloYap(int num){
    
    at=em.find(AtamaTablo.class, num);
    kt=em.find(KimlikTablo.class, num);
    mh=em.find(Malihaklar.class, num);
    
    String sicil=at.getSicil().toString();
    
    String name=at.getName();
    String vorname=at.getSirname();
    
    int sicilIyi=sicilIyi(num);
    
    int yilPuan=yilPuan();
    
    int lojmanYarar=lojmanYarar()*3;  
    int evli=evli();
    int cocuk=cocuk()*3;
    int bakmakla=bakmakla(num);
    int bekledigiYil=bekleYil();
    int konutVar=konutVar(num);
int puan=sicilIyi+yilPuan-lojmanYarar+evli+cocuk+bakmakla+bekledigiYil-konutVar;
    
    populate(sicil, name, vorname, String.valueOf(sicilIyi), String.valueOf(yilPuan),String.valueOf(lojmanYarar),
            String.valueOf(evli), String.valueOf(cocuk),String.valueOf(bakmakla), 
            String.valueOf(bekledigiYil), String.valueOf(konutVar),puan);

    
    
    
}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 for(int i=0;i<model2.size();++i){
    String k=model2.get(i).toString();
String[] sic=k.split("-");
    int sicil=Integer.parseInt(sic[0]);
      
    tabloYap(sicil);  // işlem
 }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        filter(jTextField1.getText().toLowerCase());
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MessageFormat header=new MessageFormat("Rapor Çıktı");
        MessageFormat footer=new MessageFormat("Sayfa{0,number,integer}");
        try{
            jTable1.print(JTable.PrintMode.NORMAL,header,footer);
        }catch(java.awt.print.PrinterException e){
            System.err.println("Yazılanmadı");
        }
        // TODO add your handling code here:

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
public String hucreAl(int x, int y){
    return tabloModel.getValueAt(x, y).toString();
}
 
public void ekseleYaz(){
//      System.out.println(hucreAl(0, 0));
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet ws=wb.createSheet();
       TreeMap<String,Object[]> data=new TreeMap<>();
       int k=tabloModel.getColumnCount();
       int t=tabloModel.getRowCount();
       Object[] nesne=new Object[k];
       for (int i=0;i<k;i++){
       nesne[i]=tabloModel.getColumnName(i);
       
       }
     //data.put("0",(Object[]) (Object) p(k));
      // data.put("0",new Object[]{nesne[0], nesne [1], nesne [2], nesne [3], nesne [4]}); 
       data.put("0",new Object[]{tabloModel.getColumnName(0),
           tabloModel.getColumnName(1),tabloModel.getColumnName(2),tabloModel.getColumnName(3),
       tabloModel.getColumnName(4),tabloModel.getColumnName(5),tabloModel.getColumnName(6),
       tabloModel.getColumnName(7),tabloModel.getColumnName(8),tabloModel.getColumnName(9),
       tabloModel.getColumnName(10),tabloModel.getColumnName(11)
       });
     for (int i=0;i<t;i++)   {
     data.put(String.valueOf(i+1),new Object[]{hucreAl(i,0),hucreAl(i,1),hucreAl(i, 2),hucreAl(i, 3),
     hucreAl(i,4),hucreAl(i,5),hucreAl(i, 6),hucreAl(i, 7),hucreAl(i,8),hucreAl(i,9),
     hucreAl(i, 10),hucreAl(i, 11)
     
     });
     }  
        
        Set<String> ids=data.keySet();
        XSSFRow row;
        int rowID=0;
        for(String key:ids){
            row=ws.createRow(rowID++);
            Object [] values=data.get(key);
            int cellID=0;
            for (Object o:values){
                Cell hucre=row.createCell(cellID++);
                hucre.setCellValue(o.toString());
            }
        }try{
            try (FileOutputStream fos = new FileOutputStream(new File("lojmallist.xlsx"))) {
                wb.write(fos);
            }
        } catch (IOException ex) {
            Logger.getLogger(lojmanEkran.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            ekseleYaz.writeToExcel(jTable1, "lojmanListesi.xlsx");

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"LojmanListesi.xlsx");
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(lojmanEkran.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            pdfYap.pdfYapp(jTable1, "lojman.pdf");
            
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"lojman.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(lojmanEkran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

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
            java.util.logging.Logger.getLogger(lojmanEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lojmanEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lojmanEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lojmanEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lojmanEkran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
private void curDate(){
    Calendar cal=new GregorianCalendar();
    int month=cal.get(Calendar.MONTH);
    year=cal.get(Calendar.YEAR);
    int day=cal.get(Calendar.DAY_OF_MONTH);
    
    
    
    int hour=cal.get(Calendar.HOUR);
    int second=cal.get(Calendar.SECOND);
    int minute=cal.get(Calendar.MINUTE);
    
}

}
