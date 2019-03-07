import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.lang.*;

public class Question4 extends Application {
  @Override
  public void start(Stage primaryStage) {
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String,Number> bc =
      new BarChart<>(xAxis,yAxis);
    bc.setTitle("Question_4");
    xAxis.setLabel("Letters");
    yAxis.setLabel("Times");

    int[] times = new int[26];

    XYChart.Series series1 = new XYChart.Series();
    series1.setName("Letters");
    for (int i = 65; i <= 90; i += 1) {
      series1.getData().add(new XYChart.Data(Character.toString((char)i), times[i-65]));
    }

    TextField tf = new TextField();
    tf.setOnAction(e -> {
        String text = String.valueOf(tf.getText());
        for (int i = 0; i < 26; i += 1) {
          times[i] = 0;
        }
        for (int i = 0; i < tf.getLength(); i += 1) {
          if (65 <= (int)Character.toUpperCase(text.charAt(i)) && (int)Character.toUpperCase(text.charAt(i)) <= 90) {
            times[(int)Character.toUpperCase(text.charAt(i))-65] += 1;
          }
        }
        series1.getData().clear();
        for (int i = 65; i <= 90; i += 1) {
          series1.getData().add(new XYChart.Data(Character.toString((char)i), times[i-65]));
        }
    });

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(bc);
    borderPane.setBottom(tf);
    bc.setAnimated(false);
    bc.getData().add(series1);

    Scene scene  = new Scene(borderPane,800,600);
    primaryStage.setTitle("Question_4");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
