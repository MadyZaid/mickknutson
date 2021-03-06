package com.baselogic.tutorials.service;

// Hamcrest
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

// JUnit
//import static org.junit.Assert.*;
import com.baselogic.tutorials.domain.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

// Mockito
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

// PowerMock

// Project
import com.baselogic.tutorials.dao.OrderDAO;

import com.baselogic.tutorials.util.ExampleUtils;

/**
 * OrderMockServiceTests
 *
 * <p>
 * Spring Certification objective: 1.2 Lifecycle
 * </p>
 *
 * at_see <a
 *      href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective
 *      1.2 Lifecycle</a>
 *
 * @author Mick Knutson
 * at_see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * at_see <a href="http://linkedin.com/in/mickknutson">LinkedIN:
 *      http://linkedin.com/in/mickknutson</a>
 * at_see <a href="http://twitter.com/mickknutson">Twitter:
 *      http://twitter.com/mickknutson</a>
 * at_see <a href="http://github.com/mickknutson">Git hub:
 *      http://github.com/mickknutson</a>
 *
 * at_see <a
 *      href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE
 *      6 Cookbook Packt</a>
 * at_see <a
 *      href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE
 *      6 Cookbook Amazon</a>
 *
 * @since 2012
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderMockServiceTests {


	/**
	 * Prepare Objects that will execute user defined API behavior
	 */
	@Mock
	OrderDAO supportingDao;

	@Mock
	ExampleUtils supportingUtils;

	@Mock
	UnImplementedService unImplementedService;

	/**
	 * After Mocks are created
	 */
	@InjectMocks
	OrderServiceImpl classUnderTest = new OrderServiceImpl();

	@Before
	public void beforeEachUnitTest() {

		MockitoAnnotations.initMocks(this);
	}

	@After
	public void afterEachUnitTest() {
	}


	// ----- Test methods ----------------------------------------------------//

	@Test
	public void testGetMessage() throws Exception {

		String result = classUnderTest.getMessage();

		assertThat(result, is(nullValue()));
	}

	@Test
	public void testNonMockedTest() throws Exception {

		String result = classUnderTest.getMessage();
		assert result == null;

		result = classUnderTest.staticFunction();
		assert result != null;

		result = classUnderTest.localFunction();
		assert result != null;

		result = classUnderTest.voidedFunction();
		assert result != null;

		result = classUnderTest.staticFunctions();
		assert result != null;

		result = classUnderTest.complexFunctions();
		assert result != null;

		result = classUnderTest.duplicateCallsFunction();
		assert result != null;

		result = classUnderTest.executeInternalPrivate();
		assert result != null;

		// assertThat(result, is(nullValue()));
	}

	// ----- Mock Test methods -----------------------------------------------//
	/*-----------------------------------------------------------------------//
	 * Mockito:
	 * http://code.google.com/p/mockito/
	 *
	 * PowerMock:
	 * http://code.google.com/p/powermock/
	 *
	 * PowerMock Mockito Extensions:
	 * http://code.google.com/p/powermock/wiki/MockitoUsage13
	 *-----------------------------------------------------------------------*/

	/**
	 * Intermediate
	 *
	 * Create a mock and validate the order the mocks are called.
	 *
	 * @throws Exception
	 */
	@Test
	public void callsToMockInOrder() throws Exception {

		when(supportingUtils.nestedFunction()).thenReturn("1st mocked message");

		when(supportingUtils.nestedFunctionTwo()).thenReturn(
				"Another mock being called");

		InOrder inOrder = inOrder(supportingUtils);


		String result = classUnderTest.callsFunctionInOrder();

		assertThat(
				result,
				is("OrderServiceImpl: callsFunctionInOrder(): 1st mocked message: Another mock being called"));

		// nestedFunction() should have been called first
		inOrder.verify(supportingUtils).nestedFunction();

		// nestedFunctionTwo() should have been called second
		inOrder.verify(supportingUtils).nestedFunctionTwo();

	}

	/**
	 * Basic
	 *
	 * Mock a class to throw an exception inside the Class under test.
	 *
	 * @throws Exception
	 */
	@Test
	public void mockException() throws Exception {

		when(supportingUtils.nestedFunction()).thenThrow(
				new RuntimeException("mock Exception"));

		String result = classUnderTest.localFunction();

		assertThat(result,
				is("OrderServiceImpl: localFunction: mock Exception"));

		verify(supportingUtils).nestedFunction();
	}

	/*
	 * @Test //basic public void mockNotInvoked() throws Exception {
	 *
	 * // Control input Order orderInput = new Order();
	 * orderInput.setDescription("Mick's Order");
	 *
	 * // Control Sample Order orderOutput = new Order();
	 * orderOutput.setDescription("Someone Else's Order");
	 *
	 * // Create Mock Behavior when(supportingDao.placeOrder(orderInput))
	 * .thenReturn(orderOutput);
	 *
	 * // execute class under test Order resultOrder =
	 * classUnderTest.placeOrder2(orderInput);
	 *
	 * assertThat(resultOrder.getDescription(), is(notNullValue()));
	 *
	 * // Verify behavior was executed
	 * verify(supportingDao).placeOrder(orderInput); }
	 */

	/**
	 * Intermediate
	 *
	 * Mock an non-implemented Interface such that would be done during Test
	 * Driven Development (TDD)
	 *
	 * @throws Exception
	 */
	@Test
	public void mockUnImplementedService() throws Exception {

		when(unImplementedService.getMessage()).thenReturn(
				"unImplementedService message");

		String result = unImplementedService.getMessage();

		assertThat(result, is("unImplementedService message"));

		verify(unImplementedService).getMessage();
	}

	/**
	 * Intermediate
	 *
	 * Create a mock to be called multiple times by the Class under test.
	 *
	 * @throws Exception
	 */
	@Test
	public void multipleCallsToMock() throws Exception {

		when(supportingUtils.nestedFunction()).thenReturn("1st mocked message")
				.thenReturn("2nd mocked message")
				.thenReturn("nth mocked message");

		// 2nd mock called
		// 3rd mock called

		when(supportingUtils.nestedFunctionTwo()).thenReturn(
				"Another mock being called");

		String result = classUnderTest.duplicateCallsFunction();

		assertThat(
				result,
				is("OrderServiceImpl: duplicateCallsFunction(): 1st mocked message: 2nd mocked message: nth mocked message: nth mocked message"));

		verify(supportingUtils, atLeastOnce()).nestedFunction();

		verify(supportingUtils, atLeast(2)).nestedFunction();

		verify(supportingUtils, times(4)).nestedFunction();

	}


	/**
	 * Basic
	 *
	 * Partially mock the Class under test to mock certain methods.
	 *
	 * @throws Exception
	 */
	@Test
	public void partialMock() throws Exception {

		// Not mocked
		String result = classUnderTest.getProxiedMessage();

		assertThat(result, is(nullValue()));

		// Partial Mock
		OrderServiceImpl partialMock = spy(classUnderTest);

		when(partialMock.getMessage()).thenReturn("partially mocked message");

		String partialResult = partialMock.getProxiedMessage();

		assertThat(partialResult, is("partially mocked message"));

		verify(partialMock).getMessage();
	}

	/*
	 * TODO: @Test //int public void mockMultipleExceptions() throws Exception {
	 * //orderService.complexFunctions()
	 *
	 * }
	 */

	/**
	 * Basic
	 *
	 * Simple Mock used in a Class under test.
	 *
	 * @throws Exception
	 */
	@Test
	// basic
	public void simpleMock() throws Exception {

		// Control input
		Order orderInput = new Order();
		orderInput.setDescription("Mick's Order");

		// Control Sample
		Order orderOutput = new Order();
		orderOutput.setDescription("Someone Else's Order");

		// Create Mock Behavior
		when(supportingDao.placeOrder(any(Order.class)))
				.thenReturn(orderOutput);

		// No need to manually wire Mock's into classUnderTest (CUT).
		// no-op

		// execute class under test
		Order resultOrder = classUnderTest.placeOrder(orderInput);

		assertThat(resultOrder.getDescription(), is("Someone Else's Order"));

		// Verify behavior for supporting were executed
		verify(supportingDao).placeOrder(any(Order.class));
	}

	/**
	 * Basic
	 *
	 * Stub the call to a method that returns void inside the Class under test.
	 *
	 * @throws Exception
	 */
	@Test
	public void stubVoidMock() throws Exception {

		doNothing().when(supportingUtils).voidMethod();

		String result = classUnderTest.voidedFunction();

		assertThat(result, is("OrderServiceImpl: localFunction"));

		verify(supportingUtils).voidMethod();
	}

	/**
	 * Basic
	 *
	 * Stub the call to a method that returns void inside the Class under test.
	 *
	 * @throws Exception
	 */
	@Test
	public void stubVoidMockException() throws Exception {

		doThrow(new RuntimeException("void stubbed method exception")).when(
				supportingUtils).voidMethod();

		String result = classUnderTest.voidedFunction();

		assertThat(
				result,
				is("OrderServiceImpl: localFunction: void stubbed method exception"));

		verify(supportingUtils).voidMethod();
	}


	/**
	 * Advanced
	 *
	 * Create a mock that delays the response back to the Class under test.
	 *
	 * @throws Exception
	 */
	@Test
	public void timeDelayMock() throws Exception {

		when(supportingUtils.nestedFunction()).thenAnswer(
				delayedAnswerWithObject("delayed response", 50));

		long start = System.currentTimeMillis();

		String result = classUnderTest.localFunction();

		long end = System.currentTimeMillis();

		assertThat(result,
				is("OrderServiceImpl: localFunction: delayed response"));

		assertThat(end - start, is(greaterThan(49L)));

		verify(supportingUtils, times(1)).nestedFunction();
	}

	// Helper method for mock delays.
	@SuppressWarnings("rawtypes")
	private static Answer delayedAnswerWithObject(final Object o,
			final long delay) {

		return new Answer() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Thread.sleep(delay);
				return o;
			}
		};
	}

} // the end...
