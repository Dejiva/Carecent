//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.19 at 01:35:49 PM IST 
//


package com.codecoop.interact.carepath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="step_flag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="care_path_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="step_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stepFlag",
    "carePathCode",
    "stepId"
})
@XmlRootElement(name = "next_care_path")
public class NextCarePath {

    @XmlElement(name = "step_flag")
    protected boolean stepFlag;
    @XmlElement(name = "care_path_code", required = true)
    protected String carePathCode;
    @XmlElement(name = "step_id")
    protected Integer stepId;

    /**
     * Gets the value of the stepFlag property.
     * 
     */
    public boolean isStepFlag() {
        return stepFlag;
    }

    /**
     * Sets the value of the stepFlag property.
     * 
     */
    public void setStepFlag(boolean value) {
        this.stepFlag = value;
    }

    /**
     * Gets the value of the carePathCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarePathCode() {
        return carePathCode;
    }

    /**
     * Sets the value of the carePathCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarePathCode(String value) {
        this.carePathCode = value;
    }

    /**
     * Gets the value of the stepId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStepId() {
        return stepId;
    }

    /**
     * Sets the value of the stepId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStepId(Integer value) {
        this.stepId = value;
    }

}
