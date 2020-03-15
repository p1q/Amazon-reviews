package com.reviews.amazon.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CsvReader {
    Optional<List> openFile();

    Set<String> getMostActiveUsers(List<String> fileLines);
}
