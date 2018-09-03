package br.com.alura.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEmAte(caracteres: Int) : String{
    if(this.length > caracteres){
        val primeiroCaracter = 0
        return "${this.substring(primeiroCaracter,caracteres)}..."
    }
    return this
}

fun String.conveteParaCalendar() : Calendar {
    val formataParaBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida = formataParaBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}