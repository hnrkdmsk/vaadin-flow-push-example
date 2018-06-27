package com.avides.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.avides.vaadin.domain.Listing;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import lombok.extern.slf4j.Slf4j;

@Route(value = "", layout = MainLayout.class)
@Slf4j
public class MainView extends VerticalLayout implements MainViewInterface
{
    private static final long serialVersionUID = 7314300070167738427L;

    private final Grid<Listing> listingGrid = new Grid<>();

    private final transient List<Listing> listingGridItems = new ArrayList<>();

    private final Button refreshGridButton = new Button("Refresh items", cmd -> listingGrid.getDataProvider().refreshAll());

    @Autowired
    private transient MainViewPresenter mainViewPresenter;

    private UI currentUi;

    public MainView()
    {
        initRefreshGridButton();
        initListingGrid();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent)
    {
        mainViewPresenter.setMainViewInterface(this);
        currentUi = attachEvent.getUI();
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
        currentUi.access(() ->
        {
            listingGridItems.add(listing);
            listingGrid.getDataProvider().refreshAll();
            log.info("Updated grid {}", listingGridItems);
        });
    }
}
