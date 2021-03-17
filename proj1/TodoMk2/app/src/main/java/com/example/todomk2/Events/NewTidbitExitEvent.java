package com.example.todomk2.Events;

import com.example.todomk2.Models.TidbitModel;

public class NewTidbitExitEvent {
    public boolean success;
    public TidbitModel tidbit;

    public NewTidbitExitEvent (boolean success, TidbitModel tidbit) {
        this.success = success;
        this.tidbit = tidbit;
    }
}
