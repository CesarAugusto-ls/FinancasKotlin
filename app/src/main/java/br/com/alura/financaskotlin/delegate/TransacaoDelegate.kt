package br.com.alura.financaskotlin.delegate

import br.com.alura.financaskotlin.model.Transacao

interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}