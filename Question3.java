import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Random;

public class Question3 extends Application{

    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        pane.setPrefSize(600, 600);

        //creating the bigger circle
        Circle bCircle = new Circle();
        bCircle.setCenterX(300);
        bCircle.setCenterY(300);
        bCircle.setRadius(150);
        bCircle.setStroke(Color.BLACK);
        bCircle.setFill(Color.WHITE);
        pane.getChildren().add(bCircle);

        //creating three random smaller points/circles
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Point2D> points = new ArrayList<>();
        for(int i = 0; i<3; i++){
            //generating a random angle
            Random random = new Random();
            int angle = random.nextInt(160)+20;

            Circle sCircle = new Circle();
            sCircle.setRadius(10);
            sCircle.setCenterX(bCircle.getRadius()*Math.cos(angle) + bCircle.getCenterX());
            sCircle.setCenterY(bCircle.getRadius()*Math.sin(angle) + bCircle.getCenterY());
            points.add(new Point2D(sCircle.getCenterX(), sCircle.getCenterY()));
            sCircle.setStroke(Color.BLACK);
            sCircle.setFill(Color.RED);
            circles.add(sCircle);
            pane.getChildren().add(sCircle);

        }

        //connecting the smaller circles with lines
        Line l12 = new Line(circles.get(0).getCenterX(), circles.get(0).getCenterY(), circles.get(1).getCenterX(), circles.get(1).getCenterY());
        Line l23 = new Line(circles.get(1).getCenterX(), circles.get(1).getCenterY(), circles.get(2).getCenterX(), circles.get(2).getCenterY());
        Line l31 = new Line(circles.get(2).getCenterX(), circles.get(2).getCenterY(), circles.get(0).getCenterX(), circles.get(0).getCenterY());
        ArrayList<Line> lines = new ArrayList<>();
        lines.add(l12);
        lines.add(l23);
        lines.add(l31);
        pane.getChildren().addAll(l12, l23, l31);

        //getting measurement of sides for angle calculation
        double[] sides = calculateSides(lines);

        //calculating angles
        double[] angles = calculateAngle(sides[0], sides[2], sides[1]);


        //displaying the angles
        ArrayList<Text> texts = new ArrayList<>();
        for(int i=0; i<3; i++){
            Text text = new Text(String.format("%.2f",angles[i]));
            text.setX(points.get(i).getX()+15);
            text.setY(points.get(i).getY());
            texts.add(text);
            pane.getChildren().add(text);
        }


        //adding handlers for moving smaller circles
        for(int i =0; i<3; i++){
            final int j = i;
            circles.get(i).setOnMouseDragged(event -> {
                //get coordinates wrt center of bigger circle
                double x = event.getX()-bCircle.getCenterX();
                double y = event.getY()-bCircle.getCenterY();

                //calculate the angle from center
                double tanTheta = Math.atan(y/x);
                if(x<0){
                    tanTheta+=Math.PI;
                }
                //change the center
                double updatedX = bCircle.getRadius()*Math.cos(tanTheta);
                double updatedY = bCircle.getRadius()*Math.sin(tanTheta);
                circles.get(j).setCenterX(updatedX+bCircle.getCenterX());
                circles.get(j).setCenterY(updatedY+bCircle.getCenterY());

                //update lines
                if(j==0){
                    lines.get(0).setStartX(updatedX+300);
                    lines.get(0).setStartY(updatedY+300);

                    lines.get(2).setEndX(updatedX+300);
                    lines.get(2).setEndY(updatedY+300);
                }

                if(j==1){
                    lines.get(1).setStartX(updatedX+300);
                    lines.get(1).setStartY(updatedY+300);

                    lines.get(0).setEndX(updatedX+300);
                    lines.get(0).setEndY(updatedY+300);
                }

                if(j==2){
                    lines.get(2).setStartX(updatedX+300);
                    lines.get(2).setStartY(updatedY+300);

                    lines.get(1).setEndX(updatedX+300);
                    lines.get(1).setEndY(updatedY+300);
                }

                //updating the angles
                double[] updatedSides = calculateSides(lines);
                double[] updatedAngles = calculateAngle(updatedSides[0], updatedSides[2], updatedSides[1]);



                for(int k =0; k<3; k++){
                    texts.get(k).setText(String.format("%.2f",updatedAngles[k]));
                    texts.get(k).setX(lines.get(k).getStartX()+10);
                    texts.get(k).setY(lines.get(k).getStartY()+20);
                }

            });
        }


        primaryStage.setTitle("Question 3");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

    }
    public static double[] calculateAngle(double side1, double side2, double side3){
        double[] angles = new double[3];
        angles[0] = Math.acos((side3*side3-side1*side1-side2*side2)/(-2*side1*side2))*180/Math.PI;
        angles[1] = Math.acos((side2*side2-side1*side1-side3*side3)/(-2*side1*side3))*180/Math.PI;
        angles[2] = Math.acos((side1*side1-side2*side2-side3*side3)/(-2*side2*side3))*180/Math.PI;
        return angles;
    }

    public static double[] calculateSides(ArrayList<Line> lines){
        Line l12 = lines.get(0);
        Line l23 = lines.get(1);
        Line l31 = lines.get(2);
        double[] sides = new double[3];
        sides[0] = Math.sqrt(Math.pow((l12.getStartX()-l12.getEndX()),2)+Math.pow(l12.getStartY()-l12.getEndY(), 2));
        sides[1] = Math.sqrt(Math.pow((l23.getStartX()-l23.getEndX()),2)+Math.pow(l23.getStartY()-l23.getEndY(), 2));
        sides[2] = Math.sqrt(Math.pow((l31.getStartX()-l31.getEndX()),2)+Math.pow(l31.getStartY()-l31.getEndY(), 2));
        return sides;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
