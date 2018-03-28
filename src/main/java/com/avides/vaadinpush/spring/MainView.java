package com.avides.vaadinpush.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.avides.vaadinpush.spring.domain.Listing;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import lombok.extern.slf4j.Slf4j;

@Route(value = "", layout = MainLayout.class)
@Slf4j
public class MainView extends VerticalLayout implements MainViewInterface
{
    private final Grid<Listing> listingGrid = new Grid<>();

    private final transient List<Listing> listingGridItems = new ArrayList<>();

    private final Button refreshGridButton = new Button("Refresh items", new Icon(VaadinIcons.REFRESH), cmd -> listingGrid.getDataProvider().refreshAll());

    @Autowired
    private transient MainViewPresenter mainViewPresenter;

    public MainView()
    {
        initRefreshGridButton();
        initListingGrid();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent)
    {
        mainViewPresenter.setMainViewInterface(this);

        UI ui = attachEvent.getUI();
        Broadcaster.register(value -> ui.access(() -> displayListing(value)));
    }

    @Override
    protected void onDetach(DetachEvent detachEvent)
    {
        mainViewPresenter.setMainViewInterface(null);
    }

    private void initRefreshGridButton()
    {
        add(refreshGridButton);
    }

    private void initListingGrid()
    {
        listingGrid.setDataProvider(new ListDataProvider<>(listingGridItems));
        listingGrid.addColumn(Listing::getId).setHeader("ID");

        add(listingGrid);
    }

    @Override
    public void displayListing(Listing listing)
    {
        listingGridItems.add(listing);
        listingGrid.getDataProvider().refreshAll();
        log.info("Updated grid {}", listingGridItems);

        add(new ExampleTemplate("Listing: " + listing.getId()));
    }
}
