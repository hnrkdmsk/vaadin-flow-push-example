package com.avides.vaadin.ui;

import org.springframework.stereotype.Component;

import com.avides.vaadin.domain.Listing;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MainViewPresenter
{
    private MainViewInterface mainViewInterface;

    public void setMainViewInterface(MainViewInterface mainViewInterface)
    {
        this.mainViewInterface = mainViewInterface;
    }

    public void handleListing(Listing listing)
    {
        if (mainViewInterface != null)
        {
            mainViewInterface.displayListing(listing);
            log.info("Display {}", listing);
        }
    }
}
