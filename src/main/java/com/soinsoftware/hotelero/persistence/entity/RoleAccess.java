package com.soinsoftware.hotelero.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "rolexaccess")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class RoleAccess extends CommonData {

	private static final long serialVersionUID = 5291932332851357957L;

	@ManyToOne
	@JoinColumn(name = "idrole")
	private Role role;

	@ManyToOne
	@JoinColumn(name = "idaccess")
	private Access access;

	@Transient
	private boolean delete;
}