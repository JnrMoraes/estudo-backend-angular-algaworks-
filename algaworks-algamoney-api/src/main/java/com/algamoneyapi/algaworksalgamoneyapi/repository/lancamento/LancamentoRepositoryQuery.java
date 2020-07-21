package com.algamoneyapi.algaworksalgamoneyapi.repository.lancamento;

import com.algamoneyapi.algaworksalgamoneyapi.model.Lancamento;
import com.algamoneyapi.algaworksalgamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
