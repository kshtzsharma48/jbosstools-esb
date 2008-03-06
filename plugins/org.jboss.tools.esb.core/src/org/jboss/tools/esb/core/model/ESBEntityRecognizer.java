/******************************************************************************* 
 * Copyright (c) 2007 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.esb.core.model;

import java.io.IOException;

import org.jboss.tools.common.model.loaders.EntityRecognizer;
import org.jboss.tools.common.xml.XMLEntityResolver;
import org.jboss.tools.esb.core.ESBCorePlugin;

/**
 * @author Viacheslav Kabanovich
 */
public class ESBEntityRecognizer implements EntityRecognizer, ESBConstants {

    public ESBEntityRecognizer() {}

    public String getEntityName(String ext, String body) {
        if(body == null) return null;
    	if(!isSchema(body)) {
    		return null;
    	}
    	
//    	int i = body.indexOf("xsi:schemaLocation"); //$NON-NLS-1$
    	int i = body.indexOf("xmlns="); //$NON-NLS-1$
    	if(i < 0) return null;
    	int j = body.indexOf("\"", i); //$NON-NLS-1$
    	if(j < 0) return null;
    	int k = body.indexOf("\"", j + 1); //$NON-NLS-1$
    	if(k < 0) return null;
    	String schemaLocation = body.substring(j + 1, k);
    	
    	int i101 = schemaLocation.indexOf("1.0.1"); //$NON-NLS-1$
    	if(i101 >= 0) {
    		return ENT_ESB_FILE_101;
    	}
        return null;
    }
    
    private boolean isSchema(String body) {
    	int i = body.indexOf("<jbossesb"); //$NON-NLS-1$
    	if(i < 0) return false;
    	int j = body.indexOf(">", i); //$NON-NLS-1$
    	if(j < 0) return false;
    	String s = body.substring(i, j);
    	return s.indexOf("\"" + SCHEMA_101 + "\"") > 0; //$NON-NLS-1$
    }
    
}