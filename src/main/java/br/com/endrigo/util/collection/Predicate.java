package br.com.endrigo.util.collection;

/**
 * Used to judge an object. <br/>
 * 
 * @deprecated Use the {@link com.eidoscode.util.collection.Predicate} instead
 *             of this one.
 * 
 * @see CollectionsUtils
 * @author antonini
 * @since 1.0
 * @version 1.0
 */
@Deprecated
public interface Predicate<T> extends
		com.eidoscode.util.collection.Predicate<T> {

	/**
	 * Method to be implemented to qualify an object.
	 * 
	 * @param type
	 *            Object that is going to be qualified.
	 * @return <code>true</code> If the qualification passed, <code>false</code>
	 *         if don't.
	 */
	@Override
	boolean apply(T type);
}
