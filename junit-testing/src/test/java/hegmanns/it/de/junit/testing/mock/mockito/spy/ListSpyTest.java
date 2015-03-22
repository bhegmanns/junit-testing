package hegmanns.it.de.junit.testing.mock.mockito.spy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class ListSpyTest {

	@Test
	public void beispielSpy(){
		List<String> strings = new ArrayList<>();
		
		List<String> spyStrings = Mockito.spy(strings);
		spyStrings.addAll(Arrays.asList("A", "B"));
		spyStrings.add("C");
		int anzahlElemente = spyStrings.size();
		
		assertThat(anzahlElemente, is(3));
		Mockito.verify(spyStrings).size();
		Mockito.verify(spyStrings, never()).add("A");
		Mockito.verify(spyStrings, never()).add("B");
		Mockito.verify(spyStrings).add("C");
	}
	
	
}
