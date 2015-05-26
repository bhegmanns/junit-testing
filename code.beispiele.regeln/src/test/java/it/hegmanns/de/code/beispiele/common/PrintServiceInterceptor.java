package it.hegmanns.de.code.beispiele.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PrintServiceInterceptor implements MethodInterceptor{

	public PrintServiceInterceptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		System.out.println("START");
		
		Object result = invocation.proceed();

		System.out.println("ENDE");
		return result;
	}

}
