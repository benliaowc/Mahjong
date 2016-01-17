import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;

import java.util.ArrayList;

class FrameTest extends JFrame {

	private JPanel contentPane;
	JButton btnOpen;
	ArrayList<JLabel> table;
	private JPanel myPlayer;
	private JPanel myPlayerOpen;
	private JButton btnClear;
	private JButton btnDisable;
	private JButton button_1;
	private JPanel playerRight;
	private JPanel playerLeft;
	private JPanel playerUpOpen;
	private JPanel playerUp;
	private JPanel playerRightOpen;
	private JPanel playerLeftOpen;
	private JButton btnReset;
	//private JButton button;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest frame = new FrameTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void startThread()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest frame = new FrameTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void start()
	{
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public FrameTest() {
		this.setTitle("POOMahjong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 10, 796, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new ArrayList<JLabel>();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 100, 0));
		panel.setBounds(137, 114, 499, 435);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		for(int i = 0; i < 134; i++){
			if(i % 4  == 3){
				addLabel(panel, i%4 , i%7+1, false);
			}
			else
				addLabel(panel, i%4 , i%9+1, false);
		}
		/*for(int i = 0; i < 4; i++){
			int length = 0;
			if(i < 3)
				length = 9;
			else
				length = 7;
			for(int j = 0; j < length; j++){
				addLabel(panel, i , j+1, false);
			}
		}*/
		
		
		myPlayer = new JPanel();
		myPlayer.setBounds(137, 611, 499, 42);
		contentPane.add(myPlayer);
		myPlayer.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		/*for(int i = 0; i < 14; i++)
			addButton(panel_1, panel, 0, 1);*/
		addButton(myPlayer, panel, 0, 1);
		addButton(myPlayer, panel, 0, 9);
		addButton(myPlayer, panel, 1, 1);
		addButton(myPlayer, panel, 1, 9);
		addButton(myPlayer, panel, 2, 1);
		addButton(myPlayer, panel, 2, 9);
		addButton(myPlayer, panel, 3, 1);
		addButton(myPlayer, panel, 3, 2);
		addButton(myPlayer, panel, 3, 3);
		addButton(myPlayer, panel, 3, 4);
		addButton(myPlayer, panel, 3, 5);
		addButton(myPlayer, panel, 3, 6);
		addButton(myPlayer, panel, 3, 7);
		addButton(myPlayer, panel, 3, 7);
		
		
		btnOpen = new JButton("open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameOpen();
				btnOpen.setEnabled(false);
			}
		});
		btnOpen.setBounds(10, 597, 74, 23);
		contentPane.add(btnOpen);
		
		myPlayerOpen = new JPanel();
		myPlayerOpen.setBounds(137, 559, 499, 42);
		contentPane.add(myPlayerOpen);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int length = panel.getComponentCount();
				for(int i = 0; i < length; i++){
					//panel.remove(0);
					removeLabel(panel, 0);
				}
				//panel.revalidate();
				//panel.repaint();
				
			}
		});
		btnClear.setBounds(10, 564, 74, 23);
		contentPane.add(btnClear);
		
		btnDisable = new JButton("disable");
		btnDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeEable(false);
			}
		});
		btnDisable.setBounds(646, 630, 83, 23);
		contentPane.add(btnDisable);
		
		button_1 = new JButton("enable");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeEable(true);
			}
		});
		button_1.setBounds(646, 597, 83, 23);
		contentPane.add(button_1);
		
		playerRight = new JPanel();
		playerRight.setBounds(714, 62, 56, 499);
		contentPane.add(playerRight);
		
		playerLeft = new JPanel();
		playerLeft.setBounds(5, 62, 56, 499);
		contentPane.add(playerLeft);
		
		playerUpOpen = new JPanel();
		playerUpOpen.setBounds(137, 62, 499, 42);
		contentPane.add(playerUpOpen);
		playerUpOpen.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		playerUp = new JPanel();
		playerUp.setBounds(137, 10, 499, 42);
		contentPane.add(playerUp);
		
		playerRightOpen = new JPanel();
		playerRightOpen.setBounds(648, 62, 56, 499);
		contentPane.add(playerRightOpen);
		
		playerLeftOpen = new JPanel();
		playerLeftOpen.setBounds(71, 62, 56, 499);
		contentPane.add(playerLeftOpen);
		
		btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setBounds(10, 630, 64, 23);
		contentPane.add(btnReset);
		
		for(int i = 0; i < 14; i++){
			addLabel(playerRight, 0, 0, true);
			addLabel(playerLeft,0, 0, true);
			addLabel(playerUpOpen,0, 0, false);
			addLabel(playerUp,0 , 0, false);
			addLabel(playerRightOpen,0, 0, true);
			addLabel(playerLeftOpen,0, 0, true);
			addLabel(myPlayerOpen,0, 0, false);
		}
		
	}
	
	public void changeEable(boolean b)
	{
		for(Component component : ((Container)myPlayer).getComponents()) {
		    component.setEnabled(b);
		}
	}
	public void addButton(JPanel panel, JPanel tablePanel, int suit, int value)
	{
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeButton(panel, button);
				addLabel(tablePanel, suit, value, false);
			}
		});
		button.setIcon(decideIcon(suit, value, false));
		button.setPreferredSize(new java.awt.Dimension(30, 37));
		panel.add(button);
	}
	public void removeButton(JPanel panel, JButton button)
	{
		panel.remove(button);
		panel.revalidate();
		panel.repaint();
	}
	
	public void removeLabel(JPanel panel, int index)
	{
		panel.remove(index);
		panel.revalidate();
		panel.repaint();
	}
	public void removeLabel(JPanel panel, JLabel label)
	{
		panel.remove(label);
		panel.revalidate();
		panel.repaint();
	}
	public void addLabel(JPanel panel, int suit, int value, boolean fall)
	{
		JLabel label = new JLabel("");
			
		label.setIcon(decideIcon(suit, value, fall));
		if(fall)
			label.setPreferredSize(new java.awt.Dimension(37, 30));
		else
			label.setPreferredSize(new java.awt.Dimension(30, 37));
		panel.add(label);
		
		//table.add(label);
	}
	public ImageIcon decideIcon(int suit, int value, boolean fall)
	{
		String filePath = "";
		if(value == 0)
			filePath = "icon/cover";
		else if(suit == 0)
			filePath = "icon/character_" + value;
		else if(suit == 1)
			filePath = "icon/dot_" + value;
		else if(suit == 2)
			filePath = "icon/bamboo_" + value;
		else if(suit == 3){
			if(value < 5)
				filePath = "icon/wind_" + value;
			else
				filePath = "icon/dragon_" + (value % 4);
		}
		if(fall)
			filePath += "_fall.png";
		else
			filePath += ".png";
		return (new ImageIcon(FrameTest.class.getResource(filePath)));
	}
	
	public void frameOpen()
	{
		JFrame frame = new JFrame ("請選擇");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.setSize(300, 250);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        frame.getContentPane().add(panel);
        //frame.pack();
        
        changeEable(false);
		
        boolean[] choice = {false, false, false};
        
		ButtonGroup group = new ButtonGroup();
		group.add(addRadioButton(panel, "吃", 0, choice));
		group.add(addRadioButton(panel, "碰", 1, choice));
		group.add(addRadioButton(panel, "槓", 2, choice));
		
		
		JButton btnClose = new JButton("確認");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choice[0]|choice[1]|choice[2]){
					btnOpen.setEnabled(true);
					changeEable(true);
					if(choice[0])
						panel.add(new JLabel("吃"));
					if(choice[1])
						panel.add(new JLabel("碰"));
					if(choice[2])
						panel.add(new JLabel("槓"));
					panel.revalidate();
					panel.repaint();
					frame.dispose();
					hu(true);
				}
			}
		});
		btnClose.setBounds(607, 91, 87, 23);
		panel.add(btnClose);
		
        frame.setVisible (true);
	}
	
	public JRadioButton addRadioButton(JPanel panel, String name, int index, boolean[] choice)
	{
		JRadioButton rdbtnNewRadioButton = new JRadioButton(name);
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				for(int i = 0; i < choice.length; i++)
					choice[i] = false;
				choice[index] = true;
			}
		});
		rdbtnNewRadioButton.setBounds(592, 246, 107, 23);
		panel.add(rdbtnNewRadioButton);
		return rdbtnNewRadioButton;
	}
	
	public void reset()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new ArrayList<JLabel>();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 100, 0));
		panel.setBounds(137, 114, 499, 435);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		/*for(int i = 0; i < 134; i++){
			if(i % 4  == 3){
				addLabel(panel, i%4 , i%7+1, false);
			}
			else
				addLabel(panel, i%4 , i%9+1, false);
		}*/
		for(int i = 0; i < 4; i++){
			int length = 0;
			if(i < 3)
				length = 9;
			else
				length = 7;
			for(int j = 0; j < length; j++){
				addLabel(panel, i , j+1, false);
			}
		}
		
		
		myPlayer = new JPanel();
		myPlayer.setBounds(137, 611, 499, 42);
		contentPane.add(myPlayer);
		myPlayer.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		addButton(myPlayer, panel, 0, 1);
		addButton(myPlayer, panel, 0, 9);
		addButton(myPlayer, panel, 1, 1);
		addButton(myPlayer, panel, 1, 9);
		addButton(myPlayer, panel, 2, 1);
		addButton(myPlayer, panel, 2, 9);
		addButton(myPlayer, panel, 3, 1);
		addButton(myPlayer, panel, 3, 2);
		addButton(myPlayer, panel, 3, 3);
		addButton(myPlayer, panel, 3, 4);
		addButton(myPlayer, panel, 3, 5);
		addButton(myPlayer, panel, 3, 6);
		addButton(myPlayer, panel, 3, 7);
		addButton(myPlayer, panel, 3, 7);
		
		
		btnOpen = new JButton("open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameOpen();
				btnOpen.setEnabled(false);
			}
		});
		btnOpen.setBounds(10, 597, 74, 23);
		contentPane.add(btnOpen);
		
		myPlayerOpen = new JPanel();
		myPlayerOpen.setBounds(137, 559, 499, 42);
		contentPane.add(myPlayerOpen);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int length = panel.getComponentCount();
				for(int i = 0; i < length; i++){
					removeLabel(panel, 0);
				}
			}
		});
		btnClear.setBounds(10, 564, 74, 23);
		contentPane.add(btnClear);
		
		btnDisable = new JButton("disable");
		btnDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeEable(false);
			}
		});
		btnDisable.setBounds(646, 630, 83, 23);
		contentPane.add(btnDisable);
		
		button_1 = new JButton("enable");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeEable(true);
			}
		});
		button_1.setBounds(646, 597, 83, 23);
		contentPane.add(button_1);
		
		playerRight = new JPanel();
		playerRight.setBounds(714, 62, 56, 499);
		contentPane.add(playerRight);
		
		playerLeft = new JPanel();
		playerLeft.setBounds(5, 62, 56, 499);
		contentPane.add(playerLeft);
		
		playerUpOpen = new JPanel();
		playerUpOpen.setBounds(137, 62, 499, 42);
		contentPane.add(playerUpOpen);
		playerUpOpen.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		playerUp = new JPanel();
		playerUp.setBounds(137, 10, 499, 42);
		contentPane.add(playerUp);
		
		playerRightOpen = new JPanel();
		playerRightOpen.setBounds(648, 62, 56, 499);
		contentPane.add(playerRightOpen);
		
		playerLeftOpen = new JPanel();
		playerLeftOpen.setBounds(71, 62, 56, 499);
		contentPane.add(playerLeftOpen);
		
		btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setBounds(10, 630, 64, 23);
		contentPane.add(btnReset);
		
		/*for(int i = 0; i < 14; i++){
			addLabel(playerRight, 0, 0, true);
			addLabel(playerLeft,0, 0, true);
			addLabel(playerUpOpen,0, 0, false);
			addLabel(playerUp,0 , 0, false);
			addLabel(playerRightOpen,0, 0, true);
			addLabel(playerLeftOpen,0, 0, true);
			addLabel(myPlayerOpen,0, 0, false);
		}*/
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void hu(boolean win)
	{
		changeEable(false);
		JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.setSize(300, 250);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        frame.getContentPane().add(panel);
		if(win){
			panel.add(new JLabel("你贏了!"));
		}
		else{
			panel.add(new JLabel("你輸了!"));
		}
		
		
		JButton btnClose = new JButton("確認");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeEable(true);
				reset();
				frame.dispose();
			}
		});
		btnClose.setBounds(607, 91, 87, 23);
		panel.add(btnClose);
		
        frame.setVisible (true);
	}
	
}
