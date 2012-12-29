package br.com.endrigo.utils.collection;

/**
 * Used to judge an object. <br/>
 *
 * @see CollectionsUtils
 * @author antonini
 * @since 1.0
 * @version 1.0
 */
public interface Predicate<T> {

	/**
	 * Method to be implemented to qualify an object.
	 * 
	 * @param type
	 *            Object that is going to be qualified.
	 * @return <code>true</code> If the qualification passed, <code>false</code> if don't.
	 */
	boolean apply(T type);
}
