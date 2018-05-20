/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardpieceexam;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;

/**
 * GUIの階層構造
 * 最下層root - VBox root
 * 1, StackPane gameMat
 *   1-1, AnchorPane boardLines
 *   1-2, GridPane squareLattice
 * 2, ???Pane ScoreBoard(未実装)
 * @author hilty
 */
public class BoardPieceExam extends Application {
    public static final int SIDES = 70;
    public static final int WIDTH = 5;
    final int LineLength = (SIDES / 2) - (WIDTH * 2);
    final int PieceSize = SIDES - 5 * WIDTH;
    final int BOARD_SIZE = SIDES * 5;
    final int BoardMargin = 20;
    final int ScreenSizeV = BOARD_SIZE + BoardMargin * 2;
    final int ScreenSizeH = BOARD_SIZE + BoardMargin * 2;
    // 後々Event実装などで呼び出す必要があるのでここで宣言しておく
    // 1, Board表示系統
    // 2, Board表示のビジネスロジック - Log, Turn数, Boolean, 仮ログ, 
    
    @Override
    public void start(Stage stage) throws Exception {
        // Controllerを生成し、View要素を貰う
        Controller controller = new Controller();
        
        
        // StackPane gameMatの宣言　＋　盤面をそこに載せる
        StackPane gameMat = stackGameMat();
        AnchorPane boardLines = boardLines();
        gameMat.getChildren().addAll(boardLines,controller.boardLattice);
        
        // VBoxの作成
        VBox root = new VBox();
        root.getChildren().add(gameMat);
        root.getChildren().add(controller.directionButton);

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //StackPane stackGameMat 編集
    private StackPane stackGameMat(){
        StackPane gameMat = new StackPane();
        // css用にIDを設定
        gameMat.setId("gameMat");
        
        gameMat.setAlignment(Pos.CENTER);
        gameMat.setPadding(new Insets(BoardMargin));
        gameMat.setMaxSize(ScreenSizeV, ScreenSizeH);
        return gameMat;
    }
    
    // AnchorPane anchorBoardLines の編集メソッド
    private AnchorPane boardLines(){
        AnchorPane anchorPane = new AnchorPane();
        
        // 基準となる開始地点の数値 - sides * 0,1,1,2,2,3,3,4,4,5
        int[] array = new int[10];
        for(int i=0; i < 10; i += 2){
            array[i] = (i / 2) * SIDES;
            array[i+1] = array[i] + SIDES;
        }
        
        // stackGameMat に表示する盤面（Path）の作成
        Path[][] horizonPaths = new Path[6][10];
        for(int i=0; i < 6; i++){
            for(int j=0; j < 10; j += 2){
                horizonPaths[i][j] = new Path(new MoveTo(array[j], i * SIDES),     new HLineTo(array[j] + LineLength));
                horizonPaths[i][j+1] = new Path(new MoveTo(array[j+1], i * SIDES), new HLineTo(array[j+1] - LineLength));
                horizonPaths[i][j].setStrokeWidth(WIDTH);
                horizonPaths[i][j+1].setStrokeWidth(WIDTH);
                anchorPane.getChildren().addAll(horizonPaths[i][j],horizonPaths[i][j+1]);
            }
        }
        Path[][] verticalPaths = new Path[6][10];
        for(int i=0; i < 6; i++){
            for(int j=0; j < 10; j += 2){
                verticalPaths[i][j] = new Path(new MoveTo(i * SIDES, array[j]),     new VLineTo(array[j] + LineLength));
                verticalPaths[i][j+1] = new Path(new MoveTo(i * SIDES, array[j+1]), new VLineTo(array[j+1] - LineLength));
                verticalPaths[i][j].setStrokeWidth(WIDTH);
                verticalPaths[i][j+1].setStrokeWidth(WIDTH);
                anchorPane.getChildren().addAll(verticalPaths[i][j],verticalPaths[i][j+1]);
            }
        }
        return anchorPane;
    }
    
}
