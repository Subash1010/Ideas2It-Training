package com.I2I.healthCare.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {

	private static final long serialVersionUID = -7134195975158556179L;

	private long roleId;

	private String roleName;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updatedAt;

}
