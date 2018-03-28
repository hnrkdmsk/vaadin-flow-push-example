package com.avides.vaadin.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Push(transport = Transport.LONG_POLLING)
@Theme(Lumo.class)
@BodySize(height = "100vh", width = "100vw")
public class MainLayout extends Div implements RouterLayout
{
}
