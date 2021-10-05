package com.hanu.webapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creatAt", "updateAt"}, allowGetters = true)
public abstract class AuditModel {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createAt", nullable = false, updatable = false)
	@CreatedDate
	private Date createAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateAt", nullable = false, updatable = false)
	@LastModifiedDate
	private Date updateAt;


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Date getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	
}
