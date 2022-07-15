package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TrelloValidator {

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> boards) {
        log.info("Starting filtering boards...");

        List<TrelloBoard> filteredBoardList = boards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());

        log.info("Boards have been filtered. Current list size: " + filteredBoardList.size());

        return filteredBoardList;
    }

    public void validateCard(final TrelloCard trelloCard) {

        if(trelloCard.getName().equals("test")) {
            log.info("Someone is testing my application!");
        } else {
            log.info("Seems that my application is used in proper way.");
        }
    }
}
