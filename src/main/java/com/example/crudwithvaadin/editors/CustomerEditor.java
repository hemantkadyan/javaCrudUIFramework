package com.example.crudwithvaadin.editors;

import com.example.crudwithvaadin.editors.GeneralEditor;
import com.example.crudwithvaadin.entities.Customer;
import com.example.crudwithvaadin.repository.CustomerRepository;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CustomerEditor extends GeneralEditor<Customer, CustomerRepository,Long> {

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    IntegerField age = new IntegerField("Age");

    private final Binder<Customer> binder = new Binder<>(Customer.class);
    @Autowired
    public CustomerEditor(CustomerRepository repository) {
        super(repository);
        setBinder(binder);
        add(firstName,lastName,age);
        binder.bindInstanceFields(this);
    }
}
