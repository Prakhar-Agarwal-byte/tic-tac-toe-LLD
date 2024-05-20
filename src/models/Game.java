package models;

import models.pieces.Piece;

import java.util.*;

public class Game {
    private final Board board;
    private final Deque<Player> players;
    private final Random random;

    public Game(int size, Player... players) {
        this.board = new Board(size);
        this.players = new LinkedList<>(Arrays.asList(players));
        this.random = new Random();
    }

    public void startGame() {
        int boardSize = board.getSize();
        while (players.size() > 1) {
            Player player = players.poll();
            System.out.println("Player "+ player.getName()+ " turn.");
            int row = random.nextInt(boardSize);
            int col = random.nextInt(boardSize);
            boolean isEmptySpacePresent = checkEmptySpaces();
            if (!isEmptySpacePresent) {
                System.out.println("Game over");
                return;
            }
            if (!board.canPlacePiece(row, col)) {
                System.out.println("Invalid Move");
                players.addFirst(player);
            } else {
                board.putPiece(row, col, player);
                boolean hasPlayerWon = checkForWinner(player);
                if (hasPlayerWon) {
                    System.out.println("Player " + player.getName() + " won!");
                } else {
                    players.add(player);
                }
                System.out.println(board);
            }
        }
    }

    private boolean checkEmptySpaces() {
        int boardSize = board.getSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board.getBoard().get(i).get(j).getPiece() == null) return true;
            }
        }
        return false;
    }

    private boolean checkForWinner(Player player) {
        Piece piece = player.getPiece();
        if (checkRows(piece)) return true;
        if (checkCols(piece)) return true;
        if (checkDiagonalOne(piece)) return true;
        if (checkDiagonalTwo(piece)) return true;
        return false;
    }

    private boolean checkRows(Piece piece) {
        int boardSize = board.getSize();
        for (int i = 0; i < boardSize; i++) {
            boolean rowFound = true;
            for (int j = 0; j < boardSize; j++) {
                Piece boardPiece = board.getBoard().get(i).get(j).getPiece();
                if (boardPiece == null || !boardPiece.equals(piece)) {
                    rowFound = false;
                    break;
                }
            }
            if (rowFound) return true;
        }
        return false;
    }

    private boolean checkCols(Piece piece) {
        int boardSize = board.getSize();
        for (int i = 0; i < boardSize; i++) {
            boolean colFound = true;
            for (int j = 0; j < boardSize; j++) {
                Piece boardPiece = board.getBoard().get(j).get(i).getPiece();
                if (boardPiece == null || !boardPiece.equals(piece)) {
                    colFound = false;
                    break;
                };
            }
            if (colFound) return true;
        }
        return false;
    }

    private boolean checkDiagonalOne(Piece piece) {
        int boardSize = board.getSize();
        boolean diagOneFound = true;
        for (int i = 0; i < boardSize; i++) {
            Piece boardPiece = board.getBoard().get(i).get(i).getPiece();
            if (boardPiece == null || !boardPiece.equals(piece)) {
                diagOneFound = false;
                break;
            }
        }
        if (diagOneFound) return true;
        return false;
    }

    private boolean checkDiagonalTwo(Piece piece) {
        int boardSize = board.getSize();
        boolean diagTwoFound = true;
        for (int i = 0; i < boardSize; i++) {
            Piece boardPiece = board.getBoard().get(i).get(boardSize-i-1).getPiece();
            if (boardPiece == null || !boardPiece.equals(piece)) {
                diagTwoFound = false;
                break;
            }
        }
        if (diagTwoFound) return true;
        return false;
    }
}
