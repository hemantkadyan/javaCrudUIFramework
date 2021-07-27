package com.example.crudwithvaadin.views;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import org.apache.commons.lang3.StringUtils;

public class MainView extends VerticalLayout {

	public MainView() {
		createHeader(null);
	}
	public MainView(String headerTitle) {
		createHeader(headerTitle);
	}
	private void createHeader(String title) {
		H1 logo = new H1("Edit Table Util");
		if(!StringUtils.isEmpty(title)){
			logo = new H1(title);
		}
		logo.addClassName("logo");
		Anchor logout = new Anchor("logout", "Log out");
		Anchor home = new Anchor("dbutil","Home");
		HorizontalLayout header = new HorizontalLayout(logo, home,logout);
		header.expand(logo);
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.setWidth("100%");
		header.addClassName("header");

		add(header);
	}

}
