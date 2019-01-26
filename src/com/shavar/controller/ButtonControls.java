package com.shavar.controller;

import com.shavar.model.DataModel;
import com.view.UiView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * When an index in the combo-box is selected the program uses a switch
 * statement to check the position of the index if the index selected is the
 * same as the case in the switch statement that method within that case is
 * executed, while that is true i've also written other snippet of code that
 * will covert to the other formats.
 * 
 * 
 * Model class somewhat implemented. Alerts will be used for our exception
 * handling to give the user feedback On how to use the application
 * 
 * 9/11/18 The model class is being implemented
 * 
 */
public class ButtonControls {
	private final UiView view;
	private final DataModel model;

	public ButtonControls(UiView view, DataModel model) {
		this.model = model;
		this.view = view;

		this.view.ClickEvent(new eventhandler());

		view.feedback.setOnAction(event -> {

			view.getStage().setScene(view.bugReport());

		});
		view.help.setOnAction(event -> {
			view.getStage().setScene(view.dialogSplash());
		});
		view.reset.setOnAction(event -> {
			view.entryText.clear();
			view.binaryLabel.setText("Binary: ");
			view.octalLabel.setText("Octal: ");
			view.decimalLabel.setText("Decimal: ");
			view.hexadecimalLabel.setText("Hexadecimal: ");
			view.binaryString.setText("Binary Character: ");
		});

	}

	class eventhandler implements EventHandler<ActionEvent> {

		String userInput;
		String convertedOutput;

		@Override
		public void handle(ActionEvent event) {

			if (event.getSource() == view.convert) {
				try {
					userInput = view.getData();
					convertedOutput = model.setOutputData(userInput);

					int index = view.dropmenu.getSelectionModel().getSelectedIndex();
					switch (index) {

					case 0:

						binaryConversion();
						binarytoText();

						view.binaryLabel.setText("Binary: " + convertedOutput);

						String results = Integer.toOctalString(Integer.parseInt(userInput, 2));
						view.octalLabel.setText("Octal: " + results);
						int decimal = Integer.parseInt(userInput, 2);
						String hex = Integer.toString(decimal, 16);
						view.hexadecimalLabel.setText("Hexadecimal: " + hex);
						break;
					case 1:

						view.decimalLabel.setText("Decimal: " + userInput);
						binaryCon();
						String octal = userInput;
						Integer deci = Integer.parseInt(octal);
						view.octalLabel.setText("Octal: " + Integer.toOctalString(deci));

						String decimalInput = userInput;

						Integer hexadecimal = Integer.parseInt(decimalInput);
						view.hexadecimalLabel.setText("Hexadecimal: " + Integer.toHexString(hexadecimal));
						break;
					case 2:

						hexaConversion();
						view.hexadecimalLabel.setText("Hexadecimal: " + userInput);
						String hexdec = userInput;
						Integer output = Integer.parseInt(hexdec, 16);
						view.octalLabel.setText("Octal: " + output);
						int binaryNum1 = Integer.parseInt(userInput);
						String decimalOutput = Integer.toBinaryString(binaryNum1);
						view.binaryLabel.setText("Binary: " + decimalOutput);

						break;
					case 3:

						octalConversion();
						view.octalLabel.setText("Octal: " + userInput);
						String octalString = userInput;
						Integer octal2 = Integer.parseInt(octalString, 8);
						String hexnum = Integer.toHexString(octal2);
						view.hexadecimalLabel.setText("Hexadecimal: " + hexnum);

						int convertedOutput = Integer.parseInt(userInput, 8);
						String binary = Integer.toBinaryString(convertedOutput);
						view.binaryLabel.setText("Binary: " + binary);
						break;

					case 4:

						view.getStage().setScene(view.showStringDialog());

						break;

					case 5:

						view.getStage().setScene(view.binaryToTextDialog());

						break;

					default:
						view.getStage().setScene(view.makeSelection());
						break;
					}

				}

				catch (Exception error) {
					view.getStage().setScene(view.dialog());
				}
			}

		}

		void binaryCon() {
			int a;
			StringBuilder decimal = new StringBuilder();
			String ans = userInput;
			Integer convertedOuput = Integer.parseInt(ans);

			while (convertedOuput > 0) {
				a = convertedOuput % 2;
				decimal.insert(0, a + "");
				convertedOuput = convertedOuput / 2;

			}

			view.binaryLabel.setText("Binary: " + decimal);

		}

		void hexaConversion() {
			String decimalInput = userInput;

			Integer convertedOutput = Integer.parseInt(decimalInput);
			view.decimalLabel.setText("Decimal: " + Integer.toHexString(convertedOutput));
		}

		void octalConversion() {
			String convertedOutput = userInput;
			Integer octal = Integer.parseInt(convertedOutput, 8);

			view.decimalLabel.setText("Decimal: " + octal);
		}

		void binaryConversion() {
			String convertedOutput = "";

			convertedOutput = userInput;

			view.decimalLabel.setText("Decimal: " + Integer.parseInt(convertedOutput, 2));

		}

		public void binarytoText() {
			String info = userInput;
			int charCode = Integer.parseInt(info, 2);
			String convertedOutput = new Character((char) charCode).toString().trim();

			view.binaryString.setText("Binary Character: " + convertedOutput);

		}

	}

}
