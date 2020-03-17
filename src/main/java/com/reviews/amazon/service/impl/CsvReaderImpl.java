package com.reviews.amazon.service.impl;

import com.reviews.amazon.service.CsvReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReaderImpl implements CsvReader {
    private final static int PROFILE_NAME_NUMBER = 3;
    private final static int PRODUCT_ID_NUMBER = 1;
    private final static int REVIEW_NUMBER = 8;
    private final static int SEPARATOR_LIMIT = 9;
    private final static int LIMIT_RECORDS_TO_FIND = 1000;

    @Override
    public Optional<List> openFile() {
        Path path = Paths.get(".\\src\\main\\resources\\reviews.csv");
        try (Stream<String> lineStream = Files.lines(path)) {
            List<String> fileLines = lineStream.collect(Collectors.toList());
            return Optional.of(fileLines);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public SortedSet<String> getMostActiveUsers(List<String> fileLines) {
        Map<String, Integer> profileNames = new HashMap<>();
        for (int i = 1; i < fileLines.size(); i++) {
            String[] lineElements = fileLines.get(i).split(",");
            String currentProfileName = lineElements[PROFILE_NAME_NUMBER];
            Integer profileNameDuplicatesValue = profileNames.get(currentProfileName);
            profileNames.put(currentProfileName,
                    profileNameDuplicatesValue == null ? 1 : profileNameDuplicatesValue + 1);
        }
        return getFinalEntries(profileNames)
                .stream()
                .map(finalEntry -> (String) finalEntry.getKey())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public SortedSet<String> getMostCommentedFoodItems(List<String> fileLines) {
        Map<String, Integer> productIds = new HashMap<>();
        for (int i = 1; i < fileLines.size(); i++) {
            String[] lineElements = fileLines.get(i).split(",");
            String currentProductId = lineElements[PRODUCT_ID_NUMBER];
            Integer productIdDuplicatesValue = productIds.get(currentProductId);
            productIds.put(currentProductId,
                    productIdDuplicatesValue == null ? 1 : productIdDuplicatesValue + 1);
        }
        return getFinalEntries(productIds)
                .stream()
                .map(finalEntry -> (String) finalEntry.getKey())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public SortedSet<String> getMostUsedWordsInReviews(List<String> fileLines) {
        Map<String, Integer> wordsInReviews = new HashMap<>();
        for (int i = 1; i < fileLines.size(); i++) {
            String[] lineElements = fileLines.get(i).split(",", SEPARATOR_LIMIT);
            String[] currentReview = lineElements[REVIEW_NUMBER]
                    .replaceAll("([.,?!:;()@#$%&^<>'])", "")
                    .toLowerCase().split(" ");
            for (String currentWordOfReview : currentReview) {
                if (currentWordOfReview.isEmpty()) {
                    continue;
                }
                Integer wordDuplicatesValue = wordsInReviews.get(currentWordOfReview);
                wordsInReviews.put(currentWordOfReview,
                        wordDuplicatesValue == null ? 1 : wordDuplicatesValue + 1);
            }
        }
        return getFinalEntries(wordsInReviews)
                .stream()
                .map(finalEntry -> (String) finalEntry.getKey())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private List<Map.Entry> getFinalEntries(Map<String, Integer> initData) {
        return initData.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue()
                        .reversed())
                .limit(LIMIT_RECORDS_TO_FIND)
                .collect(Collectors.toList());
    }
}
