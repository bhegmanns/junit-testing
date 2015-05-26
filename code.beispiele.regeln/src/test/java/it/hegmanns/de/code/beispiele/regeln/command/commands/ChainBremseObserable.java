package it.hegmanns.de.code.beispiele.regeln.command.commands;

import java.util.Observable;

public class ChainBremseObserable extends Observable{

	public void addChainBremse(ChainBremse chainBremse){
		addObserver(chainBremse);
	}
	
	public void informiere()
	{
		setChanged();
		notifyObservers();
	}

}
