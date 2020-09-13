import java.util.NoSuchElementException;

/*
 * The shell of the class, to be completed as part of the
 * CSC115 Assignment 5 : Emergency Room
 */

/* ALLL METHODS WERE COMPLETED BY THE STUDENT. */

public class PriorityQueue<E extends Comparable<E>> {
	
	private Heap<E> heap;

	/**

	 Creates an empty priority queue.
	 **/

	public PriorityQueue() {
 	/********  COMPLETE *******/
 		heap = new Heap<E>();
	}

	/**
	 * Inserts an item into the queue.
	 * @param item
	 */
	public void enqueue(E item) {
		heap.insert(item);
	}
	/**
	 * Removes the highest priority item from the queue.
	 * @return: The item.
	 */
	public E dequeue(){

		return heap.removeRootItem();	// delete the root and heapRebuild from semiheap.

	}

	/**
	 * Retrieves, but does not remove the next item out of the queue.
	 * @return: The item with the highest priority in the queue.
	 */
	public E peek(){

		return heap.getRootItem(); // simply return the root of the heap.
	}

	/**
	 *
	 * @return: True if the queue is empty, false if it is not.
	 */

	public boolean isEmpty(){

		return heap.isHeapEmpty();

	}


	/* Only for testing the Priority Queue.*/

	public static void main(String[] args) {

		PriorityQueue<ER_Patient> patientPriorityQueue = new PriorityQueue<ER_Patient>();

		/* Patients disorder type */

		String[] complaints = {"Walk-in", "Life-threatening","Chronic","Major fracture","Walk-in", "Life-threatening", "Major fracture", "Life-threatening"};

		ER_Patient[] patients = new ER_Patient[complaints.length];

		System.out.print("Taking in patients :");
		for (int i=0; i<complaints.length; i++) {


			try {

				patients[i] = new ER_Patient(complaints[i]);

				System.out.print("Patient " + i + ": " + patients[i] +"\n");
				patientPriorityQueue.enqueue(patients[i]);
				// spread out the admission times by 1 second
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("sleep interrupted");
					return;
				}

			}
			catch(NoSuchCategoryException e){
				System.out.print("Category does not exist. Please enter valid patient information\n");
			}




		}

		System.out.print("\n\non top right now :" + patientPriorityQueue.peek() +"\n");

		System.out.print("\nCalling the patients using their priority number : ");

		for(int i =0; i< complaints.length;i++) {
			System.out.print("\n" + patientPriorityQueue.dequeue() + "\n");
		}

	}

			
}
	
