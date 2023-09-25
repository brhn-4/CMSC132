package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;

@SuppressWarnings("restriction")

public class MediaExample extends Application {
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 450, sceneHeight = 300;

		String mediaFile = "http://www.cs.umd.edu/hcil/manynets/manynets-2009-11-03.mp4";
		Media media = new Media(mediaFile);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView(mediaPlayer);
		mediaView.setFitWidth(sceneWidth);
	
		/* Adding to BorderPane */
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(mediaView);
		
		Button playButton = new Button("Play");
		playButton.setOnAction(e -> mediaPlayer.play());
		playButton.setPrefSize(sceneWidth / 3, sceneHeight / 10);

		Button pauseButton = new Button("Pause");
		pauseButton.setOnAction(e -> mediaPlayer.pause());
		pauseButton.setPrefSize(sceneWidth / 3, sceneHeight / 10);

		FlowPane pane = new FlowPane();
		pane.setHgap(sceneWidth / 3);
		pane.getChildren().addAll(playButton, pauseButton);
		borderPane.setTop(pane);
		
		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Media Example");
		primaryStage.setScene(scene);
		
		/* For full screen */
		//primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
