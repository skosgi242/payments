package com.hometask.payments.Model;

public class Transfer {

	private String fromAcc;
	private String toAcc;
	private int amount;
	public String getFromAcc() {
		return fromAcc;
	}
	public void setFromAcc(String fromAcc) {
		this.fromAcc = fromAcc;
	}
	public String getToAcc() {
		return toAcc;
	}
	public void setToAcc(String toAcc) {
		this.toAcc = toAcc;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
