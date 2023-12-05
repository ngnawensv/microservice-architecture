package com.belrose.microsvclistener.model.actlog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "act-log")
public class ActLog implements Serializable {
    @Id
    private  String id;
    private  String eventId;
    private  String module;
    private  String action;
    private  String data;
    private  String user;
    private  String date;
}
