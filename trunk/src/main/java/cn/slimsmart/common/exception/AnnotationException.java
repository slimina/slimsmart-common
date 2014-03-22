package cn.slimsmart.common.exception;

public class AnnotationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AnnotationException() {
	}

	public AnnotationException(final String message) {
		super(message);
	}

	public AnnotationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public AnnotationException(final Throwable cause) {
		super(cause);
	}
}
