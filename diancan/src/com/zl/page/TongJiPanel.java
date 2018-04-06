package com.zl.page;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;

public class TongJiPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TongJiPanel() {
		setSize(637, 405);
		setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u65E5\u671F");
		label.setBounds(62, 41, 54, 15);
		add(label);
		
		SpinnerDateModel model = new SpinnerDateModel();
		// 获得JSPinner对象
		JSpinner year = new JSpinner(model);
		year.setValue(new Date());
		// 设置时间格式
		JSpinner.DateEditor editor = new JSpinner.DateEditor(year, "yyyy-MM-dd");
		year.setEditor(editor);
		
		year.setBounds(126, 38, 125, 21);
		add(year);
		
		JButton button = new JButton("\u6309\u65E5\u671F\u67E5\u8BE2");
		button.setBounds(279, 37, 93, 23);
		add(button);
		
		JLabel label_1 = new JLabel("\u8F93\u5165\u6708\u4EFD");
		label_1.setBounds(62, 72, 54, 15);
		add(label_1);
		
		SpinnerDateModel model2 = new SpinnerDateModel();
		// 获得JSPinner对象
		JSpinner year2 = new JSpinner(model2);
		year2.setValue(new Date());
		// 设置时间格式
		JSpinner.DateEditor editor2 = new JSpinner.DateEditor(year2, "yyyy-MM");
		year2.setEditor(editor2);
		
		year2.setBounds(126, 69, 125, 21);
		add(year2);
		
		JButton button_1 = new JButton("\u6309\u6708\u4EFD\u67E5\u8BE2");
		button_1.setBounds(279, 68, 93, 23);
		add(button_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(62, 131, 495, 209);
		add(textPane);
		
	}
}
