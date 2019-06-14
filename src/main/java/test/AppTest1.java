package test;

class Pro {

	public int flag = 1;

}

class XX implements Runnable {
	private Pro p;

	public XX(Pro p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (p) {
				while (p.flag != 1) {
					try {
						p.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("A");
				p.flag = 2;
				p.notifyAll();
			}
		}
	}

}

class YY implements Runnable {
	private Pro p;

	public YY(Pro p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (p) {
				while (p.flag != 2) {
					try {
						p.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("B");
				p.flag = 3;
				p.notifyAll();
			}
		}
	}

}

class ZZ implements Runnable {
	private Pro p;

	public ZZ(Pro p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (p) {
				while (p.flag != 3) {
					try {
						p.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("C");
				p.flag = 1;
				p.notifyAll();
			}
		}
	}

}

public class AppTest1 {

	public static void main(String[] args) {
		Pro p = new Pro();
		XX x = new XX(p);
		YY y = new YY(p);
		ZZ z = new ZZ(p);
		Thread A = new Thread(x);
		Thread B = new Thread(y);
		Thread C = new Thread(z);
		A.start();
		B.start();
		C.start();

	}
}
