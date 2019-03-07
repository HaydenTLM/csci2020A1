

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Question2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane gridPane = new GridPane();

        //Creating text fields and respective labels
        Label lAmount = new Label("Investment Amount");
        TextField tfAmount = new TextField();
        tfAmount.setAlignment(Pos.BASELINE_RIGHT);

        Label lYears = new Label("Years");
        TextField tfYears = new TextField();
        tfYears.setAlignment(Pos.BASELINE_RIGHT);

        Label lRate = new Label("Annual Interest Rate");
        TextField tfRate = new TextField();
        tfRate.setAlignment(Pos.BASELINE_RIGHT);

        Label lFutureValue = new Label("Future Value");
        TextField tfFutureValue = new TextField();
        tfFutureValue.setAlignment(Pos.BASELINE_RIGHT);

        //adding the calculate button
        Button bCalculate = new Button("Calculate");
        GridPane.setHalignment(bCalculate, HPos.RIGHT);

        //adding handler for the calculate button
        bCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double amount = Double.parseDouble(tfAmount.getText());
                double years = Double.parseDouble(tfYears.getText());
                double interestRate = Double.parseDouble(tfRate.getText());
                double futureValue = calculateFutureVal(amount, years, interestRate);
                tfFutureValue.setText(String.valueOf(futureValue));
            }
        });



        //adding stuff to gridPane
        gridPane.add(lAmount, 0, 0);
        gridPane.add(tfAmount,1,0);

        gridPane.add(lYears, 0, 1);
        gridPane.add(tfYears, 1, 1);

        gridPane.add(lRate, 0, 2);
        gridPane.add(tfRate, 1, 2);

        gridPane.add(lFutureValue, 0, 3);
        gridPane.add(tfFutureValue, 1, 3);

        gridPane.add(bCalculate, 1, 4);


        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(7);

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Question 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static double calculateFutureVal(double amount, double years, double interestRate){
        double monthlyInterestRate = interestRate/1200;
        double futureValue = amount*Math.pow((1+monthlyInterestRate), years*12);
        return futureValue;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
