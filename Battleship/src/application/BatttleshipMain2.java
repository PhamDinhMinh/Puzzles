package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BatttleshipMain2 extends BattleshipMain{
	 private static Stack<Cell> Q = new Stack<>();
	    List<Cell> done = new ArrayList<Cell>();
    public void enemyMove(Stage primaryStage) {
    	while (enemyTurn) {
    		if (!Q.isEmpty()) {
    			
    			Cell cell = Q.pop();
    			for(Cell m : done) {
	            	if(cell.x==m.x&&cell.y==m.y) {
	            		cell.wasShot= true;break;
	            	}
	            }
                if (cell.wasShot)
                    continue;

                enemyTurn = cell.shoot();
                count2++;
                textArea2.appendText("Step"+count2+":  x = "+ cell.x+" "+"  y="+(char)(cell.y+65)+"\n");
                
                if(cell.ship!=null) {
                	   
        			   score = score - cell.ship.point;
                
    			if (enemyTurn) {
                        if(cell.ship.vertical) {
                        	
                        	if(cell.y>0) Q.push(playerBoard.getCell(cell.x, cell.y-1));
                        	if(cell.y<9) Q.push(playerBoard.getCell(cell.x, cell.y+1));
                        }
                        if(!cell.ship.vertical) {
                        	
                        	if(cell.x>0) Q.push(playerBoard.getCell(cell.x-1, cell.y));
                        	if(cell.x<9) Q.push(playerBoard.getCell(cell.x+1, cell.y));
                        }

                        if(!cell.ship.isAlive()) { 
                        	Q.clear();
                        	for(int k=0;k<cell.ship.type;k++) {
                        		for(Cell c: playerBoard.getNeighbors(cell.ship.arr[k].x, cell.ship.arr[k].y)) {
              	    			  done.add(c);
              	    		  }
                        		
                        	}
    					    
                        }
    		    	}
                }
    		}
    		 
    		else {
    			
    	            int x = random.nextInt(10);
    	            int y = random.nextInt(10);

    	            Cell cell = playerBoard.getCell(x, y);
    	            for(Cell m : done) {
    	            	if(cell.x==m.x&&cell.y==m.y) {
    	            		cell.wasShot= true;break;
    	            	}
    	            }
    	            if (cell.wasShot)
    	                continue;

    	            enemyTurn = cell.shoot();
    	            count2++;
                    textArea2.appendText("Step"+count2+":  x = "+ cell.x+" "+"  y="+(char)(cell.y+65)+"\n");
    	           
    	       if(cell.ship!=null) {    	           	   
           			   score = score - cell.ship.point;
             
           	 
    	    	  for(Cell p: playerBoard.getNeighbors(x, y)) {
    	    		  Q.push(p);
    	    		  
    	    	  }
    	       }
    	       
    		}
    		
        } 
            if (playerBoard.ships == 0) {
                
                newscore.addlose();
                mediaPlayer.stop();
                Media media3 = new Media(getClass().getResource("/sound/lose.mp3").toString());
            	MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
                mediaPlayer3.setVolume(1);
               mediaPlayer3.setAutoPlay(true);
                
                Image image3= new Image(getClass().getResourceAsStream("/images/youlose.jpg"));
                ImageView imageView3 = new ImageView(image3);
                Pane root2 = new Pane(imageView3);
                menu.setFont(new Font(25));
    	        menu.setLayoutX(250);
    	        menu.setLayoutY(430);
    	        history.setFont(new Font(25));
    	        history.setLayoutX(680);
    	        history.setLayoutY(430);
                root2.getChildren().addAll(menu , history);
                Scene secondScene = new Scene(root2, 1000, 560);
                primaryStage.setScene(secondScene);
                primaryStage.show();
                enemyTurn = false;
           }
       }
    }

 


