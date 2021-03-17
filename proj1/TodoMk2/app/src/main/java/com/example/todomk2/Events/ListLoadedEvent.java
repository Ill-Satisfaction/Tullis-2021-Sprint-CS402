package com.example.todomk2.Events;

import com.example.todomk2.Models.LIST_ID;

public class ListLoadedEvent {
    public LIST_ID id;

    public ListLoadedEvent (LIST_ID id) {
        this.id = id;
    }
}
