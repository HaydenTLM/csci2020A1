import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.concurrent.ThreadLocalRandom;

public class Question1 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setHgap(5);
    pane.setVgap(5);

    for (int x = 0; x < 3; x += 1) {
      Image image1 = new Image(StringMaker());
      ImageView imageView = new ImageView(image1);
      pane.add(imageView, x,0);
    }

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Question_1"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

  }

  public static void main(String[] args) {
    launch(args);
  }

  public static String StringMaker() {
    int randomNum = ThreadLocalRandom.current().nextInt(1, 55);
    return "file:///C:/Users/HaydenLaptop/Documents/School 2018-2019/Winter/csci2020u/Assignment/Cards/" + randomNum + ".png";
   }
}
