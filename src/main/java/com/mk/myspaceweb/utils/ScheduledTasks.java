package com.mk.myspaceweb.utils;

import com.mk.myspaceweb.service.CardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@EnableScheduling
public class ScheduledTasks {

    private final CardService cardService;

    private final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    /**
     * Deleting marked to delete entity Card and Deck
     */
    @Scheduled(cron = "${crons.clean-deleted}", zone = "Europe/Moscow")
    public void cleanDeletedEntities() {
        logger.info("start cleanDeletedEntities");
        var deletionResult = cardService.cleanDeletedEntities();
        logger.info(deletionResult.deletedDecks() + " decks and " + deletionResult.deletedCards() + " cards were deleted");
    }
}
