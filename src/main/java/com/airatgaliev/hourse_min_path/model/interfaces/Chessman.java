package com.airatgaliev.hourse_min_path.model.interfaces;

import com.airatgaliev.hourse_min_path.model.Cell;

public interface Chessman {

    Cell getNextCellFrom(Cell from, int possibleMovement);

    int getPossibleMovementCnt();
}
