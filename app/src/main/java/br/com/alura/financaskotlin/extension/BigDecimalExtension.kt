package br.com.alura.financaskotlin.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataParaBrasileiro() : String{
    val formatoBrasilerio = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasilerio.format(this).replace("R$","R$ ")
}