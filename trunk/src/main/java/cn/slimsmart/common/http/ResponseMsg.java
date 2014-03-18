package cn.slimsmart.common.http;

/**
 * 响应ajax消息对象
 * 
 * @author Zhu.TW
 * 
 */
public class ResponseMsg {
	
	private boolean result;
	private String msg;
	private String code;
	private Object data;

	public ResponseMsg success() {
		this.result = true;
		return this;
	}

	public ResponseMsg error() {
		this.result = false;
		return this;
	}

	public ResponseMsg success(String msg) {
		this.result = true;
		this.msg = msg;
		return this;
	}

	public ResponseMsg error(String msg) {
		this.result = false;
		this.msg = msg;
		return this;
	}

	public boolean isResult() {
		return result;
	}

	public ResponseMsg setResult(boolean result) {
		this.result = result;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseMsg setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResponseMsg setData(Object data) {
		this.data = data;
		return this;
	}

	public String getCode() {
		return code;
	}

	public ResponseMsg setCode(String code) {
		this.code = code;
		return this;
	}
}
