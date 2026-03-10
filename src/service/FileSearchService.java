package service;

import model.FileResult;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileSearchService {

    public List<FileResult> search(Path startPath, String term) {

        List<FileResult> results = new ArrayList<>();

        try {

            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

                    try {

                        String name = file.getFileName().toString().toLowerCase();

                        if(match(name, term)) {

                            FileResult result = new FileResult(
                                    file.getFileName().toString(),
                                    file,
                                    Files.size(file),
                                    Files.getLastModifiedTime(file).toMillis()
                            );

                            results.add(result);
                        }

                    } catch (IOException ignored) {}

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    private boolean match(String filename, String search) {

        if(filename.contains(search))
            return true;

        int distance = levenshtein(filename, search);

        return distance <= 3; // tolerância de erro
    }

    // algoritmo de distância de Levenshtein
    private int levenshtein(String a, String b) {

        int[][] dp = new int[a.length()+1][b.length()+1];

        for(int i=0;i<=a.length();i++)
            dp[i][0] = i;

        for(int j=0;j<=b.length();j++)
            dp[0][j] = j;

        for(int i=1;i<=a.length();i++) {

            for(int j=1;j<=b.length();j++) {

                int cost = a.charAt(i-1) == b.charAt(j-1) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(
                                dp[i-1][j] + 1,
                                dp[i][j-1] + 1
                        ),
                        dp[i-1][j-1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }
}