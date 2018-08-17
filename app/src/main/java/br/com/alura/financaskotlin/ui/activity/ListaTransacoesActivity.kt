package br.com.alura.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.alura.financaskotlin.R
import br.com.alura.financaskotlin.model.Tipo
import br.com.alura.financaskotlin.model.Transacao
import br.com.alura.financaskotlin.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(
                Transacao(valor = BigDecimal(21.5),
                        Tipo = Tipo.DESPESA),
                Transacao(valor = BigDecimal(100.0),
                        categoria = "Economia",
                        Tipo = Tipo.RECEITA),
                Transacao(valor = BigDecimal(30.0),
                        Tipo = Tipo.DESPESA,
                        categoria = "Lazer"))

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }


}