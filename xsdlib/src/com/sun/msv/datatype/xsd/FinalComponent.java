/*
 * @(#)$Id$
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.datatype.xsd;

import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;
import com.sun.msv.datatype.SerializationContext;

/**
 * "final" component.
 * 
 * @author <a href="mailto:kohsuke.kawaguchi@eng.sun.com">Kohsuke KAWAGUCHI</a>
 */
public final class FinalComponent extends Proxy {
	
	private final int finalValue;
	
	public FinalComponent( XSDatatypeImpl baseType, int finalValue ) {
		this( baseType.getName(), baseType, finalValue );
	}
	
	public FinalComponent( String newTypeName, XSDatatypeImpl baseType, int finalValue ) {
		super( newTypeName, baseType );
		this.finalValue = finalValue;
	}
	
	public boolean isFinal( int derivationType ) {
		if( (finalValue&derivationType) != 0 )	return true;
		return super.isFinal(derivationType);
	}
}
