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
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReaderImpl implements CsvReader {
    private final static int PROFILE_NAME_NUMBER = 3;
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
    public Set<String> getMostActiveUsers(List<String> fileLines) {
        Map<String, Integer> profileNames = new HashMap<>();
        for (int i = 1; i < fileLines.size(); i++) {
            String[] lineElements = fileLines.get(i).split(",");
            String currentProfileName = lineElements[PROFILE_NAME_NUMBER];
            Integer profileNameDuplicatesValue = profileNames.get(currentProfileName);
            profileNames.put(currentProfileName,
                    profileNameDuplicatesValue == null ? 1 : profileNameDuplicatesValue + 1);
        }

        List<Map.Entry> finalEntries = profileNames.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue()
                .reversed())
                .limit(LIMIT_RECORDS_TO_FIND)
                .collect(Collectors.toList());

        Set<String> setKeys = new TreeSet();
        for (Map.Entry finalEntry : finalEntries) {
            setKeys.add((String) finalEntry.getKey());
        }
        return setKeys;
    }
}
