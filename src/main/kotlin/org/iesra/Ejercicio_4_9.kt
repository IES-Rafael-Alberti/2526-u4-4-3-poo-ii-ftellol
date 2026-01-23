package org.iesra
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListaDeTareas() {
    val tareas = ArrayList<Tarea>()
    private var contadorId = 1

    fun agregarTarea(descripcion: String) {
        val tarea = Tarea(contadorId, descripcion)
        tareas.add(tarea)
        contadorId= contadorId + 1
    }

    fun eliminarTarea(id: Int) {
        tareas.removeIf { it.id == id }
    }

    fun cambiarEstadoTarea(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null && tarea.pendiente) {
            tarea.marcarComoRealizada()
        }
    }

    fun mostrarTodas() {
        if (tareas.isEmpty()) {
            println("No hay tareas")
        } else {
            tareas.forEach { println(it) }
        }
    }

    fun mostrarTareasPendientes() {
        tareas.filter { it.pendiente }.forEach { println(it) }
    }

    fun mostrarTareasRealizadas() {
        tareas.filter { !it.pendiente }.forEach { println(it) }
    }
}

class Tarea(val id: Int, val descripcion: String) {
    var pendiente: Boolean = true
    var fechaFormateada: String? = null

    fun marcarComoRealizada() {
        pendiente = false
        val fechaHoraActual: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        fechaFormateada = fechaHoraActual.format(formatter)
    }
}
