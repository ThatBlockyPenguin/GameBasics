package com.blockypenguin.gamebasicslib.multiples;

public class Triple<T1, T2, T3> {
	
	private final T1 t1;
	private final T2 t2;
	private final T3 t3;
	
	private Triple(T1 t1, T2 t2, T3 t3) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
	
	public static <T1, T2, T3> Triple<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
		return new Triple<T1, T2, T3>(t1, t2, t3);
	}
	
	public T1 getT1() {
		return t1;
	}
	
	public T2 getT2() {
		return t2;
	}
	
	public T3 getT3() {
		return t3;
	}
}
