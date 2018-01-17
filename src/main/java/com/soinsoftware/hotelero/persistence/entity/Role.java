package com.soinsoftware.hotelero.persistence.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
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
@Entity(name = "role")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Role extends CommonData implements Comparable<Role> {

	private static final long serialVersionUID = -8489046504902092980L;

	@NaturalId
	private String name;

	@ManyToOne
	@JoinColumn(name = "idhotel")
	private Hotel hotel;

	@Transient
	private String newName;

	@Transient
	private boolean delete;

	public Role() {
		super();
	}

	public Role(final String name, final Date creation, final Date updated, final boolean enabled, final Hotel hotel) {
		super(creation, updated, enabled);
		this.name = name;
		this.hotel = hotel;
	}

	@Override
	public int compareTo(final Role other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}