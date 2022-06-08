package com.rba.performance.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "tb_transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACTION")
	@SequenceGenerator(name = "SEQ_TRANSACTION", sequenceName = "SEQ_TRANSACTION", allocationSize = 1)
	private Long id_transaction;
	@Column(nullable = false)
	private String message;

	public Transaction(String message) {
		this.message = message;
	}
}
