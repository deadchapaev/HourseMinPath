package com.airatgaliev.hourse_min_path.controller;

import static com.airatgaliev.hourse_min_path.controller.CellUtils.parseCell;
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
        int minPath = getMinPathCaught(start, end, width, height);
        Count count = new Count(String.format(TEMPLATE, minPath));
        count.add(linkTo(methodOn(HoursePathController.class).count(width, height, start.toUpperCase(), end.toUpperCase())).withSelfRel());

        return new ResponseEntity<>(count, HttpStatus.OK);

    }

    private int getMinPathCaught(String start, String end, int width, int height) throws NumberFormatException {
        try {
            return getMinPath(width, height, start, end);
        } catch (NullPointerException | NumberFormatException e) {
            return -1;
        }
    }

    private int getMinPath(int width, int height, String start, String end) throws NumberFormatException {
        Board board = new Board(width, height);
        
        ChessBoardCell startChessBoardCell = parseCell(start);
        ChessBoardCell endChessBoardCell = parseCell(end);
        
        return bfs.getMinPath(board, startChessBoardCell, endChessBoardCell, chessman);
    }


}
