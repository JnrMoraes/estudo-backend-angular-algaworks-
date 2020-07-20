package com.algamoneyapi.algaworksalgamoneyapi.resource;

import com.algamoneyapi.algaworksalgamoneyapi.event.RecursoCriadoEvent;
import com.algamoneyapi.algaworksalgamoneyapi.model.Lancamento;
import com.algamoneyapi.algaworksalgamoneyapi.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

  @GetMapping
    public List<Lancamento> listAllLancamento(){
      return lancamentoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> findByLancamentoCode(@PathVariable Long codigo){
      return this.lancamentoRepository.findById(codigo)
              .map(lancamento -> ResponseEntity.ok(lancamento))
              .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Lancamento> createLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
      Lancamento saveLancamento = lancamentoRepository.save(lancamento);
      publisher.publishEvent( new RecursoCriadoEvent(this, response, saveLancamento.getCodigo()));
      return ResponseEntity.status(HttpStatus.CREATED).body(saveLancamento);

    }

}
