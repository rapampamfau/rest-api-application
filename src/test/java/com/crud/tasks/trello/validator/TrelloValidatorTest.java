package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloValidatorTest {

    @Test
    void shouldValidateTrelloBoards() {
        //Given
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "name", new ArrayList<>()));
        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, resultList.size());
    }
}
