import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
Provide player informations

@author John
**/
public class Player {

    // player name
	private String name; 
	// player id
    private int PlayerId; 
	// list of player's key
    private ArrayList<Key> playerKey = new ArrayList<Key>(); 
	// row position of player
    private int x; 
	// initial row position of player
	private int initX; 
	// column position of player
    private int y; 
	// initial column position of player
	private int initY;
	// icon for player
	private ImageIcon playerimg; 
	// player's turn
	private int turn; 
	// line to get file's name **refers line 30 and 32**
	private String filename;
	// parameters to receive key **refers line 107**
	private Key key; 
	
	//Constructor for player class
    public Player(String name, int PlayerId, int x ,int y, String strplayerimg, int turn){
        this.name = name;
        this.PlayerId = PlayerId;
		this.x=x;
		this.y=y;
		filename = strplayerimg;
		this.turn = turn;
		playerimg = new ImageIcon(filename);
    }	
	//to get the player's icon
	public ImageIcon getIcon(){
		return playerimg;
	}
	public String getFilename(){
		return filename;
	}
	//to get the player's turn
	public int getTurn(){
		return this.turn;
	}
	//to set the player's turn
	public void setTurn(int turn){
		this.turn = turn;
	}
	//to get the player's row position
    public int getX(){
        return this.x;
    }
	//to set the player's row position
    public void setX(int x){
        this.x = x;
    }
	//to get the player's column position
    public int getY(){
        return this.y;
    }
	//to set player's column position
    public void setY(int y){
        this.y = y;
    }
	//to get the player's id for reference 
    public int getPlayerId(){
        return PlayerId;
    }
	//to set the player's id 
     public void setPlayerId(){
        this.PlayerId=PlayerId;
    }
	//to get the player's name
    public String getName(){
        return this.name;
    }
	//to set the player's name
    public void setName(String name){
        this.name = name;
    }
	//player's initial move
    public boolean playerMove(int i, int j,int x,int y){
		if(playerKey.size() == 0)	
		{
			int rowDelta = Math.abs(i-x);
			int colDelta = Math.abs(j-y);
			if((rowDelta == 0)&& (colDelta == 1)||(rowDelta == 1)&& (colDelta == 1)
				||(rowDelta == 1)&& (colDelta == 0)||(rowDelta == 0)&& (colDelta == 2)
				||(rowDelta == 2)&& (colDelta == 2)||(rowDelta == 2)&& (colDelta == 0)
				||(rowDelta == 2)&& (colDelta == 0))
			{
				return true;
			}
			return false;
		}else {
			return playerKey.get(playerKey.size() - 1).keyMove(i,j,x,y);
		}
    }
	//to add player's key
    public void addPlayerKey(Key key)
    {	
		for(int i=0; i < playerKey.size(); i++){
			if(playerKey.get(i).getKeyId() == key.getKeyId()){
				return;
			}
		}
		playerKey.add(key);
    }
	//to return player's key
	public ArrayList<Key> getPlayerKey()
	{
		return playerKey;
	}

}
