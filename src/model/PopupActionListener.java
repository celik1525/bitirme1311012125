/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 *
 * @author Ruhi ÇELİK
 */
public class PopupActionListener implements ActionListener{
String metin;
    JPopupMenu popup;

    public PopupActionListener(JPopupMenu popup) {
        this.popup = popup;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField cagiranLabel=(JTextField) popup.getInvoker();
        switch(e.getActionCommand()){
            case "kes":
                metin=cagiranLabel.getSelectedText();
                cagiranLabel.replaceSelection("");
                break;
            case "kopyala":
                metin=cagiranLabel.getSelectedText();
                break;
            case "yapıştır":
                cagiranLabel.replaceSelection(metin);
                break;
            default: break;
        }}
    
}
