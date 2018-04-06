package com.zl.page;

import com.orderfood.pojo.Menu;
import com.orderfood.pojo.Order;
import com.orderfood.pojo.OrderMenu;
import com.orderfood.service.MenuService;
import com.orderfood.service.OrderService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private OrderService orderService;
	/**
	 * Create the panel.
	 */
	public OrderManagerPanel() throws Exception {
		setLayout(null);
		setSize(637, 405);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 617, 352);
		add(scrollPane);
		
		//TODO 传入订单列表
        orderService=new OrderService();
		List<Order> list=orderService.findOrders();
		OrderTableModel orderTableModel = new OrderTableModel(list);
		orderTableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				//TODO 表格中数据修改时保存到数据库
			}
		});
		JTable table = new JTable(orderTableModel);
		scrollPane.setViewportView(table);
		table.setRowHeight(100);
		JButton btnNewButton = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int n = table.getSelectedRow();
                if (n != -1) {
                    try {
                        orderTableModel.remove(n);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    table.revalidate();
                }
            }
		});
		btnNewButton.setBounds(534, 10, 93, 23);
		add(btnNewButton);
	}
	
	private class OrderTableModel extends AbstractTableModel{

		private List<Order> list;
        private String[] tiles = {"桌号", "金额", "日期", "详情"};
        public OrderTableModel(List<Order> list) {
			this.list = list;
		}

		@Override
		public int getRowCount() {

			return list.size();
		}


        @Override
        public String getColumnName(int column) {
            return tiles[column];
        }

        @Override
		public int getColumnCount() {

			return tiles.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
            switch (columnIndex){
                case 0:
                   return list.get(rowIndex).getZhuohao();
                case 1:
                    return list.get(rowIndex).getZongjiage();
                case 2:
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return simpleDateFormat.format(list.get(rowIndex).getCreateDate());
                case 3:
                    StringBuilder sb=new StringBuilder();
                    try {
                        List<OrderMenu> ordeDetails = orderService.findOrdeDetail(list.get(rowIndex).getId());
                        ordeDetails.stream().forEach(t->{
                            sb.append(String.format("%s X %d ,",t.getName(),t.getNum()));
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return  sb.toString();
                default:
                    break;
            }
			return null;
		}

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            switch (columnIndex){
                case 0:
                    list.get(rowIndex).setZhuohao((String) aValue);
                    break;
                case 1:
                    list.get(rowIndex).setZongjiage(Float.valueOf((String)aValue));
                    break;
                case 2:
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        list.get(rowIndex).setCreateDate(simpleDateFormat.parse((String) aValue));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
            }
            try {
                orderService.update(list.get(rowIndex));
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.setValueAt(aValue, rowIndex, columnIndex);
        }

        public void remove(int n) throws Exception {

            orderService.removeOrder(list.remove(n));
        }
    }

}
