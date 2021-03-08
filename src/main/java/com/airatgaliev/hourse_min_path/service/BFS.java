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

    public int getMinPath(Cell startCell, Cell endCell, Board board, T t) {
        if (!board.contains(startCell) || !board.contains(endCell)) {
            return -1;
        }

        // содержит обработанные ячейки  
        Set<Cell> visited = new HashSet<>();

        Queue<Cell> queue = new ArrayDeque<>();
        queue.add(startCell);

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            if (cell.equals(endCell)) {
                return cell.getDist();
            }

            if (visited.contains(cell)) {
                continue;
            }

            visited.add(cell);

            // проверяем все возможные движения коня
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
        return -1;
    }

}
