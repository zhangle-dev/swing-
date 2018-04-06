package com.zl.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.ibatis.session.SqlSession;

import com.orderfood.pojo.User;
import com.orderfood.service.UserService;
import com.orderfood.util.SqlSessionUtil;
import com.orderfood.util.StringUtil;

public class UserManagerPanel extends JPanel {
	private JTable table;
	private UserService userService;
	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public UserManagerPanel() throws Exception {
		setLayout(null);
		setSize(637, 405);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 617, 352);
		add(scrollPane);

		// 传入用户列表
		userService = new UserService();

		List<User> list = userService.findUsers();
		UserTableModel userTableModel = new UserTableModel(list);
		userTableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO 表格中数据修改时保存到数据库
				System.out.println(e.getColumn());
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

	private class UserTableModel extends AbstractTableModel {

		List<User> list = null;

		public UserTableModel(List<User> list) {
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
			// TODO 在这添加你需要在表格显示的数据

			if (!list.isEmpty()) {
				User user = list.get(rowIndex);
				
				if (columnIndex == 0) {
					return user.getName();
				}
				if (columnIndex == 1) {
					return user.getUsername();
				}
				
				if(columnIndex ==2) {
					return user.getPassword();
				}
				if(columnIndex ==3) {
					return user.getRole();
				}
			}
			return null;
		}

		public void addUser() {
			// TODO 添加用户
			this.list.add(new User());	
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// 这个函数式设置每个单元格的编辑属性的
			// 这个函数AbstractTableModel已经实现，默认的是 不允许编辑状态
			// 这里我们设置为允许编辑状态
			return true;// super.isCellEditable(rowIndex, columnIndex);
		}
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			switch (columnIndex) {
			case 0:
				list.get(rowIndex).setName((String)aValue);
				break;
			case 1:
				list.get(rowIndex).setUsername((String)aValue);
				break;
			case 2:
				list.get(rowIndex).setPassword((String)aValue);
				break;
			case 3:
				if(StringUtil.isNumber(aValue.toString())){
					
					list.get(rowIndex).setRole(Integer.valueOf((String)aValue));
				}else {
					return;
				}
				break;
			default:
				break;
			}
			System.out.println("修改数据了");
			try {
				int id=userService.updateUser(list.get(rowIndex));
				list.get(rowIndex).setId(id);
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			super.setValueAt(aValue, rowIndex, columnIndex);
		}
	}
}
