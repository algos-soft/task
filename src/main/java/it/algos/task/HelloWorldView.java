package it.algos.task;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import it.algos.vbase.ui.view.MainLayout;

@Route(value = "hello", layout = MainLayout.class)
@Uses(Icon.class)
public class HelloWorldView extends Composite<VerticalLayout> {

    public HelloWorldView() {
        getContent().setHeightFull();
        getContent().setWidthFull();
    }
}
