package org.example.rest;

import org.example.model.entity.Produto;
import org.example.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("http://localhost:4200")
public class ProdutoController {

    private final ProdutoRepository repository;
    @Autowired
    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody @Valid Produto produto){
        return repository.save(produto);
    }

    @GetMapping
    public List<Produto> obterTodos(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Produto acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não Encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(produto -> {repository.delete(produto); return Void.TYPE;}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar (@PathVariable Integer id, @RequestBody @Valid Produto produtoAtualizado){
        repository.findById(id).map(produto -> {
            produto.setNomeProduto(produtoAtualizado.getNomeProduto());
            produto.setDescricaoProduto(produtoAtualizado.getDescricaoProduto());
            produto.setValorProduto(produtoAtualizado.getValorProduto());
            return repository.save(produto);}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

}
