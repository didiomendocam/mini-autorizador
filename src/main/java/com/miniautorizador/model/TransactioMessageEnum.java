package com.miniautorizador.model;

public enum TransactioMessageEnum {
	OK("COMPLETADA"),
	SALDO_INSUFICIENTE("SALDO_INSUFICIENTE"), 
	SENHA_INVALIDA("SENHA_INVALIDA"), 
	CARTAO_INEXISTENTE("CARTAO_INEXISTENTE");

	private String value;

	private TransactioMessageEnum(String descricao) {
		this.setValue(descricao);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String estado) {
		this.value = estado;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
