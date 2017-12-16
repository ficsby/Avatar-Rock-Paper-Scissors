import java.net.*;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Client extends JFrame implements ActionListener
{
	private JLabel yourScore, compScore, messageNotification, dummy;
	private JLabel round, roundNum;
	private JButton fireBtn;
	private JButton waterBtn;
	private JButton grassBtn;
	private JButton quitBtn;
	private JLabel compAtk;
	private JTextArea textArea;
	private int rnd;
	private int uScore;
	private int cScore;
	private static Clip clip;
	
	public static void main(String[] args) throws IOException
	{
		Client c = new Client();
		playSound("Last_Air_Bender_End_Credits.wav");
	}
	
	public Client() throws IOException
	{		
		this.setTitle("PokeMindReader");
		this.setSize(1200,800);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		this.setBounds(0,0,1200,800);
		//setContentPane(null);
		
        JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("avatarMap.png"))));
        setContentPane(label);
        setLayout(new BorderLayout());
		
		rnd = 1;
		uScore = 0;
		cScore = 0;
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setOpaque(false);
		add(buttonPanel);
	
		
		fireBtn = new JButton("FIRE");
		waterBtn = new JButton("WATER");
		grassBtn = new JButton("GRASS");
		quitBtn = new JButton("QUIT");
		
		fireBtn.setForeground(Color.WHITE);
		fireBtn.setFont(new Font("TimesRoman", Font.BOLD, 20));
		fireBtn.setHorizontalTextPosition(JButton.CENTER);
		fireBtn.setVerticalTextPosition(JButton.CENTER);
		
		waterBtn.setForeground(Color.WHITE);
		waterBtn.setFont(new Font("TimesRoman", Font.BOLD, 20));
		waterBtn.setHorizontalTextPosition(JButton.CENTER);
		waterBtn.setVerticalTextPosition(JButton.CENTER);
		
		grassBtn.setForeground(Color.WHITE);
		grassBtn.setFont(new Font("TimesRoman", Font.BOLD, 20));
		grassBtn.setHorizontalTextPosition(JButton.CENTER);
		grassBtn.setVerticalTextPosition(JButton.CENTER);
		
		quitBtn.setForeground(Color.WHITE);
		quitBtn.setFont(new Font("TimesRoman", Font.BOLD, 20));
		quitBtn.setHorizontalTextPosition(JButton.CENTER);
		quitBtn.setVerticalTextPosition(JButton.CENTER);

		fireBtn.addActionListener(this);
		waterBtn.addActionListener(this);
		grassBtn.addActionListener(this);
		quitBtn.addActionListener(this);
		
		//Window Width: 1200 Window Height: 800
		//SCALES BUTTONS
		fireBtn.setBounds(220,490,125,125); // x: 1200/5 - 20
		waterBtn.setBounds(220,140,125,125);
		grassBtn.setBounds(840,140,125,125);
		quitBtn.setBounds(840,490,125,125);
		
		
		//FIRE BUTTON IMAGE
		ImageIcon fireImg = new ImageIcon(getClass().getResource("avatarFire.png"));
		Image newimgFire = fireImg.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		fireImg = new ImageIcon( newimgFire );
	    fireBtn.setIcon(fireImg);
	    
	    //WATER BUTTON IMAGE
		ImageIcon waterImg = new ImageIcon(getClass().getResource("avatarWater.png"));
	    Image newimgWater = waterImg.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		waterImg = new ImageIcon( newimgWater );
	    waterBtn.setIcon(waterImg);
	    
	    //GRASS BUTTON IMAGE
	    ImageIcon grassImg = new ImageIcon(getClass().getResource("avatarEarth.png"));
	    Image newimgEarth = grassImg.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		grassImg = new ImageIcon( newimgEarth );
	    grassBtn.setIcon(grassImg);
	    
	    //QUIT BUTTON IMAGE
	    ImageIcon quitImg = new ImageIcon(getClass().getResource("avatarAir.png"));
	    Image newimgAir = quitImg.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		quitImg = new ImageIcon( newimgAir );
	    quitBtn.setIcon(quitImg);
		
	    buttonPanel.add(fireBtn);
		buttonPanel.add(waterBtn);
		buttonPanel.add(grassBtn);
		buttonPanel.add(quitBtn);
		compAtk = new JLabel();
		compAtk.setBounds(480,300,200,200);
		buttonPanel.add(compAtk);
		
		JButton waterMusic = new JButton();
		JButton fireMusic = new JButton();
		JButton earthMusic = new JButton();
		JButton airMusic = new JButton();
		
		waterMusic.setBounds(1050,490,125,125);
		fireMusic.setBounds(1050,365,125,125);
		earthMusic.setBounds(1050,240,125,125);
		airMusic.setBounds(1050,115,125,125);
		
		ImageIcon waterSound = new ImageIcon(getClass().getResource("avatarWaterMusic.png"));
		Image newimgWaterSound = waterSound.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		waterSound = new ImageIcon( newimgWaterSound );
		waterMusic.setIcon(waterSound);
		
		ImageIcon fireSound = new ImageIcon(getClass().getResource("avatarFireMusic.png"));
		Image newimgFireSound = fireSound.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		fireSound = new ImageIcon( newimgFireSound );
		fireMusic.setIcon(fireSound);
		
		ImageIcon earthSound = new ImageIcon(getClass().getResource("avatarEarthMusic.png"));
		Image newimgEarthSound = earthSound.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		earthSound = new ImageIcon( newimgEarthSound );
		earthMusic.setIcon(earthSound);
		
		ImageIcon airSound = new ImageIcon(getClass().getResource("avatarAirMusic.png"));
		Image newimgAirSound = airSound.getImage().getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  
		airSound = new ImageIcon( newimgAirSound );
		airMusic.setIcon(airSound);
		
	    fireBtn.setIcon(fireImg);
		buttonPanel.add(waterMusic);
		buttonPanel.add(fireMusic);
		buttonPanel.add(earthMusic);
		buttonPanel.add(airMusic);
		
		
		
		/*
		JLabel avatarOne = new JLabel();
		avatarOne.setBounds(0,290,200,200);
		buttonPanel.add(avatarOne);
		ImageIcon avatarImg1 = new ImageIcon(getClass().getResource("aangAvatar.jpg"));
		Image newimgAng1 = avatarImg1.getImage().getScaledInstance( 140, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
		avatarImg1 = new ImageIcon( newimgAng1 );
		avatarOne.setIcon(avatarImg1);
		*/
		/*
		JLabel avatarTwo = new JLabel();
		avatarTwo.setBounds(1042,290,200,200);
		//buttonPanel.add(avatarTwo);
		ImageIcon avatarImg2 = new ImageIcon(getClass().getResource("aangAvatar.jpg"));
		Image newimgAng2 = avatarImg2.getImage().getScaledInstance( 140, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
		avatarImg2 = new ImageIcon( newimgAng2 );
		avatarTwo.setIcon(avatarImg2);
		*/
		
		//URL url = getClass().getResource("air.gif");//new URL("air.gif");
		//Icon icon2 = new ImageIcon(getClass().getResource("air.gif"));
		//JLabel testLabel = new JLabel(icon2);
		//testLabel.setBounds(1042, 290, 200, 200);
		//buttonPanel.add(testLabel);

		
		//Rounds
		round = new JLabel("<html>R<br>O<br>U<br>N<br>D<br> </html>" + rnd);  //STARTS AT 1
		round.setForeground(Color.WHITE);
		round.setBounds(40,8,150,500);
		round.setFont(new Font("Impact", Font.PLAIN, 75));
		buttonPanel.add(round);
		
		roundNum = new JLabel("" + rnd);  //STARTS AT 1
		roundNum.setForeground(Color.WHITE);
		roundNum.setBounds(45,300,150,500);
		roundNum.setFont(new Font("Impact", Font.PLAIN, 75));
		buttonPanel.add(roundNum);
		
		//Computer Score
		compScore = new JLabel("Computer Score: " + cScore);  //STARTS AT 0
		compScore.setForeground(Color.WHITE);
		compScore.setFont(new Font("Arial", Font.PLAIN, 20));
		compScore.setBounds(835,8,200,20);
		buttonPanel.add(compScore);
		
		//User Score
		yourScore = new JLabel("Your Score: " + uScore);  //STARTS AT 0
		yourScore.setForeground(Color.WHITE);
		yourScore.setFont(new Font("Arial", Font.PLAIN, 20));
		yourScore.setBounds(220,8,200,20);
		buttonPanel.add(yourScore);
		
		//Messages that tell who wins or loses
		messageNotification = new JLabel("WELCOME!");  //STARTS AT 0
		messageNotification.setForeground(Color.WHITE);
		messageNotification.setFont(new Font("Arial Black", Font.BOLD, 25));
		messageNotification.setBounds(500,8,250,20);
		buttonPanel.add(messageNotification);
		
		//Displays history of rounds played
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Border roundedBorder = new LineBorder(Color.DARK_GRAY, 8, true);
		scrollPane.setBorder(roundedBorder);
		scrollPane.setBounds(8,600,120,140);
		buttonPanel.add(scrollPane);
		
		//Dummy label
		dummy = new JLabel("");	//This is needed so I can move the other JLabels
		buttonPanel.add(dummy);
		

		System.out.println("Welcome to Pokemon Mind Reader!");
		System.out.println("Let's play~~~");
		
		
		//background.add(buttonPanel,new GridBagConstraints());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String youAttack = "";
		try{
			Socket s = new Socket("localhost", 1025);
			PrintStream out = new PrintStream(s.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			if (e.getSource().equals(fireBtn)) 
			{
				youAttack = "F";
				round.setText("<html>R<br>O<br>U<br>N<br>D<br> </html>");
				if(rnd + 1 > 9)
					roundNum.setBounds(30,300,150,500);
				roundNum.setText("" + ++rnd);
			}
			else if (e.getSource().equals(waterBtn)) 
			{
				youAttack = "W";
				
				round.setText("<html>R<br>O<br>U<br>N<br>D<br> </html>");
				if(rnd + 1 > 9)
					roundNum.setBounds(30,300,150,500);
				roundNum.setText("" + ++rnd);
			}
			else if (e.getSource().equals(grassBtn)) 
			{
				youAttack = "G";
				
				round.setText("<html>R<br>O<br>U<br>N<br>D<br> </html>");
				if(rnd + 1 > 9)
					roundNum.setBounds(30,300,150,500);
				roundNum.setText("" + ++rnd);
			}
			else if (e.getSource().equals(quitBtn))
			{
				System.out.println("Goodbye!");
				out.println("Q");
				out.flush();
				System.exit(0);
			}
			
			//SENDING MESSAGE TO SERVER
			
			System.out.println("Client Sending Data...");
			out.println(youAttack);
			out.flush();
			System.out.println("Sent: " + youAttack);
			
			
			//GET MESSAGE FROM SERVER
			System.out.println("Client Receiving Result...");
			int compAttack = Integer.parseInt(in.readLine());
			System.out.println(compAttack);
			System.out.println("Received Result: " + compAttack);	
			out.flush();
					
			switch(compAttack)
			{
				case 1: System.out.println("The computer chose fire!");
						if(youAttack.equals("F"))
						{
							ImageIcon aangImg = new ImageIcon(getClass().getResource("aangCalm.png"));
							Image newimgAng = aangImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							aangImg = new ImageIcon( newimgAng );
							Border border = BorderFactory.createLineBorder(Color.CYAN);
			                compAtk.setBorder(border);
							compAtk.setIcon(aangImg);
							
							round.setForeground(Color.CYAN);
							roundNum.setForeground(Color.CYAN);
							
							yourScore.setForeground(Color.CYAN);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.CYAN);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.CYAN);
							messageNotification.setText("It's a tie!!!");
							
							textArea.append("Round: " + (rnd-1) +"| Tied.\n");
						}
						else if(youAttack.equals("W"))
						{
							ImageIcon kataraImg = new ImageIcon(getClass().getResource("kataraAttack.jpg"));
							Image newimgKat = kataraImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							kataraImg = new ImageIcon( newimgKat );
							Border border = BorderFactory.createLineBorder(Color.GREEN);
			                compAtk.setBorder(border);
							compAtk.setIcon(kataraImg);
							
							round.setForeground(Color.GREEN);
							roundNum.setForeground(Color.GREEN);
							
							System.out.println("Water beats fire!\nYou win!");
							uScore++;
							
							yourScore.setForeground(Color.GREEN);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.WHITE);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.GREEN);
							messageNotification.setText("You won!!!");
							
							textArea.append("Round: " + (rnd-1) +"| You won.\n");
						}
						else 
						{
							ImageIcon azulaImg = new ImageIcon(getClass().getResource("azulaAttack.jpg"));
							Image newimgAzl = azulaImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							azulaImg = new ImageIcon( newimgAzl );
			                Border border = BorderFactory.createLineBorder(Color.RED);
			                compAtk.setBorder(border);
							compAtk.setIcon(azulaImg);
							
							round.setForeground(Color.RED);
							roundNum.setForeground(Color.RED);
							
							System.out.println("Fire beats grass!\nYou lose!");
							cScore++;
							
							yourScore.setForeground(Color.WHITE);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.RED);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.RED);
							messageNotification.setText("Computer won!!!");
							
							textArea.append("Round: " + (rnd-1) +"| Computer won.\n");
						}
						break;
				case 2: System.out.println("The computer chose water!");
						if(youAttack.equals("F"))
						{
							ImageIcon hamaImg = new ImageIcon(getClass().getResource("hamaAttack.png"));
							Image newimgHam = hamaImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							hamaImg = new ImageIcon( newimgHam );
							Border border = BorderFactory.createLineBorder(Color.RED);
			                compAtk.setBorder(border);
							compAtk.setIcon(hamaImg);
							
							
							round.setForeground(Color.RED);
							roundNum.setForeground(Color.RED);
							
							System.out.println("Water beats fire!\nYou lose!");
							cScore++;
							
							yourScore.setForeground(Color.WHITE);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.RED);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.RED);
							messageNotification.setText("Computer won!!!");
							
							textArea.append("Round: " + (rnd-1) +"| Computer won.\n");
						}
						else if(youAttack.equals("W"))
						{
							ImageIcon aangImg = new ImageIcon(getClass().getResource("aangCalm.png"));
							Image newimgAng = aangImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							aangImg = new ImageIcon( newimgAng );
							Border border = BorderFactory.createLineBorder(Color.CYAN);
			                compAtk.setBorder(border);
							compAtk.setIcon(aangImg);
							
							round.setForeground(Color.CYAN);
							roundNum.setForeground(Color.CYAN);
							
							yourScore.setForeground(Color.CYAN);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.CYAN);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.CYAN);
							messageNotification.setText("It's a tie!!!");
							
							textArea.append("Round: " + (rnd-1) +"| Tied.\n");
						}
						else 
						{
							ImageIcon tophImg = new ImageIcon(getClass().getResource("tophAttack.jpg"));
							Image newimgTof = tophImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							tophImg = new ImageIcon( newimgTof );
							Border border = BorderFactory.createLineBorder(Color.GREEN);
			                compAtk.setBorder(border);
							compAtk.setIcon(tophImg);
							
							round.setForeground(Color.GREEN);
							roundNum.setForeground(Color.GREEN);
							
							System.out.println("Grass beats water!\nYou win!");
							uScore++;
							
							yourScore.setForeground(Color.GREEN);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.WHITE);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.GREEN);
							messageNotification.setText("You won!!!");
							
							textArea.append("Round: " + (rnd-1) +"| You won.\n");
						}
						break;
				case 3: System.out.println("The computer chose grass!");
						if(youAttack.equals("F"))
						{
							ImageIcon zukoImg = new ImageIcon(getClass().getResource("zukoAttack.jpg"));
							Image newimgZuk = zukoImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							zukoImg = new ImageIcon( newimgZuk );
							Border border = BorderFactory.createLineBorder(Color.GREEN);
			                compAtk.setBorder(border);
							compAtk.setIcon(zukoImg);
							
							round.setForeground(Color.GREEN);
							roundNum.setForeground(Color.GREEN);
							
							System.out.println("Fire beats grass!\nYou win!");
							uScore++;
							
							yourScore.setForeground(Color.GREEN);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.WHITE);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.GREEN);
							messageNotification.setText("You won!!!");
							
							textArea.append("Round: " + (rnd-1) +"| You won.\n");
						}
						else if(youAttack.equals("W"))
						{
							ImageIcon longFengImg = new ImageIcon(getClass().getResource("longFengAttack.jpg"));
							Image newimgLng = longFengImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							longFengImg = new ImageIcon( newimgLng );
							Border border = BorderFactory.createLineBorder(Color.RED);
			                compAtk.setBorder(border);
							compAtk.setIcon(longFengImg);
							
							round.setForeground(Color.RED);
							roundNum.setForeground(Color.RED);
							
							System.out.println("Grass beats water!\nYou lose!");
							cScore++;
							
							yourScore.setForeground(Color.WHITE);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.RED);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.RED);
							messageNotification.setText("Computer won!!!");
							
							textArea.append("Round: " + (rnd-1) +"| Computer won.\n");
						}
						else 
						{
							ImageIcon aangImg = new ImageIcon(getClass().getResource("aangCalm.png"));
							Image newimgAng = aangImg.getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
							aangImg = new ImageIcon( newimgAng );
							Border border = BorderFactory.createLineBorder(Color.CYAN);
			                compAtk.setBorder(border);
							compAtk.setIcon(aangImg);
							
							round.setForeground(Color.CYAN);
							roundNum.setForeground(Color.CYAN);
							
							yourScore.setForeground(Color.CYAN);
							yourScore.setText("Your Score: " + uScore);
							
							compScore.setForeground(Color.CYAN);
							compScore.setText("Comp Score: " + cScore);
							
							messageNotification.setForeground(Color.CYAN);
							messageNotification.setText("It's a tie!!!");
							
							textArea.append("Round: " + (rnd-1) +"| Tied.\n");
						}
						break;
			}
	
			//CLOSE CONNECTION
			//s.close();
		}
		catch(IOException err){
			System.out.println("ERROR client");
			err.printStackTrace();
		}
	}
	
	public static void playSound(String filename) {
		try{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}catch(LineUnavailableException err){
			System.out.println("Audio Error");
		}catch(IOException err){
			System.out.println("File not found.");
		}catch(UnsupportedAudioFileException err){
			System.out.println("Wrong file type.");
		}
	}

}

