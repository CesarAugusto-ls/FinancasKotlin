package br.com.alura.financaskotlin.extension

fun String.limitaEmAte(caracteres: Int) : String{
    if(this.length > caracteres){
        return "${this.substring(0,14)}..."
    }
    return this
}