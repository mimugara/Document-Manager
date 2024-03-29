package test.database;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import junit.framework.AssertionFailedError;

public class BoolExpressionFileTest {


    public void setUpInsertEnv() {
        File insert_test = new File("testData","insertTest");
        File valid_start_case = new File("testData","testData1");
        try {
            copyDirectoryRecursively(Paths.get(valid_start_case.getAbsolutePath()), Paths.get(insert_test.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tearDownInsertEnv() {
        File insert_test = new File("testData","insertTest");
        try {
            deleteDirRecursively(insert_test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //////////// UTILS /////////////////////////////////////////////////////////////////////////////////

    public void copyDirectoryRecursively(Path sourceDir, Path destinationDir) throws IOException{
        Files.walk(sourceDir)
                .forEach(sourcePath -> {
                    try {
                        Path targetPath = destinationDir.resolve(sourceDir.relativize(sourcePath));
                        //System.out.printf("Copying %s to %s%n", sourcePath, targetPath);
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        System.out.format("I/O error: %s%n", ex);
                    }
                });
    }

    public void deleteDirRecursively(File file) throws Exception{
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
              for (File entry : entries) {
                deleteDirRecursively(entry);
              }
            }
          }
          if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
          }
    }

    private void verifyDirsAreEqual(Path one, Path other) throws IOException {
        Files.walkFileTree(one, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attrs)
                    throws IOException {
                FileVisitResult result = super.visitFile(file, attrs);
    
                // get the relative file name from path "one"
                Path relativize = one.relativize(file);
                // construct the path for the counterpart file in "other"
                Path fileInOther = other.resolve(relativize);
                //log.debug("=== comparing: {} to {}", file, fileInOther);
    
                byte[] otherBytes = Files.readAllBytes(fileInOther);
                byte[] theseBytes = Files.readAllBytes(file);
                if (!Arrays.equals(otherBytes, theseBytes)) {
                    FileReader fr1 = new FileReader(file.toFile());
                    FileReader fr2 = new FileReader(fileInOther.toFile());
                    JSONObject js1, js2;
                    JSONParser parser = new JSONParser();
                    
                    try {
                        js1 = (JSONObject) parser.parse(fr1);
                        System.out.println(js1.toJSONString());
                        js2 = (JSONObject) parser.parse(fr2);
                        System.out.println(js2.toJSONString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    throw new AssertionFailedError(file + " is not equal to " + fileInOther);
                }  
                return result;
            }
        });
    }
    
}
