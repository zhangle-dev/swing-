package com.zl.page;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class ItemCom extends JPanel {
	
	private String name;
	private float price;
	private JTextField n;
	private ActionListener actionListener;

	/**
	 * Create the panel.
	 */
	public ItemCom(String name,float price) {
		this.name = name;
		this.price = price;
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setBounds(5, 10, 54, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(70, 10, 12, 15);
		add(lblNewLabel_1);
		
		JLabel lblX = new JLabel(" x");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ItemCom item = ItemCom.this;
				Container p = item .getParent();
				p.remove(item);
				p.repaint();
				p.revalidate();
				
			}
		});
		lblX.setBounds(128, 10, 18, 15);
		add(lblX);
		
		n = new JTextField("1");
		n.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				actionListener.actionPerformed(null);
				System.out.println("inputMethodTextChanged");
			}
		});
		n.setBounds(80, 7, 32, 21);
		add(n);
		n.setColumns(10);
	}
	
	public float getManay(){
		return price * Integer.valueOf(n.getText());
	}
	
	public void setActionListeneraa(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
}
