package jtm.activity09;


/*-#2
 * Implement Iterator interface with Orders class
 * Hint! Use generic type argument of iterateable items in form: Iterator<Order>
 * 
 * #3 Override/implement public methods for Orders class:
 * - Orders()                — create new empty Orders
 * - add(Order item)            — add passed order to the Orders
 * - List<Order> getItemsList() — List of all customer orders
 * - Set<Order> getItemsSet()   — calculated Set of Orders from list (look at description below)
 * - sort()                     — sort list of orders according to the sorting rules
 * - boolean hasNext()          — check is there next Order in Orders
 * - Order next()               — get next Order from Orders, throw NoSuchElementException if can't
 * - remove()                   — remove current Order (order got by previous next()) from list, throw IllegalStateException if can't
 * - String toString()          — show list of Orders as a String
 * 
 * Hints:
 * 1. To convert Orders to String, reuse .toString() method of List.toString()
 * 2. Use built in Collections.sort(...) method to sort list of orders
 * 
 *  #4
 * When implementing getItemsSet() method, join all requests for the same item from different customers
 * in following way: if there are two requests:
 *  - ItemN: Customer1: 3
 *  - ItemN: Customer2: 1
 *  Set of orders should be:
 *  - ItemN: Customer1,Customer2: 4
 */

import java.util.*;


public class Orders implements Iterator <Object> {
	List <Order> orders ;
	Iterator<Order> iterator;

	public Orders() {
		orders = new LinkedList<>();
		iterator = orders.iterator();
	}

	public void add (Order item){
		orders.add(item);
		iterator = orders.iterator();
	}

	public void remove(){
		iterator.remove();
		iterator = orders.iterator();
	}

	public void sort(){
		Collections.sort(orders);
	}

	public List<Order> getItemsList(){
		return orders;
	}

	public Set<Order> getItemsSet() {
		sort();
		Set<Order> orderSet = new TreeSet<>();
		Order prev = null;
		Order current = null;
		if (hasNext()){
			prev = next();
		}
		while (hasNext()){
			current = next();
			if (current.name.equals(prev.name)){
				prev.count = prev.count+ current.count;
			} if (!prev.customer.contains(current.customer)){
				prev.customer = prev.customer + "," +
						current.customer;
			}
			else {
				orderSet.add(prev);
				prev = current;
			}
		}
		if (prev !=null){
			orderSet.add(prev);
		}
		return orderSet;
	}

	@Override
	public String toString() {
		return orders.toString();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Order next() {
		return iterator.next();
	}
}

