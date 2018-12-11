import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
/**
Provides game logic and rule 

@author John, Rahimi, Zafir, and Aaron
**/

public class Board extends JFrame implements ActionListener
{
	// integer of row
    private int row= 0; 
	// integer of col
    private int col = 0; 
	// parents of board game panel and statistic panel
    private JPanel contents; 
	// the board game panel
	private JPanel boardPanel; 
	// statistic of player's key panel
	private JPanel statsPanel; 
	// Boxes of the game panel
    private ButtonBox[][] squares = new ButtonBox[9][9]; 
	// Boxes of statistic panel
	private JLabel[][] labels = new JLabel[4][6]; 
	// Arraylist of players
    private ArrayList<Player> addPlayer = new ArrayList<Player>(); 
	// Arraylist of keys
    private ArrayList<Key> addKey = new ArrayList<Key>(); 
	// this will decide player's turn 
	private int turn = 0; 
	// this will generate random numbers for keys
	Random rand = new Random(); 
	// key's position of row or column
	int n; 
	// key's position of row or column
	int m; 
	// treasure item
	private Treasure treasure; 
	// the file name for the title to save the progress of the game
	private String SAVEFILE = "save.txt";
	
	/***
	Setting up the User interface and Gameplay
	
	@author John
	**/
    public Board(){
        super("KEY COLLECTOR GAMES");
        contents = new JPanel(new GridLayout(1,2,1,1)); 
		
		boardPanel = new JPanel(new GridLayout(9,9,2,2));
        boardPanel.setLayout(new GridLayout(9,9,2,2));
		
		
		for(int i= 0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                squares[i][j] = new ButtonBox();
                boardPanel.add(squares[i][j]);
                squares[i][j].addActionListener(this);
            }
        }
		
		setJMenuBar(createTopMenu());
		
		statsPanel = new JPanel(new GridLayout(4, 6, 0,0));
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++){
				labels[i][j] = new JLabel();
				statsPanel.add(labels[i][j]);
			}
		}
		reset();
		for(int i = 0; i < 4; i++){
			labels[i][0].setIcon(addPlayer.get(i).getIcon());
		}
		labels[0][0].setBackground(Color.green);
		labels[0][0].setOpaque(true);
		contents.add(boardPanel);
		contents.add(statsPanel);
		
        setContentPane(contents);        
        pack();
		
        setSize(1000,650);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);		
    }
    public static void main(String[] args){
		Board gui = new Board();
    }
	
	/**
	This is when the player click on the button
	
	@author Rahimi
	**/
    public void actionPerformed(ActionEvent e){	 
		Object source = e.getSource();

		for (int i=0;i<9;i++)
		{
			for (int j=0; j<9;j++)
			{
				if (source == squares[i][j])
				{
					processClick(i,j,turn);
					return;
				}
			}
		}
	}
	/**
	This is to check the player's desired position

	@author Zafir
	**/
	public void processClick(int i, int j, int k){	//Checking after the player click if the box available or not
		Player p = addPlayer.get(k);
		
		if (p.playerMove(i, j, p.getX(), p.getY())){
			if(squares[i][j].hasPlayer()||(squares[i][j].hasTreasure()&& p.getPlayerKey().size()<5)){
				return;
			}else if((squares[i][j].hasTreasure()&& p.getPlayerKey().size()==5)){
					JOptionPane.showMessageDialog(null, 
                              p.getName()+", you're a winner", 
                              "Treasure has been opened", 
                              JOptionPane.WARNING_MESSAGE);
					reset();
					return;
				}
		
			if(squares[i][j].hasKey()){
				p.addPlayerKey(squares[i][j].getKey());
				squares[i][j].setPlayer(p);
				if(p.getPlayerKey().size()>1){
					labels[p.getTurn()][p.getPlayerKey().size()-1].setBackground(null);
				}
				labels[p.getTurn()][p.getPlayerKey().size()].setIcon(p.getPlayerKey().get(p.getPlayerKey().size()-1).getKeyImg());
			}else if (!squares[i][j].hasKey()&& !squares[i][j].hasPlayer()){
				squares[i][j].setPlayer(p);
			}
			
			squares[p.getX()][p.getY()].resetCur();
			p.setX(i);
			p.setY(j);
			
			turn++;
			if(turn > 3){
				turn=0;
			}
			if(turn==0){
				labels[turn][0].setBackground(Color.green);
				labels[turn][addPlayer.get(turn).getPlayerKey().size()].setBackground(Color.green);
				labels[turn][0].setOpaque(true);
				labels[turn][addPlayer.get(turn).getPlayerKey().size()].setOpaque(true);
				labels[3][0].setBackground(null);
				labels[3][addPlayer.get(3).getPlayerKey().size()].setBackground(null);
			}else{
				labels[turn][0].setBackground(Color.green);
				labels[turn][addPlayer.get(turn).getPlayerKey().size()].setBackground(Color.green);
				labels[turn][0].setOpaque(true);
				labels[turn][addPlayer.get(turn).getPlayerKey().size()].setOpaque(true);
				labels[turn-1][0].setBackground(null);
				labels[turn-1][addPlayer.get(turn-1).getPlayerKey().size()].setBackground(null);
			}
		}
	}
	/**
	This to reset the position of player, key and treasure
	
	@author Aaron
	**/
	public void reset() { //reset the game
		addPlayer.removeAll(addPlayer);
		addKey.removeAll(addKey);
		treasure = null;
		treasure = new Treasure(4,4,"5.gif");
		
		addPlayer.add(new Player("Player 1",1,0,0,"1.gif",0));
        addPlayer.add(new Player("Player 2",2,0,8,"2.gif",1));
		addPlayer.add(new Player("Player 3",3,8,8,"3.gif",2));
        addPlayer.add(new Player("Player 4",4,8,0,"4.gif",3));
		for(int i= 0;i<9;i++)
        {
			for(int j=0;j<9;j++)
			{
				squares[i][j].resetAll();
			}
        }
		squares[treasure.getX()][treasure.getY()].setTreasure(treasure);
		for(int i = 1; i < 6;i++){
			while(true){
				n = rand.nextInt(7) + 1;
				m = rand.nextInt(7) + 1;
				if((n == 4 && m == 4 ) || squares[m][n].hasKey()|| squares[n][m].hasKey()
					|| squares[m][n].hasPlayer() || squares[n][m].hasPlayer())
				{
					continue;
				}
				else{
					break;
				}
			}
			
			if(i==1)
				addKey.add(new Key(i,m,n,"a.gif"));
			else if(i==2)
				addKey.add(new Key(i,n,m,"b.gif"));
			else if(i==3)
				addKey.add(new Key(i,m,n,"c.gif"));
			else if(i==4)
				addKey.add(new Key(i,n,m,"d.gif"));
			else
				addKey.add(new Key(i,m,n,"e.gif"));
		}		
		
		for(int k=0; k<4; k++){
			Player p = addPlayer.get(k);
			squares[p.getX()][p.getY()].setPlayer(p); 
		}
		for(int j=0; j<5; j++){ 
			Key k = addKey.get(j);
			squares[k.getX()][k.getY()].setKey(k);
		}
		
		for(int row=0; row<4; row++){
			for(int col=0; col<6; col++){		
			labels[row][col].setBackground(null);
			}
		}
		for(int row=0; row<4; row++){
			for(int col=1; col<6; col++){
				labels[row][col].setIcon(null);
			}
		}
		
		turn = 0;
		
	}
	/**
	save game function
	
	@author by Zafir
	**/	
	public String saveGame(){
		File file = new File(SAVEFILE);
		FileWriter writer;
		try{
			writer = new FileWriter(file);
			writer.write(turn + "\r\n");
			
			for(int i =0; i <= (addKey.size()-1); i++){
				writer.write(Integer.toString(addKey.get(i).getX())+ "\r\n");
				writer.write(Integer.toString(addKey.get(i).getY())+ "\r\n");	
			}
			for(int i =0; i<= (addPlayer.size()-1); i++){
				writer.write(Integer.toString(addPlayer.get(i).getX())+ "\r\n");
				writer.write(Integer.toString(addPlayer.get(i).getY())+ "\r\n");
				for(int j=0; j<=4; j++){
					if(j>=addPlayer.get(i).getPlayerKey().size()){
						writer.write("0"+ "\r\n");
					}else{
						writer.write(addPlayer.get(i).getPlayerKey().get(j).getKeyId()+ "\r\n");
					}
				}
			}
			
			writer.flush();
			writer.close();
			return "Saved";
		}catch (IOException ex){
			return "Error";
		}
	}
	/**
	Load game function
	
	@author by Zafir
	**/	
	public String loadGame(){
		try{
			File file = new File(SAVEFILE);
			Scanner scanner = new Scanner(file);
			
			turn = scanner.nextInt();
			
			for(int i=0; i <5; i++){
				addKey.get(i).setX(scanner.nextInt());
				addKey.get(i).setY(scanner.nextInt());
			}
			for(int i=0; i< 4; i++){
				addPlayer.get(i).setX(scanner.nextInt());
				addPlayer.get(i).setY(scanner.nextInt());
				int k;
				for(int j=0; j<5; j++){
					k = scanner.nextInt();
					if(k!=0){
						for(int l=0; l<=(addKey.size()-1); l++){
							if(addKey.get(l).getKeyId()==k){
								addPlayer.get(i).addPlayerKey(addKey.get(l));
							}
						}
					}
				}
			}
			for(int i= 0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					squares[i][j].resetAll();
				}
			}
			for(int j=0; j<5; j++){ 
				Key k = addKey.get(j);
				squares[k.getX()][k.getY()].setKey(k);
			}
			squares[treasure.getX()][treasure.getY()].setTreasure(treasure);
			for(int k=0; k<4; k++){
				Player p = addPlayer.get(k);
				squares[p.getX()][p.getY()].setPlayer(p); 
			}
			
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 6; j++){
					labels[i][j].setIcon(null);
				}
			}
			for(int i = 0; i < 4; i++){
				labels[i][0].setIcon(addPlayer.get(i).getIcon());
				if(addPlayer.get(i).getTurn() == turn){
					labels[i][0].setBackground(Color.green);
					labels[i][addPlayer.get(i).getPlayerKey().size()].setBackground(Color.green);
				}
			}
			for(int i=0; i < 4; i++){	
				for(int j =1; j<=(addPlayer.get(i).getPlayerKey().size()); j++){
					labels[i][j].setIcon(addPlayer.get(i).getPlayerKey().get(j-1).getKeyImg());
				}
			}
			return "Load Success";
		}catch (Exception ex){
			return "Error";
		}
	}
	/**
	Menu bar on the top 
	
	@author by Rahimi, John and Aaron
	**/
	public JMenuBar createTopMenu(){
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Menu");
        /**
		New Game Button
		@author by Rahimi
		**/
        JMenuItem newGameButton = new JMenuItem("New Game");
        newGameButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newGameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reset();
				//i is row and j is column
				
				labels[0][0].setBackground(Color.green);
            }
        });
		menu.add(newGameButton);
		/**
		Save Game button
		
		@author by John
		**/
		JMenuItem saveGameButton = new JMenuItem("Save Game");
		saveGameButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveGameButton.addActionListener(new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,saveGame());
			}
        });
        menu.add(saveGameButton);
		/**
		Load Game Button
		
		@author by Aaron
		**/
		JMenuItem loadGameButton = new JMenuItem("Load last game");
        loadGameButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        loadGameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reset();
				JOptionPane.showMessageDialog(null,loadGame());
            }
        });
        menu.add(loadGameButton);
		/**
		Help menu bar 
		Inside the menu bar has About button
		
		@author by Rahimi
		**/
		JMenu help = new JMenu("Help");
        JMenuItem howButton = new JMenuItem("About");
        howButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
			
						JOptionPane.showMessageDialog(null,"Key Collector Game\n"+
                        "developed by HelloWorld\n\n"+
                        "Team Member:\n"+
                        "- 1142702599 Zafir Naim Bin Zulkifli\n"+
                        "- 1141128491 Muhammad Rahimie Bin Rosli\n"+
                        "- 1132701350 John Escobia\n"+
                        "- 1131120615 Aaron Raj\n");
  
            }
        });
        
        help.add(howButton);
        /**
		Inside the menu bar has Instruction button
		
		@author by Rahimi
		**/
        JMenuItem aboutButton = new JMenuItem("Instruction");
        aboutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
			Key key = new Key(0,0,0,null);
                JPanel panel = new JPanel(new GridLayout(10, 1, 2, 2));
                panel.add(new JLabel(addKey.get(0).getKeyImg()));
                panel.add(new JLabel("Can only move 1 square in any direction.", JLabel.CENTER));
                panel.add(new JLabel(addKey.get(1).getKeyImg()));
                panel.add(new JLabel("Can move up to 3 squares diagonally, but cannot move horizontally or vertically.", JLabel.CENTER));
                panel.add(new JLabel(addKey.get(2).getKeyImg()));
                panel.add(new JLabel("Can move up to 3 squares horizontally or vertically, but cannot move diagonally.", JLabel.CENTER));
				panel.add(new JLabel(addKey.get(3).getKeyImg()));
                panel.add(new JLabel("Must move 2 squares in any direction (i.e. skip 1 square.).", JLabel.CENTER));
				panel.add(new JLabel(addKey.get(4).getKeyImg()));
                panel.add(new JLabel("Can move up to 3 squares in any direction.", JLabel.CENTER));
				
				showSimpleDialog(panel, "HOW TO PLAY?");
				
            }
        });
        help.add(aboutButton);
		
		menuBar.add(menu);
		menuBar.add(help);
		return menuBar;
	}
	 public static void showSimpleDialog(Object message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

