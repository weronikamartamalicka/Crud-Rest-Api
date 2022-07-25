package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin("*")
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@RestController
public class TrelloController {

    private final TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards() {

        return ResponseEntity.ok(trelloFacade.fetchTrelloBoards());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
    public ResponseEntity<CreatedTrelloCardDto> createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return ResponseEntity.ok(trelloFacade.createTrelloCard(trelloCardDto));
    }

}
