package com.mk.myspaceweb.document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.data.entity.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DeckPdfCreator {

    private static DeckPdfCreator instance;
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

        Document document = new Document(PageSize.A4, 42f, 40f, 36, 36);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            Font headerFont = loadFont("static/fonts/Roboto-Bold.ttf", 12);
            Font textFont = loadFont("static/fonts/Roboto-Regular.ttf", 11);

            createHeader(document, deck, headerFont);
            createTable(document, deck, textFont);

            document.close();
            logger.info("PDF file created at: " + filePath);

        } catch (DocumentException | IOException e) {
            logger.error("Error while creating PDF document: " + e.getMessage());
        }

        return pdfFile;
    }

    private Font loadFont(String fontPath, float size) throws IOException, DocumentException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(baseFont, size);
    }

    private void createHeader(Document document, Deck deck, Font headerFont) throws DocumentException {
        Paragraph header = new Paragraph(deck.getName(), headerFont);
        header.setAlignment(Element.ALIGN_CENTER);

        header.setSpacingBefore(0f);
        header.setSpacingAfter(15f);

        document.add(header);
    }

    private void createTable(Document document, Deck deck, Font textFont) throws DocumentException {

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{30f, 30f, 40f});

        createTableHeader(table, textFont);

        for (Card card : deck.getCards()) {
            table.addCell(new PdfPCell(new Paragraph(card.getFront(), textFont)));
            table.addCell(new PdfPCell(new Paragraph(card.getBack(), textFont)));
            table.addCell(new PdfPCell(new Paragraph(card.getExample(), textFont)));
        }

        document.add(table);
    }

    private void createTableHeader(PdfPTable table, Font textFont) {

        PdfPCell cell;

        cell = new PdfPCell(new Paragraph("Front", textFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Back", textFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Example", textFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
    }
}
