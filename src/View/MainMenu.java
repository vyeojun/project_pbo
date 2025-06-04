    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class MainMenu extends JFrame {
    JLabel header = new JLabel("mY Peternakan Bebek (dm @wehateghiva)");
    JButton tombolKelolaKandang = new JButton("Kelola Kandang");
    JButton tombolKelolaProduksi = new JButton("Kelola Produksi");

    public MainMenu() {
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        
        getContentPane().setBackground(Color.decode("#f69ae5"));
        
        
        add(header);
        add(tombolKelolaKandang);
        add(tombolKelolaProduksi);

        header.setBounds(20, 20, 260, 30);
        tombolKelolaKandang.setBounds(20, 60, 260, 40);
        tombolKelolaProduksi.setBounds(20, 110, 260, 40);

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

        
    }
}
