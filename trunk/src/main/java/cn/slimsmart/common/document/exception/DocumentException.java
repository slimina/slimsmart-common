/*
 * All rights Reserved, tianwei7518.
 * Copyright(C) 2014-2015
 * 2013年3月14日 下午2:33:27 
 */
package cn.slimsmart.common.document.exception;

public class DocumentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DocumentException() {
	}

	public DocumentException(final String message) {
		super(message);
	}

	public DocumentException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DocumentException(final Throwable cause) {
		super(cause);
	}
}
