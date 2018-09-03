package br.com.alura.financaskotlin.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import br.com.alura.financaskotlin.R
import br.com.alura.financaskotlin.extension.formataParaBrasileiro
import br.com.alura.financaskotlin.model.Tipo
import br.com.alura.financaskotlin.model.Transacao
import br.com.alura.financaskotlin.ui.ResumoView
import br.com.alura.financaskotlin.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesExemplo()

        configuraResumo(transacoes)
        configuraLista(transacoes)

        lista_transacoes_adiciona_receita.setOnClickListener {
            val view = window.decorView
            val viewCriada = LayoutInflater.from(this)
                    .inflate(R.layout.form_transacao, view as ViewGroup, false)

            val ano = 2018
            val mes = 8
            val dia = 1

            val hoje = Calendar.getInstance()
            viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())
            viewCriada.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            val dataSelecionada = Calendar.getInstance()
                            dataSelecionada.set(year, month, dayOfMonth)
                            viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                        }, ano, mes, dia)
                        .show()
            }

            val adapter = ArrayAdapter
                    .createFromResource(this,
                            R.array.categorias_de_receita,
                            android.R.layout.simple_spinner_dropdown_item)
            viewCriada.form_transacao_categoria.adapter = adapter

            AlertDialog.Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setView(viewCriada)
                    .setPositiveButton("Adicionar",null)
                    .setNegativeButton("Cancelar", null)
                    .show()
        }


    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesExemplo(): List<Transacao> {
        return listOf(
                Transacao(valor = BigDecimal(21.5),
                        Tipo = Tipo.DESPESA),
                Transacao(valor = BigDecimal(100.0),
                        categoria = "Economia",
                        Tipo = Tipo.RECEITA),
                Transacao(valor = BigDecimal(30.0),
                        Tipo = Tipo.DESPESA,
                        categoria = "Lazer"))
    }


}