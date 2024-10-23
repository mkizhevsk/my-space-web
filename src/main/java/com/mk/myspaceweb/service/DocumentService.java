package com.mk.myspaceweb.service;

import com.mk.myspaceweb.data.repository.DeckRepository;
import com.mk.myspaceweb.document.DeckPdfCreator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private  final DeckRepository deckRepository;

    private final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    public File generateDeckPdf(int deckId) {

        var deck = deckRepository.findById(deckId).orElse(null);
        if (deck == null)
            throw new IllegalArgumentException("Deck with ID " + deckId + " does not exist.");

        File tempDir = getOrCreateTempDir();
        if (tempDir == null || !tempDir.exists())
            throw new IllegalStateException("Failed to create or access the temp directory.");

        File pdfFile = DeckPdfCreator.getInstance().createSimplePdfInTemp(deck, tempDir);
        return pdfFile;
    }

    private File getOrCreateTempDir() {

        String jarDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        String tempDirPath = jarDir + File.separator + "temp";

        File tempDir = new File(tempDirPath);
        if (!tempDir.exists()) {
            if (tempDir.mkdir()) {
                logger.info("Temp folder created at: " + tempDirPath);
            } else {
                logger.info("Failed to create temp folder.");
                return null;
            }
        }
        return tempDir;
    }
}

