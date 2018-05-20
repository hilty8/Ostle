package boardpieceexam;

import javafx.scene.layout.StackPane;

/**
 *
 * @author hilty
 */
public class Square extends StackPane{
    // 追加するPiece
    Piece piece;
    
    // Pieceインスタンスを新たに生成
    public void updatePiece(PieceType pieceType){
        super.getChildren().remove(piece);
        piece = new Piece(pieceType);
        super.getChildren().add(piece);
    }
    
    public Square(){
        super.setPrefSize(70, 70);
        // マウスがOverしたときに反応
        super.setOnMouseEntered(event -> {
            super.setStyle("-fx-background-color: palegreen;");
        });
        // マウスが離れたら背景色が透明に変更
        super.setOnMouseExited(event -> {
            super.setStyle("-fx-background-color: transparent;");
        });
    }
}
