package main.java.CSE306;

public class TTT_BoardRight extends TTT_Board {

    @Override
    public void makeMove() {
        int end = this.board.length - 1;
        for (int i = end; i >= 0; i--) {
            if (this.board[i] == '-') {
                this.board[i] = 'x';
                break;
            }
        }
    }

}