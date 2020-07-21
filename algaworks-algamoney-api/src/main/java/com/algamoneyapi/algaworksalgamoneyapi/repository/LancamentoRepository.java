package com.algamoneyapi.algaworksalgamoneyapi.repository;

import com.algamoneyapi.algaworksalgamoneyapi.model.Lancamento;
import com.algamoneyapi.algaworksalgamoneyapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository <Lancamento, Long >, LancamentoRepositoryQuery {


}
