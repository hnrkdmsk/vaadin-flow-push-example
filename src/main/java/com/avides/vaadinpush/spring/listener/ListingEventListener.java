package com.avides.vaadinpush.spring.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.avides.vaadinpush.spring.MainViewPresenter;
import com.avides.vaadinpush.spring.domain.Listing;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ListingEventListener
{
    @Autowired
    private MainViewPresenter mainViewPresenter;

    @Scheduled(fixedDelay = 5000)
    public void handle()
    {
        Listing listing = new Listing(UUID.randomUUID().toString());
        mainViewPresenter.handleListing(listing);
        log.info("Handled inbound {}", listing);
    }
}
