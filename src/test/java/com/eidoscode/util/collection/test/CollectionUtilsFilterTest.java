package com.eidoscode.util.collection.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.eidoscode.util.collection.CollectionsUtils;
import com.eidoscode.util.collection.Predicate;

/**
 * Unit test to check the method
 * {@link CollectionsUtils#filter(Collection, Predicate)}.
 * 
 * @author eantonini
 * @since 1.0
 * @version 1.0
 */
public class CollectionUtilsFilterTest {

	@Test(expected = IllegalAccessException.class)
	public void testFailConstructor() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<CollectionsUtils> constructor = CollectionsUtils.class
				.getDeclaredConstructor();
		// constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void testGiveConstructorAccess() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<CollectionsUtils> constructor = CollectionsUtils.class
				.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test(expected = NullPointerException.class)
	public void testFilterNPESource() {
		CollectionsUtils.filter(null, null);
	}

	@Test(expected = NullPointerException.class)
	public void testFilterNPEPredicate() {
		CollectionsUtils.filter(getList(), null);
	}

	@Test
	public void testFilterReturnZero() {
		Collection<String> b = CollectionsUtils.filter(getList(),
				new Predicate<String>() {

					@Override
					public boolean apply(String type) {
						return false;

					};
				});
		assertEquals(0, b.size());
	}

	@Test
	public void testFilterBlankList() {
		Collection<String> b = CollectionsUtils.filter(new ArrayList<String>(),
				new Predicate<String>() {

					@Override
					public boolean apply(String type) {
						return false;

					};
				});
		assertEquals(0, b.size());
	}

	@Test
	public void testFilterPassAll() {
		List<String> list = getList();
		long result = list.size();
		Collection<String> b = CollectionsUtils.filter(list,
				new Predicate<String>() {

					@Override
					public boolean apply(String type) {
						return true;

					};
				});
		assertEquals(result, b.size());
	}

	@Test
	public void testFilterJustObjectStartByA() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("AA1");
		list.add("AA2");
		list.add("AA3");
		list.add("AA4");
		list.add("AA5");
		list.add("AA6");
		list.add("BA1");
		list.add("BA2");
		list.add("BA3");
		list.add("BA4");
		list.add("BA5");
		list.add("BA6");

		Collection<String> b = CollectionsUtils.filter(list,
				new Predicate<String>() {

					@Override
					public boolean apply(String type) {
						return (type.charAt(0) == 'A');

					};
				});
		assertEquals(6, b.size());
	}

	@Ignore
	public ArrayList<String> getList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("AA1");
		list.add("AA2");
		list.add("AA3");
		list.add("AA4");
		list.add("AA5");
		list.add("AA6");
		list.add("BA1");
		list.add("BA2");
		list.add("BA3");
		list.add("BA4");
		list.add("BA5");
		list.add("BA6");
		return list;
	}

}
