
package eu.choreos.vvws.simplestorews.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "purchaseResponse", namespace = "http://simplestorews.vvws.choreos.eu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "purchaseResponse", namespace = "http://simplestorews.vvws.choreos.eu/")
public class PurchaseResponse {

    @XmlElement(name = "return", namespace = "")
    private Boolean _return;

    /**
     * 
     * @return
     *     returns Boolean
     */
    public Boolean getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Boolean _return) {
        this._return = _return;
    }

}
