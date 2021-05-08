package com.tullis.todomkvii.events;

import com.tullis.todomkvii.data.models.Tidbit;

public class ShiftedTidbitEvent {

    public Tidbit tidbit;
    public Long newCollectionID;

    public ShiftedTidbitEvent (Tidbit t, Long newCID) {
        this.tidbit = t;
        this.newCollectionID = newCID;
    }
}
