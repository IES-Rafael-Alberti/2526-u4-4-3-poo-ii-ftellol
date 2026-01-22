package org.iesra

data class Compra(val cliente: Cliente, val dia: String, val monto: Double)

data class Cliente(val nombre: String, val domicilio: Domicilio)

data class Domicilio(val calle: String, val numero: Int) {
    fun dirCompleta(): String {
        return "$calle $numero"
    }
}

class RepositorioCompras() {
    var compras = mutableListOf<Compra>()

    fun agregarCompra(compra: Compra) {
        compras.add(compra)
    }

    fun domicilios(): Set<Domicilio> {
        return compras.map {it.cliente.domicilio}.toSet()
    }
}