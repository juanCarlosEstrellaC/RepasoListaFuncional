package com.funcional.lista;

final class Nil<T> implements Lista<T> {

	protected Nil() {}

	@Override
	public T head() {
		return null;
	}

	@Override
	public Lista<T> tail() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
	
	@Override
	public String toString() {
		return "NIL";
	}
	
}
