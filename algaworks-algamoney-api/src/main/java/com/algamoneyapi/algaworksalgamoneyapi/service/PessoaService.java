package com.algamoneyapi.algaworksalgamoneyapi.service;

import com.algamoneyapi.algaworksalgamoneyapi.model.Pessoa;
import com.algamoneyapi.algaworksalgamoneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa update(Long codigo, Pessoa pessoa){
        Pessoa pessoaSaved = findPessoaByCodigo(codigo);

        BeanUtils.copyProperties(pessoa, pessoaSaved,"codigo");
        pessoa.setCodigo(codigo);
        return pessoaRepository.save(pessoaSaved);

    }



    public void updatePropertAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSaved = findPessoaByCodigo(codigo);
        pessoaSaved.setAtivo(ativo);
        pessoaRepository.save(pessoaSaved);
    }

    private Pessoa findPessoaByCodigo(Long codigo) {
        Pessoa pessoaSaved = pessoaRepository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        if(pessoaSaved == null){
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSaved;
    }
}
