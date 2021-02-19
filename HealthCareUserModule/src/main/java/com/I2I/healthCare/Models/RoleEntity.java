package com.I2I.healthCare.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 7911446475052134190L;

	@Id
	@Column(name = "role_id")
	private long roleId;

	@Column(name = "role_Name")
	private String roleName;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
