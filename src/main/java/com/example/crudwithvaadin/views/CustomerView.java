package com.example.crudwithvaadin.views;

import com.example.crudwithvaadin.editors.CustomerEditor;
import com.example.crudwithvaadin.entities.Customer;
import com.example.crudwithvaadin.repository.CustomerRepository;
import com.vaadin.flow.router.Route;

@Route(CustomerView.routeName)
public class CustomerView extends GeneralTableView<Customer,CustomerRepository, CustomerEditor>{

    public static final String routeName = "dbutilcustomer";

    public CustomerView(CustomerEditor editor) {
        super(Customer.class, "id", "firstName", "lastName","age","username");
        initView(editor.getRepository(),editor,Customer.class);
    }
}
