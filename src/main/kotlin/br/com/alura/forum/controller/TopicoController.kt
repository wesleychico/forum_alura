package br.com.alura.forum.controller

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.service.TopicoService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val topicoService: TopicoService
) {

    @GetMapping
    fun listar(): List<TopicoView>{
        return topicoService.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return topicoService.buscarPorId(id)

    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid form: NovoTopicoForm) {
        topicoService.cadastrar(form)
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable(required = true) id: Long,
        @RequestBody @Valid form: AtualizacaoTopicoForm
    ) {
        topicoService.atualizar(id, form)

    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable(required = true) id: Long) {
        topicoService.deletar(id)
    }
}