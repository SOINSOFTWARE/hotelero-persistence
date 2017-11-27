package com.soinsoftware.hotelero.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

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

	public RoomType() {
		super();
	}

	public RoomType(final String code, final String name) {
		super();
		this.code = code;
		this.name = name;
		this.setEnabled(true);
	}

	@Override
	public int compareTo(final RoomType other) {
		return code.compareToIgnoreCase(other.code);
	}
}