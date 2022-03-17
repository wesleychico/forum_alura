package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.streams.toList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    fun listar(): List<TopicoView> {
      return topicos.stream().map { t -> topicoViewMapper.map(t) }.toList()
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico =  topicos.stream().filter { it -> it.id == id }.findFirst().get()

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm) {
        var topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }

    fun atualizar(id: Long, form: AtualizacaoTopicoForm) {

        val topico = topicos.stream().filter{ it -> it.id == id }.findFirst().get()

        topicos = topicos.minus(topico).plus(Topico(
            id = id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            repostas = topico.repostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao,
            dataModificacao = LocalDateTime.now()
        ))
    }


    fun deletar(id: Long) {
        val topico = topicos.stream().filter{ it -> it.id == id }.findFirst().get()

        topicos = topicos.minus(topico)

    }
}