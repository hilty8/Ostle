/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardpieceexam;

/**
 *
 * @author hilty
 */
public enum PieceType {
    
    // 定数を４つ宣言
    NoPiece("NoPiece"),
    Player1("Player1"),
    Player2("Player2"),
    PitFall("PitFall");
    
    private final String _pieceCode;
    
    private PieceType(String str){
        _pieceCode = str;
    }
    
    public String getDirection(){
        return _pieceCode;
    }
    
    
}
