package edu.greenblitz.robotName.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a function from R to R^n. You can supply any number of known points and then later
 * use linear interpolation to get any point of the desired graph.
 * @author alexey
 */
public class Dataset {
	
	/**
	 * The function is from R to R^n, then the dimension is n + 1. This is because a function as described is an n+1
	 * dimensional curve.
	 */
	private int dimension;
	/**
	 * The data, ordered by x the function input. The second element in the tuple is the function output.
	 */
	private List<TwoTuple<Double, double[]>> data;
	
	/**
	 *
	 * @param dim The dimension of the function. (output size + 1)
	 */
	public Dataset(int dim){
		dimension = dim;
		data = new ArrayList<>();
	}
	
	/**
	 *
	 * Adds a new datapoint to the list of known points of the function. If a point of the same x exists
	 * in the dataset it updates it.
	 *
	 * @param x The function input
	 * @param y The function output
	 * @throws RuntimeException if the output size isn't equal (dimension - 1)
	 */
	public void addDatapoint(double x, double[] y) {
		if (y.length != dimension - 1){
			throw new RuntimeException("The dimension of this dataset is " + dimension + " but " + (y.length + 1)
					+ " was given.");
		}
		if (data.size() == 0) {
			data.add(new TwoTuple<>(x, y));
			return;
		}
		int index = 0;
		for (TwoTuple<Double, double[]> point : data) {
			if (point.getFirst() >= x) {
				if (point.getFirst() == x) {
					point.setSecond(y);
					return;
				}
				break;
			}
			index += 1;
		}
		
		addAt(index, new TwoTuple<>(x, y));
	}
	
	private void addAt(int index, TwoTuple<Double, double[]> newData){
		if (index == data.size()){
			data.add(newData);
			return;
		}
		TwoTuple<Double, double[]> wasAtIndex = data.get(index);
		data.set(index, newData);
		addAt(index + 1, wasAtIndex);
	}
	
	/**
	 *
	 * @param x An input for the function
	 * @return A tuple where the first element is the largest point who's x is smaller than the input x
	 * adn the second element is the smallest point who's x is larger than the input x
	 */
	public TwoTuple<TwoTuple<Double, double[]>, TwoTuple<Double, double[]>> getAdjesent(double x) {
		if (data.size() < 2 || x < data.get(0).getFirst() || x > data.get(data.size() - 1).getFirst()) {
			throw new RuntimeException("point given now within range of dataset, or data set is smaller than 2 points");
		}
		int index = 0;
		for (TwoTuple<Double, double[]> point : data) {
			if (point.getFirst() >= x) {
				if (point.getFirst() == x) {
					return new TwoTuple<>(point, point);
				}
				break;
			}
			index += 1;
		}
		return new TwoTuple<>(data.get(index - 1), data.get(index));
	}
	
	/**
	 *
	 * @param x A function input
	 * @return The output of the function, calculated using linear interpolation.
	 * @throws RuntimeException If the given input is smaller than the smallest known sample or larger than the largest
	 * known sample.
	 */
	public double[] linearlyInterpolate(double x){
		if(Double.isNaN(x)){
			throw new RuntimeException("x is NaN");
		}
		TwoTuple<TwoTuple<Double, double[]>, TwoTuple<Double, double[]>> data = getAdjesent(x);
		double weight;
		if(x == data.getFirst().getFirst()){
			weight = 0;
		}
		else {
			double denom = data.getSecond().getFirst() - data.getFirst().getFirst();
			if (denom == 0) {
				throw new RuntimeException("denom is 0" + x + " : " + data.getFirst().getFirst() + " : " + data.getSecond().getFirst());
			}
			weight = (x - data.getFirst().getFirst()) / (denom);
			if (Double.isNaN(weight)) {
				throw new RuntimeException("weight is NaN");
			}
		}
		double[] ret = new double[dimension];
		for (int i = 0; i < dimension - 1; i++){
			ret[i] = data.getFirst().getSecond()[i] +
					(data.getSecond().getSecond()[i] - data.getFirst().getSecond()[i]) * weight;
		}
		return ret;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("Dataset dataset = new Dataset(" + dimension + ");\n");
		for (TwoTuple<Double, double[]> val : data){
			ret.append("dataset.addDatapoint(").append(val.getFirst()).append(", new double[]{");
			for (double d : val.getSecond()){
				ret.append(d).append(", ");
			}
			ret.append("});\n");
		}
		return ret.toString();
	}
	
	public boolean containsKey(double x){
		for (TwoTuple<Double, double[]> point : data) {
			if (point.getFirst() >= x) {
				if (point.getFirst() == x) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public boolean containsValue(double[] y){
		for (TwoTuple<Double, double[]> point : data) {
			if (Arrays.equals(point.getSecond(), y)) {
				return true;
			}
		}
		return false;
	}
	
	
}