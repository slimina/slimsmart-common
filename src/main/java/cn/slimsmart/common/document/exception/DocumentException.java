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
