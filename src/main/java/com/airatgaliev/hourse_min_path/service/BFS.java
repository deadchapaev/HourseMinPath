package com.airatgaliev.hourse_min_path.service;

import com.airatgaliev.hourse_min_path.model.Board;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.airatgaliev.hourse_min_path.model.Cell;
import com.airatgaliev.hourse_min_path.model.interfaces.Chessman;

@Component
public class BFS<T extends Chessman> {

    public int getMinPath(int width, int height, int startX, int startY, int endX, int endY, T t) {
        Board board = new Board(width, height);
        //TODO:слишком многословное название для класса. Из контекста и так ясно.
        Cell startChessBoardCell = new Cell(startX, startY);
        Cell endChessBoardCell = new Cell(endX, endY);
        return getMinPath(startChessBoardCell, endChessBoardCell, board, t);
    }

    private int getMinPath(Cell startCell, Cell endCell, Board board, T t) {
        if (!board.contains(startCell) || !board.contains(endCell)) {
            return -1;
        }

        // проверка, посещена ли ячейка матрицы раньше или нет
        Set<Cell> visited = new HashSet<>();
        // создать очередь и поставить в очередь первый узел
        Queue<Cell> queue = new ArrayDeque<>();
        queue.add(startCell);

        // выполняем пока очередь не пуста
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            // если пункт назначения достигнут, вернуть расстояние
            if (cell.equals(endCell)) {
                return cell.getDist();
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
                    Cell nextCell = new Cell(cell.getX() + t.getDx(i), cell.getY() + t.getDy(i), cell.getDist() + 1);
                    if (board.contains(nextCell)) {
                        queue.add(nextCell);
                    }
                }
            }
        }
        return -1;
    }

}
