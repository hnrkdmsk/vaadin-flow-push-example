package com.avides.vaadin.ui;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.avides.vaadin.domain.Listing;
import com.vaadin.flow.shared.Registration;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Broadcaster
{
    private static Executor executor = Executors.newSingleThreadExecutor();

    private static LinkedList<Consumer<Listing>> listeners = new LinkedList<>();

    public static synchronized Registration register(Consumer<Listing> listener)
    {
        listeners.add(listener);

        return () ->
        {
            synchronized (Broadcaster.class)
            {
                listeners.remove(listener);
            }
        };
    }

    public static synchronized void broadcast(Listing listing)
    {
        listeners.forEach(listener -> executor.execute(() -> listener.accept(listing)));
    }
}
