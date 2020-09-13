import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Comparator;
/*
 * The shell of the class, to be completed as part
 * of CSC115 Assignment 5: Emergency Room.
 */

/* ALL METHODS WERE COMPLETED BY THE STUDENT. */


public class Heap<E extends Comparable<E>> {

	public Vector<E> heapArray;
	private E item;
	private Comparator<? super E> comparator;

	/**
	 *
	 Create an empty heap
	 *
	 **/
	public Heap() {

		heapArray = new Vector<E>();
	}

	/**
	 * @return : True if the heap is empty, false if it is not.
	 **/
	public boolean isHeapEmpty(){

		return heapArray.isEmpty();
	}

	/**
	 * @return : The number of items in the heap.
	 *
	 **/
	public int size(){

		return  heapArray.size();
	}

	/**
	 * 	 Inserts an item into the heap.
	 *
	 * 	@param : item - The newly added item.:
	 *
	 *
	 **/
	public void insert(E item){

		if(!heapArray.add(item)){
		System.out.println("Cannot insert the element : " + item);
			return ;
		}

		heapRebuild(heapArray.size()-1);
		}

	/**
	 * @return :  Removes the item at the root node of the heap and rebuilds the heap.
	 *
	 * @throws : java.util.NoSuchElementException - if the heap is empty.
	 *
	 **/
	public E removeRootItem() throws java.util.NoSuchElementException {

		E root = null;
		int loc;
		if(!isHeapEmpty())
		{
			root = heapArray.get(0);
			loc = heapArray.size()-1;
			heapArray.set(0,heapArray.get(loc));
			heapArray.remove(loc);
			InsertheapRebuild(0);
		}

		return root;

	}

	private void InsertheapRebuild(int index) {

		int child  =index*2+1;	//left child.

		if (child < heapArray.size()) {

			int right = child+1;
			// if it is not the leaf, the it has children and it will surely have a left child and possibly a right child.

			if((right<heapArray.size()) && (compareItems(heapArray.get(right),heapArray.get(child))<0) ) {
				child = right;
			}

			if(  compareItems(heapArray.get(child),heapArray.get(index))<0 ){

				// swap.
				E temp = heapArray.get(child) ;
				heapArray.set(child, heapArray.get(index));
				heapArray.set(index, temp);

				InsertheapRebuild(child);
			}
		}

	}


	private int compareItems(E item1, E item2){

		int comp = item1.compareTo(item2);

		return (comp);
	}

	protected void heapRebuild(int index){

		if(index>0) {

			int parent = (index-1);


			int value =  compareItems(heapArray.get(index),heapArray.get(parent))  ;

			if(value<0){

				E temp = heapArray.get(parent) ;
				heapArray.set(parent, heapArray.get(index));
				heapArray.set(index, temp);

				heapRebuild(parent);
			}


		}

	}

	/**
	 * 	@return :  The top item in the tree.
	 * 	@throws : java.util.NoSuchElementException - if the heap is empty.
	 **/
	public E getRootItem()  throws java.util.NoSuchElementException {

		if(heapArray==null) {
			throw new java.util.NoSuchElementException("Element does not exist");

		}
		if (!isHeapEmpty()) {
			return heapArray.get(0);
		}
	return null;
	}
	/**
	 * 	Used for internal testing purposes.
	 * @param : args - Not used.
	 *
	 **/
	public static void main(String[] args){

		Heap<ER_Patient> heap = new Heap<ER_Patient>();
		ER_Patient[] patients = new ER_Patient[5];
		String[] complaints = {"Walk-in", "Chronic", "Chronic", "Major fracture", "Life-threatening"};
		for (int i = 0; i < 5; i++) {
			patients[i] = new ER_Patient(complaints[i]);
			heap.insert(patients[i]);
			// spread out the admission times by 1 second
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
				return;
			}
		}

		for (int i = 0; i < 5; i++) {
			System.out.print("\nroot : " + heap.getRootItem() + "\n");
		}
	}




}
