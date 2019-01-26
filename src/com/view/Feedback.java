package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Feedback extends Application {
	private final StackPane stackpane1 = new StackPane();
	@Override
	public void start(Stage primaryStage) throws Exception {

		Stage stage = new Stage();
	
		
		JFXButton btn = new JFXButton("HI");
		stackpane1.getChildren().add(btn);

		Scene view = new Scene(stackpane1, 400, 400);
		stage.setScene(view);
		btn.setOnAction(event->{
			loadDialog();
		});

		stage.show();

	}

	private void loadDialog() {

		

		JFXDialogLayout content = new JFXDialogLayout();
        StackPane stack = new StackPane();
		content.setHeading(new Label("Exception Error"));
		content.setBody(new Label(
				"Warning please check the prompt below\nOnly numerical Values are accepted.\n\nWhen converting from binary to any other format please use -> 11011 Format."));
		JFXDialog dialog = new JFXDialog(stackpane1, content, JFXDialog.DialogTransition.CENTER);
		JFXButton close = new JFXButton("exit");
		close.setOnAction(event -> {
			dialog.close();
		});
		content.setActions(close);
		dialog.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
