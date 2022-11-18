package org.example.xml;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

/**
 * purpose:XPath检索XML文件
 *
 * @author Pan Liuyang
 * 2022/11/17 16:10
 */
public class JaxenXPath {

    /*
        XPath:绝对路径
        采用绝对路径获取从根节点开始逐层的查找/students/student/name节点列表并打印信息
        /根元素/子元素/孙元素 从根元素开始，一级级向下查找，不能跨级
     */
    @Test
    public void absolutePath() {
        SAXReader saxReader = new SAXReader();
        //读取XML Document文档对象
        try {
            Document read = saxReader.read(new File("/IDEAProject/basic-learning/basic/src/main/resources/xml/xmlFile.xml"));
            List<Node> nodes = read.selectNodes("/students/student/name");
            nodes.forEach(node -> {
                Element element = (Element) node;
                System.out.println(element.getTextTrim());
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        XPath:相对路径
        采用绝对路径获取从根节点开始逐层的查找/students/student/name节点列表并打印信息
        ./子元素/孙元素 .从当前元素开始
     */
    @Test
    public void relativePath() {
        SAXReader saxReader = new SAXReader();
        //读取XML Document文档对象
        try {
            Document read = saxReader.read(new File("/IDEAProject/basic-learning/basic/src/main/resources/xml/xmlFile.xml"));
            //获取根元素
            Element rootElement = read.getRootElement();
            List<Node> nodes = rootElement.selectNodes("./student/name");
            nodes.forEach(node -> {
                Element element = (Element) node;
                System.out.println(element.getTextTrim());
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        XPath:全文检索
        //全节点的搜索
        //元素1/元素2 找元素1下的一级元素2
        //元素1//元素2 找元素1下的全部元素2
     */
    @Test
    public void fullTextSearch() {
        SAXReader saxReader = new SAXReader();
        //读取XML Document文档对象
        try {
            Document read = saxReader.read(new File("/IDEAProject/basic-learning/basic/src/main/resources/xml/xmlFile.xml"));
            //找所有的name元素
            List<Node> nodes = read.selectNodes("//name");
            nodes.forEach(node -> {
                Element element = (Element) node;
                System.out.println(element.getTextTrim());
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        XPath:属性查找
        //@在全文检索属性对象
        //元素[@属性名称] 在全文检索包含此属性的元素对象
        //元素[@属性名称=值] 在全文检索包含此属性=值的元素对象
     */
    @Test
    public void propertyLookup() {
        SAXReader saxReader = new SAXReader();
        //读取XML Document文档对象
        try {
            Document read = saxReader.read(new File("/IDEAProject/basic-learning/basic/src/main/resources/xml/xmlFile.xml"));
            //有id属性的属性对象
            List<Node> attributes = read.selectNodes("//@id");
            attributes.forEach(node -> {
                Attribute attribute = (Attribute) node;
                System.out.println(attribute.getValue());
            });
            List<Node> nodes = read.selectNodes("//student[@id]");
            nodes.forEach(node -> {
                Element element = (Element) node;
                System.out.println(element.getName());
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
