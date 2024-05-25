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

public class ProducerView {
	private TableView table = new TableView();

	VBox vbox;
	TextField tf1;
	TextField tf2;
	TextField tf3;

	private ObservableList<Producer> data;

	public ProducerView() {

		final Label label = new Label("");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn<Producer, Integer> idCol = new TableColumn<Producer, Integer>("id");
		TableColumn<Producer, String> nameCol = new TableColumn<Producer, String>("Name");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<Producer, Integer>("Producer_id"));
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<Producer, String>("Producer_name"));

		Button insert = new Button("Insert");
		Button update = new Button("update");
		Button delete = new Button("delete");
		HBox buttonbox = new HBox();
		buttonbox.setSpacing(20);
		buttonbox.getChildren().addAll(insert, update, delete);
		buttonbox.setAlignment(Pos.CENTER);
		setData(cdd.getAll());
		table.setItems(data);
		table.getColumns().addAll(idCol, nameCol);
		vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(label, table, buttonbox);
		update.setOnAction(e -> {
			Text t1 = new Text("enter Producer id ");
			Text t2 = new Text("enter Producer  new name ");

			tf1 = new TextField();
			tf2 = new TextField();
			HBox textBox1 = new HBox();
			HBox textBox2 = new HBox();
			HBox textBox3 = new HBox();

			textBox1.setSpacing(20);
			textBox2.setSpacing(20);
			textBox3.setSpacing(20);

			Button submit = new Button("Submit");
			Button close = new Button("Close");

			textBox1.getChildren().addAll(t1, tf1);
			textBox2.getChildren().addAll(t2, tf2);
			textBox3.getChildren().addAll(submit, close);

			VBox updateVBox = new VBox();
			updateVBox.getChildren().addAll(textBox1, textBox2, textBox3);
			updateVBox.setSpacing(20);
			updateVBox.setPadding(new Insets(10));
			submit.setOnAction(ex -> {
				Producer producer = new Producer(Integer.parseInt(tf1.getText()), tf2.getText());
				cdd.update(producer);
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
			Text t1 = new Text("enter Producer id ");
			tf1 = new TextField();
			HBox textBox1 = new HBox();
			textBox1.setSpacing(20);
			Button submit = new Button("confirm");
			Button close = new Button("Close");
			HBox textBox2 = new HBox();
			textBox2.setSpacing(20);

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
			Text t1 = new Text("enter Producer id ");
			Text t2 = new Text("enter Producer name ");

			tf1 = new TextField();
			tf2 = new TextField();

			HBox textBox1 = new HBox();
			HBox textBox2 = new HBox();
			HBox textBox3 = new HBox();

			textBox1.setSpacing(20);
			textBox2.setSpacing(20);
			textBox3.setSpacing(20);

			Button submit = new Button("Submit");
			Button close = new Button("Close");
			Label lable = new Label("duplicate id");

			textBox1.getChildren().addAll(t1, tf1);
			textBox2.getChildren().addAll(t2, tf2);
			textBox3.getChildren().addAll(submit, close);

			VBox insertVBox = new VBox();
			insertVBox.getChildren().addAll(textBox1, textBox2, textBox3);
			insertVBox.setSpacing(20);
			insertVBox.setPadding(new Insets(10));
			submit.setOnAction(ex -> {
				try {
					cdd.insert(new Producer(Integer.parseInt(tf1.getText()), tf2.getText()));
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

	ProducerDao cdd = new ProducerDao();

	private final void setData(List<Producer> list) {
		this.data = FXCollections.observableList(list);
	}

}
