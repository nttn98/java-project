package main.java.CSE306;

import java.net.*;
import java.io.*;

public class TTTServer extends Thread {
    private final static int PORT = 10;

    private Socket connection;
    private String[] args;

    public TTTServer(Socket connection, String[] args) {
        this.connection = connection;
        this.args = args;
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = server.accept();
                Thread serverThread = new TTTServer(connection, args);
                serverThread.start();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            Writer out = new OutputStreamWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            Board board = null;

            String startegy = in.readLine();

            if (startegy.equals("left")) {
                board = new BoardLeft();
            } else {
                board = new BoardRight();
            }

            while (true) {

                String move = in.readLine();
                if (move.equals("left")) {
                    board = new BoardLeft();
                    continue;
                } else if (move.equals("right")) {
                    board = new BoardRight();
                    continue;
                }

                if (move.equals("quit")) {
                    break;
                } else {
                    int cell = Character.getNumericValue(move.charAt(0));
                    // check that the move is within range
                    if (cell >= 0 && cell < 9) {
                        // check that the move is to an empty cell
                        boolean empty = board.checkMove(cell);
                        // System.out.println(empty);
                        if (empty) {
                            /// update board
                            board.updateBoard(cell);
                            // check status for player 'o'
                            // 0. player has not won yet
                            // 1. player won
                            if (board.checkStatus('o') == 0) {
                                if (board.checkBoard() == 0) {
                                    board.makeMove();
                                    // check status for player 'x'
                                    if (board.checkStatus('x') == 0) {

                                        if (board.checkBoard() == 0) {
                                            // return new board
                                            out.write(board.encodeBoard() + "\r\n");
                                            out.flush();
                                        } else {
                                            // return new board
                                            out.write(board.encodeBoard() + " *** ");
                                            out.write("It's a draw!" + " *** ");
                                            out.write("Let's play again!" + " *** ");
                                            out.write("Choose the strategy for next time: " + "\r\n");
                                            out.flush();
                                            board.initialize();
                                        }
                                    } else {
                                        // return new board
                                        out.write(board.encodeBoard() + " *** ");
                                        out.write("I won!" + " *** ");
                                        out.write("Let's play again!" + " *** ");
                                        out.write("Choose the strategy for next time: " + "\r\n");
                                        out.flush();
                                        board.initialize();
                                    }

                                } else {
                                    // return new board
                                    out.write(board.encodeBoard() + " *** ");
                                    out.write("It's a draw!" + " *** ");
                                    out.write("Let's play again!" + " *** ");
                                    out.write("Choose the strategy for next time: " + "\r\n");
                                    out.flush();
                                    board.initialize();
                                }

                            } else {
                                // return new board
                                out.write(board.encodeBoard() + " *** ");
                                out.write("You won!" + " *** ");
                                out.write("Let's play again!" + " *** ");
                                out.write("Choose the strategy for next time: " + "\r\n");
                                out.flush();
                                board.initialize();
                            }

                        } else {
                            // return new board
                            out.write("Occupied cell!" + "\r\n");
                            out.flush();
                        }

                    } else {
                        // return new board
                        out.write("Wrong input!" + "\r\n");
                        out.flush();
                    }
                }

            }
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
