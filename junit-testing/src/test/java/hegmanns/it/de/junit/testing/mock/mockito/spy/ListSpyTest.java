package hegmanns.it.de.junit.testing.mock.mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

public class ListSpyTest {

	@Test
	public void beispielSpy(){
		List<String> strings = new ArrayList<>();
		
		List<String> spyStrings = Mockito.spy(strings);
		
		spyStrings.add("A");
		spyStrings.add("B");
		System.out.println("" + spyStrings.size());
		
		Mockito.verify(spyStrings).size();
		Mockito.verify(spyStrings).add("A");
		Mockito.verify(spyStrings).add("B");
	}
}
