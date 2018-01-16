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
 * @since 18/07/2016
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "servicetype")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class ServiceType extends CommonData implements Comparable<ServiceType> {

	private static final long serialVersionUID = 7265381353242674545L;

	@NaturalId
	private String name;

	@ManyToOne
	@JoinColumn(name = "idhotel")
	private Hotel hotel;

	@Transient
	private String newName;

	@Transient
	private boolean delete;

	public ServiceType() {
		super();
	}

	public ServiceType(final String name, final Date creation, final Date updated, final boolean enabled,
			final Hotel hotel) {
		super(creation, updated, enabled);
		this.name = name;
		this.delete = false;
		this.hotel = hotel;
	}

	@Override
	public int compareTo(final ServiceType other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}