package boardpieceexam;

import java.io.IOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

/**
 *
 * @author hilty
 */
public class Controller {
    public BoardLattice boardLattice;
    public LogManager logManager;
    public GridPane directionButton;
    public Boolean pieceChoosen;
    //public IntegerProperty currentTurn;
    
    public Controller() throws IOException{
        logManager = new LogManager();
        // GUIの定義
        boardLattice = new BoardLattice(logManager.getLog(), logManager.turnPlayer1);
        directionButton = FXMLLoader.load(getClass().getResource("DirectionButton.fxml"));

        // ターン数のBind
        //currentTurn = new SimpleIntegerProperty(0);
        //currentTurn.bind(logManager.turn);
        // Boolean値の初期化
        pieceChoosen = false;
        
        // Bindingの設定１ー駒情報とのBinding
        logManager.pieceRow.bind(boardLattice.propertyRow);
        logManager.pieceCol.bind(boardLattice.propertyCol);
        logManager.pieceRow.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            pieceChoosen = true;
        });

        // Bindingの設定２ー移動方向のBinding、移動変数の更新に伴う移動処理と、GridPaneの再描画
        logManager.propertyDirection.bind(DirectionButtonController.propertyDirection);
        
        logManager.propertyDirection.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // 駒が選択されていなければ何もしない
            if(!pieceChoosen){
                System.out.println("駒が選択されてないよ！");
                return;
            }
            // ●検証
            System.out.println("移動方向: " + logManager.propertyDirection.toString());
            
            // 移動処理
            logManager.moveMethod();
            // Viewの再描画
            boardLattice.rendering(logManager.getLog(), logManager.turnPlayer1);
            // 駒選択情報のRESET
            boardLattice.resetPieceChoice();
            // 駒選択の判定をRESET
            pieceChoosen = false;
        });
    }
}
