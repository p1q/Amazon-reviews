package com.reviews.amazon.service;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

public interface CsvReader {
    Optional<List> openFile();

    SortedSet<String> getMostActiveUsers(List<String> fileLines);

    SortedSet<String> getMostCommentedFoodItems(List<String> fileLines);

    SortedSet<String> getMostUsedWordsInReviews(List<String> fileLines);
}
