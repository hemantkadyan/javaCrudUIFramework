package com.example.crudwithvaadin.repository;

import com.example.crudwithvaadin.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {

	//List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);

}
