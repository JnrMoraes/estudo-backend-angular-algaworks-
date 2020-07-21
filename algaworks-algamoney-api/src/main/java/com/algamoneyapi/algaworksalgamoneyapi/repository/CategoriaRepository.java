package com.algamoneyapi.algaworksalgamoneyapi.repository;

import com.algamoneyapi.algaworksalgamoneyapi.model.Categoria;
import com.algamoneyapi.algaworksalgamoneyapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
}
