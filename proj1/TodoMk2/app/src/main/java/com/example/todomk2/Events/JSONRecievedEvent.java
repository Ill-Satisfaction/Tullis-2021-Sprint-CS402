package com.example.todomk2.Events;

import com.example.todomk2.Models.LIST_ID;

import org.json.JSONArray;

public class JSONRecievedEvent {
    public JSONArray results;
    public LIST_ID id;
    public boolean success;

    public JSONRecievedEvent(boolean success, LIST_ID id, JSONArray results) {
        this.success = success;
        this.results = results;
        this.id = id;
    }
}
