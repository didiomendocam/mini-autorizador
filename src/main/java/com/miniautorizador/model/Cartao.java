package com.miniautorizador.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {
	@Id
	Long numeroCartao;
	String senhaCartao;
	BigDecimal valor;
}
