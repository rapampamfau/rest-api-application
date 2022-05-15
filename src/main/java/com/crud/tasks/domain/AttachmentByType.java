package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttachmentByType {

    @JsonProperty("trello")
    private Trello trello;
}
