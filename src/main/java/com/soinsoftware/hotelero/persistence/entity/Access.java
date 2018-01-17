package com.soinsoftware.hotelero.persistence.entity;

import javax.persistence.Entity;

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
@Entity(name = "access")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Access extends CommonData implements Comparable<Access> {

	private static final long serialVersionUID = 3879241718828639979L;

	@NaturalId
	private String code;

	private String description;

	public Access() {
		super();
	}

	@Override
	public int compareTo(final Access other) {
		final String firstCode = (this.code != null) ? this.code : "";
		final String secondCode = (other.code != null) ? other.code : "";
		return firstCode.compareToIgnoreCase(secondCode);
	}
}