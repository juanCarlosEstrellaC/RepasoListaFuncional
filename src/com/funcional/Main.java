package com.funcional;

import com.funcional.lista.Lista;

public class Main {

	public static void main(String[] args) {

		System.out.println("hola");

		var listaVacia = Lista.of();
		System.out.println(listaVacia);

		var miLista = Lista.of(2, 2, 3, 4, 5);
		System.out.println(miLista);

		var l1 = miLista.append(99);
		System.out.println(l1);

		var l2 = miLista.prepend(88);
		System.out.println(l2);

		var l3 = miLista.remove(4);
		System.out.println(l3);

		var l4 = miLista.drop(4);
		System.out.println(l4);

		var l5 = miLista.dropWhile(x -> x % 2 == 0);
		System.out.println(l5);
		
		var l5_1 = miLista.dropWhile2(x -> x % 2 == 0);
		System.out.println(l5_1);

		var l6 = miLista.take(3);
		System.out.println(l6);

		var l7 = miLista.takeWhile(x -> x % 2 == 0);
		System.out.println(l7);
		
		var l7_1 = miLista.takeWhile2(x -> x % 2 == 0);
		System.out.println(l7_1);
		
		var l8 = miLista.concat(Lista.of(9, 8, 7));
		System.out.println(l8);

		var l9 = miLista.size();
		System.out.println(l9);
		
		var l10 = miLista.reversa();
		System.out.println(l10);
		
	}

}
