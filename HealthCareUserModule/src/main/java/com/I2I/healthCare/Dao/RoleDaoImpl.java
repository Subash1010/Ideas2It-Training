package com.I2I.healthCare.Dao;

import java.util.Objects;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Models.RoleEntity;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManger;

	Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	Consumer<String> errorConsumer = exception -> logger.error(exception);

	@Override
	public String addNewRole(RoleEntity roleEntity) {
		try {
			entityManger.persist(roleEntity);
		} catch (Exception exception) {
			logger.error("Error in Addition of new record");
			errorConsumer.accept(exception.toString());
			return "Record not Added";
		}
		return "Record Added Successfully!!!";
	}

	@Override
	public RoleEntity updateRole(RoleEntity roleEntity) {
		try {
			RoleEntity existingRoleEntity = getRoleById(roleEntity.getRoleId());
			if (Objects.nonNull(existingRoleEntity)) {
				existingRoleEntity.setRoleId(roleEntity.getRoleId());
				existingRoleEntity.setRoleName(roleEntity.getRoleName());
				return entityManger.merge(roleEntity);
			} else {
				errorConsumer.accept("No Record is found for Updation");
				return null;
			}
		} catch (Exception exception) {
			logger.error("Error in Updation of the record" + exception);
			errorConsumer.accept(exception.toString());
			return null;
		}
	}

	@Override
	public String deleteRoleById(long roleId) {
		try {
			RoleEntity existingRole = getRoleById(roleId);
			if (Objects.nonNull(existingRole)) {
				entityManger.remove(existingRole);
			} else {
				return "Record with Role Id " + roleId + " is not found for Deletion";
			}
		} catch (Exception exception) {
			logger.error("Error in Deletion of the record with Role Id " + roleId + StringUtils.EMPTY + exception);
			return "Record with Role Id  " + roleId + "is not Deleted";
		}

		return "Record with Role Id " + roleId + " Deleted Successfully!!!";
	}

	@Override
	public RoleEntity getRoleById(long roleId) {
		try {
			RoleEntity response = (RoleEntity) entityManger.find(RoleEntity.class, roleId);
			return Objects.nonNull(response) ? response : null;
		} catch (Exception exception) {
			logger.error("Error in Fetching of the record with Role Id " + roleId + StringUtils.EMPTY + exception);
		}
		return null;
	}

}
