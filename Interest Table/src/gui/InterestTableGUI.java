package gui;

import java.awt.Window;
import java.text.NumberFormat;

//import gui.HandlingEvent.ButtonHandler;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterestTableGUI extends Application {
	
	private TextArea displayArea;
	protected double rate;
	protected double time;
	protected double principal;
	private TextField rateField;
	private TextField principalField;
	private Slider yearSlider;
	
	public void start(Stage primaryStage) 
	{
		int sceneWidth = 500, sceneHeight = 300;
		int spaceBetweenNodes = 8;
						
		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		
		
		
		VBox mainWindow = new VBox();
		mainWindow.getChildren().addAll(displayArea);
		
	
		/* Setting userInput properties */
		HBox userInput = new HBox(spaceBetweenNodes);
		mainWindow.getChildren().addAll(userInput);
		userInput.setPadding(new Insets(5, 50, 20, 50));
		
		/* Adding GUI elements */
		Label principalLabel = new Label("Principal: ");
		principalLabel.setMinWidth(Region.USE_PREF_SIZE);
		principalField = new TextField();
		userInput.getChildren().addAll(principalLabel, principalField);
		
		Label rateLabel = new Label("Rate (Percentage): ");
		rateLabel.setMinWidth(Region.USE_PREF_SIZE);
		rateField = new TextField();
		userInput.getChildren().addAll(rateLabel, rateField);
		
		/* Setting slideElements properties */
		HBox sliderElements = new HBox(spaceBetweenNodes);
		sliderElements.setPadding(new Insets(0, 50, 10, 50));
		mainWindow.getChildren().addAll(sliderElements);

		/* Adding GUI elements */
		Label yearSliderTitle = new Label("Number of Years:");
		yearSliderTitle.setMinWidth(Region.USE_PREF_SIZE);
		
		//slider
		yearSlider = new Slider();
		yearSlider.setMin(1);
		yearSlider.setMax(25);
		yearSlider.setValue(10);
		yearSlider.setMajorTickUnit(4);
		yearSlider.setShowTickMarks(true);
		yearSlider.setShowTickLabels(true);
		
		sliderElements.getChildren().addAll(yearSliderTitle, yearSlider);
		
		/* Setting buttonBox properties */
		HBox buttonBox = new HBox(spaceBetweenNodes);
		buttonBox.setPadding(new Insets(20, 50, 10, 50));
		mainWindow.getChildren().addAll(buttonBox);
		
		/* Adding GUI elements */
		Button simpleInterestButton = new Button("Simple Interest");
		simpleInterestButton.setOnAction(new computeSimpleInterest());
		Button compoundInterestButton = new Button("Compound Interest");
		/* compute Compound Interest, anonymous */
		compoundInterestButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				principal = Double.parseDouble(principalField.getText());
				rate = Double.parseDouble(rateField.getText());
				time = (Double) yearSlider.getValue();
				displayArea.clear();
				
				displayArea.appendText("Principal: $" + principal + ". Rate: " + rate + "\nYear, Compound Interest Amount");
				
				for(int x = 1; x <= time; x++)
				{
					displayArea.appendText("\n" + x + "-->" + NumberFormat.getCurrencyInstance().format(Computations.calcCompoundInterest(rate, principal, x)));
				}	
			}
		});
		Button bothInterestButton = new Button("Both Interests");
		
		/*  compute both Interest, with lambda expression */
		bothInterestButton.setOnAction(e -> {
			principal = Double.parseDouble(principalField.getText());
			rate = Double.parseDouble(rateField.getText());
			time = (Double) yearSlider.getValue();
		
			displayArea.clear();
			displayArea.appendText("Principal: $" + principal + ". Rate: " + rate + "\nYear, Simple Interest Amount, Compound Interest Amount");
			for(int x = 1; x <= time; x++)
			{
				displayArea.appendText("\n" + x + "-->" + NumberFormat.getCurrencyInstance().format(Computations.calcSimpleInterest(rate, principal, x)) + "-->" + NumberFormat.getCurrencyInstance().format(Computations.calcCompoundInterest(rate, principal, x)));
			}
		});
		
		buttonBox.getChildren().addAll(simpleInterestButton, compoundInterestButton, bothInterestButton);
		
		Scene scene = new Scene(mainWindow,sceneWidth, sceneHeight);

		primaryStage.setTitle("Interest Table");
		primaryStage.setScene(scene);
		primaryStage.show();
			
	}
	
	/*  compute Simple Interest, non anonymous */
	private class computeSimpleInterest implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e) 
		{
			principal = Double.parseDouble(principalField.getText());
			rate = Double.parseDouble(rateField.getText());
			time = (Double) yearSlider.getValue();
			displayArea.clear();
					
			displayArea.appendText("Principal: $" + principal + ". Rate: " + rate + "\nYear, Simple Interest Amount");
			for(int x = 1; x <= time; x++)
			{
				displayArea.appendText("\n" + x + "-->" + NumberFormat.getCurrencyInstance().format(Computations.calcSimpleInterest(rate, principal, x)));
			}
		}
	}
	
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	

}
