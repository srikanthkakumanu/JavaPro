package com.srikanth.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Java 5 introduced an improvement with the Executor and ExecutorService
 * interfaces and the classes that implement them (for example, the
 * ThreadPoolExecutor class). The Executor framework separates the task creation
 * and its execution. With it, you only have to implement the Runnable objects
 * and use an Executor object. You send the Runnable tasks to the executor and
 * it creates, manages, and finalizes the necessary threads to execute those
 * tasks. Java 7 goes a step further and includes an additional implementation
 * of the ExecutorService interface oriented to a specific kind of problem. It's
 * the Fork/Join framework. This framework is designed to solve problems that
 * can be broken into smaller tasks using the divide and conquer technique.
 * Inside a task, you check the size of the problem you want to resolve and, if
 * it's bigger than an established size, you divide it in smaller tasks that are
 * executed using the framework. If the size of the problem is smaller than the
 * established size, you solve the problem directly in the task and then,
 * optionally, it returns a result. 
 * The framework is based on the following two operations: 
 * 1. The fork operation: When you divide a task into smaller tasks and execute them 
 * using the framework 
 * 2. The join operation: When a task waits for the finalization of the tasks it has 
 * created The main difference between the Fork/Join and the Executor frameworks
 * is the work-stealing algorithm.
 * The invokeAll() method of the ForkJoinTask class is one of the main differences
 * between the Executor and the Fork/Join framework. In the Executor framework, all the tasks
 * have to be sent to the executor, while in this case, the tasks include methods to execute and
 * control the tasks inside the pool. You have used the invokeAll() method in the Task class,
 */
public class ForkAndJoinDemo {
	static Logger logger = LoggerFactory.getLogger(ForkAndJoinDemo.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProductLIstGenerator generator=new ProductLIstGenerator();
		List<Product> products=generator.generate(10000);
		Task task=new Task(products, 0, products.size(), 0.20);
		ForkJoinPool pool=new ForkJoinPool();
		pool.execute(task);
		do {
			logger.info(String.format("Main: Thread Count: %d\n",pool.getActiveThreadCount()));
			logger.info(String.format("Main: Thread Steal: %d\n",pool.getStealCount()));
			logger.info(String.format("Main: Parallelism: %d\n",pool.getParallelism()));
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException ie) {
				logger.error(ie.toString(), ie);
			}
		} while (!task.isDone());
		pool.shutdown();
		
		if (task.isCompletedNormally()) {
			logger.info("Main: The process has completed normally.\n");
		}
		for (int i = 0; i < products.size(); i++) {
			Product product=products.get(i);
			if (product.getPrice() != 12) {
				logger.info(String.format("Product %s: %f\n",product.getName(),product.getPrice()));
			}
		}
		logger.info("Main: End of the program.\n");
	}
}
class Product {
	private String name;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
class ProductLIstGenerator {
	public List<Product> generate(int size) {
		List<Product> returnList = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			Product product = new Product();
			product.setName("Product " + i);
			product.setPrice(10);
			returnList.add(product);
		}
		return returnList;
	}
}
class Task extends RecursiveAction {
	Logger logger = LoggerFactory.getLogger(Task.class);
	private static final long serialVersionUID = 1L;
	private List<Product> products;
	private int first, last;
	private double increment;
	
	public Task(List<Product> products, int first, int last, double increment) {
		super();
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}
	@Override
	protected void compute() {
		if(last - first < 10) {
			updatePrices();
		} else {
			int middle = (last + first)/2;
			logger.info(String.format("Task: Pending Tasks: %s\n", getQueuedTaskCount()));
			Task task1 = new Task(products, first, middle + 1, increment);
			Task task2 = new Task(products, first, middle + 1, increment);
			invokeAll(task1, task2);
		}
	}
	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product=products.get(i);
			product.setPrice(product.getPrice()*(1+increment));
		}
	}	
}