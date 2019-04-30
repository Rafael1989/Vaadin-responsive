package com.vaadin.training.layout.ex1;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.training.layout.MainLayout;

import java.util.Random;
import java.util.stream.IntStream;

@Route(value = ResponsiveLayout.ROUTE, layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ResponsiveLayout extends HorizontalLayout {

    private static final long serialVersionUID = 1L;

    public static final String ROUTE = "ex1";
    public static final String TITLE = "Exercise 1";

    private int linknum = 1;
    private final Random rng = new Random();

    public ResponsiveLayout() {
        setSizeFull();
        addClassName("root-layout");
        setDefaultVerticalComponentAlignment(Alignment.STRETCH);

        final VerticalLayout menu = new VerticalLayout();
        menu.setSizeUndefined();
        menu.addClassName("menu");
        add(menu);

        IntStream.range(1, 6).forEach(i -> menu.add(createLinkButton()));

        final FlexLayout blockLayout = new FlexLayout();
        blockLayout.addClassName("responsivelayout");
        blockLayout.getElement().getStyle().set("overflow", "auto");
        blockLayout.setWidth("100%");
        blockLayout.add(createBlock("red"));
        blockLayout.add(createBlock("blue"));
        blockLayout.add(createBlock("yellow"));
        blockLayout.add(createBlock("green"));
        Component block = createBlock("black");
        ((HasStyle) block).addClassName("double");
        blockLayout.add(block);
        block = createBlock("gray");
        ((HasStyle)block).addClassName("double");
        blockLayout.add(block);

        add(blockLayout);
        expand(blockLayout);
    }

    private Button createLinkButton() {
        Icon icon = VaadinIcon.values()[rng.nextInt(VaadinIcon.values().length)].create();
        icon.getElement().setProperty("slot", "prefix");

        final Button anchor = new Button("Anchor " + linknum++);
        anchor.getElement().setAttribute("theme", "tertiary");
        anchor.addClassName("linkbtn");
        anchor.getElement().appendChild(icon.getElement());
        return anchor;
    }

    private Component createBlock(String color) {
        final Div block = new Div();
        block.setHeight("250px");
        block.addClassName("block");
        block.addClassName(color);
        return block;
    }

}