package boardpieceexam;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * FXML Controller class
 *
 * @author hilty
 */
public class DirectionButtonController implements Initializable {
    public static StringProperty propertyDirection = new SimpleStringProperty("have not choosen!");

    // DirectionButton.fxml より
    @FXML GridPane direction;
    @FXML Polygon upButton;
    @FXML Polygon downButton;
    @FXML Polygon rightButton;
    @FXML Polygon leftButton;
    @FXML Circle cancelButton;
    @FXML Label cancelButtonLabel;
    @FXML StackPane upPane;
    @FXML StackPane downPane;
    @FXML StackPane rightPane;
    @FXML StackPane leftPane;
    
    
    EventHandler<MouseEvent> upMethod = (event)->{
        propertyDirection.setValue("up");
    };
    EventHandler<MouseEvent> downMethod = (event)->{
        propertyDirection.setValue("down");
    };
    EventHandler<MouseEvent> rightMethod = (event)->{
        propertyDirection.setValue("right");
    };
    EventHandler<MouseEvent> leftMethod = (event)->{
        propertyDirection.setValue("left");
    };
    EventHandler<MouseEvent> cancelMethod = (event)->{
        
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 各方向のボタンにEventを実装
        upButton.setOnMouseClicked(upMethod);
        downButton.setOnMouseClicked(downMethod);
        rightButton.setOnMouseClicked(rightMethod);
        leftButton.setOnMouseClicked(leftMethod);
        cancelButton.setOnMouseClicked(cancelMethod);
        cancelButtonLabel.setOnMouseClicked(cancelMethod);
        
        upPane.setOnMouseClicked(upMethod);
        downPane.setOnMouseClicked(downMethod);
        rightPane.setOnMouseClicked(rightMethod);
        leftPane.setOnMouseClicked(leftMethod);
        
        
        // PaneごとのEvent
        upPane.setOnMouseEntered(event -> {
            upPane.setStyle("-fx-background-color: palegreen;");
        });
        upPane.setOnMouseExited(event -> {
            upPane.setStyle("-fx-background-color: transparent;");            
        });
        downPane.setOnMouseEntered(event -> {
            downPane.setStyle("-fx-background-color: palegreen;");
        });
        downPane.setOnMouseExited(event -> {
            downPane.setStyle("-fx-background-color: transparent;");            
        });
        rightPane.setOnMouseEntered(event -> {
            rightPane.setStyle("-fx-background-color: palegreen;");
        });
        rightPane.setOnMouseExited(event -> {
            rightPane.setStyle("-fx-background-color: transparent;");            
        });
        leftPane.setOnMouseEntered(event -> {
            leftPane.setStyle("-fx-background-color: palegreen;");
        });
        leftPane.setOnMouseExited(event -> {
            leftPane.setStyle("-fx-background-color: transparent;");            
        });
        
        
    }
}
