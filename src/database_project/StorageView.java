package database_project;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;

public class StorageView {
	private TableView table = new TableView();

	VBox vbox;
	TextField tf1;
	TextField tf2;
	TextField tf3;

	private ObservableList<Storage> data;

	public StorageView() {

		final Label label = new Label("");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn<Storage, Integer> idCol = new TableColumn<Storage, Integer>("id");
		TableColumn<Storage, String> nameCol = new TableColumn<Storage, String>("Name");
		TableColumn<Storage, String> shelfCol = new TableColumn<Storage, String>("shelf_num");

		idCol.setMinWidth(100);
		shelfCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<Storage, Integer>("Storage_id"));
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<Storage, String>("Storage_name"));
		shelfCol.setCellValueFactory(new PropertyValueFactory<Storage, String>("Shelf_num"));

		Button insert = new Button("Insert");
		Button update = new Button("update");
		Button delete = new Button("delete");
		HBox buttonbox = new HBox();
		buttonbox.setSpacing(20);
		buttonbox.getChildren().addAll(insert, update, delete);
		buttonbox.setAlignment(Pos.CENTER);
		setData(cdd.getAll());
		table.setItems(data);
		table.getColumns().addAll(idCol, nameCol, shelfCol);
		vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(label, table, buttonbox);
		update.setOnAction(e -> {
			Text t1 = new Text("enter Storage id ");
			Text t2 = new Text("enter Storage  new name ");
			Text t3 = new Text("enter new shelf num ");

			tf1 = new TextField();
			tf2 = new TextField();
			tf3 = new TextField();
			HBox textBox1 = new HBox();
			HBox textBox2 = new HBox();
			HBox textBox3 = new HBox();
			HBox textBox4 = new HBox();
			textBox4.setSpacing(20);
			textBox3.setSpacing(20);
			textBox1.setSpacing(20);
			textBox2.setSpacing(20);
			Button submit = new Button("Submit");
			Button close = new Button("Close");

			textBox1.getChildren().addAll(t1, tf1);
			textBox2.getChildren().addAll(t2, tf2);
			textBox3.getChildren().addAll(t3, tf3);
			textBox4.getChildren().addAll(submit,close);


			VBox updateVBox = new VBox();
			updateVBox.getChildren().addAll(textBox1, textBox2, textBox3,textBox4);
			updateVBox.setSpacing(20);
			updateVBox.setPadding(new Insets(10));
			submit.setOnAction(ex -> {
				Storage storage = new Storage(Integer.parseInt(tf1.getText()), tf2.getText(), tf3.getText());
				cdd.update(storage);
				setData(cdd.getAll());
				table.setItems(data);
				vbox.getChildren().remove(updateVBox);

			});
			close.setOnAction(ex -> {
				vbox.getChildren().remove(updateVBox);
			});
			vbox.getChildren().add(updateVBox);

		});
		delete.setOnAction(e -> {
			Text t1 = new Text("enter Storage id ");
			tf1 = new TextField();
			HBox textBox1 = new HBox();
			HBox textBox2 = new HBox();
			textBox2.setSpacing(20);

			textBox1.setSpacing(20);
			Button submit = new Button("confirm");
			Button close = new Button("Close");

			VBox deleteVBox = new VBox();
			textBox1.getChildren().addAll(t1, tf1);
			textBox2.getChildren().addAll(submit, close);

			deleteVBox.getChildren().addAll(textBox1, textBox2);
			deleteVBox.setPadding(new Insets(10));
			submit.setOnAction(ex -> {
				cdd.delete(Integer.parseInt(tf1.getText()));
				setData(cdd.getAll());
				table.setItems(data);
				vbox.getChildren().remove(deleteVBox);

			});
			close.setOnAction(ex -> {
				vbox.getChildren().remove(deleteVBox);
			});
			vbox.getChildren().add(deleteVBox);
		});
		insert.setOnAction(e -> {
			Text t1 = new Text("enter Storage id ");
			Text t2 = new Text("enter Storage name ");
			Text t3 = new Text("enter shelf num ");

			tf1 = new TextField();
			tf2 = new TextField();
			tf3 = new TextField();
			HBox textBox1 = new HBox();
			HBox textBox2 = new HBox();
			HBox textBox3 = new HBox();
			HBox textBox4 = new HBox();
			textBox1.setSpacing(20);
			textBox2.setSpacing(20);
			textBox3.setSpacing(20);
			textBox4.setSpacing(20);
			Button submit = new Button("Submit");
			Button close = new Button("Close");
			textBox1.getChildren().addAll(t1, tf1);
			textBox2.getChildren().addAll(t2, tf2);
			textBox3.getChildren().addAll(t3, tf3);
			Label lable = new Label("duplicate id");
			textBox4.getChildren().addAll(submit, close);

			VBox insertVBox = new VBox();
			insertVBox.getChildren().addAll(textBox1, textBox2, textBox3, textBox4);
			insertVBox.setSpacing(20);
			insertVBox.setPadding(new Insets(10));
			submit.setOnAction(ex -> {
				try {
					cdd.insert(new Storage(Integer.parseInt(tf1.getText()), tf2.getText(), tf3.getText()));
					setData(cdd.getAll());
					table.setItems(data);
					vbox.getChildren().remove(insertVBox);
				} catch (RuntimeException se) {
					insertVBox.getChildren().add(lable);
				}
			});
			close.setOnAction(ex -> {
				vbox.getChildren().remove(insertVBox);
			});
			vbox.getChildren().add(insertVBox);
		});

	}

	public VBox getVbox() {
		return vbox;
	}

	public TextField getTf1() {
		return tf1;
	}

	public TextField getTf2() {
		return tf2;
	}

	StorageDao cdd = new StorageDao();

	private final void setData(List<Storage> list) {
		this.data = FXCollections.observableList(list);
	}

}
