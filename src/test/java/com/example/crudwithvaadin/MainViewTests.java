package com.example.crudwithvaadin;

import java.util.List;

import javax.annotation.PostConstruct;

import com.example.crudwithvaadin.editors.CustomerEditor;
import com.example.crudwithvaadin.entities.Customer;
import com.example.crudwithvaadin.repository.CustomerRepository;
import com.example.crudwithvaadin.views.MainView;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@SpringBootTest(classes = MainViewTests.Config.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MainViewTests {

	@Autowired
	CustomerRepository repository;

	VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);

	CustomerEditor editor;

	MainView mainView;

	@BeforeEach
	public void setup() {
		this.editor = new CustomerEditor(this.repository);
		this.mainView = new MainView();
	}

	@Test
	public void shouldInitializeTheGridWithCustomerRepositoryData() {
		int customerCount = (int) this.repository.count();

		//then(mainView.grid.getColumns()).hasSize(3);
		then(getCustomersInGrid()).hasSize(customerCount);
	}

	private List<Customer> getCustomersInGrid() {
		//ListDataProvider<Customer> ldp = (ListDataProvider) mainView.grid.getDataProvider();
		//return new ArrayList<>(ldp.getItems());
		return null;
	}

	@Test
	public void shouldFillOutTheGridWithNewData() {
		int initialCustomerCount = (int) this.repository.count();

		customerDataWasFilled(editor, "Marcin", "Grzejszczak");

		this.editor.save();

		then(getCustomersInGrid()).hasSize(initialCustomerCount + 1);

		then(getCustomersInGrid().get(getCustomersInGrid().size() - 1))
			.extracting("firstName", "lastName")
			.containsExactly("Marcin", "Grzejszczak");

	}

	@Test
	public void shouldFilterOutTheGridWithTheProvidedLastName() {

		this.repository.save(new Customer("Josh", "Long", 5));

		//mainView.listCustomers("Long");

		then(getCustomersInGrid()).hasSize(1);
		then(getCustomersInGrid().get(getCustomersInGrid().size() - 1))
			.extracting("firstName", "lastName")
			.containsExactly("Josh", "Long");
	}

	@Test
	public void shouldInitializeWithInvisibleEditor() {

		then(this.editor.isVisible()).isFalse();
	}

	@Test
	public void shouldMakeEditorVisible() {
		Customer first = getCustomersInGrid().get(0);
		//this.mainView.grid.select(first);

		then(this.editor.isVisible()).isTrue();
	}

	private void customerDataWasFilled(CustomerEditor editor, String firstName,
                                       String lastName) {
		//this.editor.firstName.setValue(firstName);
		//this.editor.lastName.setValue(lastName);
		editor.editEntity(new Customer(firstName, lastName, 5));
	}

	@Configuration
	@EnableAutoConfiguration(exclude = com.vaadin.flow.spring.SpringBootAutoConfiguration.class)
	static class Config {

		@Autowired
		CustomerRepository repository;

		@PostConstruct
		public void initializeData() {
			int age =6;
			this.repository.save(new Customer("Jack", "Bauer", age));
			this.repository.save(new Customer("Chloe", "O'Brian", age));
			this.repository.save(new Customer("Kim", "Bauer", age));
			this.repository.save(new Customer("David", "Palmer", age));
			this.repository.save(new Customer("Michelle", "Dessler", age));
		}
	}
}
