package com.example.crudwithvaadin.editors;

import com.example.crudwithvaadin.entities.SecureEntity;
import com.example.crudwithvaadin.security.SecurityUtils;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GeneralEditor<T extends SecureEntity<U>, V extends JpaRepository<T, U>, U> extends VerticalLayout implements KeyNotifier {

    private V repository;

    private T entity;

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    Binder<T> binder;
    public Binder<T> getBinder() {
        return binder;
    }

    public void setBinder(Binder<T> binder) {
        this.binder = binder;
    }


    private ChangeHandler changeHandler;


    public GeneralEditor(V repository) {

        this.repository = repository;
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editEntity(entity));
        setVisible(false);
    }

    public void delete() {
        repository.delete(entity);
        changeHandler.onChange();
    }

    public void save() {
        if(SecurityUtils.isUserAdmin()) {
            entity.setUsername(SecurityUtils.getLoggedInUser());
            repository.save(entity);
            changeHandler.onChange();
        }
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editEntity(T c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            entity = repository.findById(c.getId()).get();
        }
        else {
            entity = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(entity);

        setVisible(true);

        // Focus first name initially
        //firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    public V getRepository() {
        return repository;
    }
    public void addEditorButtons(){
        add(actions);
    }

}
