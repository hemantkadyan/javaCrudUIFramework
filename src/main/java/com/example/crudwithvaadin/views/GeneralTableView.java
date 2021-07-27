package com.example.crudwithvaadin.views;

import com.example.crudwithvaadin.editors.GeneralEditor;
import com.example.crudwithvaadin.entities.SecureEntity;
import com.example.crudwithvaadin.security.SecurityUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GeneralTableView<T extends SecureEntity,U extends JpaRepository,V extends GeneralEditor> extends MainView {

        final Grid<T> grid;

        final TextField filter;

        private final Button addNewBtn;

        private U repo;

        private V editor;

        public GeneralTableView(Class<? extends SecureEntity> beanType, String ... columnHeaders) {
            super("Edit "+beanType.getSimpleName()+" Table");
            this.grid = new Grid<>((Class)beanType);
            this.filter = new TextField();
            this.addNewBtn = new Button("New "+beanType.getSimpleName(), VaadinIcon.PLUS.create());

            // build layout
            HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
            initGrid(columnHeaders);
            add(actions, grid);

        }
        private void initGrid(String ... columnHeaders) {
            grid.setHeight("500px");
            grid.setColumns(columnHeaders);
            grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        }
        void initView(U repoParam, V editorParam, Class<? extends SecureEntity> beanType) {
            this.repo = repoParam;
            this.editor = editorParam;
            filter.setPlaceholder("Filter By");
            // Hook logic to components

            // Replace listing with filtered content when user changes filter
            filter.setValueChangeMode(ValueChangeMode.EAGER);
            filter.addValueChangeListener(e -> listEntries(e.getValue()));

            // Connect selected Customer to editor or hide if none is selected
            grid.asSingleSelect().addValueChangeListener(e -> {
                editor.editEntity(e.getValue());
            });

            // Instantiate and edit new Entity the new button is clicked
            addNewBtn.addClickListener(e -> {
                try {

                    Constructor entityConstructor = beanType.getDeclaredConstructor();
                    entityConstructor.setAccessible(true);
                    editor.editEntity((SecureEntity) entityConstructor.newInstance());

                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (NoSuchMethodException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                }
            });

            // Listen changes made by the editor, refresh data from backend
            editor.setChangeHandler(() -> {
                editor.setVisible(false);
                listEntries(filter.getValue());
            });
            if(SecurityUtils.isUserAdmin()){
                add(editor);
                editor.addEditorButtons();
            }

            // Initialize listing
            listEntries(null);


        }

        void listEntries(String filterText) {
            if (StringUtils.isEmpty(filterText)) {
                grid.setItems(repo.findAll());
            }
            else {
                //grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
            }
        }



}
