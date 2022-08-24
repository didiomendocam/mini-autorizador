package com.miniautorizador.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.miniautorizador.model.Cartao;
import com.miniautorizador.service.CatraoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaoController.class);

	@Autowired
	private CatraoService cartaoService;

	@GetMapping
	public ResponseEntity<?> getCartao(@PathVariable("numeroCartao") Long numeroCartao) {
		try {
			LOGGER.info("Cartao findById..: " + numeroCartao);
			Optional<Cartao> cartao = cartaoService.getCartao(numeroCartao);

			return ResponseEntity.ok(cartao);
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found", exc);
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Cartao cartao) {
		try {
			LOGGER.info("Cartao save.: " + cartao.getNumeroCartao());
			cartaoService.save(cartao);
			LOGGER.info("Cartao .: " + cartao.getNumeroCartao() + " salvo com sucesso !!!");
			return ResponseEntity.ok(cartao);
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found", exc);
		}

	}

	@PostMapping("/transacoes")
	public ResponseEntity<?> transacao(@RequestBody Cartao cartao) {
		try {
			LOGGER.info("Cartao transacao.: " + cartao.getNumeroCartao());
			cartaoService.transacaoCartao(cartao);
			LOGGER.info("Cartao .: " + cartao.getNumeroCartao() + " salvo com sucesso !!!");
			return ResponseEntity.ok(cartao);
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found", exc);
		}
	}
}
