package it.hegmanns.de.code.beispiele.regeln.command;

public interface UnitOfWork<C, R> {

	
	public R doWork(C context);
}
