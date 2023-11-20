package main.java.CSE306;

public class BoardLeft extends Board {
    @Override
    public void makeMove() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                board[i] = 'x';
                break;
            }
        }
    }
}
