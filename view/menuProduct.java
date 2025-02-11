package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class menuProduct extends JPanel {

	private static final long serialVersionUID = 1L;

	public menuProduct() {
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
		
		Category categoryPanel = new Category();
		categoryPanel.setBorder(new LineBorder(borderColor, 3));
		tabbedPane.addTab("Loại Sản Phẩm", null, categoryPanel, null);
		
		Product1 productPanel = new Product1();
		productPanel.setBorder(new LineBorder(borderColor, 3));
		tabbedPane.addTab("Sản Phẩm", null, productPanel, null);
		
                lohang lohangpanel = new lohang();
                lohangpanel.setBorder(new LineBorder(borderColor, 3));
		tabbedPane.addTab("Lô Hàng", null, lohangpanel, null);
	}
	public static void main(String[] args) {
		JFrame fr = new JFrame();       
		menuProduct a=new menuProduct();
		fr.add(a);
		fr.setVisible(true);
	}

}
