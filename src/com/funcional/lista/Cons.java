package com.funcional.lista;

final class Cons<T> implements Lista<T> {

	public final T head;
	public final Lista<T> tail;
	
	public Cons(T head, Lista<T> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public T head() {
		return head;
	}

	@Override
	public Lista<T> tail() {
		return tail;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("[%s,%s]", head, tail);
	}
	
	
}
