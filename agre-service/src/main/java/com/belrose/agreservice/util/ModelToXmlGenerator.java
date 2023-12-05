package com.belrose.agreservice.util;

import com.belrose.agreservice.exception.AgreServiceException;
import com.belrose.agreservice.model.Address;
import com.belrose.agreservice.model.Agre;
import com.belrose.agreservice.model.Location;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelToXmlGenerator {
    public  static String modelToXml(Agre agre)  {
        try{
            var builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            var xmlDoc = builder.newDocument();
            var form = xmlDoc.createElement("Form");
            xmlDoc.appendChild(form);
            var info = xmlDoc.createElement("Info");
            var agreId = xmlDoc.createElement("AgreId");
            var type = xmlDoc.createElement("Type");
            var version = xmlDoc.createElement("Version");
            var name = xmlDoc.createElement("Name");

            agreId.appendChild(xmlDoc.createTextNode(Optional.ofNullable(agre.getAgreId()).orElse("")));
            type.appendChild(xmlDoc.createTextNode(Optional.ofNullable(agre.getType()).orElse("")));
            version.appendChild(xmlDoc.createTextNode(Optional.ofNullable(agre.getVersion()).orElse("")));
            name.appendChild(xmlDoc.createTextNode(Optional.ofNullable(agre.getName()).orElse("")));

            info.appendChild(agreId);
            info.appendChild(type);
            info.appendChild(version);
            info.appendChild(name);

            form.appendChild(info);

            var addresses = xmlDoc.createElement("Addresses");
            setAddresses(agre,xmlDoc,addresses);
            form.appendChild(addresses);

            var locations= xmlDoc.createElement("Locations");
            setLocations(agre,xmlDoc,locations);
            form.appendChild(locations);

            xmlDoc.setXmlStandalone(true);
            var domSource = new DOMSource(xmlDoc);
            Writer writer = new StringWriter();
            var streamResult = new StreamResult(writer);

            var transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD,"");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET,"");
            transformerFactory.setAttribute("indent-number","2");

            var transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT,"Yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"Yes");

            transformer.transform(domSource,streamResult) ;
            return writer.toString();

        } catch (Exception ex){
            throw new AgreServiceException("Error to convert model to xml");
        }


    }

    private static void setAddresses(Agre agre, Document xmlDoc, Element addresses) {
        List<Address> addressList = CollectionUtils.isEmpty(agre.getAddressList())? Collections.emptyList():agre.getAddressList();
        for (Address ad:addressList){
            var address = xmlDoc.createElement("Address");

            var phoneNumber = xmlDoc.createElement("PhoneNumber");
            var email = xmlDoc.createElement("Email");
            var code = xmlDoc.createElement("Code");

            phoneNumber.appendChild(xmlDoc.createTextNode(Optional.ofNullable(ad.getPhoneNumber()).orElse("")));
            email.appendChild(xmlDoc.createTextNode(Optional.ofNullable(ad.getEmail()).orElse("")));
            code.appendChild(xmlDoc.createTextNode(Optional.ofNullable(ad.getCode()).orElse("")));
            address.appendChild(phoneNumber);
            address.appendChild(email);
            address.appendChild(code);
            addresses.appendChild(address);
        }
    }

    private static void setLocations(Agre agre, Document xmlDoc, Element locations) {
        List<Location> locationList = CollectionUtils.isEmpty(agre.getLocationList())? Collections.emptyList():agre.getLocationList();
        for (Location loc:locationList){
            var location = xmlDoc.createElement("Location");

            var road = xmlDoc.createElement("Road");
            var city = xmlDoc.createElement("City");
            var country = xmlDoc.createElement("Country");

            road.appendChild(xmlDoc.createTextNode(Optional.ofNullable(loc.getRoad()).orElse("")));
            city.appendChild(xmlDoc.createTextNode(Optional.ofNullable(loc.getCity()).orElse("")));
            country.appendChild(xmlDoc.createTextNode(Optional.ofNullable(loc.getCountry()).orElse("")));
            location.appendChild(road);
            location.appendChild(city);
            location.appendChild(country);
            locations.appendChild(location);
        }
    }
}
