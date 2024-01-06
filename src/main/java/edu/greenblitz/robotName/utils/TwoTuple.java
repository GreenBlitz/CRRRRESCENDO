package edu.greenblitz.robotName.utils;

import java.util.Objects;

public class TwoTuple<F, S> {
	
	private F first;
	private S second;
	
	public F getFirst() {
		return first;
	}
	
	public void setFirst(F first) {
		this.first = first;
	}
	
	public S getSecond() {
		return second;
	}
	
	public void setSecond(S second) {
		this.second = second;
	}
	
	public TwoTuple(F first, S second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString() {
		return "TwoTuple{" +
				"first=" + first +
				", second=" + second +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TwoTuple<?, ?> twoTuple = (TwoTuple<?, ?>) o;
		return twoTuple.getFirst().equals(getFirst()) && twoTuple.getSecond().equals(getSecond());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}
