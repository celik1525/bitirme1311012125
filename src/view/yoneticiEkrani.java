
package view;

import com.itextpdf.text.DocumentException;
import controller.javaConnect;
import entities.AtamaTablo;
import entities.GirisTablo;
import entities.Izindurum;
import entities.KimlikTablo;
import entities.KisiAyrilan;
import entities.Malihaklar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.BarChartAWT;
import model.PopupActionListener;
import model.ekseleYaz;
import model.jasperReportYap;
import model.pdfYap;
import model.pieChartAWT;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ruhi ÇELİK
 */
public class yoneticiEkrani extends javax.swing.JFrame {

    Connection conn=null;
ResultSet rs=null;
PreparedStatement ps=null;
    EntityManager em=null;
    EntityManagerFactory emf=null;
int sicil=0;    
int buyil=0;


public yoneticiEkrani() {
        initComponents();
PopupActionListener pal=new PopupActionListener(popupMenu);
kesMenu.addActionListener(pal);
yapistirMenu.addActionListener(pal);
kopyalaMenu.addActionListener(pal);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
conn=javaConnect.ConnectDb();
sicil=javaConnect.sicil;
emf=javax.persistence.Persistence.createEntityManagerFactory("personelOtamasyonPU");
        em=emf.createEntityManager();
    FillCombo();
    updateTable();
currentDate();
    curDate();
    yilKontrol();
    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        popupMenu = new javax.swing.JPopupMenu();
        kesMenu = new javax.swing.JMenuItem();
        kopyalaMenu = new javax.swing.JMenuItem();
        yapistirMenu = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dtf1 = new javax.swing.JTextField();
        dtf2 = new javax.swing.JTextField();
        dtf3 = new javax.swing.JTextField();
        dtf4 = new javax.swing.JTextField();
        dtf5 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDialog3 = new javax.swing.JDialog();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ekleMenu = new javax.swing.JMenuItem();
        guncelleMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        silMenu = new javax.swing.JMenuItem();
        ayirMenu = new javax.swing.JMenuItem();
        ayirDialog = new javax.swing.JDialog();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        yerTF = new javax.swing.JTextField();
        nedTF = new javax.swing.JTextField();
        ayrDate = new com.toedter.calendar.JDateChooser();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        imajLabel = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        RAPORBT = new javax.swing.JButton();
        PRINTBT = new javax.swing.JButton();
        TEFRIKBUTTON = new javax.swing.JButton();
        DELETEBUTTON = new javax.swing.JButton();
        InsertButton = new javax.swing.JButton();
        UPDATEBUTTON = new javax.swing.JButton();
        ChartBT = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        kesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/cut.png"))); // NOI18N
        kesMenu.setText("kes");
        popupMenu.add(kesMenu);

        kopyalaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/copying_and_distribution.png"))); // NOI18N
        kopyalaMenu.setText("kopyala");
        popupMenu.add(kopyalaMenu);

        yapistirMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/paste_plain.png"))); // NOI18N
        yapistirMenu.setText("yapıştır");
        popupMenu.add(yapistirMenu);

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setTitle("Derece Kademe İlerleme");
        jDialog1.setBounds(new java.awt.Rectangle(100, 100, 800, 500));
        jDialog1.setModal(true);
        jDialog1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog1ComponentShown(evt);
            }
        });
        jDialog1.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                jDialog1WindowOpened(evt);
            }
        });

        jLabel1.setText("DERECE KADEME İLERLEME");

        jLabel11.setText("Sicil");

        jLabel12.setText("İsim");

        jLabel13.setText("Soyisim");

        jLabel14.setText("Derece");

        jLabel15.setText("Kademe");

        dtf1.setEditable(false);
        dtf1.setColumns(20);

        dtf2.setEditable(false);
        dtf2.setColumns(20);

        dtf3.setEditable(false);
        dtf3.setColumns(20);

        dtf4.setColumns(20);

        dtf5.setColumns(20);

        jButton13.setText("Değiştir");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel1))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel11)
                        .addGap(9, 9, 9)
                        .addComponent(dtf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel14)
                        .addGap(2, 2, 2)
                        .addComponent(dtf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton13))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel12)
                        .addGap(8, 8, 8)
                        .addComponent(dtf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel15)
                        .addGap(0, 0, 0)
                        .addComponent(dtf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel13)
                        .addGap(0, 0, 0)
                        .addComponent(dtf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(dtf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(dtf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton13))
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15))))
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13))
                    .addComponent(dtf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(390, Short.MAX_VALUE))
        );

        jDialog2.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog2.setTitle("HAKKINDA");
        jDialog2.setBounds(new java.awt.Rectangle(200, 200, 800, 300));
        jDialog2.setModal(true);

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel16.setText("HAKKINDA");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Bu Uygulama 2017-2018 Yılında Bitirme Projesi Kapsamında  hazırlanmıştır.");

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jLabel16))
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel16)
                .addGap(53, 53, 53)
                .addComponent(jLabel17)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jDialog3.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog3.setTitle("İletişim");
        jDialog3.setBounds(new java.awt.Rectangle(200, 200, 800, 300));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel18.setText("İLETİŞİM");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Telefon");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Adres");

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog3Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jLabel18))
                    .addGroup(jDialog3Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(279, Short.MAX_VALUE))
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel18)
                .addGap(53, 53, 53)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        ekleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/add.png"))); // NOI18N
        ekleMenu.setText("Ekle");
        ekleMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ekleMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ekleMenu);

        guncelleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/update.png"))); // NOI18N
        guncelleMenu.setText("Güncelle");
        guncelleMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guncelleMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(guncelleMenu);
        jPopupMenu1.add(jSeparator1);

        silMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/delete.png"))); // NOI18N
        silMenu.setText("Sil");
        silMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(silMenu);

        ayirMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/application_go.png"))); // NOI18N
        ayirMenu.setText("Ayır");
        ayirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayirMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ayirMenu);

        ayirDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Ayrılan Kişi Bilgileri");
        jLabel21.setPreferredSize(new java.awt.Dimension(400, 400));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ayirDialog.getContentPane().add(jLabel21, gridBagConstraints);

        jLabel22.setText("Tarih");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        ayirDialog.getContentPane().add(jLabel22, gridBagConstraints);

        jLabel23.setText("GittiğiYer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        ayirDialog.getContentPane().add(jLabel23, gridBagConstraints);

        jLabel24.setText("Ayrılma Nedeni");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        ayirDialog.getContentPane().add(jLabel24, gridBagConstraints);

        yerTF.setColumns(20);
        yerTF.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        ayirDialog.getContentPane().add(yerTF, gridBagConstraints);

        nedTF.setColumns(20);
        nedTF.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        ayirDialog.getContentPane().add(nedTF, gridBagConstraints);

        ayrDate.setDateFormatString("dd.MM.yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ayirDialog.getContentPane().add(ayrDate, gridBagConstraints);

        jButton14.setText("DEVAM");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        ayirDialog.getContentPane().add(jButton14, gridBagConstraints);

        jButton15.setText("İPTAL");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        ayirDialog.getContentPane().add(jButton15, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 51));

        jToolBar1.setBackground(new java.awt.Color(204, 204, 255));
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(66, 70));
        jToolBar1.setMinimumSize(new java.awt.Dimension(66, 70));
        jToolBar1.setPreferredSize(new java.awt.Dimension(100, 70));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/autos.png"))); // NOI18N
        jButton1.setText("izin Ekranı");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton4.setBackground(new java.awt.Color(255, 204, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/billiard_marker.png"))); // NOI18N
        jButton4.setText("Görevlendirme Ekranı");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setBackground(new java.awt.Color(255, 255, 204));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/application_form.png"))); // NOI18N
        jButton5.setText("İKİNCİL TABLO YÖNETİMİ");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setBackground(new java.awt.Color(204, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/balance.png"))); // NOI18N
        jButton6.setText("DİSİPLİN");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setBackground(new java.awt.Color(51, 102, 0));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/award_star_bronze_2.png"))); // NOI18N
        jButton7.setText("ÖDÜL");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton8.setBackground(new java.awt.Color(204, 255, 204));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/education.png"))); // NOI18N
        jButton8.setText("SEMİNER");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton9.setBackground(new java.awt.Color(204, 204, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/application_home.png"))); // NOI18N
        jButton9.setText("Lojman Tablo");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton11.setBackground(new java.awt.Color(51, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/table_heatmap.png"))); // NOI18N
        jButton11.setText("Ayrılan Kişiler Tablosu");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 0));

        imajLabel.setBackground(new java.awt.Color(255, 204, 255));

        jDesktopPane2.setLayer(imajLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imajLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imajLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
        );

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
                        .addComponent(KalanIzin_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 610));

        jTable1.setAutoCreateRowSorter(true);
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
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ARAMA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 12), new java.awt.Color(204, 204, 0))); // NOI18N

        jTextField5.setBackground(new java.awt.Color(204, 255, 0));
        jTextField5.setComponentPopupMenu(popupMenu);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Listeden Seçim"));

        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/arrow_refresh.png"))); // NOI18N
        jButton3.setText("Güncelle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setMaximumSize(new java.awt.Dimension(150, 570));
        jPanel3.setMinimumSize(new java.awt.Dimension(150, 570));
        jPanel3.setPreferredSize(new java.awt.Dimension(150, 569));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Hızlı İşlem Düğmeleri", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        RAPORBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/report.png"))); // NOI18N
        RAPORBT.setText("RAPOR AL");
        RAPORBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RAPORBTActionPerformed(evt);
            }
        });

        PRINTBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/printer.png"))); // NOI18N
        PRINTBT.setText("YAZDIR");
        PRINTBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRINTBTActionPerformed(evt);
            }
        });

        TEFRIKBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/user_go.png"))); // NOI18N
        TEFRIKBUTTON.setText("KİŞİ AYIR");
        TEFRIKBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TEFRIKBUTTONActionPerformed(evt);
            }
        });

        DELETEBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/user_delete.png"))); // NOI18N
        DELETEBUTTON.setText("KİŞİ SİL");
        DELETEBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEBUTTONActionPerformed(evt);
            }
        });

        InsertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/user_add.png"))); // NOI18N
        InsertButton.setText("YENİ KİŞİ EKLE");
        InsertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertButtonActionPerformed(evt);
            }
        });

        UPDATEBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/user_edit.png"))); // NOI18N
        UPDATEBUTTON.setText("KİŞİ GÜNCELLE");
        UPDATEBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEBUTTONActionPerformed(evt);
            }
        });

        ChartBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/chart_bar.png"))); // NOI18N
        ChartBT.setText("GRAFİK");
        ChartBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChartBTActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/chart_pie.png"))); // NOI18N
        jButton2.setText("GRAFİK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/export_excel.png"))); // NOI18N
        jButton10.setText("Excele Gönder");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/page_white_acrobat.png"))); // NOI18N
        jButton12.setText("PDF Aktar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(UPDATEBUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(InsertButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TEFRIKBUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DELETEBUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PRINTBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RAPORBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ChartBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InsertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UPDATEBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TEFRIKBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DELETEBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PRINTBT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAPORBT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ChartBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_END);

        jMenu1.setBackground(new java.awt.Color(255, 153, 51));
        jMenu1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setText("Kayıt");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Yeni Kayıt");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Kayıt Düzenle");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Kayıt Sil");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Kayıt Ayır");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(255, 255, 204));
        jMenu2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jMenu2.setText("Giriş");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("İzin Girişi");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Görevlendirme Girişi");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Hizmet İçi Eğitim Girişi");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Ödül Giriş");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Disiplin Giriş");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Derece/Kademe Girişi");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(204, 255, 153));
        jMenu3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu3.setText("Listeler");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Sendika Listesi");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("İkincil Tablo Yönetimi");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setText("Lojman Tablo");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setText("Ayrılan Kişiler Tablosu");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem14);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setText("İstatistik ve Listeler");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuBar1.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(204, 204, 255));
        jMenu4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jMenu4.setText("Hakkında");

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setText("Yardım");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuItem17.setText("Hakkında");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuItem18.setText("İletişim");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem18);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Tarih");
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Saaat");
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void temizle(){
   isim_TF.setText("");
Soyisim_TF.setText("");
Unvan_TF.setText("");
Sicil_TF.setText("");
TCkimlik_TF.setText("");
 telefon_TF.setText("");
 Derece_TF.setText("");
 Kademe_TF.setText("");
 KalanIzin_TF.setText("");
 imajLabel.setIcon(null);
}
    private void tablo(int num){
    temizle();
    try{
        int tableClick=num;
        sicil=tableClick; // seçili kişinin üzerinde işlem yapılabilmesi için
TypedQuery<KimlikTablo> query =em.createNamedQuery("KimlikTablo.findBySicil", KimlikTablo.class).setParameter("sicil",tableClick );
TypedQuery<Malihaklar> sor =em.createNamedQuery("Malihaklar.findBySicil", Malihaklar.class).setParameter("sicil",tableClick );
TypedQuery<Izindurum> sorg =em.createNamedQuery("Izindurum.findBySicil", Izindurum.class).setParameter("sicil",tableClick );
TypedQuery<AtamaTablo> sorgu=em.createNamedQuery("AtamaTablo.findBySicil",AtamaTablo.class).setParameter("sicil",tableClick);
TypedQuery<GirisTablo> so=em.createNamedQuery("GirisTablo.findBySicil",GirisTablo.class).setParameter("sicil",tableClick);
Icon resim=new ImageIcon(so.getSingleResult().getImage());
imajLabel.setIcon(resim);
isim_TF.setText(sorgu.getSingleResult().getName());
Soyisim_TF.setText(sorgu.getSingleResult().getSirname());
Unvan_TF.setText(sorgu.getSingleResult().getUnvan());
Sicil_TF.setText(sorgu.getSingleResult().getSicil().toString());
TCkimlik_TF.setText(query.getSingleResult().getTCKimlik());
 telefon_TF.setText(query.getSingleResult().getTelefon());
 Derece_TF.setText(sor.getSingleResult().getDerece().toString());
 Kademe_TF.setText(sor.getSingleResult().getKademe().toString());
 KalanIzin_TF.setText(sorg.getSingleResult().getKalanyillikizin().toString());
javaConnect.sicil=sicil;
  
    
    } catch(Exception e){
         }
    }
//    private void donatTF(int sicil){
//    String sql="SELECT a.sicil, a.name, a.sirname, a.unvan, k.telefon, k.TCKimlik, m.derece,"
//            + " m.kademe, i.kalanYillikizin, g.image FROM atamaTablo a, KimlikTablo k,"
//            + " maliHaklar m, izindurum i, girisTablo g where  a.sicil=k.sicil and"
//            + " k.sicil=m.sicil and m.sicil=i.sicil and i.sicil=g.sicil and g.sicil=?";
//try{
//    ps=conn.prepareStatement(sql);
//    ps.setInt(1, sicil);
//    ps.execute();
//    if(rs.next()){
//        Sicil_TF.setText(rs.getString(1));
//        isim_TF.setText(rs.getString(2));
//        Soyisim_TF.setText(rs.getString(3));
//        Unvan_TF.setText(rs.getString(4));
//        telefon_TF.setText(rs.getString(5));
//        TCkimlik_TF.setText(rs.getString(6));
//        Derece_TF.setText(rs.getString(7));
//        Kademe_TF.setText(rs.getString(8));
//        KalanIzin_TF.setText(rs.getString(8));
//         byte [] imagedata=rs.getBytes(9);
//    format=new ImageIcon(imagedata);
//    imajLabel.setIcon(format);
//    }
//}catch(Exception e){}
//finally{
//    try{
//        rs.close();
//        ps.close();
//    }catch(Exception e){}
//}
//}
private void hakedisDuzelt(int sicil){
    String sql="UPDATE izindurum set izinhakedis=? where sicil=?";
    try{
        ps=conn.prepareStatement(sql);
        ps.setInt(1, 30);
        ps.setInt(2, sicil);
        ps.executeUpdate();
    }catch(Exception e){}
    finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
}
    private void hakedisKontrol(){
    String sql="select * from atamaTablo";
    int devirYil=0;
    try{
        ps=conn.prepareStatement(sql);
    rs=ps.executeQuery();
    if(rs.next())
    {
        
        Date ilk=rs.getDate(11);
        devirYil=buyil-ilk.getYear();
       System.out.println(devirYil+" "+ilk.getYear());
        int sic=rs.getInt("sicil");
        System.out.println(sic+"-"+ilk.getYear());
        if (devirYil>10){
            hakedisDuzelt(sic);
            System.out.println("Yildurum duzeltildi");
        }
    }
    }catch(Exception e){}
    finally{
        try{
            rs.close();
            ps.close();
        }catch(Exception e){}
    }
}
    
private void updateizinYilDonum(){
    String sql="SELECT * FROM izindurum";
    
    hakedisKontrol();
    try{
        System.out.println("j");
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        while (rs.next()){
        int    kalan=rs.getInt("kalanyillikizin");
        int    hakedis=rs.getInt("izinhakedis");
        int sicl=rs.getInt("sicil");
        izinDuzenle(kalan, hakedis, sicl);
        }     
    }
        
    catch(Exception e){
        
    }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
    }
       
}
private void izinDuzenle(int kalann,int hakediss,int sicill){
    if(kalann>hakediss)
        kalann=hakediss;
 
    
        try{
        String sql1="UPDATE izindurum set devirizin=? where sicil=?";
            
        ps=conn.prepareStatement(sql1);
        ps.setInt(1, kalann);
        ps.setInt(2, sicill);
        ps.executeUpdate();
    } catch(Exception e){
        }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
       
    }
    }

}

private void yilKontrol(){
    String sql="select * from yildurum";
    try{
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        if (rs.next()){
            int yil=rs.getInt("yil");
            if(buyil!=yil){
                int p=JOptionPane.showConfirmDialog(null," Yıl değişikliği algılandı. İzin durumları Güncellensinmi","Değiştir",JOptionPane.YES_NO_OPTION );
                if(p==0){
                    updateizinYilDonum();
                    updateYil();
                }
            }
        }
    }catch(Exception e){
            }finally{
                try{
                    rs.close();
                    ps.close();
                } catch(Exception e){

                }}
}
private void curDate(){
    Calendar cal=new GregorianCalendar();
    int month=cal.get(Calendar.MONTH);
    int year=cal.get(Calendar.YEAR);
    int day=cal.get(Calendar.DAY_OF_MONTH);
    buyil=year;
    
    
    int hour=cal.get(Calendar.HOUR);
    int second=cal.get(Calendar.SECOND);
    int minute=cal.get(Calendar.MINUTE);
    
}

private void updateYil(){
    String sql="update yildurum set yil=? ";
    try{
           ps=conn.prepareStatement(sql);
        ps.setInt(1, buyil);
     ps.executeUpdate();
     JOptionPane.showMessageDialog(null, "Yıl güncellendi");
        
    }catch(Exception e){
            }finally{
                try{
                    rs.close();
                    ps.close();
                } catch(Exception e){

                }
    
    }
}
    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        try{
            String sql="select * from atamaTablo where name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, jTextField5.getText());
            rs=ps.executeQuery();
            if(rs.next()){
                int add1=rs.getInt("sicil");                
                sicil=add1;
                tablo(sicil);
            }}catch(Exception e){
            }finally{
                try{
                    rs.close();
                    ps.close();
                } catch(Exception e){

                }}try{
            String sql="select * from atamaTablo where sirname=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, jTextField5.getText());
            rs=ps.executeQuery();
            if(rs.next()){
                int add1=rs.getInt("sicil");                
                sicil=add1;
                tablo(sicil);
            }}catch(Exception e){
            }finally{
                try{
                    rs.close();
                    ps.close();
                } catch(Exception e){

                }}
                try{
            String sql="select * from atamaTablo where sicil=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, jTextField5.getText());
            rs=ps.executeQuery();
            if(rs.next()){
                int add1=rs.getInt("sicil");                
                sicil=add1;
                tablo(sicil);
            }}catch(Exception e){
            }finally{
                try{
                    rs.close();
                    ps.close();
                } catch(Exception e){

                }}
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible

        String tmp=(String)jComboBox1.getSelectedItem();
        String[] once=tmp.split("-");
        String sic=once[0];

        String sql="Select * from atamaTablo where sicil=?";
        try{
            ps=conn.prepareStatement(sql);
            ps.setString(1, sic);
            rs=ps.executeQuery();
            if(rs.next()){
                int add1=rs.getInt("sicil");                
                sicil=add1;
                tablo(sicil);
                }
        }catch(Exception r){

        }finally{
            try{
                rs.close();
                ps.close();
            } catch(Exception e){

            }javaConnect.sicil=sicil;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void PRINTBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRINTBTActionPerformed
        MessageFormat header=new MessageFormat("Rapor Çıktı");
        MessageFormat footer=new MessageFormat("Sayfa{0,number;integer}");
        try{
            jTable1.print(JTable.PrintMode.NORMAL,header,footer);
        }catch(java.awt.print.PrinterException e){
            System.err.println("Yazılanmadı");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_PRINTBTActionPerformed
public void kayitSil(){
           try{
            JFrame p=new JFrame();
            JProgressBar pbar=new JProgressBar();
            pbar.setStringPainted(true);
            pbar.setMaximum(5);
            pbar.setMinimum(0);
            pbar.setValue(0);
            p.add(pbar);
            p.setSize(500,200);

            p.setVisible(true);
            AtamaTablo at=em.find(AtamaTablo.class, sicil);
            em.getTransaction().begin();
            em.remove(at);
            em.getTransaction().commit();
            pbar.setValue(1);
            KimlikTablo kt=em.find(KimlikTablo.class, sicil);
            em.getTransaction().begin();
            em.remove(kt);
            em.getTransaction().commit();
            pbar.setValue(2);
            Malihaklar mh=em.find(Malihaklar.class, sicil);
            em.getTransaction().begin();
            em.remove(mh);
            em.getTransaction().commit();
            pbar.setValue(3);
            Izindurum iz=em.find(Izindurum.class, sicil);
            em.getTransaction().begin();
            em.remove(iz);
            em.getTransaction().commit();
            pbar.setValue(4);
            GirisTablo gt=em.find(GirisTablo.class, sicil);
            em.getTransaction().begin();
            em.remove(gt);
            em.getTransaction().commit();
            pbar.setValue(5);
            JOptionPane.showMessageDialog(null, "SİLİNDİ");
            p.dispose();
            if (ayirDialog.isVisible())
                ayirDialog.dispose();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
 
}
    private void DELETEBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEBUTTONActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        kayitSil();
// TODO add your handling code here:
    }//GEN-LAST:event_DELETEBUTTONActionPerformed

    private void InsertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertButtonActionPerformed
eklemeSihirbazı ekle=new eklemeSihirbazı();
ekle.setVisible(true);
        
        
    }//GEN-LAST:event_InsertButtonActionPerformed

    private void ChartBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChartBTActionPerformed
String sql="select unvan, count(name) from atamaTablo group by unvan";
        BarChartAWT bc=new BarChartAWT(sql, sql, sql);
        bc.setSize(400, 400);
        bc.setVisible(true);        
        
//        
//        try{
//            DefaultPieDataset pd=new DefaultPieDataset();
//            pd.setValue("One",new Integer(10));
//            pd.setValue("Two",new Integer(20));
//            pd.setValue("Three",new Integer(30));
//            pd.setValue("Four",new Integer(40));
//            JFreeChart chart=ChartFactory.createPieChart("PieChart", pd,true,true,false);
//            final PiePlot3D P=(PiePlot3D) chart.getPlot();
//            P.setForegroundAlpha(0.60f);
//            P.setStartAngle(270);
//            P.setInteriorGap(0.02);
//            // ChartPanel pan=new ChartPanel(chart);
//            ChartFrame fram=new ChartFrame("PieChart", chart);
//            //  merkezPanel.removeAll();
//            //  merkezPanel.add(pan);
//            fram.setVisible(true);
//            fram.setSize(450,500);
//            //   merkezPanel.validate();
//        }catch(Exception e){
//
//        }
        // TODO add your handling code here:
    }//GEN-LAST:event_ChartBTActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        try{
//            DefaultCategoryDataset dataset=new DefaultCategoryDataset();
//            dataset.setValue(80, "Markus", "Kişi1");
//            dataset.setValue(50, "Makus", "Kşi2");
//            dataset.setValue(75, "Makus", "Kii3");
//            dataset.setValue(95, "Mars", "Kişi4");
//            JFreeChart chart=ChartFactory.createBarChart("Personel DAĞILIMI", "Atama Şekli",
//                "Marks", dataset,PlotOrientation.VERTICAL,false,true,false);
//            CategoryPlot P=chart.getCategoryPlot();
//            P.setRangeGridlinePaint(Color.BLACK);
//            ChartFrame fr=new ChartFrame("Bar Chart", chart);
//            // TODO add your handling code here:
//            fr.setVisible(true);
//            fr.setSize(450,350);
//        }catch(Exception e){
//
//        }
String sql="select unvan, count(name) from atamaTablo group by unvan";
        pieChartAWT pc=new pieChartAWT("Ünvan Dağılımı", sql);
        pc.setSize(400, 400);
        pc.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void UPDATEBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEBUTTONActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
else{
        kisiGuncelle guncelle=new kisiGuncelle();
guncelle.setVisible(true);
}  
        // TODO add your handling code here:
    }//GEN-LAST:event_UPDATEBUTTONActionPerformed
public void kisiAyrilan(int sicil, String name, String sirname, String unvan, String giris,String terk,
        int derece,int kademe,int gosterge, int ekgosterge, String nedeni,String yer){
    try{
        em.getTransaction().begin();
        KisiAyrilan ka=new KisiAyrilan();
        ka.setDerece(derece);
        ka.setEkgosterge(ekgosterge);
        ka.setGiris(giris);
        ka.setGosterge(gosterge);
        ka.setKademe(kademe);
        ka.setName(name);
        ka.setNedeni(nedeni);
        
        ka.setSicil(sicil);
        ka.setSirname(sirname);
        ka.setTerk(terk);
        ka.setUnvan(unvan);
        ka.setYer(yer);
        em.persist(ka);
        em.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "EKLENDİ");
    }catch(Exception e){
    if(em.getTransaction().isActive()){
        em.getTransaction().rollback();
        JOptionPane.showMessageDialog(null, "hata oluştu");
    }
    }
}
    public void bilgiOlustur(String tarih, String neden, String yerr){

        TypedQuery<KimlikTablo> query =em.createNamedQuery("KimlikTablo.findBySicil", KimlikTablo.class).setParameter("sicil",sicil );
TypedQuery<Malihaklar> sor =em.createNamedQuery("Malihaklar.findBySicil", Malihaklar.class).setParameter("sicil",sicil );
TypedQuery<Izindurum> sorg =em.createNamedQuery("Izindurum.findBySicil", Izindurum.class).setParameter("sicil",sicil );
TypedQuery<AtamaTablo> sorgu=em.createNamedQuery("AtamaTablo.findBySicil",AtamaTablo.class).setParameter("sicil",sicil);
TypedQuery<GirisTablo> so=em.createNamedQuery("GirisTablo.findBySicil",GirisTablo.class).setParameter("sicil",sicil);
Icon resim=new ImageIcon(so.getSingleResult().getImage());
String name=sorgu.getSingleResult().getName();
String sirname=sorgu.getSingleResult().getSirname();
String unvan=sorgu.getSingleResult().getUnvan();
String giris=sorgu.getSingleResult().getAtamaTarihi();
String terk=tarih;
int derece=sor.getSingleResult().getDerece();
int kademe=sor.getSingleResult().getKademe();
int gosterge=sor.getSingleResult().getGosterge();
int ekgosterge=sor.getSingleResult().getEkgosterge();
String nedeni=neden;
String yer=yerr;
//String resim=so.getSingleResult().getImage().toString();
        kisiAyrilan(sicil, name, sirname, unvan, giris, terk, derece, kademe, gosterge, ekgosterge, nedeni, yer);
    }
    private void TEFRIKBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TEFRIKBUTTONActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
else{
ayirDialog.setVisible(true);

}
        // TODO add your handling code here:
    }//GEN-LAST:event_TEFRIKBUTTONActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
         if(evt.getKeyCode()==KeyEvent.VK_UP||evt.getKeyCode()==KeyEvent.VK_DOWN){
            int Row=jTable1.getSelectedRow();
            int tableClick=(int) (jTable1.getModel().getValueAt(Row, 0));
            tablo(tableClick);
         }  // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         
            int Row=jTable1.getSelectedRow();
            int tableClick=(int) (jTable1.getModel().getValueAt(Row, 0));
            tablo(tableClick);
       // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
FillCombo();
updateTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
izinEkran ekran=new izinEkran();
ekran.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 gorevEkran ge=new gorevEkran();
 ge.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
ikincilTabloEkran tabloEkran=new ikincilTabloEkran();
tabloEkran.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 disiplinYonetimi disip=new disiplinYonetimi();
 disip.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 odulEkran odEk=new odulEkran();
 odEk.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
 seminerTablo semT=new seminerTablo();
 semT.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
lojmanEkran loj=new lojmanEkran();
loj.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
 Listeler lis=new Listeler();
 lis.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            ekseleYaz.writeToExcel(jTable1, "atamaListe.xlsx");
            
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"atamaListe.xlsx");
        } catch (IOException ex) {
            Logger.getLogger(lojmanEkran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void RAPORBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAPORBTActionPerformed
//try{
//    String report="C:\\Users\\Ruhi ÇELİK\\Documents\\NetBeansProjects\\projec123JPA\\report1.jrxml";
//    JasperReport jr=JasperCompileManager.compileReport(report);
//    JasperPrint jp=JasperFillManager.fillReport(jr,null,conn);
//    JasperViewer.viewReport(jp);
//}catch(Exception e){}
        // TODO add your handling code here:
       String sql="select * from atamaTablo order by name";
       String path="report2.jrxml";
        new jasperReportYap().raporla(path, sql);
    }//GEN-LAST:event_RAPORBTActionPerformed

    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            pdfYap.pdfYapp(jTable1, "Atama.pdf");
            
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"Atama.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(yoneticiEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
new tefrikListe().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
new eklemeSihirbazı().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        new kisiGuncelle().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        kayitSil();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
{
ayirDialog.setVisible(true);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        new izinEkran().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        new gorevEkran().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        new seminerTablo().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        new odulEkran().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else
        new disiplinYonetimi().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
new ikincilTabloEkran().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
new lojmanEkran().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
new tefrikListe().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
new Listeler().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
String yol="help\\help.chm";
try{Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+yol);
    
}catch(Exception e){
    
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed
public void kademeIlerle(String derece, String kademe){
   String sql="update table malihaklar set (derece,kademe) values (?,?) where sicil=?";
    try{
        ps=conn.prepareStatement(sql);
        ps.setString(1, derece);
        ps.setString(2, kademe);
        ps.setInt(3, sicil);
        ps.execute();
        JOptionPane.showMessageDialog(null, "DEĞİŞTİRİLDİ");
    }catch(Exception e){}
    finally{
        try{
        rs.close();
        ps.close();
    }catch(Exception e){}
    }
}
    
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
   else     jDialog1.setVisible(true);
        

    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
String sql="update malihaklar set derece=?, kademe=? where sicil=?";
try{
    ps=conn.prepareStatement(sql);
    ps.setString(1, dtf4.getText());
    ps.setString(2, jTextField5.getText());
    ps.execute();
    JOptionPane.showMessageDialog(null, "GÜNCELLENDİ");
}catch(Exception e){}
finally{
    try{
    rs.close();
    ps.close();
}catch(Exception e){}

}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jDialog1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog1ComponentShown
String sql="Select a.sicil, a.name, a.sirname, m.derece, m.kademe from atamaTablo a, malihaklar m "
        + "where m.sicil=a.sicil and a.sicil =? ";
        try{
    ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    rs=ps.executeQuery();
    if (rs.next()){
        dtf1.setText(rs.getString(1));
        dtf2.setText(rs.getString(2));
        dtf3.setText(rs.getString(3));
        dtf4.setText(rs.getString(4));
        dtf5.setText(rs.getString(5));
            }
    
}catch(Exception e){
    
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog1ComponentShown

    private void jDialog1WindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowOpened
String sql="Select a.sicil, a.name, a.sirname, m.derece, m.kademe from atamaTablo a, malihaklar m "
        + "where m.sicil=a.sicil and a.sicil =? ";
        try{
    ps=conn.prepareStatement(sql);
    ps.setInt(1, sicil);
    rs=ps.executeQuery();
    if (rs.next()){
        dtf1.setText(rs.getString(1));
        dtf2.setText(rs.getString(2));
        dtf3.setText(rs.getString(3));
        dtf4.setText(rs.getString(4));
        dtf5.setText(rs.getString(5));
            }
    
}catch(Exception e){
    
}

        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog1WindowOpened

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
jDialog2.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
jDialog3.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void ekleMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ekleMenuActionPerformed
new eklemeSihirbazı().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_ekleMenuActionPerformed

    private void guncelleMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guncelleMenuActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Kombo kutusundan seçim yapınız");
else new kisiGuncelle().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_guncelleMenuActionPerformed

    private void silMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silMenuActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Kombo kutusundan seçim yapınız");
else kayitSil();
        // TODO add your handling code here:
    }//GEN-LAST:event_silMenuActionPerformed

    private void ayirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayirMenuActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
else{
ayirDialog.setVisible(true);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_ayirMenuActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
if(sicil==0)
    JOptionPane.showMessageDialog(null, "Tablodan veya Komboboxtan Değiştirmek istediğiniz kişiyi seçin");
else{
        String tarih=(((JTextField) ayrDate.getDateEditor().getUiComponent()).getText());
String neden=nedTF.getText();
String yer=yerTF.getText();
bilgiOlustur(tarih, neden, yer);
kayitSil();
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
ayirDialog.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

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
            java.util.logging.Logger.getLogger(yoneticiEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(yoneticiEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(yoneticiEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(yoneticiEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new yoneticiEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChartBT;
    private javax.swing.JButton DELETEBUTTON;
    private javax.swing.JTextField Derece_TF;
    private javax.swing.JButton InsertButton;
    private javax.swing.JTextField Kademe_TF;
    private javax.swing.JTextField KalanIzin_TF;
    private javax.swing.JButton PRINTBT;
    private javax.swing.JButton RAPORBT;
    private javax.swing.JTextField Sicil_TF;
    private javax.swing.JTextField Soyisim_TF;
    private javax.swing.JTextField TCkimlik_TF;
    private javax.swing.JButton TEFRIKBUTTON;
    private javax.swing.JButton UPDATEBUTTON;
    private javax.swing.JTextField Unvan_TF;
    private javax.swing.JDialog ayirDialog;
    private javax.swing.JMenuItem ayirMenu;
    private com.toedter.calendar.JDateChooser ayrDate;
    private javax.swing.JTextField dtf1;
    private javax.swing.JTextField dtf2;
    private javax.swing.JTextField dtf3;
    private javax.swing.JTextField dtf4;
    private javax.swing.JTextField dtf5;
    private javax.swing.JMenuItem ekleMenu;
    private javax.swing.JMenuItem guncelleMenu;
    private javax.swing.JLabel imajLabel;
    private javax.swing.JTextField isim_TF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem kesMenu;
    private javax.swing.JMenuItem kopyalaMenu;
    private javax.swing.JTextField nedTF;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem silMenu;
    private javax.swing.JTextField telefon_TF;
    private javax.swing.JMenuItem yapistirMenu;
    private javax.swing.JTextField yerTF;
    // End of variables declaration//GEN-END:variables
 private Icon format=null;
 byte [] personimage=null;
 
 private void FillCombo() {
    jComboBox1.removeAllItems();
     try{
        String sql="select * from atamaTablo";
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()){
            String name=rs.getString("name");
            String sirname=rs.getString("sirname");
            String sic=rs.getString("sicil");
            jComboBox1.addItem(sic+"- "+name+" "+ sirname);
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
 private void updateTable() {
    try{
        
        String sql="Select * from atamaTablo";
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    }   catch(Exception e){
        
    }finally{
        try{
            rs.close();
        ps.close();
        } catch(Exception e){
        
    }
}
 }
private void dateTime(){
    Calendar cal=new GregorianCalendar();
    int month=cal.get(Calendar.MONTH);
    int year=cal.get(Calendar.YEAR);
    int day=cal.get(Calendar.DAY_OF_MONTH);
    jMenu5.setText("Tarih:"+day+"/"+(month+1)+"/"+year);
    
    int hour=cal.get(Calendar.HOUR);
    int second=cal.get(Calendar.SECOND);
    int minute=cal.get(Calendar.MINUTE);
    jMenu6.setText("Saat:"+hour+":"+minute+":"+second);
}
    private void currentDate(){
    Thread clock=new Thread(){
      public void run(){
          for(;;){
              dateTime();
              try{
                  sleep(1000);
              }catch(Exception e){
                  
              }
          }}};
    clock.start();
    }


}
