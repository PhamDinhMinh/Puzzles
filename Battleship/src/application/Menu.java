package application;
	
import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Menu {
	public void start(Stage primaryStage) {
        // background
		InputStream input = getClass().getResourceAsStream("/images/background.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
		    Pane root = new Pane(imageView);
			Scene scene = new Scene(root,1000,600);
			// 4 nut normal, hard,history, about
			InputStream input1 = getClass().getResourceAsStream("/images/normal.png");
	        Image image1 = new Image(input1);
	        ImageView imageView1 = new ImageView(image1);
	        
	        Button but1 = new Button();
	        but1.setGraphic(imageView1);
	        but1.setLayoutX(100);
	        but1.setLayoutY(275);
	        
	        but1.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	BattleshipMain main1 = new BattleshipMain();
	            	main1.start(primaryStage);
	            }
	        });

			InputStream input2 = getClass().getResourceAsStream("/images/hard.png");
			 
	        Image image2 = new Image(input2);
	        ImageView imageView2 = new ImageView(image2);
	        
	        Button but2 = new Button();
	        but2.setGraphic(imageView2);
	        but2.setLayoutX(100);
	        but2.setLayoutY(375);
	        but2.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	BatttleshipMain2 main1 = new BatttleshipMain2();
	            	main1.start(primaryStage);
	            }
	        });
	        
			InputStream input3 = getClass().getResourceAsStream("/images/history.png");
			 
	        Image image3 = new Image(input3);
	        ImageView imageView3 = new ImageView(image3);
	        
	        Button but3 = new Button();
	        but3.setGraphic(imageView3);
	        but3.setLayoutX(100);
	        but3.setLayoutY(475);
	        
	        
	        but3.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	 
	            	history main1 = new history();
	            	main1.start(primaryStage);
	            }
	        });
	        InputStream input4 = getClass().getResourceAsStream("/images/about.png");
			 
	        Image image4 = new Image(input4);
	        ImageView imageView4 = new ImageView(image4);
	        Button but4 = new Button();
	        but4.setShape(new Circle(25));
	        but4.setMinSize(50, 50);
	        but4.setMaxSize(50, 50);
	        but4.setGraphic(imageView4 );
	        but4.setOnAction(new EventHandler<ActionEvent>() {
	        	 // hanh dong khi an nut about
	            @Override
	            public void handle(ActionEvent event) {
	               
	                Label secondLabel = new Label();
	                //doc file about
	                BufferedReader br = null;
	                String s= getClass().getResource("about.txt").toString().replace("bin", "src").substring(6);
	    			try {
	    				br = new BufferedReader(new FileReader(new File(
	    						s)));
	    			} catch (FileNotFoundException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			String str = "";
	    			String line = "";
	    			try {
	    				while((line=br.readLine())!=null){
	    					str=str+line+"\n";
	    				}
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			try {
	    				br.close();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block   
	    				e.printStackTrace();
	    			}
	    			secondLabel.setText(str);
	    		   // secondLabel.setFont(new Font(30));
	                Pane secondaryLayout = new Pane();
	                secondaryLayout.getChildren().add(secondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout, 600, 600);
	 
	                // Một cửa sổ mới (Stage)
	                Stage newWindow = new Stage();
	                newWindow.setTitle("About");
	                newWindow.setScene(secondScene);
	 
	                // Sét đặt vị trí cho cửa sổ thứ 2.
	                // Có vị trí tương đối đối với cửa sổ chính.
	                newWindow.setX(primaryStage.getX() + 200);
	                newWindow.setY(primaryStage.getY() );
	 
	                newWindow.show();
	            }
	        });

	        primaryStage.setX(250);
	        primaryStage.setY(70);
	        primaryStage.setScene(scene);
			root.getChildren().addAll(but1, but2, but3,but4);
			primaryStage.setTitle("Battleship");
			primaryStage.setResizable(false);
			primaryStage.show();
	}
	
	
}