package com.airatgaliev.hourse_min_path.service;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.airatgaliev.hourse_min_path.model.Cell;
import com.airatgaliev.hourse_min_path.model.interfaces.Chessman;

@Component
public class BoardPathFinder<T extends Chessman> {

    public int findMinPath(Board board, Cell startCell, Cell endCell, T t) {

        if (!board.contains(startCell) || board.contains(endCell)) {
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
            if (cell.isCurrentPosition(endCell)) {
                return cell.getDist();
            }

            // Пропустить, если местоположение посещалось раньше
            if (visited.contains(cell)) {
                continue;
            }
            // пометить текущий узел как посещенный
            visited.add(cell);
            // проверяем все 8 возможных движений коня
            // и вставляем в очередь каждое действующее движение
            for (int i = 0; i < t.getPossibleMovementCnt(); ++i) {
                // Получить новую действительную позицию коня
                // из текущей позиции на шахматной доске
                // и поставить ее в очередь на +1 расстояние
                Cell currentCell = t.getNextCellFrom(cell, i);
                if (board.contains(currentCell)) {
                    queue.add(currentCell);
                }
            }
        }
        return -1;

    }

}
