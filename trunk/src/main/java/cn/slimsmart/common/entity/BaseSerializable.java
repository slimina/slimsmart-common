package cn.slimsmart.common.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class BaseSerializable implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int MAX_HASH_CODE = 37;
	private static final int MIN_HASH_CODE = 17;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	protected ToStringBuilder toStringBuilder() {
		return new ToStringBuilder(ToStringStyle.SIMPLE_STYLE);
	}

	protected HashCodeBuilder hashCodeBuilder() {
		return new HashCodeBuilder(MIN_HASH_CODE, MAX_HASH_CODE);
	}

	protected boolean isEquals(Object src, Object obj) {
		boolean isEqual = true;
		if (src == obj) {
			return isEqual;
		}
		if ((obj == null) || (src == null)) {
			isEqual = false;
		}
		if (src.getClass() != obj.getClass()) {
			isEqual = false;
		}
		return isEqual;
	}
}
