/*
 * @(#)$Id$
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.reader.trex.ng;

import com.sun.msv.reader.ExpressionWithoutChildState;
import com.sun.msv.grammar.relaxng.ValueType;
import com.sun.msv.grammar.Expression;
//import org.relaxng.datatype.DataTypeException;
import org.relaxng.datatype.DataType;

/**
 * parses &lt;value&gt; pattern.
 * 
 * @author <a href="mailto:kohsuke.kawaguchi@eng.sun.com">Kohsuke KAWAGUCHI</a>
 */
public class ValueState extends ExpressionWithoutChildState {
	
	protected final StringBuffer text = new StringBuffer();
	
	public void characters( char[] buf, int from, int len ) {
		text.append(buf,from,len);
	}
	public void ignorableWhitespace( char[] buf, int from, int len ) {
		text.append(buf,from,len);
	}
	
	protected Expression makeExpression() {
		final RELAXNGReader reader = (RELAXNGReader)this.reader;
		final String typeName = startTag.getAttribute("type");
		DataType type;
		
		if(typeName==null)
			// defaults to built-in token type.
			type = com.sun.msv.datatype.TokenType.theInstance;
		else
			type = reader.resolveDataType(typeName);

		Object value = type.createValue(text.toString(),reader);
		if( value==null ) {
			// this is not a good value for this type.
			reader.reportError( reader.ERR_BAD_DATA_VALUE, typeName, text.toString().trim() );
			return Expression.nullSet;	// recover by returning something.
		}
		
		return reader.pool.createTypedString( new ValueType( type, value ) );
	}
}