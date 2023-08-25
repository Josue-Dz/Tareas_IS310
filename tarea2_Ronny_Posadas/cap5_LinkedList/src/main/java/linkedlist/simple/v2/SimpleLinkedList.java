/*

El asunto de esta version es utilizar curr como una referencia de tipo Nodo en
lugar de un entero que ubica las posiciones.

Objetivo: Cambiar en las funciones existentes este rol. También se removió la
función getNode para utilizar directamente curr.
 */

// Tarea 2: ajuste en la v2 de Simple Linked List donde el
// objeto curr debe hacer referencia a un nodo en lugar de una posición numérica.
package linkedlist.simple.v2;

import linkedlist.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> implements List<T> {
    // Atributos de la lista enlazada;
    private Nodo<T> head;  // apunta el primer nodo
    private Nodo<T> tail;  // apunta al ultimo nodo
    private int size;  // cantidad de nodos

    private Nodo<T> curr;  // posición del nodo actual

    private Nodo<T> aux = null; // Variable auxiliar

    public SimpleLinkedList() {
        clear();
    }

    private boolean insertFirstNode(Nodo<T> n) {
        this.head = n;
        this.curr = this.tail = n;
        this.size++;
        return true;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
        curr = tail= null;
    }

    @Override
    public boolean insert(T it) {
        // Esta función no permite insertar al final puede ser al inicio o enmedio.
        Nodo<T> newNodo = new Nodo<>(it);

        // insertar primer nodo
        if (this.isEmpty()) {
            return this.insertFirstNode(newNodo);
        }

        // insertar al inicio
        if (curr == head) {
            newNodo.siguiente = head;
            head = newNodo;
            curr = newNodo;
        }
        // insertar nodos intermedios
        else {
            this.prev();
            Nodo<T> previo = curr;
            //curr = curr.siguiente;  // para recuperar el curr
            newNodo.siguiente = previo.siguiente;
            previo.siguiente = newNodo;
        }

        size++;
        return true;
    }

    @Override
    public boolean append(T it) {
        var newNode = new Nodo<>(it);

        if (this.isEmpty()) {
            return this.insertFirstNode(newNode);
        }

        tail.siguiente = newNode;
        tail = newNode;

        size++;
        return true;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException();

        Nodo<T> oldCurr = curr;  // guardar el valor del nodo a eliminar

        // remover el primer nodo
        if (oldCurr == head) {
            head = head.siguiente;
        }
        else {
            // situarse justo antes de curr
            prev();
            Nodo<T> prevNodo = curr;
            prevNodo.siguiente = prevNodo.siguiente.siguiente;

            // remover el ultimo nodo
            if (oldCurr == tail) {
                tail = prevNodo;
            }
        }
        size--;
        return oldCurr.getDato();
    }

    @Override
    public void moveToStart() {
        curr = head;
    }

    @Override
    public void moveToEnd() {
        curr = tail;
    }

    @Override
    public void prev() {
        if (head == curr){
            return;                //No hay elementos previos
        }

        Nodo<T> temp = head;
        while (temp.siguiente != curr){
            temp = temp.siguiente;
        }
        curr = temp;
    }

    @Override
    public void next() {
        if (curr != tail){
            curr = curr.siguiente;
        }
    }

    @Override
    public int length() {
        return this.size;
    }

    // Retorna la currPos en la lista
    @Override
    public int currPos() {
        Nodo<T> temp = head;

        int pos = 0;
        for  (pos = 0; curr != temp; pos++){
            temp = temp.siguiente;
        }
        return pos;
    }

    @Override
    public boolean moveToPos(int pos) {

        if (pos < 0 || pos >= size) {
            return false;
        }
        curr = head;
        for (int i = 0; i < pos; i++){
            curr = curr.siguiente;
        }
        return true;
    }

    @Override
    public boolean isAtEnd() {
        return curr == tail;
    }

    @Override
    public T getValue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return curr.getDato();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public static class Nodo<T> {
        public T getDato() {
            return dato;
        }

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            return dato.toString();
        }

        private final T dato;
        Nodo<T> siguiente;
    }


    }