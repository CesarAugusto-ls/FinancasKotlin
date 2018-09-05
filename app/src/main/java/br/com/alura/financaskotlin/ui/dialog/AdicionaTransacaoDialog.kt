package br.com.alura.financaskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financaskotlin.R
import br.com.alura.financaskotlin.model.Tipo

class AdicionaTransacaoDialog(private val viewGroup: ViewGroup,
                              private val context: Context) : FormularioTransacaoDialog(viewGroup = viewGroup, context = context) {

    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        } else {
            return R.string.adiciona_despesa
        }
    }
}