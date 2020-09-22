package com.algamoneyapi.algaworksalgamoneyapi.service;

import com.algamoneyapi.algaworksalgamoneyapi.model.Lancamento;
import com.algamoneyapi.algaworksalgamoneyapi.model.Pessoa;
import com.algamoneyapi.algaworksalgamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.algaworksalgamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.algaworksalgamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

        if(!pessoaOptional.isPresent() || pessoaOptional.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar(Long codigo, Lancamento lancamento){
        Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);

        if(!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())){
            validarPessoa(lancamento);
        }
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

        return lancamentoRepository.save(lancamentoSalvo);
    }

    private void validarPessoa(Lancamento lancamento){

        Optional<Pessoa> pessoaOptional = null;

        if (lancamento.getPessoa().getCodigo() != null){
            pessoaOptional = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        }

        if (!pessoaOptional.isPresent() || pessoaOptional.isEmpty() || pessoaOptional.get().isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }

    }

    private Lancamento buscarLancamentoExistente(Long codigo){
        Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(codigo);

        return lancamentoOptional.orElseThrow(IllegalArgumentException::new);
    }
}

