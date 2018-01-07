
package view;

import controller.javaConnect;
import entities.AtamaTablo;
import entities.GirisTablo;
import entities.Izindurum;
import entities.KimlikTablo;
import entities.Malihaklar;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
conn=javaConnect.ConnectDb();
sicil=javaConnect.sicil;
emf=javax.persistence.Persistence.createEntityManagerFactory("personelOtamasyonPU");
        em=emf.createEntityManager();
    FillCombo();
    updateTable();

    curDate();
    yilKontrol();
    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

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
            .addComponent(imajLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
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
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(175, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(439, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 610));

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
                .addContainerGap(178, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setMaximumSize(new java.awt.Dimension(150, 570));
        jPanel3.setMinimumSize(new java.awt.Dimension(150, 570));
        jPanel3.setPreferredSize(new java.awt.Dimension(150, 569));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Hızlı İşlem Düğmeleri", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        RAPORBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/32x32/report.png"))); // NOI18N
        RAPORBT.setText("RAPOR AL");

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UPDATEBUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InsertButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TEFRIKBUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DELETEBUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PRINTBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RAPORBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChartBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InsertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UPDATEBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(TEFRIKBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DELETEBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PRINTBT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAPORBT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ChartBT, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
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

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

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
            if(rs.next()){int add1=rs.getInt("sicil");                
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

    private void DELETEBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEBUTTONActionPerformed
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
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_DELETEBUTTONActionPerformed

    private void InsertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertButtonActionPerformed
eklemeSihirbazı ekle=new eklemeSihirbazı();
ekle.setVisible(true);
        
        
    }//GEN-LAST:event_InsertButtonActionPerformed

    private void ChartBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChartBTActionPerformed
        
        
        
        try{
            DefaultPieDataset pd=new DefaultPieDataset();
            pd.setValue("One",new Integer(10));
            pd.setValue("Two",new Integer(20));
            pd.setValue("Three",new Integer(30));
            pd.setValue("Four",new Integer(40));
            JFreeChart chart=ChartFactory.createPieChart("PieChart", pd,true,true,false);
            final PiePlot3D P=(PiePlot3D) chart.getPlot();
            P.setForegroundAlpha(0.60f);
            P.setStartAngle(270);
            P.setInteriorGap(0.02);
            // ChartPanel pan=new ChartPanel(chart);
            ChartFrame fram=new ChartFrame("PieChart", chart);
            //  merkezPanel.removeAll();
            //  merkezPanel.add(pan);
            fram.setVisible(true);
            fram.setSize(450,500);
            //   merkezPanel.validate();
        }catch(Exception e){

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_ChartBTActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();
            dataset.setValue(80, "Markus", "Kişi1");
            dataset.setValue(50, "Makus", "Kşi2");
            dataset.setValue(75, "Makus", "Kii3");
            dataset.setValue(95, "Mars", "Kişi4");
            JFreeChart chart=ChartFactory.createBarChart("Personel DAĞILIMI", "Atama Şekli",
                "Marks", dataset,PlotOrientation.VERTICAL,false,true,false);
            CategoryPlot P=chart.getCategoryPlot();
            P.setRangeGridlinePaint(Color.BLACK);
            ChartFrame fr=new ChartFrame("Bar Chart", chart);
            // TODO add your handling code here:
            fr.setVisible(true);
            fr.setSize(450,350);
        }catch(Exception e){

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void UPDATEBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEBUTTONActionPerformed
kisiGuncelle guncelle=new kisiGuncelle();
guncelle.setVisible(true);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_UPDATEBUTTONActionPerformed

    private void TEFRIKBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TEFRIKBUTTONActionPerformed
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
    private javax.swing.JLabel imajLabel;
    private javax.swing.JTextField isim_TF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField telefon_TF;
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
}
