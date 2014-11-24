package helper;

import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JSONMapHelperTest {

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
	public void testGenerateJSONMapKey() throws IOException {
		
		String str = "{\"keyword\": \"abc\"}";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		Map<String, String> genMap = JSONMapHelper.generateJSONMap(is);

		assertThat(genMap, Matchers.<String, String> hasEntry("keyword", "abc"));
	}

	@Test
	public void testGenerateJSONMapKeyHangul() throws IOException {

		String str = "{\"keyword\": \"자바\"}";		 
		InputStream is = new ByteArrayInputStream(str.getBytes());
		Map<String, String> genMap = JSONMapHelper.generateJSONMap(is);
		
		assertThat(genMap, Matchers.<String, String> hasEntry("keyword", "자바"));
	}

}
