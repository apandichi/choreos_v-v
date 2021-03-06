package eu.choreos.vv.servicesimulator;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockService;
import com.eviware.soapui.support.SoapUIException;

public class MockUtils {
	
	public static String getDefaultResponse(String wsdl, String operationName) throws XmlException, IOException, SoapUIException{
		WsdlProject project = new WsdlProject();
		WsdlInterface iface = WsdlInterfaceFactory.importWsdl(project, wsdl, true)[0];
		WsdlMockService service = project.addNewMockService("myMock");
		service.setPort(8088);
		com.eviware.soapui.model.mock.MockOperation mockOperation = service.addNewMockOperation(iface.getOperationByName(operationName));
		com.eviware.soapui.model.mock.MockResponse response = mockOperation.addNewMockResponse("response1");
		return  response.getResponseContent();
	}
	
	public static String getDefaultRequest(String wsdl, String operationName) throws XmlException, IOException, SoapUIException{
		WsdlProject project = new WsdlProject();
		WsdlInterface iface = WsdlInterfaceFactory.importWsdl(project, wsdl, true)[0];
		return iface.getOperationByName(operationName).getRequestAt(0).getRequestContent();
	}

}
