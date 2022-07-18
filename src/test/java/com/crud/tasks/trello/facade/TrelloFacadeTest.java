package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.anyOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TrelloFacadeTest {
    @InjectMocks
    TrelloFacade trelloFacade;
    @Mock
    TrelloService trelloService;
    @Mock
    TrelloMapper trelloMapper;
    @Mock
    TrelloValidator trelloValidator;

    @Test
    void shouldFetchEmptyTrelloBoards() {
        List<TrelloListDto> trelloListDtos = List.of(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoardsDtos = List.of(new TrelloBoardDto("1", "test", trelloListDtos));
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "test_list", false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "test", trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDtos);
        when(trelloMapper.mapToTrelloBoard(trelloBoardsDtos)).thenReturn(trelloBoards);
        when(trelloMapper.mapToTrelloBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(List.of());

        List<TrelloBoardDto> retrievedBoard = trelloFacade.fetchTrelloBoards();

        assertThat(retrievedBoard).isNotNull();
        assertThat(retrievedBoard.size()).isEqualTo(0);
    }

    @Test
    void shouldFetchTrello() {
        List<TrelloListDto> trelloListDtos = List.of(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoardsDtos = List.of(new TrelloBoardDto("test", "1", trelloListDtos));
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "test_list", false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "test", trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDtos);
        when(trelloMapper.mapToTrelloBoard(trelloBoardsDtos)).thenReturn(trelloBoards);
        when(trelloMapper.mapToTrelloBoardsDto(anyList())).thenReturn(trelloBoardsDtos);
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(trelloBoards);

        List<TrelloBoardDto> retrievedBoard = trelloFacade.fetchTrelloBoards();

        assertThat(retrievedBoard).isNotNull();
        assertThat(retrievedBoard.size()).isEqualTo(1);

        retrievedBoard.forEach(trelloBoardDto -> {
            assertThat(trelloBoardDto.getName()).isEqualTo("test");
            assertThat(trelloBoardDto.getId()).isEqualTo("1");
            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertThat(trelloListDto.getName()).isEqualTo("test_list");
                assertThat(trelloListDto.isClosed()).isFalse();
                assertThat(trelloListDto.getId()).isEqualTo("1");
            });
        });
    }

    @Test
    void shouldCreateTrelloCard() {
        TrelloCardDto cardDto = new TrelloCardDto("DONE", "Done tasks", "top", "34Gf6u7");
        TrelloCard card = new TrelloCard("DONE", "Done tasks", "top", "34Gf6u7");
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("34g5", "DONE", "trello.com/5341");

        when(trelloMapper.mapToCard(cardDto)).thenReturn(card);
        when(trelloMapper.mapToCardDto(any())).thenReturn(cardDto);
        when(trelloFacade.createTrelloCard(cardDto)).thenReturn(newCard);

        CreatedTrelloCardDto retrievedCard = trelloFacade.createTrelloCard(cardDto);

       assertThat(retrievedCard.getName()).isEqualTo("DONE");
       assertThat(retrievedCard.getId()).isEqualTo("34g5");
       assertThat(retrievedCard.getShortUrl()).isEqualTo("trello.com/5341");
    }

}