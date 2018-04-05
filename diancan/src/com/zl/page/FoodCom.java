package com.zl.page;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoodCom extends JPanel {

	private String foodName;
	private float price;
	private ActionListener actionListeneraa;
	/**
	 * Create the panel.
	 */
	public FoodCom(String name,float price,String img) {
		this.foodName = name;
		this.price = price;
		setLayout(null);
		
		JButton btnNewButton = new JButton(new ImageIcon(img));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListeneraa.actionPerformed(e);
			}
		});
		btnNewButton.setBounds(10, 10, 93, 82);
		add(btnNewButton);
		
		JLabel n = new JLabel(name);
		n.setBounds(31, 92, 54, 15);
		add(n);
		
		JLabel p = new JLabel(price+"Ԫ");
		p.setBounds(31, 114, 54, 15);
		add(p);
		
		setBounds(0, 0, 120, 140);
	}
	
	public void addActionListeneraa(ActionListener actionListener){
		this.actionListeneraa = actionListener;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	

}
