package it.hegmanns.de.code.beispiele.regeln.command;

import it.hegmanns.de.code.beispiele.regeln.entities.ChainTransactionCallback;
import it.hegmanns.de.code.beispiele.regeln.entities.SpringHibernateUnitOfWork;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class SpringHibernateUnitOfWorkChain extends UnitOfWorkChain<SpringHibernateTransactionChainContext, Void>{

	private HibernateTemplate hibernateTemplate;
	
	public SpringHibernateUnitOfWorkChain(HibernateTemplate hibernateTemplate , SpringHibernateUnitOfWork ... unitOfWorks) {
		super(Arrays.asList(unitOfWorks));
		this.hibernateTemplate = hibernateTemplate;
	}

	public SpringHibernateUnitOfWorkChain(
			List<UnitOfWork<SpringHibernateTransactionChainContext, Void>> unitOfWorks, HibernateTemplate hibernateTemplate) {
		super(unitOfWorks);
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	protected SpringHibernateTransactionChainContext createInitialContext() {
		SpringHibernateTransactionChainContext context = new SpringHibernateTransactionChainContext();
		context.setHibernateTemplate(hibernateTemplate);
		return context;
	}

	@Override
	protected void hookAfterUnitOfWork(
			UnitOfWork<SpringHibernateTransactionChainContext, Void> unitOfWork,
			SpringHibernateTransactionChainContext context, Void result) {
	}

	@Override
	protected Void createFinalResult(
			Map<UnitOfWork<SpringHibernateTransactionChainContext, Void>, Void> unitOfWorkResults) {
		return null;
	}


}
