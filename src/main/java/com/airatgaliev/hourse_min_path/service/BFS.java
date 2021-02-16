package com.airatgaliev.hourse_min_path.service;

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
        ChessBoardCell startChessBoardCell = new ChessBoardCell(startX, startY);
        ChessBoardCell endChessBoardCell = new ChessBoardCell(endX, endY);
        if (!board.contains(startChessBoardCell) || board.contains(endChessBoardCell)) {
            return -1;
        }

        // проверка, посещена ли ячейка матрицы раньше или нет
        Set<ChessBoardCell> visited = new HashSet<>();

        // создать очередь и поставить в очередь первый узел
        Queue<ChessBoardCell> queue = new ArrayDeque<>();
        queue.add(startChessBoardCell);

        // выполняем пока очередь не пуста
        while (!queue.isEmpty()) {
            ChessBoardCell cell = queue.poll();
            // если пункт назначения достигнут, вернуть расстояние
            if (cell.isCurrentPosition(endChessBoardCell)) {
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
                ChessBoardCell currentChessBoardCell = t.getNextCellFrom(cell, i);
                if (board.contains(currentChessBoardCell)) {
                    queue.add(currentChessBoardCell);
                }
            }
        }
        return -1;

    }

}
