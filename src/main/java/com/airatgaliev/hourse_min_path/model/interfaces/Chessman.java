package com.airatgaliev.hourse_min_path.model.interfaces;

import com.airatgaliev.hourse_min_path.model.ChessBoardCell;

public interface Chessman {

    int getDx(int index);

    int getDy(int index);

    ChessBoardCell getNextCellFrom(ChessBoardCell from, int possibleMovement);

    int getPossibleMovementCnt();
}
