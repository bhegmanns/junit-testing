package hegmanns.it.de.junit.testing.abstractservice;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

@RunWith(MockitoJUnitRunner.class)
public class AbstractServiceTest {

	@Mock
	AbstractService abstractService;
	
	@Test
	public void nullUebergeben()
	{
		MatcherAssert.assertThat(abstractService.ermittleAnzahl(null), Matchers.is(0));
	}
	
	@Test
	public void leerstringUebergeben()
	{
		MatcherAssert.assertThat(abstractService.ermittleAnzahl(""), Matchers.is(0));
	}
	
	@Test
	public void stringUebergeben()
	{
		Mockito.when(abstractService.findeErgebnis1(Mockito.anyString())).thenReturn(1);
		Mockito.when(abstractService.findeErgebnis2(Mockito.anyString(), Mockito.eq(1))).thenReturn(1);
		
		MatcherAssert.assertThat(abstractService.ermittleAnzahl(" "), Matchers.is(1));
		Mockito.verify(abstractService, Mockito.never()).aggregiere(Mockito.anySetOf(Integer.class));
	}
	
	@Test
	public void stringUebergebenMitUngleichenZwischenergebnis()
	{
		Mockito.when(abstractService.findeErgebnis1(Mockito.anyString())).thenReturn(1);
		Mockito.when(abstractService.findeErgebnis2(Mockito.anyString(), Mockito.eq(1))).thenReturn(2);
		Mockito.when(abstractService.auswaehlen(Mockito.anySetOf(Integer.class))).thenReturn(1);
		MatcherAssert.assertThat(abstractService.ermittleAnzahl(" "), Matchers.is(1));
		Mockito.verify(abstractService, Mockito.times(1)).auswaehlen(Mockito.anySetOf(Integer.class));
		Mockito.verify(abstractService, Mockito.times(1)).aggregiere(Mockito.anySetOf(Integer.class));
		
		
	}
}
