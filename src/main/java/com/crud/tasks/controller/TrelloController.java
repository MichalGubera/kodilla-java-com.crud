package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
//    public void getTrelloBoards() {
//
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards() //<--- task 18.2.3
//                .stream()
//                .filter(board -> board.getId() != null)
//                .filter(board -> board.getName() != null)
//                .filter(board -> board.getName().contains("Kodilla"))
//                .collect(Collectors.toList());
//
//        trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
//
//    }

    public Optional getTrelloBoards() {  //<--- task 18.2.4

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards()
                .stream()
                .filter(board -> board.getName().contains("Kodilla"))
                .collect(Collectors.toList());

        return Optional.of(trelloBoards);
    }
}