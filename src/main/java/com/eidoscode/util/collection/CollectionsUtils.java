package com.eidoscode.util.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Utilities classes to be used with collections.
 * 
 * @author eantonini
 * @since 1.0
 * @version 1.0
 */
public final class CollectionsUtils {

	private CollectionsUtils() {
	}

	/**
	 * Filter an {@link Collection} using an {@link Predicate} instance.<br/>
	 * Example: The sample below is going to return a {@link Collection} that
	 * inside of it, it'll have just a single instance of a String "A Object".
	 * 
	 * <pre>
	 * {@link ArrayList}&lt;String&gt; list = new ArrayList&lt;String&gt;();
	 * list.add(&quot;A Object&quot;);
	 * list.add(&quot;B Object&quot;);
	 * 
	 * {@link Collection}&lt;String&gt; b = CollectionsUtils.filter(list, new {@link Predicate}&lt;String&gt;() {
	 * 
	 * 	public boolean apply(String type) {
	 * 		return (type.charAt(0) == 'A');
	 * 	};
	 * });
	 * </pre>
	 * 
	 * @param source
	 *            Collection source to be filtered. This field is mandatory, if
	 *            you don't send it, it'll generate an
	 *            {@link NullPointerException}.
	 * @param predicate
	 *            Instance of the predicate to be used to apply the filter. This
	 *            field is mandatory, if you don't send it, it'll generate an
	 *            {@link NullPointerException}.
	 * @return It'll returns an Collection instance filtered by the predicate.
	 *         If the <code>source</code> or the <code>predicate</code> passed
	 *         through parameter is null, it'll throw a
	 *         {@link NullPointerException}.
	 */
	public static <T> Collection<T> filter(Collection<T> source,
			Predicate<T> predicate) {
		if (source == null) {
			throw new NullPointerException("The source is mandatory.");
		}
		if (predicate == null) {
			throw new NullPointerException("The predicate is mandatory.");
		}

		Collection<T> result = new ArrayList<T>();
		if (source.size() > 0) {
			for (T element : source) {
				if (predicate.apply(element)) {
					result.add(element);
				}
			}
		}
		return result;
	}

}
