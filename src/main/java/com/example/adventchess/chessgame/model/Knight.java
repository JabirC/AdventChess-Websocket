package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// Knight
public class Knight extends ChessPiece{
    public Knight(String name, String color, int homeRow, int homeColumn) {
        super(name, color, homeRow, homeColumn);
    }
    public List<int[]> possibleMoves(ChessBoard board) {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        List<int[]> moves = new ArrayList<>();


        // A Knight has upto 8 potential squares to move to
        int[][] possibleOffsets = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
         };

        for (int[] offset : possibleOffsets) {
            if(isValidPosition(currentRow + offset[0], currentColumn + offset[1])){
                ChessPiece square = board.getPieceAt(currentRow + offset[0], currentColumn + offset[1]);
                if(square != null){
                    if(!square.getColor().equals(getColor())){
                        addMove(moves, currentRow + offset[0], currentColumn + offset[1]);
                    }
                }
                else if(square == null){
                    addMove(moves, currentRow + offset[0], currentColumn + offset[1]);
                }
            }
        }

        return moves;
    }
}