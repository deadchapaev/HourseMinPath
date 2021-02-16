package com.airatgaliev.hourse_min_path.service;

import com.airatgaliev.hourse_min_path.model.ChessBoardCell;

/**
 *
 * @author chapaev
 */
public class Board {

    private final int width, height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean contains(ChessBoardCell chessBoardCell) {
        return chessBoardCell.getX() >= 0 && chessBoardCell.getX() < width
                && chessBoardCell.getY() >= 0 && chessBoardCell.getY() < height;
    }

}
