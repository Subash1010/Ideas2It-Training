package com.I2I.healthCare.Service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.I2I.healthCare.Dao.RoleDao;
import com.I2I.healthCare.Dto.RoleDto;
import com.I2I.healthCare.Models.RoleEntity;
import com.I2I.healthCare.Util.RoleDataUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Lazy
	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}

	private final RoleDao roleDao;

	Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public String addNewRole(RoleDto roleDto) {
		if (Objects.nonNull(roleDto)) {
			return roleDao.addNewRole(RoleDataUtil.convertToRoleEntity(roleDto));
		}
		logger.error("Error in Addition of new record - Empty Record Can't be Added");
		return "Record not Added";
	}

	@Override
	public RoleDto updateRole(RoleDto roleDto) {
		if (Objects.nonNull(roleDto)) {
			RoleEntity roleEntity = roleDao.updateRole(RoleDataUtil.convertToRoleEntity(roleDto));
			return RoleDataUtil.convertToRoleDto(roleEntity);
		}
		logger.error("Error in Updation of new record - Empty Record Can't be Updated");
		return null;
	}

	@Override
	public String deleteRoleById(long roleId) {
		return roleDao.deleteRoleById(roleId);
	}

	@Override
	public RoleDto getRoleById(long roleId) {
		RoleEntity roleEntity = roleDao.getRoleById(roleId);
		if (Objects.nonNull(roleEntity)) {
			return RoleDataUtil.convertToRoleDto(roleEntity);
		} else {
			return new RoleDto();
		}

	}

}
