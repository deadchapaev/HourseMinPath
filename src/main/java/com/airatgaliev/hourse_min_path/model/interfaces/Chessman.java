package com.airatgaliev.hourse_min_path.model.interfaces;

import com.airatgaliev.hourse_min_path.model.ChessBoardCell;

public interface Chessman {

    ChessBoardCell getNextCellFrom(ChessBoardCell from, int possibleMovement);

    int getPossibleMovementCnt();
}
