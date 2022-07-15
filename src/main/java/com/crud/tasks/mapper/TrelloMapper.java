package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloList> mapToTrelloList(List<TrelloListDto> list) {
        return list.stream()
                .map(trelloListDto -> new TrelloList(trelloListDto.getId(), trelloListDto.getName(),
                        trelloListDto.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToTrelloListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(),
                        trelloList.isClosed()))
                .collect(Collectors.toList());
    }


    public List<TrelloBoard> mapToTrelloBoard(List<TrelloBoardDto> board) {
        return board.stream()
                .map(trelloBoardDto -> new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(),
                        mapToTrelloList(trelloBoardDto.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToTrelloBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoard ->
                        new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(),
                                mapToTrelloListDto(trelloBoard.getTrelloList())))
                .collect(Collectors.toList());

    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPosition(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPosition(), trelloCardDto.getListId());
    }
}
