package org.example.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * purpose:Dom4JParseXML Dom4J解析XML文件
 *
 * @author Pan Liuyang
 * 2022/11/16 21:20
 */
public class Dom4JParseXML {
    public static void main(String[] args) {
        //创建XML解析器
        SAXReader saxReader = new SAXReader();
        try {
            //读取XML Document文档对象
            Document read = saxReader.read(new File("/IDEAProject/basic-learning/basic/src/main/resources/xml/xmlFile.xml"));
            //获取根元素对象
            Element rootElement = read.getRootElement();
            System.out.println("rootElement.getName() = " + rootElement.getName());
            //获取根元素的所有子元素
            List<Element> elements = rootElement.elements();
            System.out.println(elements.stream().map(Element::getName).collect(Collectors.joining(",")));
            for (Element element : elements) {
                String name = element.elementText("name");
                System.out.println(name);
                //获取元素的属性
                List<Attribute> attributes = element.attributes();
                attributes.forEach(attribute -> System.out.println(attribute.getName() + attribute.getValue()));
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
