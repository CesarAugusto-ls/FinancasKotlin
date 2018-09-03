package br.com.alura.financaskotlin.model

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaPor(Tipo.RECEITA)
    val despesa get() = somaPor(Tipo.DESPESA)
    val total get() = receita.subtract(despesa)

    private fun somaPor(tipo: Tipo) : BigDecimal{
        val somaDeTransacoesPorTipo = transacoes
                .filter { it.Tipo == tipo }
                .sumByDouble { it.valor.toDouble() }
        return BigDecimal(somaDeTransacoesPorTipo)
    }
}