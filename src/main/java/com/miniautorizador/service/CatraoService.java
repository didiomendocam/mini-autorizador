package com.miniautorizador.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniautorizador.exception.ResourceNotFoundException;
import com.miniautorizador.exception.ValidationException;
import com.miniautorizador.model.Cartao;
import com.miniautorizador.model.TransactioMessageEnum;
import com.miniautorizador.repository.CartaoRepository;

@Service
public class CatraoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	public Optional<Cartao> getCartao(Long numeroCartao) {
		return cartaoRepository.findById(numeroCartao);
	}

	public Cartao save(Cartao cartao) {
		cartao.setValor(new BigDecimal(500.00));
		return cartaoRepository.save(cartao);
	}

	public TransactioMessageEnum transacaoCartao(Cartao cartao) {
		TransactioMessageEnum transactioMessageEnum;
		Optional<Cartao> cartaoSaldo = cartaoRepository.findById(cartao.getNumeroCartao());
		transactioMessageEnum = cartaoSaldo.equals(null) || cartaoSaldo.isEmpty()
				? TransactioMessageEnum.CARTAO_INEXISTENTE
				: TransactioMessageEnum.OK;

		transactioMessageEnum = cartaoSaldo.get().getSenhaCartao().equals(cartao.getSenhaCartao())
				? TransactioMessageEnum.OK
				: TransactioMessageEnum.SENHA_INVALIDA;

		return transactioMessageEnum; 
	}

	private TransactioMessageEnum verificaSaldo(Cartao cartaoSaldo, BigDecimal valor) {
		TransactioMessageEnum transactioMessageEnum = null;
		BigDecimal saldoCartao = cartaoSaldo.getValor().subtract(valor);

//		transactioMessageEnum = saldoCartao >= new BigDecimal("0") 
//				? TransactioMessageEnum.OK
//				: TransactioMessageEnum.SENHA_INVALIDA;

		return transactioMessageEnum.OK;
	}
}
