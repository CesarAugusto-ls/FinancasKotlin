package br.com.alura.financaskotlin.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.financaskotlin.R
import br.com.alura.financaskotlin.extension.formataParaBrasileiro
import br.com.alura.financaskotlin.extension.limitaEmAte
import br.com.alura.financaskotlin.model.Tipo
import br.com.alura.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context) : BaseAdapter(){

    private val LIMITE_DA_CATEGORIA = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        adicionaValor(transacao, viewCriada)
        adicionaIcone(transacao, viewCriada)
        adicionaCategoria(viewCriada, transacao)
        adicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaData(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionaCategoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(LIMITE_DA_CATEGORIA)
    }

    private fun adicionaIcone(transacao: Transacao, viewCriada: View) {
        val icone = iconePorTipo(transacao.Tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePorTipo(tipo: Tipo): Int {
        if (tipo == Tipo.DESPESA) {
            return R.drawable.icone_transacao_item_despesa
        }
        return R.drawable.icone_transacao_item_receita
    }

    private fun adicionaValor(transacao: Transacao, viewCriada: View) {

        var cor: Int = corPorTipo(transacao.Tipo)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPorTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)
    }


    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

}