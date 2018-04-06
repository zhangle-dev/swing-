package com.zl.page;

import com.orderfood.pojo.OrderMenu;
import com.orderfood.service.OrderService;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;

public class TongJiPanel extends JPanel {
	private OrderService orderService;
	/**
	 * Create the panel.
	 */
	public TongJiPanel() {
		setSize(637, 405);
		setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u65E5\u671F");
		label.setBounds(62, 41, 54, 15);
		add(label);
		
		SpinnerDateModel model = new SpinnerDateModel();
		// ���JSPinner����
		JSpinner year = new JSpinner(model);
		year.setValue(new Date());
		// ����ʱ���ʽ
		JSpinner.DateEditor editor = new JSpinner.DateEditor(year, "yyyy-MM-dd");
		year.setEditor(editor);
		
		year.setBounds(126, 38, 125, 21);
		add(year);
		
		JButton button = new JButton("\u6309\u65E5\u671F\u67E5\u8BE2");
		button.setBounds(279, 37, 138, 23);
		add(button);
		JLabel label_1 = new JLabel("\u8F93\u5165\u6708\u4EFD");
        label_1.setBounds(62, 72, 54, 15);
        add(label_1);

        SpinnerDateModel model2 = new SpinnerDateModel();
        // ���JSPinner����
        JSpinner year2 = new JSpinner(model2);
        year2.setValue(new Date());
        // ����ʱ���ʽ
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(year2, "yyyy-MM");
        year2.setEditor(editor2);

        year2.setBounds(126, 69, 125, 21);
        add(year2);

        JButton button_1 = new JButton("\u6309\u6708\u4EFD\u67E5\u8BE2");
        button_1.setBounds(279, 68, 138, 23);
        add(button_1);
        button_1.addActionListener(e -> {
            System.out.println(year2.getValue());

        });
        JTextPane textPane = new JTextPane();
        textPane.setBounds(44, 112, 544, 249);
        textPane.setEditable(false);
        class MyActionListener implements ActionListener {
            private Date d = null;
            private Boolean b = null;
            MyActionListener(Date d,Boolean b){
                this.d = d;
                this.b = b;
            }
            @Override
            public void actionPerformed(ActionEvent e) {

                orderService = new OrderService();
                List<OrderMenu> list= orderService.findMenuDetail(d,b);
                float[] num={0.0f};
                if (null != list) {
                    StringBuilder sb = new StringBuilder();
                    list.stream().forEach(t->{
                        sb.append(String.format("%s\t X %d \t %d元\n", t.getName(), t.getNum(),t.getPrice()));
                        num[0] += t.getPrice();
                    });
                    System.out.println(sb.toString());
                    textPane.setText(sb.toString() + "\t\n" + "总价：" + num[0]+"元");


                }
            }
        }

        button.addActionListener(new MyActionListener((Date) year.getValue(),true));
        button_1.addActionListener(new MyActionListener((Date) year2.getValue(),false));

        add(textPane);

    }
}
