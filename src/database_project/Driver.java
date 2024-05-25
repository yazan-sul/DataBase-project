package database_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;  



public class Driver extends Application{


	public static void main(String[] args) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  

//			static final String USER = "guest";
//			static final String PASS = "mzu9q0j2";
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/pro","root","mzu9q0j2");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from Client"); 

			ItemDao itemDao = new ItemDao();
//			itemDao.insert(new Item(2, "first", "fitting", "mds3a"));
			ClientDao clientDao = new ClientDao();
			clientDao.delete(2);
		
//			clientDao.update(new Client(2, "has"));
//			clientDao.insert(new Client(3, "yazan"));
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			
		launch();
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		HBox nameBox = new HBox();
		Label name = new Label("  Data Base  ");
		String boxStyle = "-fx-border-radius: 1; -fx-border-color: black;";


        // create a button

		
        Button b = new Button("show");
        Button item = new Button("item");
        Button storage = new Button("storage");
        Button client = new Button("client");
        Button producer = new Button("producer");
        Button order = new Button("order");
        HBox box1 = new HBox();
		HBox box2 = new HBox();
		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(box1,box2);
		box1.getChildren().addAll(item,storage,client);
		box2.getChildren().addAll(producer,order);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(40);
		box1.setSpacing(40);
		box2.setSpacing(40);
		box1.setAlignment(Pos.CENTER);
		box2.setAlignment(Pos.CENTER);

        String st[] = { "item", "storage", "order", "client" };
 



        client.setOnAction(e->{
        	ClientView cv = new ClientView();
        	VBox vbox = cv.getVbox();
        	Stage newStage = new Stage();
        	Scene scene = new Scene(vbox, 400, 600);
        	newStage.setScene(scene);
        	newStage.show();
        });
        storage.setOnAction(e->{
        	StorageView cv = new StorageView();
        	VBox vbox = cv.getVbox();
        	Stage newStage = new Stage();
        	Scene scene = new Scene(vbox, 400, 600);
        	newStage.setScene(scene);
        	newStage.show();
        });
        producer.setOnAction(e->{
        	ProducerView cv = new ProducerView();
        	VBox vbox = cv.getVbox();
        	Stage newStage = new Stage();
        	Scene scene = new Scene(vbox, 400, 600);
        	newStage.setScene(scene);
        	newStage.show();
        });


		nameBox.getChildren().add(name);
		nameBox.setAlignment(Pos.TOP_CENTER);

		bp.setTop(nameBox);
		bp.setCenter(vbox1);
		bp.setPadding(new Insets(10));
		name.setStyle(boxStyle);
		Scene scene = new Scene(bp, 500, 500);
		stage.setScene(scene);
		stage.show();
	}
	
}
