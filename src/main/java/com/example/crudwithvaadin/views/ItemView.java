package com.example.crudwithvaadin.views;

import com.example.crudwithvaadin.editors.ItemEditor;
import com.example.crudwithvaadin.entities.Item;
import com.example.crudwithvaadin.repository.ItemRepository;
import com.vaadin.flow.router.Route;

@Route(ItemView.routeName)
public class ItemView extends GeneralTableView<Item,ItemRepository,ItemEditor> {

    public static final String routeName = "dbutilitem";

    public ItemView(ItemEditor editor){
        super(Item.class, "id", "itemName", "itemCategory","price","username");
        initView(editor.getRepository(),editor,Item.class);
    }

}
