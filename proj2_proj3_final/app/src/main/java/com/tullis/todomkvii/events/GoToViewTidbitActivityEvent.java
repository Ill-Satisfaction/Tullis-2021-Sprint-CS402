package com.tullis.todomkvii.events;

import com.tullis.todomkvii.data.models.Tidbit;

public class GoToViewTidbitActivityEvent {

    public Tidbit tidbit;

    public GoToViewTidbitActivityEvent (Tidbit t) {
            this.tidbit = t;
        }
}
