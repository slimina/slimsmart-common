package cn.slimsmart.common.entity;

import java.util.Date;

public abstract class BaseEntity extends BaseSerializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String creatorId;
	private Date createDate;
	private String editorId;
	private Date updateDate;

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
}
