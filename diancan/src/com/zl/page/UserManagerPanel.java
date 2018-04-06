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

		// �����û��б�
		userService = new UserService();

		List<User> list = userService.findUsers();
		UserTableModel userTableModel = new UserTableModel(list);
		userTableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO ����������޸�ʱ���浽���ݿ�
				System.out.println(e.getColumn());
			}
		});
		table = new JTable(userTableModel);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("添加");
		JButton btnRemove = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userTableModel.addUser();
				table.revalidate();
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {

					userTableModel.remove(row);
					table.revalidate();
				}
			}
		});
		btnRemove.setBounds(425, 10, 93, 23);
		btnNewButton.setBounds(534, 10, 93, 23);
		add(btnNewButton);
		add(btnRemove);
		table.setRowHeight(100);

	}

	private class UserTableModel extends AbstractTableModel {

		List<User> list = null;
		private String[] names = {"姓名","用户名","密码","角色"};

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
			// TODO �����������Ҫ�ڱ����ʾ������

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
			// TODO ����û�
			this.list.add(new User());	
		}
		
		@Override
		public String getColumnName(int column) {
			return names[column];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// �������ʽ����ÿ����Ԫ��ı༭���Ե�
			// �������AbstractTableModel�Ѿ�ʵ�֣�Ĭ�ϵ��� ������༭״̬
			// ������������Ϊ����༭״̬
			return true;// super.isCellEditable(rowIndex, columnIndex);
		}
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
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
			System.out.println("�޸�������");
			try {
				int id=userService.updateUser(list.get(rowIndex));
				list.get(rowIndex).setId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			super.setValueAt(aValue, rowIndex, columnIndex);
		}

		public void remove(int row) {
			userService.remove(list.remove(row));
		}
	}
}
