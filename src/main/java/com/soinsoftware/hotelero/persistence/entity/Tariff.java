package com.soinsoftware.hotelero.persistence.entity;

import javax.persistence.Column;
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
 * @since 02/08/2017
 * @since 1.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tariff")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Tariff extends CommonData {

	private static final long serialVersionUID = 3173778659449996444L;

	@NaturalId
	private String code;

	private String name;

	@Column(name = "mintariff")
	private double minTariff;

	private double tariff;

	@ManyToOne
	@JoinColumn(name = "idhotel")
	private Hotel hotel;

	@Transient
	private String newcode;

	@Transient
	private String newName;

	@Transient
	private boolean delete;
}