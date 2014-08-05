package eu.choreos.vv.clientgenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.rest.RestMethod;
import com.eviware.soapui.impl.rest.RestRequest;
import com.eviware.soapui.impl.rest.RestRequestInterface.HttpMethod;
import com.eviware.soapui.impl.rest.RestResource;
import com.eviware.soapui.impl.rest.RestService;
import com.eviware.soapui.impl.rest.RestServiceFactory;
import com.eviware.soapui.impl.rest.support.RestParamProperty;
import com.eviware.soapui.impl.rest.support.RestParamsPropertyHolder.ParameterStyle;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlSubmit;
import com.eviware.soapui.impl.wsdl.WsdlSubmitContext;
import com.eviware.soapui.model.iface.Request.SubmitException;
import com.eviware.soapui.model.iface.Response;
import com.eviware.soapui.support.SoapUIException;

public class RSClient {

	private String basePath;
	private String baseUri;
	private int port;
	
	public RSClient(String baseUri, String basePath, int port){
		this.baseUri = baseUri;
		this.basePath = basePath;
		this.port = port;
	}
	
	private String makeRequest(HttpMethod method, String path, Map<String, String> parameters) {
		RestRequest request = request(method, path, parameters);
		return responseFrom(request);
	}

	private String responseFrom(RestRequest request) {
		try {
			boolean async = false;
			WsdlSubmit<RestRequest> submit = request.submit(new WsdlSubmitContext(request), async);
			Response response = submit.getResponse();
			String asString = response.getContentAsString();
			return asString == null ? "" : asString;
		} catch (SubmitException e) {
			throw new RuntimeException(e);
		}
	}
	
	private RestRequest request(HttpMethod method, String path, Map<String, String> parameters) {
		try {
			RestRequest request = request();
			request.setEndpoint(endpoint());
			request.setMethod(method);
			request.setPath(path);
			
			if (method == HttpMethod.POST) {
				request.setPostQueryString(true);
			}
			
			//addParameters(request, parameters, ParameterStyle.HEADER);
			addParameters(request, parameters, ParameterStyle.QUERY);
				
			return request;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void addParameters(RestRequest request, Map<String, String> parameters, ParameterStyle style) {
		for (Entry<String, String> entry : parameters.entrySet()) {
			RestParamProperty property = request.getParams().addProperty(entry.getKey());
			property.setStyle(style);
			property.setValue(entry.getValue());
		}
	}

	private RestRequest request() throws XmlException, IOException, SoapUIException {
		WsdlProject wsdlProject = new WsdlProject();
		RestService service = new RestServiceFactory().createNew(wsdlProject, "restService");
		RestResource restResource = service.addNewResource("myResource", "myPath");
		RestMethod restMethod = restResource.addNewMethod("myMethod");
		RestRequest request = restMethod.addNewRequest("myRequest");
		request.setMediaType("*/*");
		request.setAccept("*/*");
		return request;
	}

	private String endpoint() {
		return String.format("%s:%d%s", baseUri, port, basePath);
	} 

	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters GET parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String get(String path, Map<String, String> parameters) {
		return makeRequest(HttpMethod.GET, path, parameters);
	}
	
	public String get(String path){
		return makeRequest(HttpMethod.GET, path, new HashMap<String, String>());
	}

	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters POST parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String post(String path, Map<String, String> parameters) {
		return makeRequest(HttpMethod.POST, path, parameters);
	}
	
	public String post(String path){
		return makeRequest(HttpMethod.POST, path, new HashMap<String, String>());
	}

	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters PUT parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String put(String path, Map<String, String> parameters) {
		return makeRequest(HttpMethod.PUT, path, parameters);
	}
	
	public String put(String path){
		return makeRequest(HttpMethod.PUT, path, new HashMap<String, String>());
	}

	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters DELETE parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String delete(String path, Map<String, String> parameters) {
		return makeRequest(HttpMethod.DELETE, path, parameters);
	}
	
	public String delete(String path){
		return makeRequest(HttpMethod.DELETE, path, new HashMap<String, String>());
	}

}
