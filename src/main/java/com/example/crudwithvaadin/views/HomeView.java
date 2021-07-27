package com.example.crudwithvaadin.views;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route(HomeView.routeName)
public class HomeView extends MainView{

    public static final String routeName = "dbutil";

    public HomeView(){
        super("List All Tables");
        Anchor table1 = new Anchor("dbutilcustomer","Customer Table");
        Anchor table2 = new Anchor("dbutilitem","Item Table Table");
        HorizontalLayout header = new HorizontalLayout(table1,table2);
        add(header);
    }

}
