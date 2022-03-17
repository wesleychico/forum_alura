package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topico(
    var id: Long? = null,
    var titulo: String,
    val mensagem: String,
    val dataCriação: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    val repostas: List<Resposta> = ArrayList()
)