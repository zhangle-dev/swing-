package com.zl.page;

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

public class UserManagerPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public UserManagerPanel() {
		setLayout(null);
		setSize(637, 405);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 617, 352);
		add(scrollPane);
		
		//TODO 传入用户列表
		List list = new ArrayList<>();
		UserTableModel userTableModel = new UserTableModel(list);
		userTableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				//TODO 表格中数据修改时保存到数据库
			}
		});
		table = new JTable(userTableModel);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0\u7528\u6237");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userTableModel.addUser();
			}
		});
		btnNewButton.setBounds(534, 10, 93, 23);
		add(btnNewButton);

	}
	
	private class UserTableModel extends AbstractTableModel{

		List list = null;
		
		public UserTableModel(List list) {
			super();
			this.list = list;
		}
		
		@Override
		public int getRowCount() {
			return list.size();
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			//TODO 在这添加你需要在表格显示的数据
			return null;
		}
		
		
		public void addUser(){
			//TODO 添加用户
		}
		
	}
}
