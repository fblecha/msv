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

import org.relaxng.datatype.ValidationContext;
import com.sun.msv.datatype.SerializationContext;

/**
 * "boolean" type.
 * 
 * type of the value object is <code>java.lang.Boolean</code>.
 * See http://www.w3.org/TR/xmlschema-2/#boolean for the spec
 * 
 * @author	Kohsuke Kawaguchi
 */
public class BooleanType extends ConcreteType {
	public static final BooleanType theInstance = new BooleanType();
	
	private BooleanType()	{ super("boolean"); }
	
	protected boolean checkFormat( String content, ValidationContext context ) {
		return "true".equals(content) || "false".equals(content)
			|| "0".equals(content) || "1".equals(content);
	}
	
	public Object convertToValue( String lexicalValue, ValidationContext context ) {
		// for string, lexical space is value space by itself
		if( lexicalValue.equals("true") )		return Boolean.TRUE;
		if( lexicalValue.equals("1") )			return Boolean.TRUE;
		if( lexicalValue.equals("0") )			return Boolean.FALSE;
		if( lexicalValue.equals("false") )		return Boolean.FALSE;
		return null;
	}

	public String convertToLexicalValue( Object value, SerializationContext context ) {
		if( value instanceof Boolean ) {
			Boolean b = (Boolean)value;
			if( b.booleanValue()==true )	return "true";
			else							return "false";
		} else
			throw new IllegalArgumentException();
	}
	
	public int isFacetApplicable( String facetName ) {
		if(facetName.equals("pattern"))		return APPLICABLE;
		return NOT_ALLOWED;
	}
	public Class getJavaObjectType() {
		return Boolean.class;
	}
}
