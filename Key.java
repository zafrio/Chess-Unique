import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
Provide key informations

@author Aaron
**/
public class  Key {

	private int KeyId; //Key id for reference
	private int x; //Key's position of row
	private int y; // Key's position of column
	private ImageIcon keyImg; //Key's icon
	private String filename; //Key's file name
	
	//Constructor for Key class
	public Key(int KeyId, int x,int y,String strKeyImg){
        this.KeyId = KeyId;
		this.x = x;
		this.y = y;
		filename = strKeyImg;
		keyImg = new ImageIcon(filename);
    }
	public String getFilename(){
		return filename;
	}
	//to get Key id for reference
    public int getKeyId(){
        return KeyId;
    }
	//to set Key id
     public void setKeyId(){
        this.KeyId=KeyId;
    }
	//to get Key's position of row
        public int getX(){
        return this.x;
    }
	//to set Key's position of row
    public void setX(int x){
        this.x = x;
    }
	//to get Key's postion of column
    public int getY(){
        return this.y;
    }
	//to set Key's position of column
    public void setY(int y){
        this.y = y;
    }
	//to get Key's image
	public ImageIcon getKeyImg(){
		return keyImg;
	}
	//movement of each keys **reference on Player.java line 89**
	public boolean keyMove(int i, int j,int x,int y){
		if (KeyId == 1){ //Pinkey movement
			int rowDelta = Math.abs(i-x);
			int colDelta =Math.abs(j-y);
			if(((rowDelta == 0)&& (colDelta == 1))||((rowDelta == 1)&& (colDelta == 1))
				||((rowDelta == 1)&& (colDelta == 0)))
			{
				return true;
			}
			return false;
			
		} else if (KeyId == 2){ //Donkey movement
			int rowDelta = Math.abs(i-x);
			int colDelta =Math.abs(j-y);

			if((rowDelta == 1)&& (colDelta == 1)||(rowDelta == 2)&& (colDelta == 2)
				||(rowDelta == 3)&& (colDelta == 3))
			{
				return true;
			}
			return false;
			
		}else if (KeyId == 3){ //KeyDisk movement
			int rowDelta = Math.abs(i-x);
			int colDelta =Math.abs(j-y);
			if((rowDelta == 0)&& (colDelta == 1)||(rowDelta == 1)&& (colDelta == 0)
				||(rowDelta == 0)&& (colDelta == 2)||(rowDelta == 2)&& (colDelta == 0)
				||(rowDelta == 0)&& (colDelta == 3)||(rowDelta == 3)&& (colDelta == 0))
			{
				return true;
			}
			return false;
			
		}else if (KeyId == 4){ //KeyNote movement
			int rowDelta = Math.abs(i-x);
			int colDelta =Math.abs(j-y);
			if (rowDelta == 2){
				if (colDelta == 2 || colDelta == 0){
					return true;
				}
			}else if (rowDelta == 0){
				if (colDelta == 2){
					return true;
				}
			}
			return false;
			
		}else if (KeyId == 5){ //Monkey movement
			int rowDelta = Math.abs(i-x);
			int colDelta =Math.abs(j-y);
			if((rowDelta == 0)&& (colDelta == 1)||(rowDelta == 1)&& (colDelta == 1)
				||(rowDelta == 1)&& (colDelta == 0)||(rowDelta == 0)&& (colDelta == 2)
				||(rowDelta == 2)&& (colDelta == 2)||(rowDelta == 2)&& (colDelta == 0)
				||(rowDelta == 0)&& (colDelta == 3)||(rowDelta == 3)&& (colDelta == 3)
				||(rowDelta == 3)&& (colDelta == 0))
			{
				return true;
			}
			return false;
			
		}else {
			return false;
		}
	}
}
