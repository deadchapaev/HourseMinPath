package com.airatgaliev.hourse_min_path.model;

import org.springframework.stereotype.Component;

import com.airatgaliev.hourse_min_path.model.interfaces.Chessman;

@Component
public class Hourse implements Chessman {

    private final int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
    private final int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

    @Override
    public Cell getNextCellFrom(Cell from, int possibleMovement) {
        int x1 = from.getX() + dx[possibleMovement];
        int y1 = from.getY() + dy[possibleMovement];
        int distance = from.getDist() + 1;
        return new Cell(x1, y1, y1);
    }

    @Override
    public int getPossibleMovementCnt() {
        return 8;
    }

}
