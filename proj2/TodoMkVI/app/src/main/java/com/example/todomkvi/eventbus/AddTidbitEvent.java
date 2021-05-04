package com.example.todomkvi.eventbus;

import com.example.todomkvi.data.Tidbit;

public class AddTidbitEvent {

    public Tidbit tidbit;

    public AddTidbitEvent (Tidbit t) {
        this.tidbit = t;
    }
}

