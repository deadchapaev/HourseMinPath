package com.airatgaliev.hourse_min_path.controller;

import com.airatgaliev.hourse_min_path.model.Board;
import com.airatgaliev.hourse_min_path.model.Cell;
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

@RestController
@RequestMapping(value = "/hourse/rest")
public class HoursePathController {

    private static final String TEMPLATE = "%d";

    private final BFS<Chessman> bfs;

    private final Hourse hourse;

    @Autowired
    public HoursePathController(BFS<Chessman> bfs, Hourse hourse) {
        this.bfs = bfs;
        this.hourse = hourse;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public HttpEntity<Count> count(@RequestParam(value = "width", defaultValue = "8") int width,
            @RequestParam(value = "height", defaultValue = "8") int height,
            @RequestParam(value = "start", defaultValue = "A1") String start,
            @RequestParam(value = "end", defaultValue = "H8") String end) {
        int minPath = getMinPathCaught(start, end, width, height);
        Count count = new Count(String.format(TEMPLATE, minPath));
        count.add(linkTo(methodOn(HoursePathController.class).count(width, height, start.toUpperCase(), end.toUpperCase())).withSelfRel());

        return new ResponseEntity<>(count, HttpStatus.OK);

    }

    private int getMinPathCaught(String start, String end, int width, int height) {
        try {
            return getMinPath(start, end, width, height);
        } catch (NullPointerException e) {
            return -1;
        }

    }

    private int getMinPath(String start, String end, int width, int height) throws NumberFormatException {
        Cell startCell = buildCell(start);
        Cell endCell = buildCell(end);
        Board board = new Board(width, height);
        return bfs.getMinPath(startCell, endCell, board, hourse);
    }

    private Cell buildCell(String cellStr) throws NumberFormatException {
        int x = NumberFromExcelColumn(cellStr.replaceAll("[0-9]", ""));
        int y = Integer.parseInt(cellStr.toUpperCase().replaceAll("[A-Z]", "")) - 1;
        return new Cell(x, y);
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
