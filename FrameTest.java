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
import javax.swing.JDialog;
import java.awt.Dialog;

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
	private JPanel tablePanel;
	private JToggleButton tglbtnToggleButton;
	
	
	private ArrayList<Tile> rightPlayerOpenTile;
	private ArrayList<Tile> upPlayerOpenTile;
	private ArrayList<Tile> leftPlayerOpenTile;
	private ArrayList<Tile> myPlayerOpenTile;
	private ArrayList<Tile> tableTile;
	private ArrayList<Tile> myPlayerHandTile;
	
	private int numRightPlayer, numUpPlayer, numLeftPlayer;
	
	private boolean[] choice = {false, false, false, false, false, false}; /*choose 吃 碰 槓 聽 胡 不要*/ 
	private boolean[] select = {false, false, false, false, false}; /*you can choose 吃 碰 槓 聽 胡*/
	private int chowOption;
	private ArrayList<ArrayList<Tile>> chewChoice;
	
	private int thrower;
	private Tile newTile;
	
	public volatile boolean ok;
	public ArrayList<Tile> push;
	
	
	
	

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
		
		ok = false;
		push = new ArrayList<Tile>();
		
		rightPlayerOpenTile = new ArrayList<Tile>();
		upPlayerOpenTile = new ArrayList<Tile>();
		leftPlayerOpenTile = new ArrayList<Tile>();
		myPlayerOpenTile = new ArrayList<Tile>();
		tableTile = new ArrayList<Tile>();
		myPlayerHandTile = new ArrayList<Tile>();
		
		numRightPlayer = 0;
		numUpPlayer = 0;
		numLeftPlayer = 0;
		
		
		this.setTitle("POOMahjong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 10, 796, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new ArrayList<JLabel>();
		
		tablePanel = new JPanel();
		tablePanel.setBackground(new Color(0, 100, 0));
		tablePanel.setBounds(137, 114, 499, 435);
		contentPane.add(tablePanel);
		tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		for(int i = 0; i < 134; i++){
			if(i % 4  == 3){
				addLabel(tablePanel, i%4 , i%7+1, false);
			}
			else
				addLabel(tablePanel, i%4 , i%9+1, false);
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
		
		addTestHand();
		
		btnOpen = new JButton("open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameOpen();
				//btnOpen.setEnabled(false);
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
				/*int length = tablePanel.getComponentCount();
				for(int i = 0; i < length; i++){
					removeLabel(tablePanel, 0);
				}*/
				clear();
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
	
	public void renew()
	{
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void changeEable(boolean b)
	{
		for(Component component : ((Container)myPlayer).getComponents()) {
		    component.setEnabled(b);
		}
	}
	public void addButton(JPanel panel, JPanel tablePanel, int suit, int value)
	{
		//JButton button = new JButton("");
		JToggleButton button = new JToggleButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeButton(panel, button);
				addLabel(tablePanel, suit, value, false);
				sendToBoard(suit, value);
			}
		});
		button.setIcon(decideIcon(suit, value, false));
		button.setSelectedIcon(decideIcon(0, 0, false));
		button.setPreferredSize(new java.awt.Dimension(30, 37));
		panel.add(button);
		renew();
	}
	public void addButton(int suit, int value)
	{
		addButton(myPlayer, tablePanel, suit, value);
	}
	public JButton addButton(JPanel panel, String name, int index, boolean[] choice, JDialog dialog)
	{
		JButton rdbtnNewRadioButton = new JButton(name);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < choice.length; i++)
					choice[i] = false;
				choice[index] = true;
				
				btnOpen.setEnabled(true);
				changeEable(true);
				
				doChoice(choice, panel);
				panel.revalidate();
				panel.repaint();
				dialog.dispose();
				//frame.dispose();
			}
		});
		rdbtnNewRadioButton.setBounds(592, 246, 107, 23);
		panel.add(rdbtnNewRadioButton);
		return rdbtnNewRadioButton;
	}
	public void removeButton(JPanel panel, JButton button)
	{
		panel.remove(button);
		panel.revalidate();
		panel.repaint();
	}
	public void removeButton(JPanel panel, JToggleButton button)
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
		renew();
		//table.add(label);
	}
	public ImageIcon decideIcon(int suit, int value, boolean fall)
	{
		String filePath = "./icon";
		if(value == 0)
			filePath += "/cover";
		else if(suit == 0)
			filePath += "/character_" + value;
		else if(suit == 1)
			filePath += "/dot_" + value;
		else if(suit == 2)
			filePath += "/bamboo_" + value;
		else if(suit == 3){
			if(value < 5)
				filePath += "/wind_" + value;
			else
				filePath += "/dragon_" + (value % 4);
		}
		if(fall)
			filePath += "_fall.png";
		else
			filePath += ".png";
		return (new ImageIcon(FrameTest.class.getResource(filePath)));
	}
	
	public void frameOpen()
	{
		boolean flag = false;
		for(int i = 0; i < 5; i++)
			flag |= select[i];
		if(flag){
			//JFrame frame = new JFrame ("請選擇");
	        //frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        //frame.setBounds(100, 100, 310, 221);
	        
	        JDialog dialog = new JDialog ();
	        dialog.setTitle("請選擇");
	        dialog.setModal (true);
	        dialog.setAlwaysOnTop (true);
	        dialog.setModalityType (Dialog.ModalityType.APPLICATION_MODAL);
	        dialog.setDefaultCloseOperation (JDialog.DO_NOTHING_ON_CLOSE);
	        dialog.setBounds(100, 100, 310, 221);
	        
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			//frame.setContentPane(panel);
			dialog.setContentPane(panel);
			panel.setLayout(null);
	        
	        
			
	        JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 0, 294, 134);
			panel.add(panel_1);
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	        
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(0, 139, 294, 44);
			panel.add(panel_2);
			panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			String s = "AAAA";
			if(thrower == 1)
				s = "你的下家打了";
			else if(thrower == 2)
				s = "你的對家打了";
			else if(thrower == 3)
				s = "你的上家打了";
			panel_2.add(new JLabel(s));
			addLabel(panel_2, newTile.suit, newTile.value+1, false);
			
			/*JButton btnClose = new JButton("確認");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnOpen.setEnabled(true);
					changeEable(true);
					
					doChoice(choice, panel_1);
					panel.revalidate();
					panel.repaint();
					//frame.dispose();
					//hu(true);
				}
			});
			btnClose.setBounds(102, 10, 87, 23);
			panel_2.add(btnClose);*/
			
			
			changeEable(false);
			//createButtonGroup(panel_1);
			createButton(panel_1, dialog);
			//frame.setVisible (true);
			dialog.setVisible (true);
		}
	}
	
	public void createButton(JPanel panel, JDialog dialog)
	{
		ButtonGroup group = new ButtonGroup();
		if(select[0])
			group.add(addButton(panel, "吃", 0, choice, dialog));
		if(select[1])
			group.add(addButton(panel, "碰", 1, choice, dialog));
		if(select[2])
			group.add(addButton(panel, "槓", 2, choice, dialog));
		if(select[3])
			group.add(addButton(panel, "聽", 3, choice, dialog));
		if(select[4])
			group.add(addButton(panel, "胡", 4, choice, dialog));
		
		group.add(addButton(panel, "不要", 5, choice, dialog));
		
		boolean[] b = {false, false, false, false, false};
		setSelect(b);
		
	}
	
	public void createButtonGroup(JPanel panel)
	{
		ButtonGroup group = new ButtonGroup();
		if(select[0])
			group.add(addRadioButton(panel, "吃", 0, choice));
		if(select[1])
			group.add(addRadioButton(panel, "碰", 1, choice));
		if(select[2])
			group.add(addRadioButton(panel, "槓", 2, choice));
		if(select[3])
			group.add(addRadioButton(panel, "聽", 3, choice));
		if(select[4])
			group.add(addRadioButton(panel, "胡", 4, choice));
		
		group.add(addRadioButton(panel, "不要", 5, choice));
		
		boolean[] b = {false, false, false, false, false};
		setSelect(b);
		
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
	public void setSelect(boolean[] _select)
	{
		for(int i = 0; i < 5; i++)
			select[i] = _select[i];
	}
	
	public void reset()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new ArrayList<JLabel>();
		
		tablePanel = new JPanel();
		tablePanel.setBackground(new Color(0, 100, 0));
		tablePanel.setBounds(137, 114, 499, 435);
		contentPane.add(tablePanel);
		tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		myPlayer = new JPanel();
		myPlayer.setBounds(137, 611, 499, 42);
		contentPane.add(myPlayer);
		myPlayer.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnOpen = new JButton("open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameOpen();
				//btnOpen.setEnabled(false);
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
				clear();
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
		playerUpOpen.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		
		refreshAllContent();
		contentPane.revalidate();
		contentPane.repaint();
	}
	public void hu(boolean win)
	{   
        changeEable(false);
        JFrame frame = new JFrame ();
        //frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 310, 221);
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(panel);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 294, 134);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		if(win){
			panel_1.add(new JLabel("你贏了!"));
		}
		else{
			panel_1.add(new JLabel("你輸了!"));
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 139, 294, 44);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton button = new JButton("確認");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeEable(true);
				reset();
				frame.dispose();
			}
		});
		button.setBounds(102, 10, 87, 23);
		panel_2.add(button);
		
		frame.setVisible(true);
	}
	public void clear()
	{
		int length = tablePanel.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(tablePanel, 0);
		}
		length = myPlayer.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(myPlayer, 0);
		}
		length = myPlayerOpen.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(myPlayerOpen, 0);
		}
		length = playerRight.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(playerRight, 0);
		}
		length = playerLeft.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(playerLeft, 0);
		}
		length = playerUp.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(playerUp, 0);
		}
		length = playerRightOpen.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(playerRightOpen, 0);
		}
		length = playerLeftOpen.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(playerLeftOpen, 0);
		}
		length = playerUpOpen.getComponentCount();
		for(int i = 0; i < length; i++){
			removeLabel(playerUpOpen, 0);
		}
		renew();
	}
	
	public void setAllContent(ArrayList<ArrayList<Tile>> temp, int[] tempNum)
	{
		tableTile = temp.get(0);
		rightPlayerOpenTile = temp.get(1);
		leftPlayerOpenTile = temp.get(2);
		upPlayerOpenTile = temp.get(3);
		myPlayerOpenTile = temp.get(4);
		myPlayerHandTile = temp.get(5);
		
		numRightPlayer = tempNum[0];
		numUpPlayer = tempNum[1];
		numLeftPlayer = tempNum[2];
	}
	
	public void refreshAllContent()
	{
		clear();
		
		for(int i = 0; i < numRightPlayer; i++)
			addLabel(playerRight, 0, 0, true);
		for(int i = 0; i < numUpPlayer; i++)
			addLabel(playerLeft, 0, 0, true);
		for(int i = 0; i < numLeftPlayer; i++)
			addLabel(playerUp, 0, 0, false);
		
		for(int i = 0; i < tableTile.size(); i++)
			addLabel(tablePanel, tableTile.get(i).suit, tableTile.get(i).value + 1, false);
		for(int i = 0; i < rightPlayerOpenTile.size(); i++)
			addLabel(playerRightOpen, rightPlayerOpenTile.get(i).suit, rightPlayerOpenTile.get(i).value + 1, true);
		for(int i = 0; i < upPlayerOpenTile.size(); i++)
			addLabel(playerUpOpen, upPlayerOpenTile.get(i).suit, upPlayerOpenTile.get(i).value + 1, false);
		for(int i = 0; i < leftPlayerOpenTile.size(); i++)
			addLabel(playerLeftOpen, leftPlayerOpenTile.get(i).suit, leftPlayerOpenTile.get(i).value + 1, true);
		for(int i = 0; i < myPlayerOpenTile.size(); i++)
			addLabel(myPlayerOpen, myPlayerOpenTile.get(i).suit, myPlayerOpenTile.get(i).value + 1, false);
		for(int i = 0; i < myPlayerHandTile.size(); i++)
			addButton(rightPlayerOpenTile.get(i).suit, rightPlayerOpenTile.get(i).value + 1);
		
		renew();
	}
	
	public void sendToBoard(int suit, int value)
	{
		Tile t = new Tile(suit*9 + (value - 1));
		ArrayList<Tile> temp = new ArrayList<Tile>();
		temp.add(t);
		ack();
		push = temp;
		
		
	}
	public void ack()
	{
		ok = true;
	}
	public void actionFail()
	{
		JFrame frame = new JFrame ("行動失敗");
		frame.setBounds(100, 100, 310, 221);
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(panel);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 294, 134);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_1.add(new JLabel("有人的優先權比你高,執行失敗!"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 139, 294, 44);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton button = new JButton("確認");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setBounds(102, 10, 87, 23);
		panel_2.add(button);
		
		frame.setVisible(true);
	}
	public void doChoice(boolean[] choice, JPanel panel_1)
	{
		if(choice[0]){
			//panel_1.add(new JLabel("吃"));
			//addLabel(panel_1, 0, 1, false);
			chewOptionFrame();
		}
		else{
			if(choice[1])
				panel_1.add(new JLabel("碰"));
			if(choice[2])
				panel_1.add(new JLabel("槓"));
			if(choice[3])
				panel_1.add(new JLabel("聽"));
			if(choice[4])
				panel_1.add(new JLabel("胡"));
			if(choice[5])
				panel_1.add(new JLabel("不要"));
			ack();
		}
	}
	public void setChowOption(int flag, ArrayList<ArrayList<Tile>> _chewChoice)
	{
		chowOption = flag;
		chewChoice = _chewChoice;
	}
	public boolean[] getChoice()
	{
		return choice;
	}
	
	public void chewOptionFrame()
	{
		if(chewChoice.size() > 1){
			//JFrame frame = new JFrame ("要吃哪一種");
			//frame.setBounds(100, 100, 310, 221);
			//JPanel panel = new JPanel();
			//panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			//frame.setContentPane(panel);
			//panel.setLayout(null);
			
			JDialog dialog = new JDialog ();
	        dialog.setTitle("要吃哪一種");
	        dialog.setModal (true);
	        dialog.setAlwaysOnTop (true);
	        dialog.setModalityType (Dialog.ModalityType.APPLICATION_MODAL);
	        dialog.setDefaultCloseOperation (JDialog.DO_NOTHING_ON_CLOSE);
	        dialog.setBounds(100, 100, 310, 221);
	        
	        JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			dialog.setContentPane(panel);
			panel.setLayout(null);
			
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 0, 294, 134);
			panel.add(panel_1);
			//panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_1.setLayout(null);
			
			JPanel[] panel_1_ = new JPanel[3];
			for(int i = 0; i < 3; i++)
				panel_1_[i] = new JPanel();
			panel_1_[0] = new JPanel();
			panel_1_[0].setBounds(10, 10, 274, 38);
			panel_1_[0].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panel_1.add(panel_1_[0]);
			panel_1_[1] = new JPanel();
			panel_1_[1].setBounds(10, 49, 274, 38);
			panel_1_[1].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panel_1.add(panel_1_[1]);
			panel_1_[2] = new JPanel();
			panel_1_[2].setBounds(10, 87, 274, 38);
			panel_1_[2].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panel_1.add(panel_1_[2]);
			
			boolean[] cChoice = {false, false, false};
			ButtonGroup group = new ButtonGroup();
			
			for(int i = 0; i < chewChoice.size(); i++){
				group.add(addRadioButton(panel_1_[i], "", i, cChoice));
				for(Tile temp : chewChoice.get(i))
					addLabel(panel_1_[i], temp.suit, temp.value + 1, false);
			}
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(0, 139, 294, 44);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JButton button = new JButton("確認");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//frame.dispose();
					if(cChoice[0] || cChoice[1] || cChoice[2]){
						dialog.dispose();
						for(int i = 0; i < 3; i++)
							if(cChoice[i])
								push = chewChoice.get(i);
						ack();
					}
					
				}
			});
			button.setBounds(102, 10, 87, 23);
			panel_2.add(button);
			
			//frame.setVisible(true);
			dialog.setVisible(true);
		}
		else
			ack();
	}
	public void setThrower(int _thrower, Tile _newTile)
	{
		thrower = _thrower;
		newTile = _newTile;
	}
	
	
	
	public void addTestHand()
	{
		addButton(myPlayer, tablePanel, 0, 1);
		addButton(myPlayer, tablePanel, 0, 9);
		addButton(myPlayer, tablePanel, 1, 1);
		addButton(myPlayer, tablePanel, 1, 9);
		addButton(myPlayer, tablePanel, 2, 1);
		addButton(myPlayer, tablePanel, 2, 9);
		addButton(myPlayer, tablePanel, 3, 1);
		addButton(myPlayer, tablePanel, 3, 2);
		addButton(myPlayer, tablePanel, 3, 3);
		addButton(myPlayer, tablePanel, 3, 4);
		addButton(myPlayer, tablePanel, 3, 5);
		addButton(myPlayer, tablePanel, 3, 6);
		addButton(myPlayer, tablePanel, 3, 7);
		addButton(myPlayer, tablePanel, 3, 7);
	}
	
}
