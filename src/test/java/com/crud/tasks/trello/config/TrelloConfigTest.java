package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrelloConfigTest {

    @Test
    void shouldNotShowInformation() {
        //Given
        TrelloConfig config = new TrelloConfig();
        //When
        String trelloApiEndpoint = config.getTrelloApiEndpoint();
        String trelloAppKey = config.getTrelloAppKey();
        String trelloAppToken = config.getTrelloToken();
        String trelloAppUser = config.getTrelloUser();
        //Then
        assertNull(trelloApiEndpoint);
        assertNull(trelloAppKey);
        assertNull(trelloAppToken);
        assertNull(trelloAppUser);
    }
}
