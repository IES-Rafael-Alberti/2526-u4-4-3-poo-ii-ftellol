package org.iesra

class Cuenta(val numCuenta: Int, var saldo: Double) {
    fun consultarSaldo(): Double {
        return saldo
    }

    fun recibirAbonos(cantidad: Double) {
        require(cantidad > 0) {"La cantidad tiene que ser mayor a 0"}
            saldo = saldo + cantidad
    }

    fun realizarPagos(cantidad: Double): Boolean {
        require(cantidad > 0) {"La cantidad tiene que ser mayor a 0"}
            saldo = saldo - cantidad
            return true
    }

    companion object {
        fun esMorosa(persona: Persona): Boolean {
            for (i in 0 until persona.numCuentas) {
                if (persona.cuentas[i]!!.saldo < 0) {
                    return true
                }
            }
            return false
        }

        fun transferencia(origen: Persona, destino: Persona, numCuentaOrigen: Int, numCuentaDestino: Int, cantidad: Double): Boolean {
            if (cantidad <= 0) return false

            val cuentaOrigen = origen.buscarCuenta(numCuentaOrigen)
            val cuentaDestino = destino.buscarCuenta(numCuentaDestino)
            if (cuentaOrigen == null || cuentaDestino == null) return false

            if (cuentaOrigen.realizarPagos(cantidad)) {
                cuentaDestino.recibirAbonos(cantidad)
                return true
            }
            return false
        }
    }
}

class Persona(val dni: String) {
    val cuentas: Array<Cuenta?> = arrayOfNulls(3)
    var numCuentas = 0

    fun añadirCuenta(cuenta: Cuenta) {
        if (numCuentas < 3) {
            cuentas[numCuentas] = cuenta
            numCuentas = numCuentas + 1
        }
    }

    fun buscarCuenta(numCuenta: Int): Cuenta? {
        for (i in 0 until numCuentas) {
            if (cuentas[i]!!.numCuenta == numCuenta) {
                return cuentas[i]
            }
        }
        return null
    }
}

fun main() {
    val persona = Persona("12345678A")
    val cuenta1 = Cuenta(1, 0.0)
    val cuenta2 = Cuenta(2, 700.0)

    persona.añadirCuenta(cuenta1)
    persona.añadirCuenta(cuenta2)

    cuenta1.recibirAbonos(1100.0)
    cuenta2.realizarPagos(750.0)

    val morosa = Cuenta.esMorosa(persona)
    println("¿La persona es morosa? $morosa")

    val transferencia = Cuenta.transferencia(persona, persona, 1, 2, 200.0)
    println("Transferencia realizada: $transferencia")

    for (i in 0 until persona.numCuentas) {
        println("Cuenta ${persona.cuentas[i]!!.numCuenta} saldo: ${persona.cuentas[i]!!.saldo}")
    }
}