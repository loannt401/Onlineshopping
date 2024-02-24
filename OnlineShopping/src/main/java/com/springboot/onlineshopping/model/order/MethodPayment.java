package com.springboot.onlineshopping.model.order;

public class MethodPayment {
    private int methodPayID;
    private String nameMethod;
	public MethodPayment(int methodPayID, String nameMethod) {
		super();
		this.methodPayID = methodPayID;
		this.nameMethod = nameMethod;
	}
	public int getMethodPayID() {
		return methodPayID;
	}
	public void setMethodPayID(int methodPayID) {
		this.methodPayID = methodPayID;
	}
	public String getNameMethod() {
		return nameMethod;
	}
	public void setNameMethod(String nameMethod) {
		this.nameMethod = nameMethod;
	}
	
    
}
