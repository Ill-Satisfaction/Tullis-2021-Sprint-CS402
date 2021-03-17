package com.example.todomk2.Events;

import com.example.todomk2.Models.LIST_ID;
import com.example.todomk2.Models.TidbitModel;

public class TidbitMovedEvent {
    public LIST_ID destination;
    public TidbitModel item;

    public TidbitMovedEvent (LIST_ID destination, TidbitModel item) {
        this.destination = destination;
        this.item = item;
    }
}
