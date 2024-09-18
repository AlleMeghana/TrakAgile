package com.trackAgile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.ListItems;

@Repository
public interface ListItemsRepository extends JpaRepository<ListItems, Long> {

	List<ListItems> findByListName(String listName);
}
