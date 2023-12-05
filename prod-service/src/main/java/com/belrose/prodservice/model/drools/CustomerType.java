package com.belrose.prodservice.model.drools;

public enum CustomerType {
	LOYAL, NEW, DISSATISFIED;

	public String getValue() {
		return this.toString();
	}
}