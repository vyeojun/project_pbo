    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class MainMenu extends JFrame {
    JLabel header = new JLabel("mY Peternakan Bebek (dm @wehateghiva)");
    JButton tombolKelolaKandang = new JButton("Kelola Kandang");
    JButton tombolKelolaProduksi = new JButton("Kelola Produksi");
    JButton surprise = new JButton("surprise");
    
    
    public MainMenu() {
        setTitle("Main Menu");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        
        getContentPane().setBackground(Color.decode("#f69ae5"));
        surprise.setBackground(Color.decode("#ff6f00"));
        surprise.setForeground(Color.decode("#ffffff"));
        
        add(header);
        add(tombolKelolaKandang);
        add(tombolKelolaProduksi);
        add(surprise);

        header.setBounds(20, 20, 260, 30);
        tombolKelolaKandang.setBounds(20, 60, 260, 40);
        tombolKelolaProduksi.setBounds(20, 110, 260, 40);
        surprise.setBounds(20, 160, 260, 40);
        
        
        tombolKelolaKandang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Kandang.ViewKandang();
            }
        });

        tombolKelolaProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Produksi.ViewProduksi();
            }
        });
        
        surprise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    Desktop.getDesktop().browse(new URI("https://soundcloud.com/xighaav/sets/iwasnevercured"));
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(MainMenu.this, 
                        "Error opening link: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
    }
}
