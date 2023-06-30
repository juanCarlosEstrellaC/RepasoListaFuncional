package com.funcional.lista;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Lista<T> permits Nil, Cons  {

	// Atributos:
	Lista NIL = new Nil<>(); 
	
	// Métodos:
	T head();
	Lista<T> tail();
	boolean isEmpty();
	
	static <T> Lista<T> of(T h, Lista<T> t){
		return new Cons<T>(h, t);
	}
	
	static <T> Lista<T> of(T... elementos){
		Lista<T> t = Lista.NIL;
		for (int i = (elementos.length -1); i >= 0 ; i--) {
			T h = elementos[i];
			var tmp = Lista.of(h, t);
			t = tmp;
		}
		return t;
	}
	
	default Lista<T> append(T elemento){
		if (this.isEmpty()) {
			return Lista.of(elemento);
		}else {
			return Lista.of(this.head(), this.tail().append(elemento));
		}
	}
	
	default Lista<T> prepend(T elemento){ //No es necesario hacerlo recursivo, pero si se puede.
		return Lista.of(elemento, this);
	}
	
	default Lista<T> remove(T elemento){
		if (elemento == this.head()) {
			return this.tail();
		} else {
			T h = this.head();
			return Lista.of(h, this.tail().remove(elemento));
		}
	}
	
	default Lista<T> drop(Integer n){
		if (n <= 0 || this.isEmpty()) {
			return this;
		} else {
			n--;
			return this.tail().drop(n);
		}
	}
	
	// Suelta el (o los) primer(os) elemento(s) que cumpla(n) con la condición, no todos.
	// Si hay elementos que cumplan la condición, pero el primer elemento no lo hace, devolverá
	// la misma lista.
	default Lista<T> dropWhile(Predicate<T> p){
		if (!this.isEmpty()) {         // Si la lista no es vacía:
			if (p.test(this.head())) { // PAR
				return this.tail().dropWhile(p);
			} else {				   // IMPAR
				return this;
			}
		}
		return NIL;
	}

	// dropWhile con la validación de la lista vacía en un solo if.
	default Lista<T> dropWhile2(Predicate<T> p) {
		if (!p.test(this.head()) || this.isEmpty()) { // IMPAR O Si la lista es vacía:
			return this;
		} else { 									  // PAR
			return this.tail().dropWhile2(p);
		}
	}

	default Lista<T> take(Integer n){
		if (n <= 0 || this.isEmpty()) {
			return NIL;
		} else {
			n--;
			return Lista.of(this.head(), this.tail().take(n) ) ;
		}
	}
	
	default Lista<T> takeWhile(Predicate<T> p){
		if (!this.isEmpty()) {				// Si la lista no es vacía:
			if (p.test(this.head())) {		// PAR
				return Lista.of(this.head(), this.tail().takeWhile(p));
			} else {						// IMPAR
				return NIL;
			}
		}
		return NIL;
	}
	
	default Lista<T> takeWhile2(Predicate<T> p) {
		if (!p.test(this.head()) || this.isEmpty()) { // IMPAR O Si la lista es vacía: 
			return NIL;
		} else { 									  // PAR
			return Lista.of(this.head(), this.tail().takeWhile2(p));
		}
	}
	
	default Lista<T> concat(Lista<T> lista2){ // uso append
		if (lista2.isEmpty()) {
			return this;
		} else {
			return this.append(lista2.head()).concat(lista2.tail());
		}
	}
	
	default Integer size() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.tail().size();
		}
	}
	
	default Integer suma() {
		if (this.isEmpty()) {
			return 0;
		} else {
			Integer h = (Integer) this.head();
			Integer t = this.tail().suma();
			return h+t;
		} 
	}
	
	default Integer max() {
		if (this.tail().tail() == null) {
			return (Integer) this.head();
		} else {
			Integer max = (Integer) this.head();
			Integer n2 = (Integer) this.tail().head();
			
			if (n2 >= max) {
				max = n2;
			}
			
			var res = this.tail().max();
			
			if (res >= max) {
				max = res;
			}
			
			return max;
		}
	}
	
	default Integer min() {
		if (this.tail().tail() == null) {
			return (Integer) this.head();
		} else {
			Integer min = (Integer) this.head();
			Integer n2 = (Integer) this.tail().head();

			if (n2 <= min) {
				min = n2;
			}

			var res = this.tail().min();

			if (res <= min) {
				min = res;
			}

			return min;
		}
	}
	
	default Lista<T> reversa() {
		if (!(this.tail() == null)) {
			if (this.tail().tail() == null) {
				return Lista.of(this.head());
			} else {
				var ret = this.tail().reversa();
				return ret.append(this.head());
			}
		}
		return this;
	}
	
	// por revisar:
//	default Lista<T> invertir() {
//		if (this.isEmpty()) {
//			return NIL;
//		} else {
//			return tail().invertir().append(head());
//		}
//	}
	
	static Integer sumaStatic(Lista<Integer> lista) {
		if (lista.isEmpty()) {
			return 0;
		} else {
			Integer h = lista.head();
			Integer t = Lista.sumaStatic(lista.tail());
			return h+t;
		} 
	}
	
	static Integer maxStatic(Lista<Integer> lista) {
		if (lista.isEmpty() || lista.tail().head() == null) {
			return lista.head();
		} else {
			Integer maxTmp = lista.head();
			Integer htail = lista.tail().head();

			if (htail > maxTmp) {
				maxTmp = htail;
			}

			Integer res = Lista.maxStatic(lista.tail());

			if (res > maxTmp) {
				maxTmp = res;
			}

			return maxTmp;
		}
	}
	
	static <T, U, V> Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>> composicion() {
		return f -> g -> x -> g.apply(f.apply(x));
	}
	
	default Lista<T> replace(T elem, T newElem) {
		if (this.head().equals(elem)) {
			return Lista.of(newElem, this.tail());
		} else {
			return Lista.of(this.head(), this.tail().replace(elem, newElem));
		}
	}

	default Optional<T> contains(T elemt) {
		if (this.isEmpty()) {
			return Optional.empty();
		} else {
			if (this.head().equals(elemt)) {
				return Optional.of(this.head());
			} else {
				return this.tail().contains(elemt);
			}
		}

//		if (this.isEmpty()) {
//			return Optional.empty();
//		}
//		return this.head().equals(elemt) ? Optional.of(this.head()) : this.tail().contains(elemt);
	}

	default void forEach(Consumer<T> c) {
		if (!this.isEmpty()) {
			c.accept(this.head());
			this.tail().forEach(c);
		}

	}
}
