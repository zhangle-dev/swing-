package com.zl.page;

import java.awt.Component;

import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import com.orderfood.pojo.Menu;
import com.orderfood.service.MenuService;
import com.orderfood.util.PropertiUtil;
import com.orderfood.util.StringUtil;

public class MenuManagerPanel extends JPanel {
	private MenuService menuService;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public MenuManagerPanel() throws Exception {
		setLayout(null);
		setSize(637, 405);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 617, 352);
		add(scrollPane);

		menuService = new MenuService();
		MenuTableModel menuTableModel = new MenuTableModel(menuService.findMenus());
		menuTableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

			}
		});
		DefaultTableColumnModel dm = new DefaultTableColumnModel();
		dm.addColumn(new TableColumn(0));
		dm.getColumn(0).setCellEditor(new MyPicEditor());
		JTable table = new JTable(menuTableModel);
		scrollPane.setViewportView(table);
		table.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (column == 2) {
					JLabel jLabel = new JLabel(new ImageIcon((String) value));
					return jLabel;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});

		table.setRowHeight(100);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
                if (column == 2) {

                    JFileChooser jf = new JFileChooser();
                    jf.showOpenDialog(MenuManagerPanel.this);		//显示打开的文件对话框
                    File f =  jf.getSelectedFile();	//使用文件类获取选择器选择的文件
                    // 保存图片到指定目录并在数据库中保持路径
                    if (null == f) {
                        return;
                    }
                    menuTableModel.store(f, column, row);
                    table.revalidate();
                    System.out.println(f.getAbsolutePath());
                }
			}
		});

		JButton btnNewButton = new JButton("添加菜单");
		JButton btnRemoveButton = new JButton("删除");
		btnRemoveButton.addActionListener((e) -> {
			int row = table.getSelectedRow();
			if (row != -1) // 存在选中行
			{
				// table.remove(row);
				menuTableModel.remove(row);
				table.revalidate();
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuTableModel.addMenu();
				table.revalidate();
			}
		});
		btnRemoveButton.setBounds(430, 10, 93, 23);
		btnNewButton.setBounds(534, 10, 93, 23);
		add(btnNewButton);
		add(btnRemoveButton);
	}

	private class MenuTableModel extends AbstractTableModel {

		List<Menu> list = null;
		String[] colunms = { "名称", "价格", "图片" };

		public MenuTableModel(List<Menu> list) {
			this.list = list;
		}

		public void remove(int row) {
			Menu menu = list.remove(row);
			try {
				menuService.remove(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void addMenu() {
			list.add(new Menu());

		}

		@Override
		public int getRowCount() {
			return list.size();
		}

		@Override
		public int getColumnCount() {
			return colunms.length;
		}

		@Override
		public String getColumnName(int column) {
			return colunms[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return list.get(rowIndex).getName();
			case 1:
				return list.get(rowIndex).getJiage();
			case 2:

				return PropertiUtil.get("img_store") + "\\" + list.get(rowIndex).getImg();
			default:
				break;
			}
			return null;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == 2) {
                return false;
            }
            return true;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				list.get(rowIndex).setName((String) aValue);
				break;
			case 1:
				if (StringUtil.isNumber(aValue.toString())) {
					list.get(rowIndex).setJiage(Float.valueOf((String) aValue));
				} else {
					return;
				}
				break;
			default:
				break;
			}
			try {
				int id = menuService.updateMenu(list.get(rowIndex));
				list.get(rowIndex).setId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("menu 修改了");
			super.setValueAt(aValue, rowIndex, columnIndex);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {

			return String.class;
		}

        public void store(File f, int column, int row) {

            String imgStore = PropertiUtil.get("img_store");
            Path path= Paths.get(imgStore);
            String uuid=StringUtil.uuidCreate();
            try {
                path= !Files.exists(path)?Files.createDirectory(path):path;
                File files = new File(imgStore + "\\" +uuid+ ".jpg");
                if (!files.exists()) {
                    files.createNewFile();
                }
                FileInputStream astream = new FileInputStream(f);
                FileOutputStream bstream = new FileOutputStream(files);
                int len;
                byte b[] = new byte[1024];
                len = astream.read(b);
                while (len != -1) {
                    bstream.write(b, 0, len);
                    len = astream.read(b);
                }
                astream.close();
                bstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.get(row).setImg(uuid+".jpg");
            try {
                menuService.updateMenu(list.get(row));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	class MyPicEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
		/*
		 * ReadMe:当我们点击表格Cell的时候，表格检测点击的消息， 检测Cell是否允许编辑， 如果允许编辑 则去调用 表格编辑器
		 * 来获取图片，获取完后将图片 送达给 TableModel 结束编辑器的编辑状态，表格刷新显示 对应的图片
		 */
		// 用于获取图片的变量
		private Icon m_IconPic;
		// 作为 编辑器 ，当我们点击的时候进行图片的选择
		private JButton m_IconButton;
		// 点击按钮的时候 进行文件选择的 Filechooser
		private JFileChooser m_PicFileChooser;
		// 设置当我们 点击2次的时候 编辑器 才起作用
		private static final int clickCountToStart = 2;

		// 构造函数，初始化一些信息
		public MyPicEditor() {
			m_IconButton = new JButton();
			m_IconButton.addActionListener(this);
			m_PicFileChooser = new JFileChooser();
		}

		// 妫�娴嬮紶鏍囩殑鐐瑰嚮娆℃暟锛屽垽鏂紪杈戝櫒鏄惁璧蜂綔鐢�
		public boolean isCellEditable(EventObject anEvent) {
			// 濡傛灉浜嬩欢 鏄� 榧犳爣鐨勪簨浠讹紝澶т簬璁惧畾鐨勬鏁板氨true,鍚﹀垯false
			if (anEvent instanceof MouseEvent) {

				System.out.println("妫�娴嬮紶鏍囩殑鐐瑰嚮娆℃暟锛岃缃紪杈戝櫒鏄惁鍝嶅簲");
				return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
			}
			return false;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			System.out.println("表格Cell获取将要显示的编辑器组件，返回值编辑器包含的控件");
			// 先前的表格Cell的 数据 先保存下来，用于初始化编辑器包含的控件的数据
			m_IconPic = (Icon) value;
			// 返回作为编辑器的组件，这里是一个按钮
			return m_IconButton;
		}

		// 响应编辑器包含的组件的事件
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("编辑器组件事件响应");
			if (e.getSource() == m_IconButton) {
				// 初始化编辑器，显示原始的图片
				m_IconButton.setIcon(m_IconPic);
				// 显示文件选择器，用于选择图片
				m_PicFileChooser.showOpenDialog(m_IconButton);
				if (m_PicFileChooser.getSelectedFile() != null) {
					// 如果选择了新的图片将按钮设置为新的图标
					m_IconPic = new ImageIcon(m_PicFileChooser.getSelectedFile().getAbsolutePath());
				}
				// 数据获取完成，终止编辑器，将数据送达 调用者
				this.fireEditingStopped();
			}
		}

		// 将数据送达调用者，关闭编辑器，表格正常显示
		@Override
		public Object getCellEditorValue() {
			System.out.println("返回结果");
			return m_IconPic;
		}
	}

}
