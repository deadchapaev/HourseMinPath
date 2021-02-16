package com.airatgaliev.hourse_min_path.controller;

import com.airatgaliev.hourse_min_path.model.ChessBoardCell;

/**
 *
 * @author chapaev
 */
public class CellUtils {

    public static ChessBoardCell parseCell(String position) throws NumberFormatException {
        int x = NumberFromExcelColumn(position.replaceAll("[0-9]", ""));
        int y = Integer.parseInt(position.toUpperCase().replaceAll("[A-Z]", "")) - 1;
        return new ChessBoardCell(x, y);
    }

    public static int NumberFromExcelColumn(String column) {
        int retVal = 0;
        for (int iChar = column.length() - 1; iChar >= 0; iChar--) {
            char colPiece = column.charAt(iChar);
            int colNum = colPiece - 64;
            retVal = retVal + colNum * (int) Math.pow(26, column.length() - (iChar + 1));
        }
        return retVal - 1;
    }

}
