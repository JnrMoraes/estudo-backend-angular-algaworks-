package com.algamoneyapi.algaworksalgamoneyapi.resource;

import com.algamoneyapi.algaworksalgamoneyapi.event.RecursoCriadoEvent;
import com.algamoneyapi.algaworksalgamoneyapi.exceptionhandler.AlgamoneyExceptionHandler;
import com.algamoneyapi.algaworksalgamoneyapi.model.Lancamento;
import com.algamoneyapi.algaworksalgamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.algaworksalgamoneyapi.repository.filter.LancamentoFilter;
import com.algamoneyapi.algaworksalgamoneyapi.service.LancamentoService;
import com.algamoneyapi.algaworksalgamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
      return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Lancamento> findByLancamentoCode(@PathVariable Long codigo){
      return this.lancamentoRepository.findById(codigo)
              .map(lancamento -> ResponseEntity.ok(lancamento))
              .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> createLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
      Lancamento saveLancamento = lancamentoService.saveLancamentoService(lancamento);
      publisher.publishEvent( new RecursoCriadoEvent(this, response, saveLancamento.getCodigo()));
      return ResponseEntity.status(HttpStatus.CREATED).body(saveLancamento);

    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
        String mensagemUsuario =  messageSource.getMessage("pessoa.inativa-ou-inexitente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLancamento(@PathVariable Long codigo){
        this.lancamentoRepository.deleteById(codigo);
    }


}
