package com.soinsoftware.hotelero.persistence.entity;

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
@Entity(name = "roomtype")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class RoomType extends CommonData implements Comparable<RoomType> {

	private static final long serialVersionUID = 3173778659449996444L;

	@NaturalId
	private String code;

	private String name;

	@Transient
	private String newCode;

	@Transient
	private String newName;

	@Transient
	private boolean delete;

	@ManyToOne
	@JoinColumn(name = "idhotel")
	private Hotel hotel;

	public RoomType() {
		super();
	}

	public RoomType(final String code, final String name, final Hotel hotel) {
		super();
		this.code = code;
		this.name = name;
		this.hotel = hotel;
		this.setEnabled(true);
	}

	@Override
	public int compareTo(final RoomType other) {
		return code.compareToIgnoreCase(other.code);
	}
}