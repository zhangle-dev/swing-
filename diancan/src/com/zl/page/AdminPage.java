package com.zl.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JScrollPane;

public class AdminPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(5, 5, 780, 25);
		contentPane.add(panel);
		
		JLabel label = new JLabel("\u70B9\u9910\u7CFB\u7EDF\u7BA1\u7406\u5458\u7AEF");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(5, 30, 144, 405);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(148, 30, 637, 405);
		contentPane.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, "name_16143762247598");
		
		//创建四个panel表示右侧四个页面
		JPanel userManagerPanel = new UserManagerPanel();
		JPanel menuManagerPanel = new MenuManagerPanel();
		JPanel orderManagerPanel = new OrderManagerPanel();
		JPanel tongJiPanel = new TongJiPanel();
		
		scrollPane.setViewportView(userManagerPanel);
		
		
		JButton userManager = new JButton("\u7528\u6237\u7BA1\u7406");
		userManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(userManagerPanel);
			}
		});
		userManager.setBounds(10, 5, 124, 23);
		panel_1.add(userManager);
		
		JButton menuButton = new JButton("\u83DC\u5355\u7BA1\u7406");
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(menuManagerPanel);
			}
		});
		menuButton.setBounds(10, 32, 124, 23);
		panel_1.add(menuButton);
		
		JButton button = new JButton("\u8BA2\u5355\u7BA1\u7406");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(orderManagerPanel);
			}
		});
		button.setBounds(10, 59, 124, 23);
		panel_1.add(button);
		
		JButton button_1 = new JButton("\u7EDF\u8BA1\u529F\u80FD");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(tongJiPanel);
			}
		});
		button_1.setBounds(10, 85, 124, 23);
		panel_1.add(button_1);
		
	}
}
