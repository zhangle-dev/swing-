package com.zl.page;

import com.orderfood.pojo.Menu;
import com.orderfood.pojo.Order;
import com.orderfood.service.MenuService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class OrderManagerPanel extends JPanel {
	private MenuService menuService;
	/**
	 * Create the panel.
	 */
	public OrderManagerPanel() throws Exception {
		setLayout(null);
		setSize(637, 405);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 617, 352);
		add(scrollPane);
		
		//TODO 传入菜单列表
        menuService=new MenuService();
		List<Menu> menus = menuService.findMenus();
		OrderTableModel orderTableModel = new OrderTableModel(menus);
		orderTableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				//TODO 表格中数据修改时保存到数据库
			}
		});
		JTable table = new JTable(orderTableModel);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("添加添加订单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderTableModel.addOrder();
			}
		});
		btnNewButton.setBounds(534, 10, 93, 23);
		add(btnNewButton);
	}
	
	private class OrderTableModel extends AbstractTableModel{

		private List<Menu> list;
        private List<Order> orderList = new ArrayList<>();

        public OrderTableModel(List<Menu> list) {
			this.list = list;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		public void addOrder() {
			// TODO
			
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
