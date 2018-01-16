package com.soinsoftware.hotelero.persistence.entity;

import java.util.Date;

import javax.persistence.Entity;
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
@Entity(name = "hotel")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Hotel extends CommonData implements Comparable<Hotel> {

	private static final long serialVersionUID = 6965206549566591919L;

	private String name;

	@NaturalId
	private String nit;

	@Transient
	private String newName;

	@Transient
	private String newNit;

	@Transient
	private boolean delete;

	public Hotel() {
		super();
	}

	public Hotel(final String name, final String nit, final Date creation, final Date updated, final boolean enabled) {
		super(creation, updated, enabled);
		this.name = name;
		this.nit = nit;
	}

	@Override
	public int compareTo(final Hotel other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}