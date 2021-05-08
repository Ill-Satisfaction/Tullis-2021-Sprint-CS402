package com.tullis.todomkvii.events;

import com.tullis.todomkvii.data.models.Tidbit;

public class AddedTidbitEvent {

    public Tidbit tidbit;

    public AddedTidbitEvent (Tidbit t) {
        this.tidbit = t;
    }
}
