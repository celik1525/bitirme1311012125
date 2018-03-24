/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

/**
 *
 * @author Ruhi ÇELİK
 */
public class frameDeneme extends JFrame{
    JTextField text1, text2, text3;
        MouseListener ml=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JComponent jc=(JComponent) e.getSource();
                TransferHandler th=jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        };

    public frameDeneme()  {
        super("Drag and Drop");
        text1=new JTextField();
        text2=new JTextField();
        text3=new JTextField();
        text1.setBounds(20, 30, 100, 20);
        text1.setBounds(250, 30, 100, 20);
        text1.setBounds(20, 100, 100, 20);
    text1.addMouseListener(ml);
    text2.addMouseListener(ml);
    text3.addMouseListener(ml);
    
    text1.setTransferHandler(new TransferHandler("text"));
    text2.setTransferHandler(new TransferHandler("text"));
    text3.setTransferHandler(new TransferHandler("text"));
    add(text1);
    add(text2);
    add(text3);
        setLayout(null);
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
     public static void main(String args[]) {
        frameDeneme fr=new frameDeneme();
        fr.setVisible(true);
     }
}
