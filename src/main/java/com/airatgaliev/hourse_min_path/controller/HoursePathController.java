package com.airatgaliev.hourse_min_path.controller;

import com.airatgaliev.hourse_min_path.model.ChessBoardCell;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airatgaliev.hourse_min_path.model.Count;
import com.airatgaliev.hourse_min_path.model.Hourse;
import com.airatgaliev.hourse_min_path.model.interfaces.Chessman;
import com.airatgaliev.hourse_min_path.service.BFS;
import com.airatgaliev.hourse_min_path.service.Board;

@RestController
@RequestMapping(value = "/hourse/rest")
public class HoursePathController {

    private static final String TEMPLATE = "%d";

    @Autowired
    private BFS<Chessman> bfs;

    @Autowired
    private Chessman chessman;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public HttpEntity<Count> count(@RequestParam(defaultValue = "8") int width,
            @RequestParam(defaultValue = "8") int height,
            @RequestParam(defaultValue = "A1") String start,
            @RequestParam(defaultValue = "H8") String end) {
        int minPath = getMinPath(start, end, width, height);
        Count count = new Count(String.format(TEMPLATE, minPath));
        count.add(linkTo(methodOn(HoursePathController.class).count(width, height, start.toUpperCase(), end.toUpperCase())).withSelfRel());

        return new ResponseEntity<>(count, HttpStatus.OK);

    }

    private int getMinPath(String start, String end, int width, int height) throws NumberFormatException {
        int minPath;
        try {
            Board board = new Board(width, height);

            ChessBoardCell startChessBoardCell = parseCell(start);
            ChessBoardCell endChessBoardCell = parseCell(end);

            minPath = bfs.getMinPath(board, startChessBoardCell, endChessBoardCell, chessman);
        } catch (NullPointerException e) {
            minPath = -1;
        }
        return minPath;
    }

    private ChessBoardCell parseCell(String position) throws NumberFormatException {
        int x = NumberFromExcelColumn(position.replaceAll("[0-9]", ""));
        int y = Integer.parseInt(position.toUpperCase().replaceAll("[A-Z]", "")) - 1;
        return new ChessBoardCell(x, y);
    }

    public int NumberFromExcelColumn(String column) {
        int retVal = 0;
        String col = column;
        for (int iChar = col.length() - 1; iChar >= 0; iChar--) {
            char colPiece = col.charAt(iChar);
            int colNum = colPiece - 64;
            retVal = retVal + colNum * (int) Math.pow(26, col.length() - (iChar + 1));
        }
        return retVal - 1;
    }
}
