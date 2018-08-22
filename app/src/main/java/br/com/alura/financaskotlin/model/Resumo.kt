package br.com.alura.financaskotlin.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    fun receita() : BigDecimal{
        var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.Tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }
        return totalReceita
    }

    fun despesa() : BigDecimal{
        var totalDespesa = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.Tipo == Tipo.DESPESA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }
        return totalDespesa
    }

    fun total() : BigDecimal{
        return receita().subtract(despesa())
    }

}