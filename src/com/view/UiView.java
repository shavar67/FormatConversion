package com.view;

import com.jfoenix.controls.*;
import com.shavar.email.EmailTest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;

public class UiView {

	public JFXTextField entryText;
	public JFXComboBox<String> dropmenu;
	private Label selectFormat;
	private Label conversionTable;
	public Label binaryLabel;
	public Label octalLabel;
	private HBox hbox;
	private VBox vbox;
	public JFXTextArea areaEntry;
	public JFXTextField field;
	public JFXTextArea displayConvertedText;
	public JFXTextArea getUserInputField;
	public Label binaryString;
	public JFXTextField user;
	public JFXTextField subject;
	public JFXTextArea message;

	private BorderPane borderpane2;
	public Label decimalLabel;
	public Label hexadecimalLabel;
	private VBox rightBox;
	public JFXButton feedback;
	private Label bugReport;
	public JFXButton convert;
	public JFXButton reset;
	private HBox navbar;
	private Label legal;
	public JFXButton help;
	private HBox bottomBar;
	private ObservableList<String> formats;
	private BorderPane borderpane;
	private VBox contentBox;
	private final Stage stage;
	public JFXButton sendReport;

	private final Scene view;
	private double xOffset = 0;
	private double yOffset = 0;

	public UiView() {
		stage = new Stage();
		stage.setTitle("BinaryFx");
		stage.initStyle(StageStyle.UNDECORATED);
		view = new Scene(createContent(), 1080, 700);
		view.setFill(Color.valueOf("#E0E0E0"));

		view.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		stage.setScene(splashScreen());

	}

	public Scene bugReport() {

		StackPane bugSceneReport = new StackPane();

		bugSceneReport.setStyle("-fx-background-color:#E0E0E0");
		Scene scene = new Scene(bugSceneReport, 1080, 700);

		sendReport = new JFXButton("Send");

		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();

		bugReport = new Label();

		bugReport.setTranslateX(300);
		bugReport.setText("\tBinaryFx\nSubmit Your Bug Report");

		content.setHeading(bugReport);
		content.setPrefSize(850, 500);
		content.setBody(createMail());

		JFXDialog dialog = new JFXDialog(bugSceneReport, content, JFXDialog.DialogTransition.BOTTOM, false);

		JFXButton close = new JFXButton("Exit");
		close.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		close.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});

		sendReport.setOnAction(event -> {

			Runnable r = () -> {
				String subjecttype = subject.getText();
				String text = message.getText();

				EmailTest.sendFromGMail("bugreportfx@gmail.com", "Hpmlvta8", "bugreportfx@gmail.com", subjecttype,
						text);
				message.setText("");
				subject.setText("");
			};

			stage.setScene(dismissBugReport());

			new Thread(r).start();

		});

		content.setActions(sendReport, close);

		dialog.show();

		return scene;

	}

	public Scene dismissBugReport() {

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane, 1080, 700);
		Label support = new Label(
				"\t\tThanks for submitting your bug!\nWe appreciate you taking the time to help us improve our product.");
		support.setTranslateX(200);
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();
		Label header = new Label("BinaryFx Support Team");
		header.setTranslateX(320);
		content.setHeading(header);

		content.setPrefSize(850, 500);
		content.setBody(support);
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

		JFXButton close = new JFXButton("Exit");
		close.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		close.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		content.setActions(close);
		dialog.show();
		return scene;
	}

	private Parent createContent() {

		borderpane = new BorderPane();
		borderpane.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();

		});

		borderpane.setOnMouseDragged(event2 -> {
			stage.setX(event2.getScreenX() - xOffset);
			stage.setY(event2.getScreenY() - yOffset);
			stage.setOpacity(0.1);
		});
		borderpane.setOnMouseReleased(defaultOpacity -> {
			stage.setOpacity(1);
		});
		borderpane.setOnMouseDragExited(source -> {
			stage.setOpacity(1);
		});
		borderpane.setLeft(leftControls());
		borderpane.setRight(rightControls());
		borderpane.setBottom(bottomButtonBar());
		borderpane.setTop(createMenuBar());
		return borderpane;

	}

	public Scene makeSelection() {

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane, 1080, 700);
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();
		Label header = new Label("BinaryFx Exception Handler");
		header.setTranslateX(320);
		content.setHeading(header);
		content.setPrefSize(850, 500);
		content.setBody(new Label(
				"Please make a selection to continue using this application.\nFor regular conversions the textfield and the dropdown menu cannot be left empty or unselected."));
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

		JFXButton close = new JFXButton("Exit");
		close.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		close.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		content.setActions(close);
		dialog.show();
		return scene;
	}

	public Scene dialog() {

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane, 1080, 700);
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();
		Label header = new Label("BinaryFx Unknown Format");
		header.setTranslateX(300);
		content.setHeading(header);
		content.setPrefSize(850, 500);
		content.setBody(new Label(
				"Warning please check the prompt below for more information\nOnly numerical Values are accepted.\n\nWhen converting from binary to any other format please use Binary Format."));
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

		JFXButton close = new JFXButton("Exit");
		close.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		close.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		content.setActions(close);
		dialog.show();
		return scene;
	}

	public Scene dialogSplash() {

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane, 1080, 700);
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();
		Label header = new Label("\tBinaryFx\nFrequently Asked Questions");
		header.setTranslateX(300);
		content.setHeading(header);
		content.setPrefSize(850, 500);
		content.setBody(new Label("Know issues\nWhen using the text to binary format\n"
				+ "The label doesn't wrap the content\nText is replaced with \"...\".\n\nChange Log && Temporary Bug Fix\n\n1.V2.5.1.Only four characters can be converted at a time.\n2.V2.5.2.New popup dialog for text conversions\nDarkmode is not system wide only appliable for the splashscreen"
				+ "for demo purposes"));
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

		JFXButton close = new JFXButton("Exit");
		close.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		close.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		content.setActions(close);
		dialog.show();
		return scene;
	}

	private Parent rightControls() {
		rightBox = new VBox(30);
		rightBox.setPadding(new Insets(30, 100, 0, 0));
		conversionTable = new Label("Converted Formats");
		binaryLabel = new Label("Binary: ");
		binaryLabel.setPrefWidth(350);

		binaryString = new Label("Binary Character: ");
		binaryString.setPrefWidth(350);

		octalLabel = new Label("Octal: ");
		octalLabel.setPrefWidth(350);
		decimalLabel = new Label("Decimal: ");
		decimalLabel.setPrefWidth(350);
		hexadecimalLabel = new Label("Hexadecimal: ");
		hexadecimalLabel.setPrefWidth(350);
		conversionTable.setFont(new Font(15));
		rightBox.getChildren().addAll(conversionTable, binaryLabel, binaryString, octalLabel, decimalLabel,
				hexadecimalLabel);
		rightBox.setAlignment(Pos.TOP_CENTER);
		return rightBox;

	}

	private Scene splashScreen() {

		StackPane stackpane = new StackPane();

		Scene splashscreen = new Scene(stackpane, 1080, 700);
		HBox combo = new HBox(5);
		JFXCheckBox yes = new JFXCheckBox("Yes");
		JFXCheckBox no = new JFXCheckBox("No");
		combo.getChildren().addAll(yes, no);
		combo.setTranslateX(550);
		combo.setTranslateY(120);
		combo.setPadding(new Insets(20, 0, 20, 0));

		splashscreen.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();

		Image atom = new Image(getClass().getResourceAsStream("atomic.png"));
		ImageView img = new ImageView(atom);
		img.setTranslateX(150);
		Label title = new Label("\t\t\t\t\t\tBinaryFX V2.5.2");
		title.setGraphic(img);
		title.setGraphicTextGap(5);
		title.setContentDisplay(ContentDisplay.TOP);
		content.setHeading(title);

		content.setBody(new Label(
				"Welcome to BinaryFx the one and only app you will ever need for format conversions\n\nTips\n1. Use the bug-report button"
						+ " to submit any bugs or errors you might come across\n2.Click"
						+ " the continue button below to navigate to the application or exit to close\n\nUpcoming Features\n1.DarkMode\n2.Real time conversions\n3.Disable SplashScreen\n4.System wide optimization\n\nNew in BinaryFx\n1.Text to binary now has its own dialog popup for conversion.\n2.Binary to text is now available."
						+ "\n3.Darkmode (BETA) implemented for the splashscreen only"));
		content.setPrefSize(850, 550);
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

		JFXButton continueNext = new JFXButton("Continue");
		continueNext.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("loginarrow.png"))));
		JFXButton exit = new JFXButton("Exit");
		exit.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));
		JFXToggleButton darkmodeOn = new JFXToggleButton();

		continueNext.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		Text darkmode = new Text("Enable darkmode");

		content.setActions(darkmode, darkmodeOn, exit, continueNext);
		darkmodeOn.selectedProperty().addListener((v, oldValue, newValue) -> stage.setScene(disableDarkMode()));
		darkmodeOn.setOnAction(main -> {

			view.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());

		});
		exit.setOnAction(event -> {

			stage.setScene(confirmCloseAction());

		});
		dialog.show();

		return splashscreen;
	}

	private Scene disableDarkMode() {
		StackPane stackpane = new StackPane();
		Scene dismissModeOff = new Scene(stackpane, 1080, 700);
		dismissModeOff.getStylesheets().add(this.getClass().getResource("application2.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();

		Image atom = new Image(getClass().getResourceAsStream("atomic.png"));
		ImageView img = new ImageView(atom);
		img.setTranslateX(150);
		Label title = new Label("\t\t\t\t\t\tBinaryFX V2.5.2");
		title.setGraphic(img);
		title.setGraphicTextGap(5);
		title.setContentDisplay(ContentDisplay.TOP);
		content.setHeading(title);

		content.setBody(new Label(
				"Welcome to BinaryFx the one and only app you will ever need for format conversions\n\nTips\n1. Use the bug-report button"
						+ " to submit any bugs or errors you might come across\n2.Click"
						+ " the continue button below to navigate to the application or exit to close\n\nUpcoming Features\n1.DarkMode\n2.Real time conversions\n3.Disable SplashScreen\n4.System wide optimization"
						+ "\n\nNew in BinaryFx\n1.Text to binary now has its own dialog popup for conversion\n2.Binary to text is now available"
						+ "\n3.Dark mode (BETA) implemented for the splash screen"));
		content.setPrefSize(850, 550);
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.TOP, false);

		JFXButton continueNext = new JFXButton("Continue");
		continueNext.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("loginarrow.png"))));
		JFXButton exit = new JFXButton("Exit");
		exit.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));
		JFXToggleButton darkmodeOff = new JFXToggleButton();

		continueNext.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});

		content.setActions(new Label("Disable dark-mode"), darkmodeOff, exit, continueNext);

		darkmodeOff.selectedProperty().addListener((v, oldValue, newValue) -> stage.setScene(splashScreen()));

		exit.setOnAction(event -> {
			stage.setScene(confirmCloseAction());
		});
		dialog.show();

		return dismissModeOff;
	}

	private Parent bottomButtonBar() {

		VBox box = new VBox(10);
		bottomBar = new HBox(10);

		legal = new Label("Designed and Developed by SpecialFx \u00a9  2018 All rights reserved.");
		bottomBar.setPadding(new Insets(20, 0, 50, 0));
		legal.setPadding(new Insets(0, 0, 20, 300));

		convert = new JFXButton("Convert");
		convert.setContentDisplay(ContentDisplay.TOP);
		convert.setOnMouseEntered(hover -> {
			convert.setStyle("-fx-background-color:#dae7f3;");
		});
		convert.setOnMouseExited(hoverOff -> convert.setStyle("-fx-background-color:transparent"));
		convert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("atomic.png"))));
		convert.setFont(new Font(15));
		reset = new JFXButton("Reset");
		reset.setOnMouseEntered(hover -> {
			reset.setStyle("-fx-background-color:#dae7f3;");
		});
		reset.setOnMouseExited(hoverOff -> {
			reset.setStyle("-fx-background-color:transparent");
		});
		reset.setContentDisplay(ContentDisplay.TOP);
		reset.setTooltip(new Tooltip("Set all Labels and TextFields to an empty state."));
		reset.setGraphicTextGap(5);
		reset.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("clear2.png"))));
		reset.setFont(new Font(15));
		feedback = new JFXButton("Bug-Report");
		feedback.setOnMouseEntered(hover -> {
			feedback.setStyle("-fx-background-color:#dae7f3;");
		});
		feedback.setOnMouseExited(hoverOff -> {
			feedback.setStyle("-fx-background-color:transparent");
		});

		feedback.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("bug.png"))));
		bottomBar.getChildren().addAll(convert, reset);
		box.getChildren().addAll(bottomBar, legal);
		bottomBar.setAlignment(Pos.TOP_CENTER);
		legal.setAlignment(Pos.BOTTOM_RIGHT);

		return box;

	}

	private Parent createMenuBar() {
		VBox themes = new VBox(5);

		JFXButton bar = new JFXButton("Exit");
		bar.setTooltip(new Tooltip("Close Application."));
		bar.setOnMouseEntered(hover -> {
			bar.setStyle("-fx-background-color:#dae7f3;");
		});
		bar.setOnMouseExited(hoverOff -> {
			bar.setStyle("-fx-background-color:transparent");
		});
		bar.setContentDisplay(ContentDisplay.LEFT);
		help = new JFXButton("Help");
		help.setOnMouseEntered(hover -> {
			help.setStyle("-fx-background-color:#dae7f3;");
		});
		help.setOnMouseExited(hoverOff -> {
			help.setStyle("-fx-background-color:transparent");
		});
		help.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("info.png"))));

		help.setContentDisplay(ContentDisplay.LEFT);
		bar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		bar.setGraphicTextGap(5);
		bar.setOnAction(exitApplication -> {
			stage.setScene(confirmCloseAction());
		});
		themes.getChildren().addAll(feedback, help, bar);
		themes.setAlignment(Pos.TOP_RIGHT);
		return themes;
	}

	private Scene confirmCloseAction() {
		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane, 1080, 700);
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();
		Label header = new Label("Confirm Closing Application");
		Label body = new Label("Are you sure you want to close the application?");
		header.setTranslateX(300);
		body.setTranslateX(230);

		content.setHeading(header);
		content.setPrefSize(850, 500);
		content.setBody(body);
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

		JFXButton confirmYes = new JFXButton("Yes");
		JFXButton confirmNo = new JFXButton("No");
		content.setAlignment(Pos.CENTER);
		confirmYes.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		confirmYes.setOnAction(event -> {
			dialog.close();
			Platform.exit();
		});
		confirmNo.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		content.setActions(confirmYes, confirmNo);

		dialog.show();
		return scene;
	}

	private Parent createMail() {

		borderpane2 = new BorderPane();
		hbox = new HBox(30);
		vbox = new VBox(30);
		user = new JFXTextField();
		Label label = new Label("Report sent to SpecialFXFeedBack@gmail.com");

		subject = new JFXTextField();
		subject.setStyle("-fx-text-fill:dodgerblue");
		subject.setPromptText("Subject");
		subject.setMaxWidth(350);
		message = new JFXTextArea();
		message.setPrefSize(400, 200);
		message.setStyle("-fx-text-fill:dodgerblue");
		message.setPromptText("Compose a bug Report.");
		message.setEditable(true);
		vbox.setAlignment(Pos.CENTER_LEFT);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().addAll(label);
		vbox.getChildren().addAll(hbox, subject, message);
		borderpane2.setCenter(vbox);
		return borderpane2;
	}

	private Parent leftControls() {
		contentBox = new VBox(50);
		navbar = new HBox(20);

		contentBox.setPadding(new Insets(0, 0, 0, 40));
		selectFormat = new Label("Supported Formats");
		selectFormat.setFont(new Font(15));

		formats = FXCollections.observableArrayList("Binary", "Decimal", "Hexadecimal", "Octal", "Text : Binary",
				"Binary : Text");

		dropmenu = new JFXComboBox<String>(formats);
		dropmenu.setFocusColor(Color.RED);
		dropmenu.setPromptText("Formats");
		dropmenu.setUnFocusColor(Color.DODGERBLUE);
		entryText = new JFXTextField();
		entryText.setPrefSize(200, 50);

		entryText.setStyle("-fx-font-size:17");
		entryText.setStyle("-fx-background-color:red");
		entryText.setStyle("-fx-prompt-text-fill: #098be1");
		entryText.setStyle("-fx-text-fill:red");
		entryText.setFocusColor(Color.RED);
		entryText.setUnFocusColor(Color.DODGERBLUE);
		entryText.setPromptText("Enter a value to convert.");

		navbar.getChildren().addAll(dropmenu);

		contentBox.getChildren().addAll(navbar, entryText);
		contentBox.setAlignment(Pos.TOP_LEFT);

		return contentBox;
	}

	public Scene showStringDialog() {
		StackPane stackpane = new StackPane();
		VBox layout = new VBox(20);
		Scene scene = new Scene(stackpane, 1080, 700);

		field = new JFXTextField();
		field.setPromptText("Enter text to be converted.");
		field.setMaxWidth(500);
		areaEntry = new JFXTextArea();

		areaEntry.setWrapText(true);
		areaEntry.setEditable(false);
		areaEntry.setPromptText("Output converted.");

		areaEntry.setPrefSize(300, 300);
		layout.getChildren().addAll(field, areaEntry);

		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();
		content.setPrefSize(850, 500);
		Label header = new Label("Text To Binary");
		header.setTranslateX(300);
		content.setHeading(header);
		content.setBody(layout);
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);
		JFXButton convert = new JFXButton("Convert");
		JFXButton clear = new JFXButton("Clear");

		JFXButton close = new JFXButton("Exit");
		close.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));
		convert.setOnAction(conversion -> {

			stringtoBinary();

		});
		clear.setOnAction(clearing -> {
			field.setText("");
			areaEntry.setText("");
		});

		close.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		content.setActions(convert, clear, close);
		dialog.show();
		return scene;
	}

	public Scene binaryToTextDialog() {
		StackPane stackpane = new StackPane();
		VBox verticalLayout = new VBox(20);
		Scene scene = new Scene(stackpane, 1080, 700);

		getUserInputField = new JFXTextArea();
		getUserInputField.setPromptText("Enter binary number to be converted.");
		getUserInputField.setPrefSize(50, 150);
		displayConvertedText = new JFXTextArea();
		displayConvertedText.setPromptText("Output converted.");
		displayConvertedText.setWrapText(true);
		displayConvertedText.setEditable(false);
		displayConvertedText.setPrefSize(300, 300);
		verticalLayout.getChildren().addAll(getUserInputField, displayConvertedText);

		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		JFXDialogLayout content = new JFXDialogLayout();
		content.setPrefSize(850, 500);
		Label header = new Label("Binary to Text");
		header.setTranslateX(300);
		content.setHeading(header);
		content.setBody(verticalLayout);
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);
		JFXButton convert = new JFXButton("Convert");

		JFXButton clear = new JFXButton("Clear");

		JFXButton close = new JFXButton("Exit");
		close.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logout.png"))));

		convert.setOnAction(conversion -> {
			bytesToAlphabeticString();

		});
		clear.setOnAction(clearing -> {
			getUserInputField.setText("");
			displayConvertedText.setText("");
		});

		close.setOnAction(event -> {
			dialog.close();
			stage.setScene(view);
		});
		content.setActions(convert, clear, close);
		dialog.show();
		return scene;
	}

	public String getData() {
		return entryText.getText();
	}

	public void ClickEvent(EventHandler<ActionEvent> eventhandler) {
		convert.setOnAction(eventhandler);

	}

	public void stringtoBinary() {
		String s = field.getText().trim();
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;

			}

			binary.append(' ');
		}

		areaEntry.appendText("" + binary);

	}

	public void binarytoText() {
		String info = getUserInputField.getText();
		int charCode = Integer.parseInt(info, 2);
		String str = new Character((char) charCode).toString().trim();
		displayConvertedText.appendText("" + str);

	}

	public void bytesToAlphabeticString() {
		String input = getUserInputField.getText().trim().replaceAll(" ", ""); // Binary input as String
		StringBuilder sb = new StringBuilder(); // Some place to store the chars
		sb.trimToSize();
		Arrays.stream( // Create a Stream
				input.split("(?<=\\G.{8})") // Splits the input string into 8-char-sections (Since a char has 8 bits = 1
											// byte)
		).forEach(s -> // Go through each 8-char-section...
		sb.append((char) Integer.parseInt(s, 2)) // ...and turn it into an int and then to a char
		);

		String output = sb.toString(); // Output text

		displayConvertedText.appendText(" " + output);

	}

	public Stage getStage() {
		return stage;
	}

	public Scene getView() {
		return view;
	}

}
