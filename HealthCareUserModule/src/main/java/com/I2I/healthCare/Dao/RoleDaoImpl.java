package com.I2I.healthCare.Dao;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Models.RoleEntity;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManger;

	@Override
	public String addNewRole(RoleEntity roleEntity) {
		try {
			entityManger.persist(roleEntity);
		} catch (Exception exception) {
			System.out.println("Error in Addition of new record" + exception);
			return "Record not Added";
		}
		return "Record Added Successfully!!!";
	}

	@Override
	public String updateRole(RoleEntity roleEntity) {
		try {
			entityManger.merge(roleEntity);
		} catch (Exception exception) {
			System.out.println("Error in Updation of the record" + exception);
			return "Record not Updated";
		}
		return "Record Updated Successfully!!!";
	}

	@Override
	public String deleteRoleById(long roleId) {
		try {
			RoleEntity existingRole = getRoleById(roleId);
			if (Objects.nonNull(existingRole)) {
				entityManger.remove(existingRole);
			} else {
				return "Record not found for Deletion";
			}
		} catch (Exception exception) {
			System.out.println("Error in Deletion of the record" + exception);
			return "Record not Deleted";
		}

		return "Record Deleted Successfully!!!";
	}

	@Override
	public RoleEntity getRoleById(long roleId) {
		try {
			RoleEntity response = (RoleEntity) entityManger.find(RoleEntity.class, roleId);
			return Objects.nonNull(response) ? response : null;
		} catch (Exception exception) {
			System.out.println("Error in Fetching of the record" + exception);
		}
		return null;
	}

}
