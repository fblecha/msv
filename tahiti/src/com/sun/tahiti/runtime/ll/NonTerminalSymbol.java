package com.sun.tahiti.runtime.ll;

/**
 * base class of the non-terminal symbols of LL grammar.
 */
public abstract class NonTerminalSymbol {
	public abstract LLParser.Receiver createReceiver( LLParser.Receiver parent );
	
	/**
	 * the derived class should implement the toString method.
	 * This method is expected to produce the small (10-15 characters) strings
	 * like C&lt;String> or N&lt;name&gt;.
	 */
	public abstract String toString();
}