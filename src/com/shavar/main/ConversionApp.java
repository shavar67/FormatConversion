package com.shavar.main;
/**
 * @author Shavar
 * 
 * Development Date: 9-6-18.   
 * 
 * Number Format Conversion Tool.
 * Open-Source Project.
 * Developed with pure JavaFX For EDUCATIONAL PURPOSES.
 * The Model class haven't been implemented yet.
 * 	No Styles have been Implemented yet.  -- styles have been implemented @  9-6-18, 3:34pm
 * Version - Demo v1.1.
 * 
 * Version 5.2.1
 * 
 */

import com.shavar.controller.ButtonControls;
import com.shavar.model.DataModel;
import com.view.UiView;
import java.util.prefs.Preferences.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ConversionApp extends Application {

	UiView uiview;
    DataModel model;
	@Override
	public void start(Stage primaryStage) throws Exception {
		uiview = new UiView();
		model = new DataModel();
		ButtonControls controller = new ButtonControls(uiview,model);

		uiview.getStage().getIcons().add(new Image(UiView.class.getResourceAsStream("atomic.png")));
		uiview.getStage().setResizable(false);
		
		
		uiview.getStage().show();
		
	}

	public static void main(String[] args) {
      
		
		launch(args);

	}

}
