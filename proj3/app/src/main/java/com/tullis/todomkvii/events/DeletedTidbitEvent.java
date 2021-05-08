package com.tullis.todomkvii.events;

import com.tullis.todomkvii.data.models.Tidbit;

public class DeletedTidbitEvent {
    public Tidbit tidbit;

    public DeletedTidbitEvent (Tidbit t) {
        this.tidbit = t;
    }
}
