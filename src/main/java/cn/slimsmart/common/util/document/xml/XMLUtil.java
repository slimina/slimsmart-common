package cn.slimsmart.common.util.document.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unchecked")
public class XMLUtil {

	static final String blank = "";
	static final String XML_DECLARE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
	
	private XMLUtil(){}

	/**
	 * 注意：如果object为null,则返回 ""
	 * 
	 * @param object
	 * @return
	 * @throws JAXBException
	 */
	public static String toStandardXML(Object object) throws JAXBException {
		if (object == null) {
			return blank;
		}
		JAXBContext jc = JAXBContext.newInstance(object.getClass());
		Marshaller m = jc.createMarshaller();
		// 构建一个流，用来存储xml内容
		StringWriter writer = new StringWriter();
		m.marshal(object, writer);
		// 得到xml字符串
		return writer.toString();
	}

	/**
	 * 注意：如果object为null,则返回 ""
	 * 
	 * @param object
	 * @param rootElement
	 * @return
	 * @throws JAXBException
	 */
	public static String toBankXML(Object object, String rootElement) throws JAXBException {
		if (object == null) {
			return blank;
		}
		String xml = toStandardXML(object).replace(XML_DECLARE, "");
		StringBuilder sBuilder = new StringBuilder(260);
		sBuilder.append("<").append(rootElement).append(">").append(xml).append("</").append(rootElement).append(">");
		return sBuilder.toString();
	}

	public static <T> T toBean(String xml, Class<T> targetClass) throws JAXBException {
		// 将一个xml字符串，转化为对应的java对象
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		JAXBContext jc = JAXBContext.newInstance(targetClass);
		Unmarshaller um = jc.createUnmarshaller();
		return (T) um.unmarshal(is);
	}
}
