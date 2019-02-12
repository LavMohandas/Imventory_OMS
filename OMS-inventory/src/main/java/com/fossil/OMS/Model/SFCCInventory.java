package com.fossil.OMS.Model;

import javax.persistence.Column;
import javax.persistence.Id;

public class SFCCInventory {
	
	 @Id
	  @Column( name = "sfcc_catentryId" )
	private Long sfcc_catentryId;
	 
	 @Column( name = "sfcc_quantity" )
	private int sfcc_quantity;
	 
	 @Column( name = "sfcc_storeId" )
	private int sfcc_storeId;

	public SFCCInventory() {
		super();
	}

	public SFCCInventory(Long catentryId, int quantity, int storeId) {
		super();
		this.sfcc_catentryId = catentryId;
		this.sfcc_quantity = quantity;
		this.sfcc_storeId = storeId;
	}

	
	public Long getCatentryId() {
		return sfcc_catentryId;
	}

	public void setCatentryId(Long id) {
		this.sfcc_catentryId = id;
	}

	public int getQuantity() {
		return sfcc_quantity;
	}

	public void setQuantity(int quantity) {
		this.sfcc_quantity = quantity;
	}

	public int getStoreId() {
		return sfcc_storeId;
	}

	public void setStoreId(int storeId) {
		this.sfcc_storeId = storeId;
	}

	@Override
	public String toString() {
		return String.format("Inventory [CatentryId=%s, Quantity=%s, StoreId=%s]", sfcc_catentryId, sfcc_quantity, sfcc_storeId);
	}




}
