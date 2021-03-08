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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
