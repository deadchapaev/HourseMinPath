package com.airatgaliev.hourse_min_path.model;

public class Cell {

    private int x, y, dist; // координаты ячейки и минимальный путь

    public Cell() {
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDist() {
        return dist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell chessBoard = (Cell) o;

        if (x != chessBoard.x) {
            return false;
        }
        if (y != chessBoard.y) {
            return false;
        }
        return dist == chessBoard.dist;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + dist;
        return result;
    }

}
