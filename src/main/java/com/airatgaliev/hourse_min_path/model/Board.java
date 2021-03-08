package com.airatgaliev.hourse_min_path.model;

/**
 *
 * @author chapaev
 */
public class Board {

    //стараемся делать final, иммутабельность спасает от ошибок
    private final int width, height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean contains(ChessBoardCell chessBoardCell) {
        return chessBoardCell.getX() >= 0 && chessBoardCell.getX() < this.width
                && chessBoardCell.getY() >= 0 && chessBoardCell.getY() < this.height;
    }

}
