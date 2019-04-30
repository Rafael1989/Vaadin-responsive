package com.vaadin.training.layout.ex2;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.training.layout.MainLayout;

import java.util.Random;
import java.util.stream.IntStream;

@Route(value = ResponsiveLayoutWithVaadinBoard.ROUTE, layout = MainLayout.class)
public class ResponsiveLayoutWithVaadinBoard extends HorizontalLayout {

    private static final long serialVersionUID = 1L;

    public static final String ROUTE = "ex2";
    public static final String TITLE = "Exercise 2";

    private int linknum = 1;
    private final Random rng = new Random();

    public ResponsiveLayoutWithVaadinBoard() {
        setSizeFull();
        addClassName("root-layout");
        setDefaultVerticalComponentAlignment(Alignment.STRETCH);

        final VerticalLayout menu = new VerticalLayout();
        menu.setSizeUndefined();
        menu.addClassName("menu");
        add(menu);

        IntStream.range(1, 6).forEach(i -> menu.add(createLinkButton()));
        
        Board board = new Board();
        
        Component black = createBlock("black");
        ((HasStyle) black).addClassName("double");
        
        Component gray = createBlock("gray");
        ((HasStyle) gray).addClassName("double");
        
        board.addRow(createBlock("red"), createBlock("blue"), createBlock("yellow"), createBlock("green"));
        board.addRow(black, gray);

        final Div scrollWrap = new Div(board);
        scrollWrap.addClassName("scrollwrap");
        add(scrollWrap);
        expand(scrollWrap);
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