package db;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ksenia on 11.12.2016.
 */
public class XMLdb {

    private static final String LOGIN_NAME = "Ksenia00";
    private static final String DOMAIN = "@tut.by";
    private static final String password = "AutomationTest";
    private static final String LOGIN_TAG_NAME = "Login";
    private static final String PASSWORD_TAG_NAME = "Password";
    private static final String ROOT_TAG_NAME = "DB";
    private static final String PATH = "E:\\java\\EPAM\\src\\main\\resources\\XMLdb.xml";
    private static String accountData[] = new String[2];

    public static void createXML() {

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element theRoot = document.createElement(ROOT_TAG_NAME);
            document.appendChild(theRoot);

            for (int i = 1; i < 11; i++) {

                Element element = document.createElement((ROOT_TAG_NAME + Integer.toString(i)));
                theRoot.appendChild(element);

                Element login = document.createElement(LOGIN_TAG_NAME);
                login.appendChild(document.createTextNode(createLoginName(i)));
                element.appendChild(login);

                Element password = document.createElement(PASSWORD_TAG_NAME);
                password.appendChild(document.createTextNode(createPassword(i)));
                element.appendChild(password);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File(PATH));
            transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    private static String createLoginName(int i) {
        return LOGIN_NAME + i + DOMAIN;
    }

    private static String createPassword(int i) {
        return password + i;
    }

    public static String[] readXML(int account) {

        File xmlFile = new File(PATH);

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            NodeList list = document.getElementsByTagName((ROOT_TAG_NAME + Integer.toString(account)));
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    accountData[0] = element.getElementsByTagName(LOGIN_TAG_NAME).item(i).getTextContent();
                    accountData[1] = element.getElementsByTagName(PASSWORD_TAG_NAME).item(i).getTextContent();
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Account with such number doesn't exist!");
        }
        return accountData;
    }

    public static String getLogin(int acc) {
        String accData[] = readXML(acc);
        return accData[0];
    }

    public static String getPassword(int acc) {
        String accData[] = readXML(acc);
        return accData[1];
    }
}
