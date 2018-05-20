package boardpieceexam;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author hilty
 */
public class BoardLattice extends GridPane{
    private Boolean turnPlayer1 = true;
    public IntegerProperty propertyRow = new SimpleIntegerProperty(-1);
    public IntegerProperty propertyCol = new SimpleIntegerProperty(-1);
    public Square[][] squares;
    
    public BoardLattice(PieceType[][] argsBoardStatus, Boolean argsTurnPlayer1){
        // コンストラクタ―
        // 1, squaresの生成
        // 2, Eventの設定
        // 3, rendering() の実行、
        squares = new Square[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                // Squareインスタンスが返す変数は、「finalもしくは事実上のfinal変数である必要がある」ため、その２つを宣言
                int row = i;
                int column = j;
                squares[i][j] = new Square();
                // GridPaneに配置　※このときj,iの順で指定しないと、反時計回りに90度回転したような見た目になる
                super.add(squares[i][j], j, i);
                squares[i][j].setOnMouseClicked(event -> {
                    propertyRow.setValue(row);
                    propertyCol.setValue(column);
                });
            }
        }
        rendering(argsBoardStatus, argsTurnPlayer1);
    }
    
    public void resetPieceChoice(){
        propertyRow.setValue(-1);
        propertyCol.setValue(-1);
    }
    
    // PieceType[5][5]に従ってSquareのPieceを更新　＋　不要なマスのEventを無効化
    public void rendering(PieceType[][] boardStatus, Boolean argsTurnPlayer1){
        turnPlayer1 = argsTurnPlayer1;
        
        // ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●
        // ●検証●
//        System.out.println("--------------------------------------------");
//        System.out.println("BoardLattice : rendering実行");
//        System.out.println("取得したPieceType[][]");
//        for(int i=0;i<5;i++){
//            for(int j=0;j<5;j++){
//                System.out.print(boardStatus[i][j] + ", ");
//            }
//            System.out.println("");
//        }
//        System.out.println("--------------------------------------------");
        // ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●
        
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                // 全てのマスのDisableを解除              
                squares[i][j].setDisable(false);
                
                // 駒（ImageViewクラス）を取得
                squares[i][j].updatePiece(boardStatus[i][j]);
                
                // コマが無いマス、手番ではない駒のマス、の２種類について、無効化処理を施す
                switch(boardStatus[i][j]){
                    case NoPiece:
                        squares[i][j].setDisable(true);
                        break;
                    case Player1:
                        if(turnPlayer1 == false){
                            squares[i][j].setDisable(true);
                        }
                        break;
                    case Player2:
                        if(turnPlayer1 == true){
                            squares[i][j].setDisable(true);
                        }
                        break;
                }
            }
        }
    }
}
