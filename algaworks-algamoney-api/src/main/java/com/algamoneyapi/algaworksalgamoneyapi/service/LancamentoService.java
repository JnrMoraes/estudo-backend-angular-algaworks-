package com.algamoneyapi.algaworksalgamoneyapi.service;

import com.algamoneyapi.algaworksalgamoneyapi.model.Lancamento;
import com.algamoneyapi.algaworksalgamoneyapi.model.Pessoa;
import com.algamoneyapi.algaworksalgamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.algaworksalgamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.algaworksalgamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

// ver pq não retorno a exception 5.6. Regra para não salvar pessoa inativa
    public Lancamento saveLancamentoService(Lancamento lancamento) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

        if(pessoa == null || pessoa.isPresent()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
}

