package com.algamoneyapi.algaworksalgamoneyapi.resource;

import com.algamoneyapi.algaworksalgamoneyapi.model.Pessoa;
import com.algamoneyapi.algaworksalgamoneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa>listPessoa(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoas(Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaCreated =  pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{pessoas}")
                .buildAndExpand().toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(pessoaCreated);
    }

    @GetMapping("/{pessoas}")
    public ResponseEntity<Pessoa> findByPessoaCode(Long codigo){
        return this.pessoaRepository.findById(codigo)
                .map(pessoa -> ResponseEntity.ok(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

}
