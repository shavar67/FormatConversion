[![CodeFactor](https://www.codefactor.io/repository/github/shavar67/formatconversion/badge)](https://www.codefactor.io/repository/github/shavar67/formatconversion)
[![BCH compliance](https://bettercodehub.com/edge/badge/shavar67/FormatConversion?branch=master)](https://bettercodehub.com/)
#### BinaryFx
* Initially used to convert from binary to decimal and vice versa.
* Functionality extended to support more formats.
* Built-in bug report feature powered by gmail.


#### Supported Formats 
* Binary
* Decimal
* Octal
* Hexadecimal 
* Alphabetical 



Before Conversion | After Conversion
----|-------
Helloworld|01101000 01100101 01101100 01101100 01101111 01110111 01101111 01110010 01101100 01100100 
01100010 01101001 01101110 01100001 01110010 01111001 00100000 01110100 01101111 00100000 01110100 01100101 01111000 01110100|binary to text

#### Behind the scenes
* A combo box is used to display the different formats to the user.
* To change formats i used a switch statement to get the selected Index from the combo box 
* The custom built in email class uses the 3rd party mail api



``` java 
if (event.getSource() == view.convert) {
try {
userInput = view.getData();
convertedOutput = model.setOutputData(userInput);
int index = view.dropmenu.getSelectionModel().getSelectedIndex();
	switch (index) {
 case 0:
 // do something
 case 1:
 // do something
 case 2:
  // do something
 case 3:
}
```

#### How to make stage transparent and draggable using the undecorated stage style 

``` java
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
	
```


  
  <img src="https://github.com/shavar67/FormatConversion/blob/master/email.jpg"/>

