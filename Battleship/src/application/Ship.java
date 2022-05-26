package application;

import javafx.scene.Parent;

public class Ship extends Parent {
    public int type;
    public boolean vertical = true;
    public int point;
    public Cell arr[]= new Cell[5];
    private int health;

    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        health = type;
        // diem cho moi loai tau
        if(type==5) {
        	point=5;
        }else {
        	if(type==4) {
        		point=6;
        	}else {
        		if(type==3) {
        			point=7;
        		}else {
        			if(type==2) {
        				point=9;
        			}else {
        				point=12;
        			}
        		}
        	}
        }
    }
     // trung 
    public void hit() {
        health--;
    }
    //con song
    public boolean isAlive() {
        return health > 0;
    }
}