package com.markbro.dzd.common.exception;

public class MethodNotPermitException extends Exception {
	private static final long serialVersionUID = -5353844786837061569L;

	public MethodNotPermitException() {
	}

	public MethodNotPermitException(String msg) {
		super(msg);
	}
}