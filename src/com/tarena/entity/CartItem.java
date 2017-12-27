package com.tarena.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartItem implements Serializable {
	private Computer computer;
	private int qty = 1;
	private boolean hasBuyed = true;

	public CartItem() {

	}

	public CartItem(Computer computer, int qty, boolean hasBuyed) {
		super();
		this.computer = computer;
		this.qty = qty;
		this.hasBuyed = hasBuyed;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public boolean isHasBuyed() {
		return hasBuyed;
	}

	public void setHasBuyed(boolean hasBuyed) {
		this.hasBuyed = hasBuyed;
	}


}
