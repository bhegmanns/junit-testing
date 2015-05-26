package it.hegmanns.de.code.beispiele.regeln.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UnitOfWorkChain<C, R> {
	
	private List<UnitOfWork<C, R>> unitOfWorks;
	
	protected abstract C createInitialContext();
	protected abstract void hookAfterUnitOfWork(UnitOfWork<C, R> unitOfWork, C context, R result);
	protected abstract R createFinalResult(Map<UnitOfWork<C, R>, R> unitOfWorkResults);
	
	
	public UnitOfWorkChain(List<UnitOfWork<C, R>> unitOfWorks){
		this.unitOfWorks = unitOfWorks;
	}
	
	public R process(){
		Map<UnitOfWork<C, R>, R> unitOfWorkResults = new HashMap<UnitOfWork<C,R>, R>();
		C context = createInitialContext();
		
		for (UnitOfWork<C, R> unitOfWork : unitOfWorks)
		{
			R result = unitOfWork.doWork(context);
			hookAfterUnitOfWork(unitOfWork, context, result);
			unitOfWorkResults.put(unitOfWork, result);
		}
		
		return createFinalResult(unitOfWorkResults);
	}

}
