//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.12 at 09:56:19 PM CEST 
//


package home_automation.settings.ports;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PortType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LightConfiguration" type="{ports.settings.home_automation}ChooseType" maxOccurs="unbounded"/>
 *         &lt;element name="MotorConfiguration" type="{ports.settings.home_automation}ChooseType" maxOccurs="unbounded"/>
 *         &lt;element name="SensorConfiguration" type="{ports.settings.home_automation}ADCType" maxOccurs="unbounded"/>
 *         &lt;element name="StepperConfiguration" type="{ports.settings.home_automation}IOType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortType", propOrder = {
    "lightConfiguration",
    "motorConfiguration",
    "sensorConfiguration",
    "stepperConfiguration"
})
public class PortType {

    @XmlElement(name = "LightConfiguration", required = true)
    protected List<ChooseType> lightConfiguration;
    @XmlElement(name = "MotorConfiguration", required = true)
    protected List<ChooseType> motorConfiguration;
    @XmlElement(name = "SensorConfiguration", required = true)
    protected List<ADCType> sensorConfiguration;
    @XmlElement(name = "StepperConfiguration", required = true)
    protected List<IOType> stepperConfiguration;

    /**
     * Gets the value of the lightConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lightConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLightConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChooseType }
     * 
     * 
     */
    public List<ChooseType> getLightConfiguration() {
        if (lightConfiguration == null) {
            lightConfiguration = new ArrayList<ChooseType>();
        }
        return this.lightConfiguration;
    }

    /**
     * Gets the value of the motorConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the motorConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMotorConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChooseType }
     * 
     * 
     */
    public List<ChooseType> getMotorConfiguration() {
        if (motorConfiguration == null) {
            motorConfiguration = new ArrayList<ChooseType>();
        }
        return this.motorConfiguration;
    }

    /**
     * Gets the value of the sensorConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensorConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensorConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ADCType }
     * 
     * 
     */
    public List<ADCType> getSensorConfiguration() {
        if (sensorConfiguration == null) {
            sensorConfiguration = new ArrayList<ADCType>();
        }
        return this.sensorConfiguration;
    }

    /**
     * Gets the value of the stepperConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stepperConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStepperConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IOType }
     * 
     * 
     */
    public List<IOType> getStepperConfiguration() {
        if (stepperConfiguration == null) {
            stepperConfiguration = new ArrayList<IOType>();
        }
        return this.stepperConfiguration;
    }

}
