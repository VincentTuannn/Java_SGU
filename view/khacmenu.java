/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lap4all
 */
public class khacmenu extends JPanel{
    
    	public khacmenu() {
		setBackground(Color.white);
		Color myColor = new Color(34, 33, 75);
		Color backGroundColor = Color.white;
		Color borderColor = myColor;
		Color textColor = myColor;
		Color buttonColor = myColor;
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.white);
		tabbedPane.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tabbedPane.setBackground(buttonColor);
		add(tabbedPane, BorderLayout.CENTER);
		
		Voucher voucherpanel = new Voucher();
		voucherpanel.setBorder(new LineBorder(borderColor, 3));
		tabbedPane.addTab("Khuyến mãi", null, voucherpanel, null);
		
		ThanhToanPanel thanhtoanPanel = new ThanhToanPanel();
		thanhtoanPanel.setBorder(new LineBorder(borderColor, 3));
		tabbedPane.addTab("Thanh toán", null, thanhtoanPanel, null);
		
                VanchuyenPanel vanchuyenPanel = new VanchuyenPanel();
                vanchuyenPanel.setBorder(new LineBorder(borderColor, 3));
		tabbedPane.addTab("Vận chuyển", null, vanchuyenPanel, null);
	}
	public static void main(String[] args) {
		JFrame fr = new JFrame();       
		khacmenu a=new khacmenu();
		fr.add(a);
		fr.setVisible(true);
	}
}
