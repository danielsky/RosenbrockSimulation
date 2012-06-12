package com.daniel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296516091843556125L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ustawienia.wczytajDane();
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				MainWindow mw = new MainWindow();
				mw.setVisible(true);
			}
		});

	}
	
	/*PoleWpisu p = new PoleWpisu("Mm/s");
	p.setColumns(10);
	panel2.add(p);*/
	
	
	
	
	private JPanel contentPane;
	private JLabel lblStatus;
	private JPanel panelDane;
	private JPanel panelObliczenia;
	private DefaultTableModel modelUstawien;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panelLogi;
	private JPanel panelPrawy;
	private static DefaultListModel<MyLog> modelLog;
	private JList<MyLog> list;
	private JScrollPane scrollPane_1;
	
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(0, 0, 500, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		panelDane = new JPanel();
		panelDane.setPreferredSize(new Dimension(300, 10));
		panelDane.setMinimumSize(new Dimension(300, 10));
		panelDane.setMaximumSize(new Dimension(300, 32767));
		contentPane.add(panelDane);
		panelDane.setLayout(new BoxLayout(panelDane, BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		panelDane.add(scrollPane);
		
		modelUstawien = new DefaultTableModel(new Object[]{"Zmienna", "Wartość"}, 0);
		
		
		modelUstawien.addRow(new Object[]{"ro_t",2.0});
		modelUstawien.addRow(new Object[]{"c_t",1000.0});
		modelUstawien.addRow(new Object[]{"T_t",1.2});
		modelUstawien.addRow(new Object[]{"ro_s",1000.0});
		modelUstawien.addRow(new Object[]{"c_s",8920.0});
		modelUstawien.addRow(new Object[]{"T_s",380.0});
		modelUstawien.addRow(new Object[]{"ro_p",0.0});
		modelUstawien.addRow(new Object[]{"c_p",0.0});
		modelUstawien.addRow(new Object[]{"T_p",0.0});
		modelUstawien.addRow(new Object[]{"ro_z",0.0});
		modelUstawien.addRow(new Object[]{"c_z",0.0});
		modelUstawien.addRow(new Object[]{"T_z",0.0});
		modelUstawien.addRow(new Object[]{"alfa_s",0.0});
		modelUstawien.addRow(new Object[]{"alfa_3",0.0});
		modelUstawien.addRow(new Object[]{"alfa_t",0.0});
		modelUstawien.addRow(new Object[]{"d1",0.0});
		modelUstawien.addRow(new Object[]{"d2",0.0});
		modelUstawien.addRow(new Object[]{"d3",0.0});
		modelUstawien.addRow(new Object[]{"d4",0.0});
		modelUstawien.addRow(new Object[]{"v_t",0.0});
		modelUstawien.addRow(new Object[]{"v_s",0.0});
		modelUstawien.addRow(new Object[]{"dlugosc",0.0});
		modelUstawien.addRow(new Object[]{"maksymalna ilość iteracji",1000});
		modelUstawien.addRow(new Object[]{"krok",1.0});
		modelUstawien.addRow(new Object[]{"wspólczynnik ekspansji",3.0});
		modelUstawien.addRow(new Object[]{"współczynnik kontrakcji",0.5});
		modelUstawien.addRow(new Object[]{"ilość poszukiwanych zmiennych",3});
		modelUstawien.addRow(new Object[]{"Wynik T_t",10});
		modelUstawien.addRow(new Object[]{"Wynik T_s",20});
		modelUstawien.addRow(new Object[]{"wynik T_p",30});
		modelUstawien.addRow(new Object[]{"wynik T_z",40});
		//modelUstawien.addRow(new Object[]{"",});
		
		
		
		
		table = new JTable(modelUstawien){
			
			
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 4629496465599561112L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}
		};
		table.getColumnModel().getColumn(0).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new Renderer());

		scrollPane.setViewportView(table);
		
		panelPrawy = new JPanel();
		contentPane.add(panelPrawy);
		panelPrawy.setLayout(new BoxLayout(panelPrawy, BoxLayout.Y_AXIS));
		
		panelObliczenia = new JPanel();
		panelPrawy.add(panelObliczenia);
		
		lblStatus = new JLabel("Status");
		panelObliczenia.add(lblStatus);
		
		JButton btnWczytajDane = new JButton("Wczytaj dane");
		btnWczytajDane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Ustawienia.wczytajDane();	
				Random r = new Random();
				for(int i=0;i<20;i++){
					System.out.println(String.format("tableColors[%d] = new Color(%d, %d, %d);",i,r.nextInt(156)+100,r.nextInt(156)+100,r.nextInt(156)+100));
				}
			}
		});
		panelObliczenia.add(btnWczytajDane);
		
		panelLogi = new JPanel();
		panelLogi.setPreferredSize(new Dimension(10, 200));
		panelLogi.setMinimumSize(new Dimension(10, 200));
		panelLogi.setMaximumSize(new Dimension(32767, 200));
		panelPrawy.add(panelLogi);
		panelLogi.setLayout(new BoxLayout(panelLogi, BoxLayout.X_AXIS));
		
		scrollPane_1 = new JScrollPane();
		panelLogi.add(scrollPane_1);
		
		modelLog = new DefaultListModel<MyLog>();
		list = new JList<MyLog>(modelLog);
		list.setCellRenderer(new MyLogRenderer());
		scrollPane_1.setViewportView(list);
		setTitle("Rurowy Wymiennik ciepła");
	}
	
	public static void addLog(MyLog log){
		modelLog.addElement(log);
	}
	
	public static void addLog(String log, Color c){
		modelLog.addElement(new MyLog(c, log));
	}
	
	public static void clearLog(){
		modelLog.clear();
	}
	
	
	class Renderer implements TableCellRenderer{
		
		
		private JPanel panel;
		private JLabel label;
		private Color[] tableColors;
		
		public Renderer() {
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			label = new JLabel();
			label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			panel.add(label);
			tableColors = new Color[20];
			tableColors[0] = new Color(122, 240, 119);
			tableColors[1] = new Color(220, 181, 226);
			tableColors[2] = new Color(124, 143, 128);
			tableColors[3] = new Color(199, 113, 157);
			tableColors[4] = new Color(146, 155, 232);
			tableColors[5] = new Color(112, 195, 148);
			tableColors[6] = new Color(156, 134, 173);
			tableColors[7] = new Color(150, 204, 211);
			tableColors[8] = new Color(169, 195, 247);
			tableColors[9] = new Color(229, 104, 100);
			tableColors[10] = new Color(128, 136, 182);
			tableColors[11] = new Color(177, 216, 218);
			tableColors[12] = new Color(194, 197, 128);
			tableColors[13] = new Color(198, 210, 180);
			tableColors[14] = new Color(166, 205, 135);
			tableColors[15] = new Color(130, 152, 170);
			tableColors[16] = new Color(142, 215, 141);
			tableColors[17] = new Color(227, 134, 174);
			tableColors[18] = new Color(119, 150, 118);
			tableColors[19] = new Color(248, 154, 110);
		}

		@Override
		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int row, int column) {
			label.setText(arg1.toString());
			
			if(row < 3) panel.setBackground(tableColors[0]);
			else if(row >= 3 && row < 6) panel.setBackground(tableColors[1]);
			else if(row >= 6 && row < 9) panel.setBackground(tableColors[2]);
			else if(row >= 9 && row < 12) panel.setBackground(tableColors[3]);
			else if(row >= 12 && row < 15) panel.setBackground(tableColors[5]);
			else if(row >= 15 && row < 19) panel.setBackground(tableColors[6]);
			else if(row >= 19 && row < 21) panel.setBackground(tableColors[7]);
			else if(row >= 21 && row < 22) panel.setBackground(tableColors[9]);
			else panel.setBackground(Color.WHITE);
			label.setForeground(Color.BLACK);
			return panel;
		}
		
	}
	
	class MyLogRenderer implements ListCellRenderer<MyLog>{
		
		private JLabel label;

		public MyLogRenderer() {
			label = new JLabel();
		}
		@Override
		public Component getListCellRendererComponent(JList<? extends MyLog> arg0, MyLog value, int arg2,boolean arg3, boolean arg4) {
			label.setText(value.getString());
			label.setForeground(value.getColor());
			return label;
		}
		
	}
	
	
	
}
