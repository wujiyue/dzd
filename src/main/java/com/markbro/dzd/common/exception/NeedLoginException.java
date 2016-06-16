package com.markbro.dzd.common.exception;

public class NeedLoginException extends Exception {
	private static final long serialVersionUID = 3301108514499100057L;

	public NeedLoginException() {
	}

	public NeedLoginException(String msg) {
		super(msg);
	}
}