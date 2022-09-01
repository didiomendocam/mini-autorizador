package com.miniautorizador.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cartao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	Long numeroCartao;
	String senhaCartao;
	BigDecimal valor;
}
