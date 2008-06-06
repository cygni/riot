/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * The Original Code is Riot.
 * 
 * The Initial Developer of the Original Code is
 * Neteye GmbH.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * the Initial Developer. All Rights Reserved.
 * 
 * Contributor(s):
 *   Felix Gnass [fgnass at neteye dot de]
 * 
 * ***** END LICENSE BLOCK ***** */
package org.riotfamily.components.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.riotfamily.common.web.util.ServletUtils;
import org.riotfamily.components.config.ComponentListConfig;

public class PageRequestContext {

	private Object key;
	
	private Map<String, String[]> parameters;
	
	private Map<String, Object> attributes;
	
	private Map<Long, ComponentListConfig> componentListConfigs =
		new HashMap<Long, ComponentListConfig>();

	
	private String method;
	
	private String pathInfo;
	
	private String servletPath;
	
	private String queryString;
	
	private String requestURI;
	
	@SuppressWarnings("unchecked")
	public PageRequestContext(Object key, HttpServletRequest request) {
		this.key = key;
		this.method = request.getMethod();
		this.pathInfo = request.getPathInfo();
		this.servletPath = request.getServletPath();
		this.queryString = request.getQueryString();
		this.requestURI = request.getRequestURI();
		
		this.attributes = ServletUtils.takeAttributesSnapshot(request);
		this.parameters = new HashMap<String, String[]>(request.getParameterMap());	
	}

	public Object getKey() {
		return this.key;
	}
	
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	public String getMethod() {
		return this.method;
	}

	public Map<String, String[]> getParameters() {
		return this.parameters;
	}

	public String getPathInfo() {
		return this.pathInfo;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public String getRequestURI() {
		return this.requestURI;
	}

	public String getServletPath() {
		return this.servletPath;
	}
	
	public void setComponentListConfig(Long listId, ComponentListConfig config) {
		componentListConfigs.put(listId, config);
	}
	
	public ComponentListConfig getComponentListConfig(Long listId) {
		return componentListConfigs.get(listId);
	}

}
