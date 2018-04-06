package com.zl.page;

import com.orderfood.pojo.Menu;
import com.orderfood.pojo.Order;
import com.orderfood.pojo.OrderDetail;
import com.orderfood.service.MenuService;
import com.orderfood.service.OrderDetailService;
import com.orderfood.service.OrderService;
import com.orderfood.util.PropertiUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
//���
public class DCPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel_2;
	private JLabel zongjine_lab;
	private MenuService menuService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DCPage frame = new DCPage();
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
	public DCPage() {
		menuService = new MenuService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(169, 0, 621, 440);
		contentPane.add(scrollPane_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(620,HEIGHT));
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		initFood(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 170, 440);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u684C\u53F7\uFF1A");
		label.setBounds(20, 10, 41, 15);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(61, 7, 66, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u603B\u91D1\u989D");
		lblNewLabel.setBounds(10, 383, 41, 15);
		panel.add(lblNewLabel);
		
		zongjine_lab = new JLabel("");
		zongjine_lab.setBounds(61, 383, 54, 15);
		panel.add(zongjine_lab);
		
		JButton button = new JButton("\u7ED3\u7B97");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO ��������Ķ���
				int count = panel_2.getComponentCount();
				float num = 0;
                List<ItemCom> list = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    ItemCom com = (ItemCom) panel_2.getComponent(i);
                    num += com.getManay();
                    list.add(com);
                }
                panel_2.removeAll();
                panel_2.revalidate();


//				numΪ�ܽ��
                OrderService orderService = new OrderService();
//                OrderDetailService od=new OrderDetailService();
                Order order = new Order();
                order.setCreateDate(new Date());
                order.setZongjiage(num);
                order.setZhuohao(textField.getText());
                orderService.save(order, list);
                textField.setText("");
                textField.revalidate();
            }
		});
		button.setBounds(34, 408, 93, 23);
		panel.add(button);
		
		panel_2 = new JPanel();
		panel_2.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				resetPrice();
			}
		});
		panel_2.setBounds(10, 30, 150, 345);
		panel.add(panel_2);
		
	}

	private void initFood(JPanel panel_1)  {
		//TODO ���Ԫ��Ӧ�ô����ݿ��в���
		List<Menu> list=null;
		try {
			list=menuService.findMenus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.stream().forEach(t->{
            FoodCom food = new FoodCom(t.getName(),t.getJiage(), PropertiUtil.get("img_store")+"\\"+t.getImg());
            food.addActionListeneraa(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ItemCom item = new ItemCom(food.getFoodName(),food.getPrice(),t.getId());
                    item.setPreferredSize(new Dimension(150, 30));
                    item.setActionListeneraa(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            resetPrice();
                        }
                    });
                    panel_2.add(item);
                    panel_2.revalidate();

                    resetPrice();
                }

            });
            food.setPreferredSize(new Dimension(100, 150));
            panel_1.add(food);
        });
}
	
	private void resetPrice() {
		int count = panel_2.getComponentCount();
		float num = 0;
		for (int i = 0; i < count; i++) {
			ItemCom com = (ItemCom) panel_2.getComponent(i);
			num += com.getManay();
		}
		zongjine_lab.setText(num + "元");
	}
}
