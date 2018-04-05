package com.zl.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class OrderManagerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public OrderManagerPanel() {
		setLayout(null);
		setSize(637, 405);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 617, 352);
		add(scrollPane);
		
		//TODO 传入菜单列表
		OrderTableModel orderTableModel = new OrderTableModel(null);
		orderTableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				//TODO 表格中数据修改时保存到数据库
			}
		});
		JTable table = new JTable(orderTableModel);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0\u7528\u6237");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderTableModel.addOrder();
			}
		});
		btnNewButton.setBounds(534, 10, 93, 23);
		add(btnNewButton);
	}
	
	private class OrderTableModel extends AbstractTableModel{

		private List list;
		public OrderTableModel(List list) {
			this.list = list;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		public void addOrder() {
			// TODO Auto-generated method stub
			
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
