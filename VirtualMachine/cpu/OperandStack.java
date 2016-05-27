package tp.pr5.mv.cpu;

import java.util.ArrayList;

import tp.pr5.mv.observers.StackObserver;

public class OperandStack {
	
	private int top;
	private int[] stack;
	private final int MAX = 10;
	private ArrayList<StackObserver> views;

	public OperandStack() {
		this.top = 0;
		this.stack = new int[MAX];
		this.views = new ArrayList<StackObserver>();
	}
	
	public int getTop() {
		return this.top;
	}
	
	public int getValorCima() {
		return this.stack[this.top-1];
	}
	
	public void addObserver(StackObserver view) {
		this.views.add(view);
	}
	
	public boolean isFull() {
		return this.top == this.stack.length;
	}
	
	public boolean isEmpty() {
		return this.top == 0;
	}
	
	public void apilar(int n) {
		if (this.isFull())
			this.resize();
		
		this.stack[this.top] = n;
		this.top++;
		
		for(StackObserver ob: this.views)
			ob.onPush(n);
	}
	
	public int desapilar() {
		this.top--;
		
		for(StackObserver ob: this.views)
			ob.onPop();
		
		return this.stack[this.top];
	}
	
	private void resize() {
		int[] nuevaPila = new int[this.stack.length + this.MAX];
		for (int i=0; i<this.top; i++)
			nuevaPila[i] = this.stack[i];
		
		this.stack = nuevaPila;
	}
	
	public String toString() {
		String s = " ";
		
		if (this.isEmpty())
			return "Pila de Operandos: <vacia>";
		
		else {
			for (int i=0; i<this.top; i++)
				s = s + this.stack[i] + " ";
			
			return "Pila de Operandos:" + s;
		}
	}
}
