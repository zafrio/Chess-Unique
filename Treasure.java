import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
Provide treasure informations

@author Rahimi
**/
public class Treasure{
	private int x; //treasure's position of row
	private int y; //treasure's position of column
	private ImageIcon treasureImg; //treasure's image
	
	//constructor for treasure class
	public Treasure(int x, int y, String strTresureImg){
		this.x = x;
		this.y = y;
		treasureImg = new ImageIcon(strTresureImg);
	}
	//to get treasure's position of row
	public int getX(){
		return x;
	}
	//to get treasure's position of column
	public int getY(){
		return y;
	}
	//to set treasure's position of row and column
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	//to get treasure's image
	public ImageIcon getTreasureImg(){
		return treasureImg;
	}
	
}