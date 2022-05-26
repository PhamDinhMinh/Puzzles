package application;
import java.io.InputStream;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BattleshipMain {
    private boolean running = false;
    public Board enemyBoard;
	public Board playerBoard;
    private int shipsToPlace = 5;
    protected boolean enemyTurn = false;
    protected Random random = new Random();
    public int score ;
    public int count=0,count2=0;
    public score newscore= new score();
    public Button history = new Button("History");
	public Button menu = new Button("Menu");
	public TextArea textArea1 = new TextArea();
	public TextArea textArea2 = new TextArea();
 	public MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sound/sound.mp3").toString()));
    public Parent createContent(Stage primaryStage) {
        //tao nen      
    	InputStream input = getClass().getResourceAsStream("/images/back.png"); 
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        Pane root = new Pane(imageView);
        root.setPrefSize(1000, 600);
        // anh bat tat am thanh
        InputStream input1 = getClass().getResourceAsStream("/images/unmute.png");
        Image image1 = new Image(input1);
        ImageView imageView1 = new ImageView(image1);
        
        InputStream input2 = getClass().getResourceAsStream("/images/mute.png");
        Image image2 = new Image(input2);
        ImageView imageView2 = new ImageView(image2);
        InputStream input4 = getClass().getResourceAsStream("/images/backk.png");
        Image image4 = new Image(input4);
        ImageView imageView4 = new ImageView(image4);
        // nut bat nat am thanh
        Button but1 = new Button();
        but1.setShape(new Circle(15));
        but1.setMinSize(30, 30);
        but1.setMaxSize(30, 30);
        but1.setLayoutY(570);
        but1.setGraphic(imageView2 );
      //  but1.setLayoutX(30);

        root.getChildren().addAll(but1);
        
      
        but1.setOnAction(new EventHandler<ActionEvent>() {
        	int k=1;
        	 
            @Override
            public void handle(ActionEvent event) {
            	k++;
            	if(k%2==1) {mediaPlayer.stop(); but1.setGraphic(imageView2 );}
				else {mediaPlayer.play(); but1.setGraphic(imageView1 );}

            }
        });
        textArea1.setText("            PLAYER\n");
        textArea2.setText("             ENEMY\n");
        // phan tau cua may
        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;
            enemyTurn = !cell.shoot();
            count++; //tang count
            // hien nuoc di
            textArea1.appendText("Step"+count+":  x = "+ cell.x+" "+"  y="+(char)(cell.y+65)+"\n");


            if(cell.ship!=null) {
          	  score = score + cell.ship.point;// trung cong diem
          }
             
                         // Neu thang
            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                newscore.addscore(score, count);//them score va count
                //nhac win
                Media media3 = new Media(getClass().getResource("/sound/win.mp3").toString());
            	MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
                mediaPlayer3.setVolume(1);
               mediaPlayer3.setAutoPlay(true);
                mediaPlayer.stop();
                // Man hinh chien thang
                Image image3= new Image(getClass().getResourceAsStream("/images/youwin.jpg"));
                ImageView imageView3 = new ImageView(image3);
                Pane root2 = new Pane(imageView3);
                //vi tri nut manu va history
                menu.setFont(new Font(25));
    	        menu.setLayoutX(250);
    	        menu.setLayoutY(480);
    	        history.setFont(new Font(25));
    	        history.setLayoutX(680);
    	        history.setLayoutY(480);
    	        //show score
            	Integer y = new Integer(score);
    			String z=y.toString();
                Label Yourscore= new Label(z);
                Yourscore.setFont(new Font(55));
                Yourscore.setLayoutX(470);
                Yourscore.setLayoutY(180);

    	       
                root2.getChildren().addAll(Yourscore,menu , history);
                Scene secondScene = new Scene(root2, 1000, 560);
                primaryStage.setScene(secondScene);
                primaryStage.show();
            }
          //luot may
            if (enemyTurn)
                enemyMove(primaryStage);
        });
        // phan dat tau
        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
            	cell.headOfShip = true ;
            	if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });
       // them 2 board+ textarea vao hbox
       HBox hbox = new HBox(100, playerBoard, enemyBoard);
       textArea1.setLayoutX(860);
       textArea1.setPrefWidth(140);
       textArea1.setPrefHeight(100);
       textArea2.setLayoutX(0);
       textArea2.setPrefWidth(140);
       textArea2.setPrefHeight(100);
       hbox.setLayoutX(90);
       hbox.setLayoutY(150);
       root.getChildren().addAll(hbox,textArea1,textArea2);

        return root;
    }

    public void enemyMove(Stage primaryStage) {
        while (enemyTurn) {
        	//random vi tri ban tau
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

          enemyTurn = cell.shoot();
          count2++; 
          textArea2.appendText("Step"+count2+":  x = "+ cell.x+" "+"  y="+(char)(cell.y+65)+"\n");
            // neu ban ko trung tru diem
            if(cell.ship!=null) {
           	   
           			   score = score - cell.ship.point;
             
           	   }
           // hithead(cell);
            // neu thua
            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                
                newscore.addlose();
                mediaPlayer.stop();
                //nhac thua
                Media media3 = new Media(getClass().getResource("/sound/lose.mp3").toString());
            	MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
                mediaPlayer3.setVolume(1);
                mediaPlayer3.setAutoPlay(true);
                // man hinh thua
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
                enemyTurn = !cell.shoot();
           }
       }
    }

    private void startGame() {
        // may dat tau
        int type = 5;
        
        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            
            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
            	Cell cell = enemyBoard.getCell(x, y);
            	
                type--;
            }
        }

        running = true;
    }

    
    public void start(Stage primaryStage)  {
    	// hanh dong khi bam but menu va history
    	 menu.setOnAction(new EventHandler<ActionEvent>() {
        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	Menu menu = new Menu();
	            	menu.start(primaryStage);
	            	}
	            });
    	 history.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				   history his= new history();
				   his.start(primaryStage);
				
			}
    		 
    	 });
    	 //show
        Scene scene = new Scene(createContent(primaryStage));
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
      
    }

}

