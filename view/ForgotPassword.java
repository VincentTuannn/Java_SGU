package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

//import dao.ForgotPasswordDAO;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.util.Properties;
import javax.swing.JPasswordField;

public class ForgotPassword extends JDialog implements MouseListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnSend;
	private JButton btnBack;
	private JTextField textField;

	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		try {
			ForgotPassword dialog = new ForgotPassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ForgotPassword() {
                setSize(500, 342);
		getContentPane().setBackground(Color.WHITE);
		GridLayout gridLayout = new GridLayout();
		getContentPane().setLayout(gridLayout);
		contentPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0,0,0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 37, 0};
		gbl_contentPanel.columnWeights = new double[]{0.25,0.25,0.25,0.25,0.25};
		contentPanel.setLayout(gbl_contentPanel);
                
                JLabel lblNewLabel_1 = new JLabel("QUÊN MẬT KHẨU?");
                lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
                GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
                gbc_lblNewLabel_1.gridx = 3;
                gbc_lblNewLabel_1.gridy = 1;
                gbc_lblNewLabel_1.insets = new Insets(10, 0, 10, 0);
                contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
                
                GridBagConstraints gbc_emailText = new GridBagConstraints();
                gbc_emailText.fill=GridBagConstraints.HORIZONTAL;
                txtEmail = new JTextField(); 
                txtEmail.setColumns(50);
                txtEmail.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(34, 33, 75)));
                txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
                gbc_emailText.gridx = 0;
                gbc_emailText.gridy = 3;
                contentPanel.add(txtEmail, gbc_emailText);

		     Color defaultTextColor = Color.GRAY;
		     txtEmail.setForeground(defaultTextColor);
			txtEmail.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (txtEmail.getText().equals("Email...")) {
	                	txtEmail.setText("");
	                }
	                txtEmail.setForeground(Color.BLACK); 
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (txtEmail.getText().isEmpty()) {
	                	txtEmail.setText("Email...");
	                	txtEmail.setForeground(defaultTextColor);
	                }
	            }
	        });
			
                        lblNewLabel_2 = new JLabel("Email:");
                        lblNewLabel_2.setForeground(new Color(34, 33, 75));
                        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
                        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
                        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
                        gbc_lblNewLabel_2.gridx = 0;
                        gbc_lblNewLabel_2.gridy = 4;
                        gbc_lblNewLabel_2.gridwidth = 3;
                        contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridwidth = 4;
			gbc_txtEmail.insets = new Insets(15, 0, 15, 10);
			gbc_txtEmail.gridx = 0;
			gbc_txtEmail.gridy = 5;
			contentPanel.add(txtEmail, gbc_txtEmail);
		
			lblNewLabel_3 = new JLabel("Mật khẩu mới:");
			lblNewLabel_3.setForeground(new Color(34, 33, 75));
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 6;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
	
			textField_1 = new JPasswordField("");
			textField_1.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(34, 33, 75)));
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(15, 0, 15, 10);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 0;
			gbc_textField_1.gridy = 7;
			gbc_textField_1.gridwidth = 4;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(50);
	
	
			btnSend = new JButton("Cập nhật");
			btnSend.addMouseListener(this);
			btnSend.setRequestFocusEnabled(true);
			btnSend.setForeground(Color.WHITE);
			btnSend.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnSend.setFocusPainted(false);
			btnSend.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnSend.setBackground(new Color(34, 33, 75));
			btnSend.setPreferredSize(new Dimension(100, 40));
			GridBagConstraints gbc_btnSend = new GridBagConstraints();
                        gbc_btnSend.ipadx = 25;
			gbc_btnSend.gridx = 4;
			gbc_btnSend.gridy = 5;
			contentPanel.add(btnSend, gbc_btnSend);
		
		
			btnBack = new JButton("Trở về");
			btnBack.addMouseListener(this);
			btnBack.setRequestFocusEnabled(true);
			btnBack.setPreferredSize(new Dimension(100, 40));
			btnBack.setForeground(Color.WHITE);
			btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnBack.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnBack.setBackground(new Color(34, 33, 75));
			btnBack.setPreferredSize(new Dimension(100, 40));
			GridBagConstraints gbc_btnBack = new GridBagConstraints();
			gbc_btnBack.ipadx = 50;
			gbc_btnBack.gridx = 4;
			gbc_btnBack.gridy = 7;
			contentPanel.add(btnBack, gbc_btnBack);
		
        this.setLocationRelativeTo(null);


	}
	@Override
	public void mouseClicked(MouseEvent e) {
                Login login = new Login();
		login.displayLogin();				
		dispose();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
