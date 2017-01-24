package gameProject;


//Have a counter for number of troops defeated and how many of your troops died
//Put this at the end, save to a file, and then sort

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	//public static int AIChoice = (int) (Math.random() * 10);
	public static int AICharacterChoice = ((int) (Math.random() * 11) + 1);
	
	Timer timer;
	//int counter = 30;
	//Timer timer;
	int counter=30;
	public static BufferedImage base;
	public static boolean mouseButtonDown = false;
	public static int x = 0;
	public static int y = 0;
	public static int[] positionArray=new int[15];
	
	public static int troopOneCurrent = -1;
	public static int troopTwoCurrent = -1;
	public static int troopThreeCurrent = -1;
	
	public static int troopOneCurrentAI = -1;
	public static int troopTwoCurrentAI = -1;
	public static int troopThreeCurrentAI = -1;
	
	public static boolean turretOneClicked = false;
	public static boolean turretTwoClicked = false;
	public static boolean turretThreeClicked = false;
	
	public static boolean turretActive = false;
	public static int turretRockCurrent = -1;
	
	JLabel turretOneLabel;
	JLabel turretTwoLabel;
	JLabel turretThreeLabel;
	JLabel refreshTurretLabel;
	
	
	//ArrayList<JLabel> troopOneLabel = new ArrayList<JLabel>();
	
	
	
	
	int width = 1350;
	int height = 700;
	//Dimension width2 = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	final int pauseDuration = 50;
	
	//static int numTroopOne = 1;
	
	//TroopOne[] troopOne = new TroopOne[numTroopOne];
	ArrayList<TroopOne> troopOne = new ArrayList<TroopOne>();
	
	ArrayList<TroopTwo> troopTwo = new ArrayList<TroopTwo>();
	
	ArrayList<TroopThree> troopThree = new ArrayList<TroopThree>();
	
	ArrayList<AITroopOne> troopOneAI = new ArrayList<AITroopOne>();
	ArrayList<AITroopTwo> troopTwoAI = new ArrayList<AITroopTwo>();
	ArrayList<AITroopThree> troopThreeAI = new ArrayList<AITroopThree>();
	
	
	
	
	
	
	
	
	ArrayList<turretShooter> turretRock = new ArrayList<turretShooter>();
	
	//static int numTroopTwo = 1;
	
	//TroopTwo[] troopTwo = new TroopTwo[numTroopTwo];
	
	//static int numTroopThree = 1;
	
	//TroopThree[] troopThree = new TroopThree[numTroopThree];
	
	private BufferedImage castleImageLeft;
	private BufferedImage castleImageRight;
	
	private BufferedImage turretOneImage;
	
	private BufferedImage turretTwoImage;
	
	private BufferedImage turretThreeImage;
	
	public BufferedImage refreshTurretImage;
	
	

	public static void main(String[] args) {

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	      //setBounds(0,0,screenSize.width, screenSize.height);
	      //setVisible(true);

	     // pack()

		JFrame frame = new JFrame("Endless War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//frame.setBounds(0, 0, screenSize.width, screenSize.height);
	frame.setSize(new Dimension(1200, 700));
		frame.setAutoRequestFocus(false);
		frame.setVisible(true);
		Container c = frame.getContentPane();
		c.add(new GamePanel());
		frame.pack();
		/*this.setPreferredSize(new Dimension(500, 300));
		this.setBackground(Color.WHITE);*/

	}

	//or if it is the same coordinates of the image- get image coordinates, then it is a click and hit
	
	
	
	
	public GamePanel(){
		
		
		
		
		
		try {                
	          castleImageLeft = ImageIO.read(new File("src/CastleGood.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
		
		try {                
	          castleImageRight = ImageIO.read(new File("src/CastleGood 2.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
		
		try {                
	          turretOneImage = ImageIO.read(new File("src/TurretOne.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
		
		try {                
	          turretTwoImage = ImageIO.read(new File("src/TurretTwo.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
		
		try {                
	          turretThreeImage = ImageIO.read(new File("src/TurretThree.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
		
		try {                
	          refreshTurretImage = ImageIO.read(new File("src/BlankWhiteBackground.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
		
		
		
		Cursor cursor = Cursor.getDefaultCursor();

		//Change the cursor
		cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR); 
		setCursor(cursor);
		
		timer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AICharacterChoice = ((int) (Math.random() * 11) + 1);
				
				if (AICharacterChoice <=4) {
					troopOneCurrentAI = troopOneCurrentAI +1;
					troopOneAI.add(new AITroopOne(1200, 465, 0, width, 0, height));
					troopOneAI.get(troopOneAI.size()-1).setXSpeed(-5);
					troopOneAI.get(troopOneAI.size()-1).setYSpeed(0);
					
					if (troopOneAI.get(troopOneAI.size()-1).getX() == 0) {
						troopOneAI.remove(troopOneAI.size()-1);
					}
					
				}
				else if (AICharacterChoice <=8) {
					//Makes AI Troop Two appear
					troopTwoCurrentAI = troopTwoCurrentAI +1;
					troopTwoAI.add(new AITroopTwo(1200, 460, 0, width, 0, height));
					troopTwoAI.get(troopTwoAI.size()-1).setXSpeed(-5);
					troopTwoAI.get(troopTwoAI.size()-1).setYSpeed(0);
					
					if (troopTwoAI.get(troopTwoAI.size()-1).getX() == 0) {
						troopTwoAI.remove(troopTwoAI.size()-1);
					}
				}
				else {
					//Makes AI Troop Three appear
					troopThreeCurrentAI = troopThreeCurrentAI +1;
					troopThreeAI.add(new AITroopThree(1200, 520, 0, width, 0, height));
					troopThreeAI.get(troopThreeAI.size()-1).setXSpeed(-5);
					troopThreeAI.get(troopThreeAI.size()-1).setYSpeed(0);
					
					if (troopThreeAI.get(troopThreeAI.size()-1).getX() == 0) {
						troopThreeAI.remove(troopThreeAI.size()-1);
					}
				}
				
			}
		});
		timer.start();

		 

		
			
	
		
//		//Makes AI Troop One appear
//		troopOneCurrentAI = troopOneCurrentAI +1;
//		troopOneAI.add(new AITroopOne(1200, 465, 0, width, 0, height));
//		troopOneAI.get(troopOneAI.size()-1).setXSpeed(-5);
//		troopOneAI.get(troopOneAI.size()-1).setYSpeed(0);
//		
//		if (troopOneAI.get(troopOneAI.size()-1).getX() == 0) {
//			troopOneAI.remove(troopOneAI.size()-1);
//		}
		
//		//Makes AI Troop Two appear
//		troopTwoCurrentAI = troopTwoCurrentAI +1;
//		troopTwoAI.add(new AITroopTwo(1200, 460, 0, width, 0, height));
//		troopTwoAI.get(troopTwoAI.size()-1).setXSpeed(-5);
//		troopTwoAI.get(troopTwoAI.size()-1).setYSpeed(0);
//		
//		if (troopTwoAI.get(troopTwoAI.size()-1).getX() == 0) {
//			troopTwoAI.remove(troopTwoAI.size()-1);
//		}
		
//		//Makes AI Troop Three appear
//		troopThreeCurrentAI = troopThreeCurrentAI +1;
//		troopThreeAI.add(new AITroopThree(1200, 520, 0, width, 0, height));
//		troopThreeAI.get(troopThreeAI.size()-1).setXSpeed(-5);
//		troopThreeAI.get(troopThreeAI.size()-1).setYSpeed(0);
//		
//		if (troopThreeAI.get(troopThreeAI.size()-1).getX() == 0) {
//			troopThreeAI.remove(troopThreeAI.size()-1);
//		}
	
		
		
		
		MouseAdapter mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) { 	
		    System.out.println("Image Hit");
	    
		    System.out.println("X Click: " + e.getX() + " Y Click: " + e.getY());
		    //setBackground(Color.BLACK);
//		    try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		    
//		    setBackground(Color.WHITE);
		    	
		    }
		  };
		
		  addMouseListener(mouseListener);
					this.setPreferredSize(new Dimension(1200, 700));
						this.setBackground(Color.WHITE);

						
						
//		JPanel imagePanel = new JPanel();
//		imagePanel.setBounds(0, 0, 100, 100);
//		add(imagePanel);
//		
//		imagePanel.add(turretOneImage);

//		JLabel picLabel = new JLabel(new ImageIcon(turretOneImage));
//		add(picLabel);
						
		JPanel troop1Panel;
		troop1Panel=new JPanel();
		troop1Panel.setBounds(75, 0, 100, 100);
		add(troop1Panel);


		JButton troop1BTN;
		troop1BTN = new JButton ("Troop 1");
		troop1BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		troop1BTN.setPreferredSize(new Dimension(100, 100));


		troop1Panel.add(troop1BTN);
		
		
		
		
		//turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
		
		
		
		troop1BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
//				//turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
//				turretRock.get(turretRock.size()-1).setX(10);
//				turretRock.get(turretRock.size()-1).setY(10);
//				
				troopOneCurrent = troopOneCurrent +1;
				
				
				
				
				
				troopOne.add(new TroopOne(0, 465, 0, width, 0, height));
				troopOne.get(troopOne.size()-1).setXSpeed(5);
				troopOne.get(troopOne.size()-1).setYSpeed(0);
				
				
				
				
				
				
				
				
				//troopOne.get(0).setY(300);
//				troopOne[i].setXSpeed(1);
//				troopOne[i].setYSpeed(0);
//				troopOne[i].setX(0);
//				troopOne[i].setY(500);
				setBackground(Color.WHITE);
			}
		});
		
		
		
		JPanel troop2Panel;
		troop2Panel=new JPanel();
		troop2Panel.setBounds(175, 0, 100, 100);
		add(troop2Panel);


		JButton troop2BTN;
		troop2BTN = new JButton ("Troop 2");
		troop2BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		troop2BTN.setPreferredSize(new Dimension(100, 100));


		troop2Panel.add(troop2BTN);
		
		troop2BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				troopTwoCurrent = troopTwoCurrent +1;
				troopTwo.add(new TroopTwo(0, 460, 0, width, 0, height));
				troopTwo.get(troopTwo.size()-1).setXSpeed(5);
				troopTwo.get(troopTwo.size()-1).setYSpeed(0);
				
				if (troopTwo.get(troopTwo.size()-1).getX() == 1200) {
					troopTwo.remove(troopTwo.size()-1);
				}
				
				
				
			
				setBackground(Color.WHITE);
			}
		});
		
		
		JPanel troop3Panel;
		troop3Panel=new JPanel();
		troop3Panel.setBounds(275, 0, 100, 100);
		add(troop3Panel);


		JButton troop3BTN;
		troop3BTN = new JButton ("Troop 3");
		troop3BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		troop3BTN.setPreferredSize(new Dimension(100, 100));


		troop3Panel.add(troop3BTN);
		
		troop3BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				troopThreeCurrent = troopThreeCurrent +1;
				troopThree.add(new TroopThree(0, 520, 0, width, 0, height));
				troopThree.get(troopThree.size()-1).setXSpeed(5);
				troopThree.get(troopThree.size()-1).setYSpeed(0);
				
				if (troopThree.get(troopThree.size()-1).getX() == 1200) {
					troopThree.remove(troopThree.size()-1);
				}
				
				
				setBackground(Color.WHITE);
			}
		});
		
		
		
		JPanel turret1Panel;
		turret1Panel=new JPanel();
		turret1Panel.setBounds(465, 0, 100, 100);
		add(turret1Panel);


		JButton turret1BTN;
		turret1BTN = new JButton ("Turret 1");
		turret1BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		turret1BTN.setPreferredSize(new Dimension(100, 100));


		turret1Panel.add(turret1BTN);
		
		
		turretOneLabel = new JLabel(new ImageIcon(turretOneImage));
		turretOneLabel.setBounds(0, 100, turretOneImage.getWidth(), turretOneImage.getHeight());
		add(turretOneLabel);
		
		turretOneLabel.setVisible(false);
		
		turretTwoLabel = new JLabel(new ImageIcon(turretTwoImage));
		turretTwoLabel.setBounds(0, 100, turretTwoImage.getWidth(), turretTwoImage.getHeight());
		add(turretTwoLabel);
		
		turretTwoLabel.setVisible(false);
		
		turretThreeLabel = new JLabel(new ImageIcon(turretThreeImage));
		turretThreeLabel.setBounds(0, 100, turretThreeImage.getWidth(), turretThreeImage.getHeight());
		add(turretThreeLabel);
		
		turretThreeLabel.setVisible(false);
		
		turret1BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Have the turret shoot a rock about every three seconds
//				
//				Timer timer = new Timer(1000, new ActionListener() {
//	                @Override
//	                public void actionPerformed(ActionEvent e) {
//	                    x += 110;
//	                    if (x >= 1000) {
//	                        x = 1000;
//	                        ((Timer)e.getSource()).stop();
//	                    }
//	                    repaint();
//	                }
//	            });
//	            timer.start();
				
				turretActive = true;
				
//				turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
//				//turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
//				turretRock.get(turretRock.size()-1).setX(10);
//				turretRock.get(turretRock.size()-1).setY(10);
				
				
				//making a new label each time, therefore doesn't remove all of them
				
				turretOneClicked = true;
				
//				turretOneLabel = new JLabel(new ImageIcon(turretOneImage));
//				turretOneLabel.setBounds(0, 100, turretOneImage.getWidth(), turretOneImage.getHeight());
//				add(turretOneLabel);
				
				turretOneLabel.setVisible(true);
				
				if (turretTwoClicked)  {
					
//					refreshTurretLabel = new JLabel(new ImageIcon(refreshTurretImage));
//					refreshTurretLabel.setBounds(0, 100, refreshTurretImage.getWidth(), refreshTurretImage.getHeight());
//					add(refreshTurretLabel);
					
				turretTwoLabel.setVisible(false);
				
				}
				if (turretThreeClicked) {
					turretThreeLabel.setVisible(false);
				}
				
				//turretOneClicked = false;
				
			
				System.out.println("Turret 1 Clicked");
			}
		});
		
		
		
		JPanel turret2Panel;
		turret2Panel=new JPanel();
		turret2Panel.setBounds(565, 0, 100, 100);
		add(turret2Panel);


		JButton turret2BTN;
		turret2BTN = new JButton ("Turret 2");
		turret2BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		turret2BTN.setPreferredSize(new Dimension(100, 100));


		turret2Panel.add(turret2BTN);
		
		turret2BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				turretActive = true;
				
				turretTwoClicked= true;
				
//				turretTwoLabel = new JLabel(new ImageIcon(turretTwoImage));
//				turretTwoLabel.setBounds(0, 100, turretTwoImage.getWidth(), turretTwoImage.getHeight());
//				add(turretTwoLabel);
				
				turretTwoLabel.setVisible(true);
				
				if (turretOneClicked ) {
				turretOneLabel.setVisible(false);
				
				}
				if (turretThreeClicked) {
					turretThreeLabel.setVisible(false);
				}
				
				//turretTwoClicked= false;
				
				System.out.println("Turret 2 Clicked");
			}
		});
		
		
		
		JPanel turret3Panel;
		turret3Panel=new JPanel();
		turret3Panel.setBounds(665, 0, 100, 100);
		add(turret3Panel);


		JButton turret3BTN;
		turret3BTN = new JButton ("Turret 3");
		turret3BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		turret3BTN.setPreferredSize(new Dimension(100, 100));


		turret3Panel.add(turret3BTN);
		
		turret3BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				turretActive = true;
				
				turretThreeClicked = true;
				
//				turretThreeLabel = new JLabel(new ImageIcon(turretThreeImage));
//				turretThreeLabel.setBounds(0, 100, turretThreeImage.getWidth(), turretThreeImage.getHeight());
//				add(turretThreeLabel);
				
				turretThreeLabel.setVisible(true);
				
				if (turretOneClicked)  {
				turretOneLabel.setVisible(false);
				
				}
				if (turretTwoClicked) {
				turretTwoLabel.setVisible(false);
				}
				
				
			//	turretThreeClicked = false;
				
				
				System.out.println("Turret 3 Clicked");
			}
		});
		
		
		
		
		//addActionListener(troop1BTN);
		
		

		//		
		//		Timer timer = new Timer(1000,new ActionListener(){
		//			@Override
		//			public void actionPerformed(ActionEvent e){
		//				int r+=110;
		//				if (r>=1000){
		//					r=1000;
		//					((Timer)e.getSource()).stop*();
		//				}
		//				//repaint();
		//			}
		//		});

		
	//	this.setPreferredSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(1200, 600));
		
//		for (int i = 0; i < numTroopOne; i++) {
//			
//			//troopOne[i] = new TroopOne2(650, 300, 0, 100, 0, 100);
//			//troopOne[i] = new TroopOne(650, 300, 0, width, 0, height);
//			
//			//troopOne[i] = new TroopOne(0, 300, 0, width, 0, height);
//			
//			
//			///troopOne.add(new TroopOne(0, 300, 0, width, 0, height));
//		
//			//troopOne[i].setX(0);
////			troopOne[i].setXSpeed(10);
////			troopOne[i].setYSpeed(0);
////			troopOne[i].setXSpeed(Math.random() * 16-8);
////			troopOne[i].setYSpeed(Math.random() * 16-8);

		
		
		
//		JLabel turretOneLabel = new JLabel(new ImageIcon(turretOneImage));
//		turretOneLabel.setBounds(0, 100, turretOneImage.getWidth(), turretOneImage.getHeight());
//		add(turretOneLabel);
		
		
		
		Thread gameThread = new Thread(this);
		gameThread.start();
		
		
		
		
		
		
	}
	public void paintComponent(Graphics g){
		//		 try {                
		//	          base = ImageIO.read(new File("/base.png"));
		//	       } catch (IOException ex) {
		//	            // handle exception...
		//	       }
		super.paintComponent(g);
		
		
		//g.drawImage(turretRock,  300,  null);
		
	//	turretRock.get(0).draw(g);
		
		g.drawImage(castleImageLeft, -75, 180, null);
		g.drawImage(castleImageRight, 1070, 180, null);
		
		//if (turretActive == true) {
		
//		Timer timer = new Timer(1000, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    x += 110;
//                    if (x >= 1000) {
//                        x = 1000;
//                        ((Timer)e.getSource()).stop();
//                    }
//                    repaint();
//              }
//          });
//          timer.start();
			
		//}
		
//		if (turretOneClicked) {
//			g.drawImage(turretOneImage, 0, 100, null);
//			if (turretTwoClicked || turretThreeClicked) {
//				g.drawImage(refreshTurret, 0, 100, null);
//			}
//			g.drawImage(turretOneImage, 0, 100, null);
//		}
//		
//		if (turretTwoClicked) {
//			if (turretOneClicked || turretThreeClicked) {
//				g.drawImage(refreshTurret, 0, 100, null);
//			}
//			g.drawImage(turretTwoImage, 0, 100, null);
//		}
//		
//		if (turretThreeClicked) {
//			if (turretOneClicked || turretTwoClicked) {
//				g.drawImage(refreshTurret, 0, 100, null);
//			}
//			g.drawImage(turretThreeImage, 0, 100, null);
//		}
		
		
		//g.drawImage(base, 0, 0, this);
		
		g.drawRect(0, 0, 1200, 100);
		
		//Right side money boxes
		//g.drawLine(1150, 0, 1150, 100);
		//g.drawLine(1150, 50, 1265, 50);
		
		g.drawString("Units:", 10, 20);
		g.drawString("Turrets:", 400, 20);
		g.drawString("Money:", 800, 20);
		g.drawString("Base Health:", 1000, 20);
		
		JPanel moneyPanel = new JPanel();
		moneyPanel.setBounds(875, 0, 100, 100);
		//moneyPanel.setBackground(new Color(181,164,13));
		//moneyPanel.setBackground(new Color(205,185,10));
		moneyPanel.setBackground(Color.LIGHT_GRAY);
		add(moneyPanel);
		
		JLabel moneyLabel = new JLabel();
		moneyLabel.setBounds(875, 0, 100, 100);
		setLayout(null);
		moneyLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		
		BufferedImage coinImage = null;
		try {                
	          coinImage = ImageIO.read(new File("src/coinImage.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
		
		JLabel coinImageLabel = new JLabel(new ImageIcon(coinImage));
		
		moneyPanel.add(coinImageLabel);
		
		moneyLabel.setText("100 Coins");
		moneyPanel.add(moneyLabel);
		
		
		JPanel baseHealthUserPanel = new JPanel();
		baseHealthUserPanel.setBounds(1100, 0, 85, 50);
		//moneyPanel.setBackground(new Color(181,164,13));
		baseHealthUserPanel.setBackground(Color.LIGHT_GRAY);
		add(baseHealthUserPanel);
		
		JLabel baseHealthUserLabel = new JLabel();
		baseHealthUserLabel.setBounds(1100, 0, 85, 50);
		setLayout(null);
		baseHealthUserLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		//baseHealthUserLabel.setLineWrap(true);
		//baseHealthUserLabel.setText("User Base Health:");
		baseHealthUserLabel.setText("<html>User Base<br>"
                + "Health: 100");
		baseHealthUserPanel.add(baseHealthUserLabel);
		
		JPanel baseHouseComPanel = new JPanel();
		baseHouseComPanel.setBounds(1100, 50, 85, 50);
		//moneyPanel.setBackground(new Color(181,164,13));
		baseHouseComPanel.setBackground(Color.LIGHT_GRAY);
		add(baseHouseComPanel);
		
		JLabel baseHouseComLabel = new JLabel();
		baseHouseComLabel.setBounds(1100, 50, 85, 50);
		setLayout(null);
		baseHouseComLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		baseHouseComLabel.setText("<html>Comp Base<br>"
                + "Health: 100");
		baseHouseComPanel.add(baseHouseComLabel);
		
		//g.drawString("Money:", 1050, 20);
		
		//Troop Boxes
		//g.drawRect(50, 20, 90, 60);
		//g.drawRect(170, 20, 90, 60);
		//g.drawRect(290, 20, 90, 60);
		
		//Larger rectangle
		//g.drawRect(20, 50, 1000, 1000);
		
		//Turret rectangle
		//g.drawRect(1100, 200, 100, 100);
		
		for (int i = 0; i < troopOneCurrent + 1; i ++) {
		if (troopOneCurrent > -1) {
			troopOne.get(i).draw(g);
//			if (troopOne.get(i).getX() == troopOneAI.get(i).getX()) {
//				troopOne.remove(i);
//			}
		}
		//put the image in a label and then remove the label
//		if (troopOne.get().getX() == troopOneAI.get(0).getX()) {
//			troopOne.remove(0);
//		}
		
		}
		
		
		
		for (int i = 0; i < troopTwoCurrent + 1; i ++) {
			if (troopTwoCurrent > -1) {
				troopTwo.get(i).draw(g);
			}
			
			}
		
		for (int i = 0; i < troopThreeCurrent + 1; i ++) {
			if (troopThreeCurrent > -1) {
				troopThree.get(i).draw(g);
			}
			
			}
		
		for (int i = 0; i < troopOneCurrentAI + 1; i ++) {
			if (troopOneCurrentAI > -1) {
				troopOneAI.get(i).draw(g);
			}
			
			}
		
		for (int i = 0; i < troopTwoCurrentAI + 1; i ++) {
			if (troopTwoCurrentAI > -1) {
				troopTwoAI.get(i).draw(g);
			}
			
			}
		for (int i = 0; i < troopThreeCurrentAI + 1; i ++) {
			if (troopThreeCurrentAI > -1) {
				troopThreeAI.get(i).draw(g);
			}
			
			}
			
		
//		
//		for (i = 0; i <turretRockCurrent +1; i++) {
//			
//		}
//		
		
//		turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
//		//turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
//		turretRock.get(turretRock.size()-1).setX(10);
//		turretRock.get(turretRock.size()-1).setY(10);
		
		if (turretActive) {
			turretRock.add(new turretShooter(0, 10, 0, width, 0, height));
			//turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
			turretRock.get(turretRock.size()-1).setXSpeed(10);
			turretRock.get(turretRock.size()-1).setYSpeed(10);
			turretRock.get(0).draw(g);
			for (int i = 0; i < turretRockCurrent+1; i++) {
				//turretRock.get(i).draw(g);
				if (turretRock.get(i).getY() == 600) {
					turretRock.add(new turretShooter(0, 10, 0, width, 0, height));
					turretRock.get(turretRock.size()-1).setXSpeed(10);
					turretRock.get(turretRock.size()-1).setYSpeed(10);
					turretRock.get(i).draw(g);
				}
			}
		}
		
		
		//for (int i = 0; i < numTroopOne; i++) {
			
			//System.out.println("Random Speed: " + (Math.random() * 16-8));
			
//			troopOne[i].setXSpeed(1);
//			troopOne[i].setYSpeed(0);
//			troopOne[i].setX(0);
//			troopOne[i].setY(500);
			//troopOne.get(i).draw(g);
			//troopOne[i].draw(g);
			
		//troopOne.get(troopOne.size()-1).draw(g);
			

		//}
		
//		for (int i = 0; i < numTroopTwo; i++) {
//			troopTwo[i].draw(g);
//
//		}
//		
//		for (int i = 0; i < numTroopTwo; i++) {
//			troopThree[i].draw(g);
//
//		}
		
		
		//troopOne[0].draw(g);
	}

	public void run()
	{
		while (true) {
			repaint();
			try {
				Thread.sleep(pauseDuration);
			} catch (InterruptedException e) {
			}
		}
	}
	/**
	 * called when a mouse button is pressed
	 * @param e The mouse event
	 **/
	public void mousePressed (MouseEvent e)
	{
		mouseButtonDown = true;
		x = e.getX ();
		y = e.getY ();
		System.out.println(x + "," + y);
		repaint ();
	}

	/**
	 * called when a mouse button is released
	 * @param e The mouse event
	 **/
	public void mouseReleased (MouseEvent e)
	{
		mouseButtonDown = false;
		repaint ();
	}
	public static int[] getPositionArray ()
	{
		return positionArray;
	}

	public void setPostionArray(int [] positionArray)
	{
		positionArray = GamePanel.positionArray;
	}
	
	
	/**
	 * 
	 * Paint component- draw the Nacks on the frame in their set location
	 */
	

}