package com.trackAgile.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.ListItems;
import com.trackAgile.repository.ListItemsRepository;
import com.trackAgile.service.ListItemsService;

@Service
public class ListItemsServiceImpl implements ListItemsService {

	@Autowired
	private ListItemsRepository listItemsRepo;

	@Override
	public void createListItemsForListSetup(String listName, List<String> listItems) {

		for (String li : listItems) {
			ListItems listItem = new ListItems();
			listItem.setListName(listName);
			listItem.setListItem(li);
			listItem.setStatus("Active");
			listItemsRepo.save(listItem);
		}

	}

}
