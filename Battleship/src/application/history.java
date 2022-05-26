package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class history {
	score scoree= new score();
	public void start(Stage primaryStage) {
		   Label secondLabel = new Label();
		   //nut menu
		   Button menu = new Button("Menu");
		   menu.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	Menu menu = new Menu();
	            	menu.start(primaryStage);
	            	}
	            });
		   //doc filepath
		   BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(new File(
						scoree.filePath)));
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
        //gan noi dung vao label cai dat label
		secondLabel.setText(str);
	    secondLabel.setFont(new Font(25));
	    secondLabel.setLayoutX(280);
	    secondLabel.setLayoutY(150);
	    secondLabel.setTextFill(Color.web("#aa0000", 1));
       Image history = new Image(getClass().getResourceAsStream("/images/history.jpg"));
       ImageView imageView = new ImageView(history);
       Pane secondaryLayout = new Pane(imageView);
       // nut reset
       Button reset= new Button("Reset");
       reset.setFont(new Font(20));
       reset.setLayoutX(550);
       reset.setLayoutY(300);
       menu.setFont(new Font(20));
       menu.setLayoutX(550);
       menu.setLayoutY(360);
       secondaryLayout.getChildren().addAll(secondLabel, reset,menu);

       Scene secondScene = new Scene(secondaryLayout, 800, 600);

       primaryStage.setScene(secondScene);
       // hanh dong khi an nut reset
           reset.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	 
	            	 scoree.reset();// reset score
	     			secondLabel.setText("");// gan label rong

	            }
	        });
          // show
           primaryStage.setX(primaryStage.getX() + 100);
           primaryStage.setY(primaryStage.getY() );

           primaryStage.show();
	}
}
