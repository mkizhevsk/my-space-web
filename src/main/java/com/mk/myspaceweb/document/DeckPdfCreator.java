package com.mk.myspaceweb.document;

import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.data.entity.Deck;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DeckPdfCreator {

    private static DeckPdfCreator instance;
    private PDType0Font robotoFont;
    private final Logger logger = LoggerFactory.getLogger(DeckPdfCreator.class);

    private DeckPdfCreator() {}

    public static synchronized DeckPdfCreator getInstance() {
        if (instance == null) {
            instance = new DeckPdfCreator();
        }
        return instance;
    }

    public File createSimplePdfInTemp(Deck deck, File tempDir) {

        String filePath = tempDir.getAbsolutePath() + File.separator + "output.pdf";
        File pdfFile = new File(filePath);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            initializeFont(document);


            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                createHeader(contentStream, deck);
                createTable(contentStream, deck);
            }

            document.save(pdfFile);
            logger.info("PDF file created at: " + filePath);
        } catch (IOException e) {
            logger.error("Error while creating PDF document: " + e.getMessage());
        }

        return pdfFile;
    }

    // Method to initialize the font, if not already initialized
    private void initializeFont(PDDocument document) {
        if (robotoFont == null) {
            try {
                // Load font from the resources folder
                ClassLoader classLoader = getClass().getClassLoader();
                try (InputStream fontStream = classLoader.getResourceAsStream("static/fonts/Roboto-Regular.ttf")) {
                    if (fontStream != null) {
                        robotoFont = PDType0Font.load(document, fontStream);
                    } else {
                        logger.error("Font file not found in the specified path.");
                    }
                }
            } catch (IOException e) {
                logger.error("Error loading font: " + e.getMessage());
            }
        }
    }

    private void createHeader(PDPageContentStream contentStream, Deck deck) throws IOException {
        contentStream.beginText();
        contentStream.setFont(robotoFont, 14);
        contentStream.newLineAtOffset(100, 750); // Position the text
        contentStream.showText(deck.getName()); // Set the header text to the deck name
        contentStream.endText();
    }

    private void createTable(PDPageContentStream contentStream, Deck deck) throws IOException {
        for (Card card : deck.getCards()) {
            contentStream.beginText();
            contentStream.setFont(robotoFont, 12);
            contentStream.newLineAtOffset(100, 720);
            contentStream.showText(card.getFront());
            contentStream.newLineAtOffset(0, -15); // Move to the next line
            contentStream.showText(card.getBack());
            contentStream.newLineAtOffset(0, -15); // Move to the next line
            contentStream.showText(card.getExample());
            contentStream.endText();
        }

    }
}
