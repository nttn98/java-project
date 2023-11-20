package main.java.CSE306;

public class BoardRight extends Board {
    @Override
    public void makeMove() {
        for (int i = board.length; i >= 0; i--) {
            if (board[i] == '-') {
                board[i] = 'x';
                break;
            }
        }
    }
}
