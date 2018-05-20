package boardpieceexam;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 挙動
 * ・引数は列挙型PieceTypeクラス
 * ・引数の種類によって特定のImageオブジェクトを搭載する
 * @author hilty
 */
public class Piece extends ImageView{
    // playerコマ、落とし穴コマのImageインスタンス
    private final Image player1 = new Image(getClass().getResourceAsStream("pieceBlack100.jpeg"));
    private final Image player2 = new Image(getClass().getResourceAsStream("pieceBlown100.jpg"));
    private final Image pitFall = new Image(getClass().getResourceAsStream("redPitfall100.jpg"));
    
    public Piece(PieceType pieceType){
        // Imageクラスのインスタンスをセット
        switch(pieceType){
            case Player1:
                super.setImage(player1);
                break;
            case Player2:
                super.setImage(player2);
                break;
            case PitFall:
                super.setImage(pitFall);
                break;
            default:
                break;
        }
        // 縦横サイズ指定
        super.setFitHeight(50);
        super.setFitWidth(50);
    }
}
