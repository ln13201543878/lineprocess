package test;

class Apple {
	private int i = 0;

	public synchronized void buy() {
		if (this.isFull()) {
			try {

				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("买了第" + (i++) + "个苹果");
		this.notify();

	}

	public synchronized void eat() {
		if (this.isEmpty()) {
			try {
				this.notify();
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("吃了第" + (--i) + "个苹果");
		this.notify();

	}

	public boolean isFull() {
		return i == 200;
	}

	public boolean isEmpty() {
		return i == 0;
	}

}

class X implements Runnable {
	private Apple a;

	public X(Apple a) {
		this.a = a;
	}

	@Override
	public void run() {
		while (true) {
			a.buy();
		}
	}
}

class Y implements Runnable {
	private Apple a;

	public Y(Apple a) {
		this.a = a;
	}

	@Override
	public void run() {
		while (true) {
			a.eat();
		}
	}

}

public class AppTest {
	public static void main(String[] args) {
		Apple a = new Apple();
		X x = new X(a);
		Y y = new Y(a);
		Thread buy = new Thread(x);
		Thread eat = new Thread(y);
		buy.start();
		eat.start();

	}

}
