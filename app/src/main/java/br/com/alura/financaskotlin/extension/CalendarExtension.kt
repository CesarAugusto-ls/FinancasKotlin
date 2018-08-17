package br.com.alura.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro() : String{
    val formatoBrasileiroData = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiroData)
    return format.format(time)
}