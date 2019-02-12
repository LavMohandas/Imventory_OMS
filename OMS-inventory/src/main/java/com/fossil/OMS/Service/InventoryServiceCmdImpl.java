package com.fossil.OMS.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fossil.OMS.Controller.InventoryController;
import com.fossil.OMS.Daos.SFCCInventoryJdbcRepository;
import com.fossil.OMS.Model.SFCCInventory;


@Service
public class InventoryServiceCmdImpl implements InventoryService{

	
	@Autowired
	 SFCCInventoryJdbcRepository inventoryRepository;
	private static final Logger LOG = Logger.getLogger(InventoryController.class.getName());

	@Override
	public String updateInventory(SFCCInventory inv) {
		// TODO Auto-generated method stub
		if((inventoryRepository.update(inv)) ==1){
			LOG.log(Level.INFO, "Updated Inventory at SFCC successfully");
			return "Successfully Updated Inventory at OMS for catentryId!" +inv.getCatentryId();
		}
		
		
		return "Sorry,there was an error in updating inventory for catentryId at OMS. Please check the data. CatentryId:" +inv.getCatentryId();
	}
	 
}
