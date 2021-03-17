package com.example.todomk2.Events;

import com.example.todomk2.Models.LIST_ID;

public class DeleteTidbitEvent {
    public int position;

    public DeleteTidbitEvent (int position) {
        this.position = position;
    }
}
