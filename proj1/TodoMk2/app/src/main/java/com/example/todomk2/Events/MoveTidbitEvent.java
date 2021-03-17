package com.example.todomk2.Events;

import com.example.todomk2.Models.LIST_ID;

public class MoveTidbitEvent {
    public int position;

    public MoveTidbitEvent (int position) {
        this.position = position;
    }
}
