package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards() {

        return ResponseEntity.ok(trelloClient.getTrelloBoards());
    }

    @PostMapping("createTrelloCard")
    public ResponseEntity<CreatedTrelloCard> createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return ResponseEntity.ok(trelloClient.createNewCard(trelloCardDto));
    }

}
