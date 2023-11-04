package com.blockypenguin.gamebasicslib.multiples;

public class Quadruple<T1, T2, T3, T4> {
	
	private final T1 t1;
	private final T2 t2;
	private final T3 t3;
	private final T4 t4;
	
	private Quadruple(T1 t1, T2 t2, T3 t3, T4 t4) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.t4 = t4;
	}
	
	public static <T1, T2, T3, T4> Quadruple<T1, T2, T3, T4> of(T1 t1, T2 t2, T3 t3, T4 t4) {
		return new Quadruple<>(t1, t2, t3, t4);
	}
	
	public T1 getT1() { return t1; }
	public T2 getT2() { return t2; }
	public T3 getT3() { return t3; }
	public T4 getT4() { return t4; }
}
