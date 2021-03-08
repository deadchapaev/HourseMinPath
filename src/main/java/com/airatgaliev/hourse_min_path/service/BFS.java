package com.airatgaliev.hourse_min_path.service;

import com.airatgaliev.hourse_min_path.model.Board;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.airatgaliev.hourse_min_path.model.ChessBoardCell;
import com.airatgaliev.hourse_min_path.model.interfaces.Chessman;

@Component
public class BFS<T extends Chessman> {

    public int getMinPath(int width, int height, int startX, int startY, int endX, int endY, T t) {
        Board board = new Board(width, height);
        return getMinPath(startX, startY, board, endX, endY, t);
    }

    private int getMinPath(int startX, int startY, Board board, int endX, int endY, T t) {
        ChessBoardCell startChessBoardCell = new ChessBoardCell(startX, startY);
        ChessBoardCell endChessBoardCell = new ChessBoardCell(endX, endY);
        // проверка, посещена ли ячейка матрицы раньше или нет
        Set<ChessBoardCell> visited = new HashSet<ChessBoardCell>();

        // создать очередь и поставить в очередь первый узел
        Queue<ChessBoardCell> queue = new ArrayDeque<ChessBoardCell>();
        //TODO:один в один как startChessBoardCell
        ChessBoardCell startCell = new ChessBoardCell(startX, startY);
        queue.add(startCell);
        if (!isValidCell(startChessBoardCell, board) || !isValidCell(endChessBoardCell, board)) {
            return -1;
        }

        // выполняем пока очередь не пуста
        while (!queue.isEmpty()) {
            ChessBoardCell cell = queue.poll();
            int x = cell.getX();
            int y = cell.getY();
            int dist = cell.getDist();
            // если пункт назначения достигнут, вернуть расстояние
            if (x == endChessBoardCell.getX() && y == endChessBoardCell.getX()) {
                return dist;
            }

            // Пропустить, если местоположение посещалось раньше
            if (!visited.contains(cell)) {
                // пометить текущий узел как посещенный
                visited.add(cell);

                // проверяем все 8 возможных движений коня
                // и вставляем в очередь каждое действующее движение
                for (int i = 0; i < 8; ++i) {
                    // Получить новую действительную позицию коня
                    // из текущей позиции на шахматной доске
                    // и поставить ее в очередь на +1 расстояние
                    ChessBoardCell chessBoardCell = new ChessBoardCell(x + t.getDx(i), y + t.getDy(i));
                    if (isValidCell(chessBoardCell, board)) {
                        queue.add(new ChessBoardCell(chessBoardCell.getX(), chessBoardCell.getY(), dist + 1));
                    }
                }
            }
        }
        return -1;
    }

    public boolean isValidCell(ChessBoardCell chessBoardCell, Board board) {
        return chessBoardCell.getX() >= 0 && chessBoardCell.getX() < board.getWidth()
                && chessBoardCell.getY() >= 0 && chessBoardCell.getY() < board.getHeight();
    }
}
