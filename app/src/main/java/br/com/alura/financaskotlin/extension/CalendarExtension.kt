package br.com.alura.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro() : String{
    val formatoBrasileiroData = "dd/mm/yyyy"
    val format = SimpleDateFormat(formatoBrasileiroData)
    return format.format(time)
}