/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Menu;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ruhi ÇELİK
 */
public class pupupMenu extends JPopupMenu{
    JMenuItem kesMenu;
    JMenuItem kopyalaMenu;
    JMenuItem yapistirMenu;
    public static JPopupMenu pop;
    public pupupMenu() {
        pop=new JPopupMenu();
        PopupActionListener pal=new PopupActionListener(pop);
kesMenu.addActionListener(pal);
yapistirMenu.addActionListener(pal);
kopyalaMenu.addActionListener(pal);
        kesMenu=new JMenuItem("kes");
        kesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/cut.png")));
        kopyalaMenu=new JMenuItem("kopyala");
        kopyalaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/copying_and_distribution.png")));
        
        yapistirMenu=new JMenuItem("yapistir");
        yapistirMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library/16x16/paste_plain.png")));
        
        pop.add(kesMenu);
        pop.add(kopyalaMenu);
        pop.add(yapistirMenu);
    }
    
}
