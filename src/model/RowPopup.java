/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author Ruhi ÇELİK
 */
public class RowPopup extends JPopupMenu{

    public RowPopup(JTable table) {
        JMenuItem add=new JMenuItem("Ekle");
        JMenuItem edit=new JMenuItem("Düzenle");
        JMenuItem delete=new JMenuItem("Sil");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Eklendi");
            
            }
        });
    edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Düzenlendi"); }
        });
    delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { JOptionPane.showMessageDialog(null, "Silindi");   }
        });
    add(add);
    add(edit);
    add(delete);
    }
    
    
}
