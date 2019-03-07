import javafx.scene.Scene;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.util.concurrent.ThreadLocalRandom;

public class Question3 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Polygon tri = createTriagnle();

        Group Triangle = new Group();
        Triangle.getChildren().add(tri);
        Triangle.getChildren().addAll(createAnchors(tri.getPoints()));

        primaryStage.setTitle("Question_3");
        primaryStage.setScene(new Scene(Triangle, 400, 400));
        primaryStage.show();
    }

    private Polygon createTriagnle() {
        Polygon tri = new Polygon();
        double[] coors = new double[6];
        for (int x = 0; x < 6; x += 1) {
            coors[x] = ThreadLocalRandom.current().nextInt(10, 390);
        }
        tri.getPoints().setAll(coors[0], coors[1], coors[2], coors[3], coors[4], coors[5]);
        tri.setFill(Color.WHITE);
        tri.setStroke(Color.BLACK);

        return tri;
    }

    private ObservableList<Anchor> createAnchors( ObservableList<Double> points) {
        ObservableList<Anchor> anchors = FXCollections.observableArrayList();

        for (int i = 0; i < points.size(); i+=2) {
            int idx = i;

            DoubleProperty xProperty = new SimpleDoubleProperty(points.get(i));
            DoubleProperty yProperty = new SimpleDoubleProperty(points.get(i + 1));

            xProperty.addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> ov, Number oldX, Number x) {
                    points.set(idx, (double) x);
                }
            });

            yProperty.addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> ov, Number oldY, Number y) {
                    points.set(idx + 1, (double) y);
                }
            });

            anchors.add(new Anchor(xProperty, yProperty));
        }

        return anchors;
    }

    class Anchor extends Circle {
        private DoubleProperty x, y;

        Anchor(DoubleProperty x, DoubleProperty y) {
            super(x.get(), y.get(), 5);
            setFill(Color.RED);
            setStroke(Color.BLACK);

            this.x = x;
            this.y = y;

            x.bind(centerXProperty());
            y.bind(centerYProperty());
            enableDrag();
        }

        private void enableDrag() {
            Point point = new Point();
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    point.x = getCenterX() - mouseEvent.getX();
                    point.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);
                }
            });
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    double newX = mouseEvent.getX() + point.x;
                    if (newX > 0 && newX < getScene().getWidth()) {
                        setCenterX(newX);
                    }
                    double newY = mouseEvent.getY() + point.y;
                    if (newY > 0 && newY < getScene().getHeight()) {
                        setCenterY(newY);
                    }
                }
            });
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            });
        }
        private class Point {
            double x, y;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
