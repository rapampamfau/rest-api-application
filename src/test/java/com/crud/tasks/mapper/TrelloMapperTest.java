package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloBoard> testBoards = new ArrayList<>(Arrays.asList(new TrelloBoard("1", "test", new ArrayList<>())));
        List<TrelloBoardDto> testBoardsDto = new ArrayList<>(Arrays.asList(new TrelloBoardDto("1", "test", new ArrayList<>())));
        //When
        List<TrelloBoard> resultBoard = trelloMapper.mapToBoards(testBoardsDto);
        //Then
        assertEquals(testBoards.get(0).getId(), resultBoard.get(0).getId());
        assertEquals(testBoards.get(0).getName(), resultBoard.get(0).getName());
        assertEquals(testBoards.get(0).getLists(), resultBoard.get(0).getLists());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloBoard> testBoards = new ArrayList<>(Arrays.asList(new TrelloBoard("1", "test", new ArrayList<>())));
        List<TrelloBoardDto> testBoardsDto = new ArrayList<>(Arrays.asList(new TrelloBoardDto("1", "test", new ArrayList<>())));
        //When
        List<TrelloBoardDto> resultBoard = trelloMapper.mapToBoardsDto(testBoards);
        System.out.println(testBoardsDto.get(0).getId());
        System.out.println(resultBoard.get(0).getId());
        //Then
        assertEquals(testBoardsDto.get(0).getId(), resultBoard.get(0).getId());
        assertEquals(testBoardsDto.get(0).getName(), resultBoard.get(0).getName());
        assertEquals(testBoardsDto.get(0).getLists(), resultBoard.get(0).getLists());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloList> testLists = new ArrayList<>(Arrays.asList(new TrelloList("1", "test", true)));
        List<TrelloListDto> testListsDto = new ArrayList<>(Arrays.asList(new TrelloListDto("1", "test", true)));
        //When
        List<TrelloList> resultList = trelloMapper.mapToList(testListsDto);
        //Then
        assertEquals(testLists.get(0).getId(), resultList.get(0).getId());
        assertEquals(testLists.get(0).getName(), resultList.get(0).getName());
        assertEquals(testLists.get(0).isClosed(), resultList.get(0).isClosed());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> testLists = new ArrayList<>(Arrays.asList(new TrelloList("1", "test", true)));
        List<TrelloListDto> testListsDto = new ArrayList<>(Arrays.asList(new TrelloListDto("1", "test", true)));
        //When
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
        TrelloCard resultCard = trelloMapper.mapToCard(testCardDto);
        //Then
        assertEquals(testCard.getName(), resultCard.getName());
        assertEquals(testCard.getDescription(), resultCard.getDescription());
        assertEquals(testCard.getPos(), resultCard.getPos());
        assertEquals(testCard.getListId(), resultCard.getListId());
    }
}
