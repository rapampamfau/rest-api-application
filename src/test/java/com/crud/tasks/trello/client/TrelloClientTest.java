package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        // Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUser()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("Kodilla", "test_id", new ArrayList<>());

        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        // When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        // Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("Kodilla", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        // Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");

        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "test task",
                "http://test.com"
        );
        when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCardDto);
        // When
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUser()).thenReturn("test");

        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);

        //When
        List<TrelloBoardDto> nullBoard = trelloClient.getTrelloBoards();

        //Then
        assertEquals(0, nullBoard.size());
    }

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloBoard> testBoards = new ArrayList<>(Arrays.asList(new TrelloBoard("1", "test", new ArrayList<>())));
        List<TrelloBoardDto> testBoardsDto = new ArrayList<>(Arrays.asList(new TrelloBoardDto("1", "test", new ArrayList<>())));
        //When
        when(trelloMapper.mapToBoards(testBoardsDto)).thenReturn(testBoards);
        List<TrelloBoard> resultBoard = trelloMapper.mapToBoards(testBoardsDto);
        //Then
        assertEquals(testBoards, resultBoard);
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloBoard> testBoards = new ArrayList<>(Arrays.asList(new TrelloBoard("1", "test", new ArrayList<>())));
        List<TrelloBoardDto> testBoardsDto = new ArrayList<>(Arrays.asList(new TrelloBoardDto("1", "test", new ArrayList<>())));
        //When
        when(trelloMapper.mapToBoardsDto(testBoards)).thenReturn(testBoardsDto);
        List<TrelloBoardDto> resultBoard = trelloMapper.mapToBoardsDto(testBoards);
        //Then
        assertEquals(testBoardsDto, resultBoard);
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloList> testLists = new ArrayList<>(Arrays.asList(new TrelloList("1", "test", true)));
        List<TrelloListDto> testListsDto = new ArrayList<>(Arrays.asList(new TrelloListDto("1", "test", true)));
        //When
        when(trelloMapper.mapToList(testListsDto)).thenReturn(testLists);
        List<TrelloList> resultList = trelloMapper.mapToList(testListsDto);
        //Then
        assertEquals(testLists, resultList);
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> testLists = new ArrayList<>(Arrays.asList(new TrelloList("1", "test", true)));
        List<TrelloListDto> testListsDto = new ArrayList<>(Arrays.asList(new TrelloListDto("1", "test", true)));
        //When
        when(trelloMapper.mapToListDto(testLists)).thenReturn(testListsDto);
        List<TrelloListDto> resultList = trelloMapper.mapToListDto(testLists);
        //Then
        assertEquals(testListsDto, resultList);
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard testCard = new TrelloCard("testName", "testDescription", "1", "1");
        TrelloCardDto testCardDto = new TrelloCardDto("testName", "testDescription", "1", "1");
        //When
        when(trelloMapper.mapToCardDto(testCard)).thenReturn(testCardDto);
        TrelloCardDto resultCard = trelloMapper.mapToCardDto(testCard);
        //Then
        assertEquals(testCardDto.getName(), resultCard.getName());
        assertEquals(testCardDto.getDescription(), resultCard.getDescription());
        assertEquals(testCardDto.getPos(), resultCard.getPos());
        assertEquals(testCardDto.getListId(), resultCard.getListId());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCard testCard = new TrelloCard("testName", "testDescription", "1", "1");
        TrelloCardDto testCardDto = new TrelloCardDto("testName", "testDescription", "1", "1");
        //When
        when(trelloMapper.mapToCard(testCardDto)).thenReturn(testCard);
        TrelloCard resultCard = trelloMapper.mapToCard(testCardDto);
        //Then
        assertEquals(testCard.getName(), resultCard.getName());
        assertEquals(testCard.getDescription(), resultCard.getDescription());
        assertEquals(testCard.getPos(), resultCard.getPos());
        assertEquals(testCard.getListId(), resultCard.getListId());
    }
}
