package api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookSearchAPITest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetResult() throws IOException {
		BookSearchAPI tester = new BookSearchAPI(
				"http://apis.daum.net/search/book", "DAUM_SEARCH_DEMO_APIKEY",
				"json", "q");
		String result = tester.getResult("java");

		assertTrue(getResultFromJSON(result) > 0);
	}

	@Test
	public void testGetResultHangul() throws IOException {
		BookSearchAPI tester = new BookSearchAPI(
				"http://apis.daum.net/search/book", "DAUM_SEARCH_DEMO_APIKEY",
				"json", "q");
		String result = tester.getResult("자바");

		assertTrue(getResultFromJSON(result) > 0);
	}

	public int getResultFromJSON(String rootKey) {
		JSONObject json0 = new JSONObject(rootKey);
		Object channel = json0.get("channel");
		JSONObject json1 = new JSONObject(channel.toString());
		return json1.getInt("result");
	}
}
