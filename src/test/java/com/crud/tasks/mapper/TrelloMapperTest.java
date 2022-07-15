package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TrelloMapperTest {
    @Autowired
    TrelloMapper trelloMapper;
    List<TrelloList> list;
    List<TrelloBoardDto> boardsDto;
    List<TrelloListDto> listDto;
    List<TrelloBoard> boards;
    TrelloCard card;
    TrelloCardDto cardDto;

    @BeforeEach
    void prepareTestData() {
        listDto = new ArrayList<>();
        TrelloListDto listOne = new TrelloListDto("4f", "TODO", true);
        TrelloListDto listTwo = new TrelloListDto("4J", "DONE", true);
        listDto.add(listOne);
        listDto.add(listTwo);

        list = new ArrayList<>();
        TrelloList list1 = new TrelloList("4f", "TODO", true);
        TrelloList list2 = new TrelloList("4J", "DONE", true);
        list.add(list1);
        list.add(list2);

        boardsDto = new ArrayList<>();
        TrelloBoardDto boardOne = new TrelloBoardDto("normal", "3f8", listDto);
        boardsDto.add(boardOne);

        boards = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("normal", "3f8", list);
        boards.add(board1);

        card = new TrelloCard("simple task", "yeah, really simple task", "top", "4f");

        cardDto = new TrelloCardDto("simple task", "yeah, really simple task", "top", "4f");
    }

    @Test
    void mapToTrelloList() {
        List<TrelloList> retrievedList = trelloMapper.mapToTrelloList(listDto);

        assertEquals(2, retrievedList.size());
        assertTrue(retrievedList.get(1).isClosed());
    }

    @Test
    void mapToTrelloListDto() {
        List<TrelloListDto> retrievedList = trelloMapper.mapToTrelloListDto(list);

        assertEquals(2, retrievedList.size());
        assertTrue(retrievedList.get(1).isClosed());
    }

    @Test
    void mapToTrelloBoard() {
        List<TrelloBoard> retrievedTrelloBoards = trelloMapper.mapToTrelloBoard(boardsDto);

        assertEquals(1,retrievedTrelloBoards.size());
    }

    @Test
    void mapToTrelloBoardsDto() {
        List<TrelloBoardDto> retrievedTrelloBoardsDtos = trelloMapper.mapToTrelloBoardsDto(boards);

        assertEquals(1,retrievedTrelloBoardsDtos.size());
    }

    @Test
    void mapToCardDto() {
        TrelloCardDto retrievedTrelloCardDto = trelloMapper.mapToCardDto(card);

        assertEquals("simple task", retrievedTrelloCardDto.getName());
    }

    @Test
    void mapToCard() {
        TrelloCard retrievedTrelloCard = trelloMapper.mapToCard(cardDto);

        assertEquals("simple task", retrievedTrelloCard.getName());
    }
}