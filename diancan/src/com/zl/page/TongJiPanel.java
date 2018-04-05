package com.zl.page;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class TongJiPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public TongJiPanel() {
		setSize(637, 405);
		setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u65E5\u671F");
		label.setBounds(62, 41, 54, 15);
		add(label);
		
		textField = new JTextField();
		textField.setBounds(126, 38, 125, 21);
		add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u6309\u65E5\u671F\u67E5\u8BE2");
		button.setBounds(279, 37, 93, 23);
		add(button);
		
		JLabel label_1 = new JLabel("\u8F93\u5165\u6708\u4EFD");
		label_1.setBounds(62, 72, 54, 15);
		add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(126, 69, 125, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("\u6309\u6708\u4EFD\u67E5\u8BE2");
		button_1.setBounds(279, 68, 93, 23);
		add(button_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(62, 131, 495, 209);
		add(textPane);
		
	}
}
