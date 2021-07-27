package com.example.crudwithvaadin.editors;


import com.example.crudwithvaadin.repository.ItemRepository;
import com.example.crudwithvaadin.entities.Item;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ItemEditor extends GeneralEditor<Item, ItemRepository,Long>{

    TextField itemName = new TextField("Item Name");
    TextField itemCategory = new TextField("Item Category");
    IntegerField price = new IntegerField("Price");
    private final Binder<Item> binder = new Binder<>(Item.class);
    @Autowired
    public ItemEditor(ItemRepository repository) {
        super(repository);
        setBinder(binder);
        add(itemName,itemCategory,price);
        binder.bindInstanceFields(this);
    }

}
