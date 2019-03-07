import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Question2 extends Application {
  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {
    GridPane pane = new GridPane();

    pane.setHgap(10);
    pane.setVgap(10);

    Label label1 = new Label("Investment Amount: ");
    TextField tfNumber1 = new TextField();
    Label label2 = new Label("Years: ");
    TextField tfNumber2 = new TextField();
    Label label3 = new Label("Annual Interest Rate: ");
    TextField tfNumber3 = new TextField();
    Label label4 = new Label("Future Value: ");
    TextField tfResult = new TextField();
    tfNumber1.setPrefColumnCount(3);
    tfNumber2.setPrefColumnCount(3);
    tfNumber3.setPrefColumnCount(3);
    tfResult.setPrefColumnCount(3);

    pane.add(label1,0,0);
    pane.add(tfNumber1,1,0);
    pane.add(label2,0,1);
    pane.add(tfNumber2,1,1);
    pane.add(label3,0,2);
    pane.add(tfNumber3,1,2);
    pane.add(label4,0,3);
    pane.add(tfResult,1,3);

    HBox hBox = new HBox();
    Button btCalc = new Button("Calculate");
    hBox.setAlignment(Pos.BOTTOM_RIGHT);
    hBox.getChildren().addAll(btCalc);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);
    BorderPane.setAlignment(hBox, Pos.TOP_RIGHT);

    Scene scene = new Scene(borderPane, 175, 165);
    primaryStage.setTitle("Question_2");
    primaryStage.setScene(scene);
    primaryStage.show();

    btCalc.setOnAction(e -> {
      tfResult.setText(Calculate((Double.parseDouble(tfNumber3.getText())/100 + 1),
      Double.parseDouble(tfNumber2.getText()), Double.parseDouble(tfNumber1.getText())) + "");
    });
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static double Calculate(double a, double b, double c) {
    return (Math.pow(a,b) * c);
  }
}
