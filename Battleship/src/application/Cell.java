package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.ImagePattern;

public class Cell extends Rectangle {
	Images img = new Images();
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;
    public boolean headOfShip = false;
//     Image boom  = new Image("file:///C:/Users/Admin/eclipse-workspace/hhh/src/hhh/boom.jpg");
     

   
    private Board board;
    //tao 1 o
    public Cell(int x, int y, Board board) {
        super(35, 35);// kich thuc
        this.x = x;
        this.y = y;
        this.board = board;
        // anh 1 o
       setFill(new ImagePattern(img.waterorg));
        setStroke(Color.LIGHTGREEN);
    }
    // ktra ban trung
    public boolean shoot() {
        wasShot = true;
        setFill(new ImagePattern(img.watermiss));
        
        if (ship != null) {
            ship.hit();
            setFill(new ImagePattern(img.explosion));
            if (!ship.isAlive()) {
                board.ships--;
                if(ship.vertical) {
                	if(ship.type!=1) {
            		ship.arr[0].setFill(new ImagePattern(img.HeadShipVerdes));
            		for(int i=1;i<ship.type-1;i++) ship.arr[i].setFill(new ImagePattern(img.BodyShipVerdes));
                     ship.arr[ship.type-1].setFill(new ImagePattern(img.TailShipVerdes));
                	}
                	else ship.arr[0].setFill(new ImagePattern(img.tau1docdes));
                }
                if(!ship.vertical) {
                	if(ship.type!=1) {
            		ship.arr[0].setFill(new ImagePattern(img.HeadShipHorides));
            		for(int i=1;i<ship.type-1;i++) ship.arr[i].setFill(new ImagePattern(img.BodyShipHorides));
                    ship.arr[ship.type-1].setFill(new ImagePattern(img.TailShipHorides));
                	}
                	else ship.arr[0].setFill(new ImagePattern(img.tau1ngangdes));
                }
            }
      
            
            
            return true;
        }
        

        return false;
    }
    public boolean enemyshoot() {
    	
        wasShot = true;
        setFill(new ImagePattern(img.watermiss));
        
        if (ship != null) {
            ship.hit();
            setFill(new ImagePattern(img.explosion));
            if (!ship.isAlive()) {
                board.ships--;
                if(ship.vertical) {
                	if(ship.type!=1) {
            		ship.arr[0].setFill(new ImagePattern(img.HeadShipVerdes));
            		for(int i=1;i<ship.type-1;i++) ship.arr[i].setFill(new ImagePattern(img.BodyShipVerdes));
                     ship.arr[ship.type-1].setFill(new ImagePattern(img.TailShipVerdes));
                	}
                	else ship.arr[0].setFill(new ImagePattern(img.tau1docdes));
                }
                if(!ship.vertical) {
                	if(ship.type!=1) {
            		ship.arr[0].setFill(new ImagePattern(img.HeadShipHorides));
            		for(int i=1;i<ship.type-1;i++) ship.arr[i].setFill(new ImagePattern(img.BodyShipHorides));
                    ship.arr[ship.type-1].setFill(new ImagePattern(img.TailShipHorides));
                	}
                	else ship.arr[0].setFill(new ImagePattern(img.tau1ngangdes));
                }
            }
      
            
            
            return true;
        }
        

        return false;
    }
}