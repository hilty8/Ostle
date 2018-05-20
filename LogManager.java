package boardpieceexam;

import java.util.Arrays;
import java.util.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 本ログ PieceType [256][5][5]
 * 仮ログ PieceType [5][5]
 * ターン数 int turn
 * 
 * @author hilty
 */
public class LogManager extends Observable{
    public Boolean turnPlayer1;
    // ログ関連
    public int turn;
    private PieceType[][][] mainLog = new PieceType[256][5][5];
    public PieceType[][] tmpLog = new PieceType[5][5];

       // 駒選択
    public IntegerProperty pieceRow = new SimpleIntegerProperty(-1);
    public IntegerProperty pieceCol = new SimpleIntegerProperty(-1);

    // 移動方向の選択
    public StringProperty propertyDirection = new SimpleStringProperty("");
    
    public PieceType[][] getLog(){
        return mainLog[turn];
    }
    
    public LogManager(){
        turn = 0;
        //ログの初期化
        initializeMainLog();
        turnPlayer1 = true;
    }
    
    // BoardStatus初期化
    private void initializeMainLog(){
        PieceType[][] initStatus = {
            {PieceType.Player2, PieceType.Player2, PieceType.Player2, PieceType.Player2, PieceType.Player2},
            {PieceType.NoPiece, PieceType.NoPiece, PieceType.NoPiece, PieceType.NoPiece, PieceType.NoPiece},
            {PieceType.NoPiece, PieceType.NoPiece, PieceType.PitFall, PieceType.NoPiece, PieceType.NoPiece},
            {PieceType.NoPiece, PieceType.NoPiece, PieceType.NoPiece, PieceType.NoPiece, PieceType.NoPiece},
            {PieceType.Player1, PieceType.Player1, PieceType.Player1, PieceType.Player1, PieceType.Player1}
        };
        for(int i=0; i < 5; i++){
            System.arraycopy(initStatus[i], 0, mainLog[0][i], 0, 5);
            System.arraycopy(initStatus[i], 0, mainLog[1][i], 0, 5);
        }
    }

    
    // 移動処理の前準備―tmpLogの設定
    private void setTmpLog(){
        for(int i=0; i<5;i++){
            System.arraycopy(mainLog[turn][i], 0, tmpLog[i], 0, 5);
        }
    }
    
    // 外部が実際に呼び出す移動処理
    public void moveMethod(){
        // 移動処理に用いるtmpLogを設定
        setTmpLog();
        // 移動方向に従って、移動処理に用いる変数を設定
        String str = propertyDirection.getValue();
        whichDirection(str);
        
        // 移動処理を実行し、tmpLogに移動結果を格納
        movePiece(pieceRow.intValue(), pieceCol.intValue());
        
        // 千日手チェック
        //●未実装●
        
        // 移動結果を本ログに更新
        updateMainLog();
    }
    
    // MainLog更新　＋　それに伴う変数の操作
    public void updateMainLog(){
        // 1, ターン数を進める
        turn += 1;
        
        // 2, MainLogを更新
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                mainLog[turn][i][j] = tmpLog[i][j];
            }
        }
        // 3, Playerの手番を入れ替える
        turnPlayer1 = !turnPlayer1;
    }
    
    // 移動方向に関する変数
    private int rowMove;
    private int colMove;
    void whichDirection(String str){
        switch(str){
            case "up":
                rowMove = -1;
                colMove = 0;
                break;
            case "down":
                rowMove = 1;
                colMove = 0;
                break;
            case "right":
                rowMove = 0;
                colMove = 1;
                break;
            case "left":
                rowMove = 0;
                colMove = -1;
                break;
        }
    }

    //移動処理メソッド - PieceType[5][5] tmpLog　を処理
    private void movePiece(int row, int col){
        // 選択された駒によって処理を分ける
        switch(tmpLog[row][col]){
            case NoPiece:
                return;
            
            case PitFall:
                // 移動先が場外 -> 移動処理を終了
                if(arrayIndexCheck(row, col, rowMove, colMove) == false) return;
                // 移動先が空き駒の場合のみ -> 落とし穴コマを移動する　＋　元の場所を空きマスに
                if(tmpLog[row + rowMove][col + colMove] == PieceType.NoPiece){
                    tmpLog[row + rowMove][col + colMove] = PieceType.PitFall;
                    tmpLog[row][col] = PieceType.NoPiece;
                }
                break;
            
            // Playerの駒が移動する処理
            default:
                // 移動先が場外 -> 移動処理を終了
                if(arrayIndexCheck(row, col, rowMove, colMove) == false) return;
                // 移動先に落とし穴コマ以外のとき -> 
                // 1, 移動先の駒を先に移動させる
                // 2, 移動先の駒を自分の駒で書き換え
                // 3, 元の場所を空きマスに
                switch(tmpLog[row + rowMove][col + colMove]){
                    case PitFall:
                        tmpLog[row][col] = PieceType.NoPiece;
                    case Player1: 
                    case Player2:
                        movePiece(row + rowMove, col + colMove);
                    default:
                        tmpLog[row + rowMove][col + colMove] = tmpLog[row][col];
                        tmpLog[row][col] = PieceType.NoPiece;
                        break;
                }
        }
    }
    
    // 移動先が場外か否かを確認
    private Boolean arrayIndexCheck(int row, int col, int rowMove, int colMove){
        int rowTo = row + rowMove;
        int colTo = col + colMove;
        
        if(colTo < 0) return false;
        if(colTo >= 5) return false;
        if(rowTo < 0) return false;
        if(rowTo >= 5) return false;
        return true;
    }
    
    // ●●未検証●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●
    // ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●
    // 千日手チェック -> もし千日手ではなかった場合はTurn数＋１、仮ログを書きこみ、を行う
    private boolean examineRepetition(){
        // ●ただし！先ずはきちんと比較が出来ているかをチェックする。
        // 参照型だと思うので、Arrays.equalsメソッドが通用するはず。
        int counter = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                Boolean confirmNum = Arrays.deepEquals(tmpLog, mainLog[turn-2]);
                if(confirmNum == true) counter += 1;
            }
        }
        return counter == 25;
    }
    
}
