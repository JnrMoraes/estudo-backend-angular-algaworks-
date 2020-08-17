package com.algamoneyapi.algaworksalgamoneyapi.resource;

import com.algamoneyapi.algaworksalgamoneyapi.event.RecursoCriadoEvent;
import com.algamoneyapi.algaworksalgamoneyapi.model.Pessoa;
import com.algamoneyapi.algaworksalgamoneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.algamoneyapi.algaworksalgamoneyapi.service.PessoaService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa>listPessoa(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaCreated =  pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaCreated.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCreated);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> findByPessoaCode(@PathVariable Long codigo){
        return this.pessoaRepository.findById(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
    public void deletePessoa(@PathVariable Long codigo){
        this.pessoaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable  Long codigo, @Valid @RequestBody Pessoa pessoa){
        return ResponseEntity.ok(pessoaService.update(codigo,pessoa));
        }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public void updatePropertAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
        pessoaService.updatePropertAtivo(codigo,ativo);
    }
}
