import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
Provide Board's Box of the game information

@author Zafir
**/
public class ButtonBox extends JButton{


    private Player player; // Player inside the box
	private Key key; // Key inside the box
	private Treasure treasure; // Treasure inside the box

    public ButtonBox(){
    }
	//Check if the box has treasure
	public boolean hasTreasure(){
		return treasure != null;
	}
	//Check if the box has key
	public boolean hasKey(){
		return key != null;
	}
	//Check if the box has Player
	public boolean hasPlayer(){
		return player != null;
	}
	//return the key inside the box
	public Key getKey(){
		return key;
	}
	//set key inside the box
	public void setKey(Key key){
		this.key = key;
		this.player = null;
		this.setIcon(key.getKeyImg());
	}
	//return player inside the box
	public Player getPlayer(){
		return player;
	}
	//set player inside the box
	public void setPlayer(Player player){
		this.player = player;
		this.setIcon(player.getIcon());
	}
	//set treasure inside the box
	public void setTreasure(Treasure treasure){
		this.treasure = treasure;
		this.setIcon(treasure.getTreasureImg());
	}
	//reset the box after player get out of this box
	public void resetCur(){
		this.player = null;
		this.setIcon(null);
	
		if(hasKey()){
			setKey(key);
		}
	}
	//reset the boxes
	public void resetAll(){
		this.player = null;
		this.setIcon(null);
		this.treasure = null;
		this.key = null;
	}
}

