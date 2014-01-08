package cn.slimsmart.common.base.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 实体基类
 * @author Zhu.TW
 *
 */
public class BaseEntity  implements Serializable {

private static final long serialVersionUID = 4738488394043854741L;
	
	private static final int MAX_HASH_CODE = 37;
	private static final int MIN_HASH_CODE = 17;
	
	protected String id;

	protected String creatorId;
	protected Date createDate;
	protected String editorId;
	protected Date updateDate;
	
	public String getId() {
		return id;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getEditorId() {
		return editorId;
	}

	public void setEditorId(String editorId) {
		this.editorId = editorId == null ? null : editorId.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

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
