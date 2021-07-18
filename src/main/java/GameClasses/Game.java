package GameClasses;

import java.util.Arrays;

public class Game {
    private int sizeOfBoard;
    private int totalValueToWin;
    private  int[][] board;
    public Game(int sizeOfBoard, int totalValueToWin) {
        this.sizeOfBoard = sizeOfBoard;
        this.totalValueToWin = totalValueToWin;
        this.board = new int[sizeOfBoard][sizeOfBoard];
        for (int i = 0; i < sizeOfBoard; i++) {
            Arrays.fill(board[i], -1);
        }
    }

    public int getSizeOfBoard() {
        return sizeOfBoard;
    }

    public void setSizeOfBoard(int sizeOfBoard) {
        this.sizeOfBoard = sizeOfBoard;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getTotalValueToWin() {
        return totalValueToWin;
    }

    public void setTotalValueToWin(int totalValueToWin) {
        this.totalValueToWin = totalValueToWin;
    }
}
