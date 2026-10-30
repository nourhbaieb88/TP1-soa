
package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== 1. VALIDATION XML / XSD =====");

        if (validerXML()) {
            System.out.println("\n===== 2. INTERROGATION XPATH =====");
            interrogerXPath();
        }
    }

    // Fonctionnalité 1 : validation XML/XSD
    public static boolean validerXML() {

        try (InputStream xml =
                     Main.class.getResourceAsStream("/cinema.xml");
             InputStream xsd =
                     Main.class.getResourceAsStream("/cinema.xsd")) {

            // Vérifier que les deux fichiers existent
            if (xml == null) {
                System.out.println("Erreur : fichier cinema.xml introuvable.");
                return false;
            }

            if (xsd == null) {
                System.out.println("Erreur : fichier cinema.xsd introuvable.");
                return false;
            }

            // Créer une fabrique de schémas XSD
            SchemaFactory factory =
                    SchemaFactory.newInstance(
                            XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Charger le schéma XSD
            Schema schema = factory.newSchema(new StreamSource(xsd));

            // Créer un validateur
            Validator validator = schema.newValidator();

            // Valider le document XML
            validator.validate(new StreamSource(xml));

            System.out.println("Le document XML est VALIDE.");

            return true;

        } catch (Exception e) {

            System.out.println("Document INVALIDE : " + e.getMessage());

            return false;
        }
    }

    // Fonctionnalité 2 : interrogation XPath
    public static void interrogerXPath() {

        try (InputStream xml =
                     Main.class.getResourceAsStream("/cinema.xml")) {

            // Vérifier que le fichier XML existe
            if (xml == null) {
                System.out.println("Erreur : fichier cinema.xml introuvable.");
                return;
            }

            // Charger le document XML dans un objet DOM
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(xml);

            // Créer un objet XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Première requête : récupérer tous les titres
            afficher(doc, xpath,
                    "Tous les titres de films",
                    "//film/titre");

            // Deuxième requête : récupérer les films en salle
            afficher(doc, xpath,
                    "Films en salle",
                    "//film[enSalle='true']/titre");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // Méthode pour exécuter et afficher une requête XPath
    private static void afficher(Document doc, XPath xpath,
                                 String libelle, String requete)
            throws Exception {

        // Compiler la requête XPath
        XPathExpression expr = xpath.compile(requete);

        // Exécuter la requête et récupérer les résultats
        NodeList nodes = (NodeList)
                expr.evaluate(doc, XPathConstants.NODESET);

        System.out.println("\n" + libelle + " : " + requete);

        // Parcourir et afficher les résultats
        for (int i = 0; i < nodes.getLength(); i++) {

            System.out.println("  - "
                    + nodes.item(i).getTextContent());
        }
    }
}
