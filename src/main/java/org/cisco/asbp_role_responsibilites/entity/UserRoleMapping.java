package org.cisco.asbp_role_responsibilites.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.cisco.asbp_common_utils.cu.model.CommonStatus;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


/**
 * The persistent class for the user_role_mapping database table.
 * 
 */
@Data
@Entity
@Table(name="user_role_mapping")
@NamedQuery(name="UserRoleMapping.findAll", query="SELECT u FROM UserRoleMapping u")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserRoleMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleMappingPK id;

	@Column(name="created_at")
	private Long createdAt;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="modified_at")
	private Long modifiedAt;

	@Column(name="modified_by")
	private Long modifiedBy;


	@Enumerated(EnumType.STRING)
	private CommonStatus status;

	public UserRoleMapping() {
	}
	
	public UserRoleMapping(Users user, Role role) {
		setId(new UserRoleMappingPK(user,role));
		setStatus(CommonStatus.ENABLED);
		auditCreation(user.getId());
	}
	
	public void auditCreation(Long userId) {
		this.createdAt = System.currentTimeMillis();
		this.modifiedAt = System.currentTimeMillis();
		this.createdBy = userId;
		this.modifiedBy = userId;
	}
	
	public UserRoleMappingPK getId() {
		return this.id;
	}

	public void setId(UserRoleMappingPK id) {
		this.id = id;
	}

	public Long getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Long modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}