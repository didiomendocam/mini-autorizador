package com.miniautorizador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.miniautorizador.model.Cartao;

@Repository
public interface CartaoRepository extends MongoRepository<Cartao, Long> {

}
