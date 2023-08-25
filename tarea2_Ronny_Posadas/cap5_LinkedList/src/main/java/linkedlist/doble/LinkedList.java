/*
En este caso se usa un Nodo con doble enlace: uno que apunta al siguiente y
otro al anterior.

Reutilizar los métodos existentes pensando en mantener en las operaciones de
inserción y remoción ambos enlaces.
 */
package linkedlist.doble;

import linkedlist.List;

import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    // Atributos de la lista enlazada;
    private Nodo<T> head;  // apunta el primer nodo
    private Nodo<T> tail;  // apunta al ultimo nodo
    private Nodo<T> curr;  // posición del nodo actual
    private int size;  // cantidad de nodos

    public LinkedList() {
        clear();
    }

    private boolean insertFirstNode(Nodo<T> n) {
        this.head = this.curr = this.tail = n;
        this.size++;
        return true;
    }

    @Override
    public void clear() {
        this.head = null;
        this.curr = tail = null;
        size = 0;
    }

    @Override
    public boolean insert(T it) {
        /*
        Algo particular de esta funcion es que no permite insertar al final
        puede ser al inicio o enmedio pero no al final.
         */
        var newNodo = new Nodo<>(it);

        // insertar primer nodo
        if (this.isEmpty()) {
            return this.insertFirstNode(newNodo);
        }

        // insertar al inicio
        if (curr == head) {
            newNodo = new Nodo<>(it, curr.previo, curr);

            newNodo.siguiente = head;
            head = newNodo;
            curr.previo = newNodo;

        }
        // insertar nodos intermedios
        else {
            newNodo = new Nodo<>(it, curr.previo, curr);
            this.prev();
            var previo = curr;
            //curr.getDato();  // para recuperar el curr
            newNodo.siguiente = previo.siguiente;
            previo.siguiente = newNodo;
            previo = newNodo;
            newNodo = previo;


        }

        size++;
        return true;
    }

    @Override
    public boolean append(T it) {
        Nodo<T> newNode;
        //Nodo<T> temp = tail;

        if (this.isEmpty()) {
            newNode = new Nodo<>(it);
            return this.insertFirstNode(newNode);
        }

        if(size  == 1 && tail.previo == null){
            newNode = new Nodo<>(it);
            newNode.siguiente = tail;
            tail.siguiente = newNode;
            curr.siguiente = tail;
        }
        Nodo<T> temp = curr.siguiente;
        newNode = new Nodo<>(it, temp, temp.previo);
        newNode.siguiente = tail;
        curr = curr.siguiente = temp.previo = newNode;

        size++;
        return true;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Nodo<T> removeNode = curr;  // guardar el valor del nodo a eliminar

        // remover el primer nodo
        if (removeNode == head) {
            head = head.siguiente;
        }
        else {
            // situarse justo antes de curr
            prev();
            Nodo<T> prevNodo = curr;
            prevNodo.siguiente = prevNodo.siguiente.siguiente;
            prevNodo.siguiente.previo = prevNodo;

            // remover el ultimo nodo
            if (removeNode == tail) {
                tail = prevNodo;
                tail.previo = prevNodo.previo;
            }
        }
        size--;
        return removeNode.getDato();
    }

    @Override
    public void moveToStart() {
        curr = head;
    }

    @Override
    public void moveToEnd() {
        curr = tail;
        //curr= tail.previo;
    }

    @Override
    public void prev() {

        if (head == curr){
            return;                //No hay elementos previos
        }
        curr = curr.previo;
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
        int pos;
        for(pos = 0; curr != temp; pos++){
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
        for (int i=0; i < pos; i++){
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

        return this.curr.getDato();
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
            this.previo = null;
        }

        public Nodo(T dato, Nodo<T> previo, Nodo<T> siguiente) {
            this.dato = dato;
            this.previo = previo;
            this.siguiente = siguiente;
        }

        @Override
        public String toString() {
            return dato.toString();
        }

        private final T dato;
        Nodo<T> siguiente;  // enlace al siguiente nodo
        Nodo<T> previo;  // enlace al nodo previo
    }
}
