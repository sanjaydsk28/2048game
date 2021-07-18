package GameClasses;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Controllers {
    Game game;
    boolean initialize = true;

    public void startGame(Game game) {
        this.game = game;
        while (true) {
            generateRandom();
            if (initialize == true) {
                generateRandom();
                initialize = false;
            }
            printBoard();
            Direction direction = Direction.toEnumType(getUserInput());
            updateBoard(direction);
            if (validateWinner() == 1) {
                System.out.println("Congratulations. YOU WON!");
                printBoard();
                break;
            } else if (validateWinner() == -1) {
                System.out.println("Game over");
                printBoard();
                break;
            }
        }
        System.out.println("Game over");
        printBoard();
    }

    private int getUserInput() {
        System.out.println("Give direction::0:left, 1:right, 2:down, 3:up");
        Scanner sc = new Scanner(System.in);
        return  sc.nextInt();
    }

    private int validateWinner() {
        int gameOverCheck = -1;
        for (int[] ints : this.game.getBoard()) {
            for (int anInt : ints) {
                if (anInt == this.game.getTotalValueToWin()) return 1;
                else if (anInt == -1) gameOverCheck = 0;
            }
        }
        return gameOverCheck;
    }

    private void updateBoard(Direction direction) {
        int[][] board = this.game.getBoard();
        moveCellsTogetherInSameDirection(direction);
        if (direction == Direction.LEFT) {
            for (int i = 0; i < board.length; i++) {
                for (int j = board.length - 1; j > 0; j--) {
                    if (board[i][j] != -1 && board[i][j] == board[i][j-1]) {
                        board[i][j] += board[i][j-1];
                        board[i][j-1] = -1;
                    }
                }
            }

        } else if (direction == Direction.RIGHT) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length - 2; j++) {
                    if (board[i][j] != -1 && board[i][j] == board[i][j+1]) {
                        board[i][j] += board[i][j+1];
                        board[i][j+1] = -1;
                    }
                }
            }
        } else if (direction == Direction.DOWN) {
            for (int j = 0; j < board.length; j++) {
                for (int i = board.length - 1; i > 0; i--) {
                    if (board[i][j] != -1 && board[i-1][j] == board[i][j]) {
                        board[i][j] += board[i-1][j];
                        board[i-1][j] = -1;
                    }
                }
            }
        } else if (direction == Direction.UP) {
            for (int j = 0; j < board.length; j++) {
                for (int i = 0; i < board.length - 2; i++) {
                    if (board[i][j] != -1 && board[i][j] == board[i+1][j]) {
                        board[i][j] += board[i+1][j];
                        board[i+1][j] = -1;
                    }
                }
            }
        }
        moveCellsTogetherInSameDirection(direction);
    }

    private void moveCellsTogetherInSameDirection(Direction direction) {
        int[][] board = this.game.getBoard();
        if (direction == Direction.LEFT) {
            for (int i = 0; i < board.length; i++) {
                int emptyIndex = -1;
                for (int j = board[i].length - 1; j >= 0; j--) {
                    if (emptyIndex == -1 && board[i][j] == -1) {
                        emptyIndex = j;
                    } else if (emptyIndex != -1 && board[i][j] != -1) {
                        board[i][emptyIndex--] = board[i][j];
                        board[i][j] = -1;
                    }
                }
            }
        } else if (direction == Direction.RIGHT){
            for (int i = 0; i < board.length; i++) {
                int emptyIndex = -1;
                for (int j = 0; j < board[i].length; j++) {
                    if (emptyIndex == -1 && board[i][j] == -1) {
                        emptyIndex = j;
                    } else if (emptyIndex != -1 && board[i][j] != -1) {
                        board[i][emptyIndex++] = board[i][j];
                        board[i][j] = -1;
                    }
                }
            }
        } else if (direction == Direction.DOWN) {
            for (int j = 0; j < board.length; j++) {
                int emptyIndex = -1;
                for (int i = board.length - 1; i >= 0; i--) {
                    if (emptyIndex == -1 && board[i][j] == -1) {
                        emptyIndex = i;
                    } else if (emptyIndex != -1 && board[i][j] != -1) {
                        board[emptyIndex--][j] = board[i][j];
                        board[i][j] = -1;
                    }
                }
            }
        } else if (direction == Direction.UP) {
            for (int j = 0; j < board.length; j++) {
                int emptyIndex = -1;
                for (int i = 0; i < board.length; i++) {
                    if (emptyIndex == -1 && board[i][j] == -1) {
                        emptyIndex = i;
                    } else if (emptyIndex != -1 && board[i][j] != -1) {
                        board[emptyIndex++][j] = board[i][j];
                        board[i][j] = -1;
                    }
                }
            }
        } else {
            System.out.println("Invalid Direction value");
        }
    }

    private void printBoard() {
        for (int[] ints : this.game.getBoard()) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private void generateRandom() {
        ArrayList<Integer> emptyCells = getEmptyCells();
        Random random = new Random();
        int randomNum = emptyCells.get(random.nextInt(emptyCells.size()));
        int[][] board = this.game.getBoard();
        while (board[randomNum / board.length][randomNum % board.length] != -1) {
            randomNum = emptyCells.get(random.nextInt(emptyCells.size()));
        }
        int valueToInsert = 2;
        System.out.println("RandomNumber: " + (randomNum / board.length) + " " + randomNum % board.length);
        board[randomNum / board.length][randomNum % board.length] = valueToInsert;
    }

    private ArrayList<Integer> getEmptyCells() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int[][] board = this.game.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    arrayList.add((this.game.getSizeOfBoard() * i) + j);
                }
            }
        }
        return arrayList;
    }
}
