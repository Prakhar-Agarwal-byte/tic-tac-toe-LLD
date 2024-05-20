package models;

import models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private Integer size;

    public Board(int size) {
        this.board = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>(size));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.get(i).add(new Cell());
            }
        }
        this.size = size;
    }

    public boolean canPlacePiece(int row, int col) {
        return board.get(row).get(col).getPiece() == null;
    }

    public void putPiece(int row, int col, Player player) {
        if (!canPlacePiece(row, col)) return;
        Piece piece = player.getPiece();
        board.get(row).get(col).setPiece(piece);

    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                '}';
    }
}
