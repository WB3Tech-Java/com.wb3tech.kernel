package com.wb3tech.kernel;

public interface EventDispatcher<Event> {
    void Dispatch(Event event);
}
