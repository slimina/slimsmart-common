package cn.slimsmart.common.util.exception;

/**
 * 异常类
 * @author Zhu.TW
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	public ServiceException(final String message) {
		super(message);
	}

	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ServiceException(final Throwable cause) {
		super(cause);
	}
}
