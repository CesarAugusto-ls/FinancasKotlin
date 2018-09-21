package br.com.alura.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.alura.financaskotlin.R
import br.com.alura.financaskotlin.dao.TransacaoDao
import br.com.alura.financaskotlin.model.Tipo
import br.com.alura.financaskotlin.model.Transacao
import br.com.alura.financaskotlin.ui.ResumoView
import br.com.alura.financaskotlin.ui.adapter.ListaTransacoesAdapter
import br.com.alura.financaskotlin.ui.dialog.AdicionaTransacaoDialog
import br.com.alura.financaskotlin.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val dao = TransacaoDao()
    private val transacoes = dao.transacoes
    private val viewDaActivity by lazy {
        window.decorView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogDeAdicao(Tipo.RECEITA)
        }
        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewDaActivity as ViewGroup, this)
                .chama(tipo) {transacaoCriada ->
                    adiciona(transacaoCriada)
                    lista_transacoes_adiciona_menu.close(true)
                }

    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, posicao, _ ->
                val transacao = transacoes[posicao]
                chamaDialogAlteracao(transacao, posicao)
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val idDoMenu = item?.itemId
        if(idDoMenu == 1){
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoDaTransacao = adapterMenuInfo.position
            remove(posicaoDaTransacao)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(posicao: Int) {
        dao.remove(posicao)
        atualizaTransacoes()
    }

    private fun chamaDialogAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(viewDaActivity as ViewGroup, this)
                .chama(transacao) { transacaoAlterada ->
                    altera(transacaoAlterada, posicao)
                }
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        dao.altera(transacao, posicao)
        atualizaTransacoes()
    }


}