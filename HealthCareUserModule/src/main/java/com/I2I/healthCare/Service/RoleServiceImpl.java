package com.I2I.healthCare.Service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.I2I.healthCare.Dao.RoleDao;
import com.I2I.healthCare.Dto.RoleDto;
import com.I2I.healthCare.Models.RoleEntity;

@Service
public class RoleServiceImpl implements RoleService {

	@Lazy
	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}

	private final RoleDao roleDao;

	@Override
	public String addNewRole(RoleDto roleDto) {
		if (Objects.nonNull(roleDto)) {
			return roleDao.addNewRole(RoleDto.convertToRoleEntity(roleDto));
		}
		System.out.println("Error in Addition of new record - Empty Record Can't be Added");
		return "Record not Added";
	}

	@Override
	public String updateRole(RoleDto roleDto) {
		if (Objects.nonNull(roleDto)) {
			return roleDao.updateRole(RoleDto.convertToRoleEntity(roleDto));
		}
		System.out.println("Error in Updation of new record - Empty Record Can't be Updated");
		return "Record not Updated";
	}

	@Override
	public String deleteRoleById(long roleId) {
		return roleDao.deleteRoleById(roleId);
	}

	@Override
	public RoleDto getRoleById(long roleId) {
		RoleEntity roleEntity = roleDao.getRoleById(roleId);
		if (Objects.nonNull(roleEntity)) {
			return RoleDto.convertToRoleDto(roleEntity);
		} else {
			return new RoleDto();
		}

	}

}
