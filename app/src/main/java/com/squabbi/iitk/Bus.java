package com.squabbi.iitk;

import org.greenrobot.eventbus.EventBus;

public class Bus {
    private static EventBus sBus = new EventBus();

    public static EventBus getInstance() { return sBus; }
}
