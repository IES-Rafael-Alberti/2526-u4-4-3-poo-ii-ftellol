package org.iesra

class Libro(val titulo: String, val autor: String, val numPaginas: Int, val calificacion: Double)

class ConjuntoLibros(val capacidad: Int) {
    val libros: Array<Libro?> = arrayOfNulls(capacidad)
    var numLibros = 0

    fun añadirLibro(libro: Libro) {
        if (numLibros < capacidad) {
            libros[numLibros] = libro
            numLibros = numLibros + 1
        }
    }

    fun eliminarPorTitulo(titulo: String) {
        var indice = 0
        while (indice < numLibros) {
            if (libros[indice]!!.titulo == titulo) {
                for (indiceSiguiente in indice until numLibros - 1) {
                    libros[indiceSiguiente] = libros[indiceSiguiente + 1]
                }
                libros[numLibros - 1] = null
                numLibros = numLibros - 1
            }
            else {
                indice = indice + 1
            }
        }
    }

    fun eliminarPorAutor(autor: String) {
        var indice = 0
        while (indice < numLibros) {
            if (libros[indice]!!.autor == autor) {
                for (indiceSiguiente in indice until numLibros - 1) {
                    libros[indiceSiguiente] = libros[indiceSiguiente + 1]
                }
                libros[numLibros - 1] = null
                numLibros = numLibros - 1
            }
            else {
                indice = indice + 1
            }
        }
    }

    fun libroMayorCalificacion(): Libro? {
        if (numLibros == 0) return null
        var libroMax = libros[0]
        for (i in 1 until numLibros) {
            if (libros[i]!!.calificacion > libroMax!!.calificacion) libroMax = libros[i]
        }
        return libroMax
    }

    fun libroMenorCalificacion(): Libro? {
        if (numLibros == 0) return null
        var libroMin = libros[0]
        for (i in 1 until numLibros) {
            if (libros[i]!!.calificacion < libroMin!!.calificacion) libroMin = libros[i]
        }
        return libroMin
    }

    fun mostrarConjunto() {
        if (numLibros == 0) {
            println("El conjunto está vacío.")
        }
        else {
            for (indice in 0 until numLibros) {
                val libro = libros[indice]!!
                println("Título: ${libro.titulo}, Autor: ${libro.autor}, Páginas: ${libro.numPaginas}, Calificación: ${libro.calificacion}")
            }
        }
    }
}

fun main() {
    val conjunto = ConjuntoLibros(5)

    val libro1 = Libro("Harry Potter", "J.K.Rowling", 858, 10.0)
    val libro2 = Libro("Mario Bros", "Antonio Perez", 252, 7.0)

    conjunto.añadirLibro(libro1)
    conjunto.añadirLibro(libro2)

    conjunto.eliminarPorTitulo("Harry Potter")
    conjunto.eliminarPorTitulo("Mario Bros")

    conjunto.eliminarPorAutor("J.K.Rowling")
    conjunto.eliminarPorAutor("Antonio Perez")

    val libro3 = Libro("Romeo y su nieta", "Shakespeare", 500, 8.5)
    conjunto.añadirLibro(libro3)

    conjunto.mostrarConjunto()
}