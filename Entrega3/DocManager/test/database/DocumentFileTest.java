package test.database;

import static org.junit.Assert.*;

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
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DocumentFile;
import domainlayer.model.Document;
import junit.framework.AssertionFailedError;
import utils.BiMap;
import utils.PseudoDocument;

public class DocumentFileTest {

    private DocumentFile docFile;

    @Before
    public void setUp() {
        docFile = new DocumentFile();
        setUpInsertEnv();
    }

    @After
    public void tearDown() {
        docFile = null;
        tearDownInsertEnv();
    }

    @Test
    public void init_database_valid_case() {
        File test_folder = new File("testData", "testData1");
        HashMap<String,HashMap<String,Document>> ls = new HashMap<String,HashMap<String,Document>>();

        // The Document on this test folder is:
        Document expected;
        try {
            expected = new Document("txt", "Genesis I", "Jesus", "\"In the beginning God created the heaven and the earth.  And the earth was");
        } catch (Exception e) {
            // Never fails if Document class is correct
            e.printStackTrace();
            expected = null;
        }
        try {
            ls = docFile.init_database(test_folder.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println((new File(".")).getAbsolutePath());
            assertTrue("Error en la inicialització",false);
        }

        // Check Validity of Database

        File data_folder = docFile.get_data_folder();
        File repvec_folder = docFile.get_repvec_folder();
        File content_folder = docFile.get_content_folder();

        assertEquals(test_folder.getAbsolutePath(), data_folder.getAbsolutePath());
        assertEquals((new File(test_folder.getAbsolutePath(),"RepVec")).getAbsolutePath(),repvec_folder.getAbsolutePath());
        assertEquals((new File(test_folder.getAbsolutePath(),"Contingut")).getAbsolutePath(),content_folder.getAbsolutePath());

        Document result = ls.get(expected.get_autor()).get(expected.get_titol());
        // We need to remeber that the content is not loaded for efficency puroposes at first, only loaded when queried
        assertEquals("Autor test", expected.get_autor(), result.get_autor());
        assertEquals("Titol test", expected.get_titol(), result.get_titol());
        assertEquals("Format test", expected.get_format(), result.get_format());
        assertEquals("RepVec test", expected.get_repvec(), result.get_repvec());
        
    }

    /////////////////////// FALTEN TIPUS D'EXCEPCIONS! ////////////////////////////////////////////////////////////////////////////
    @Test(expected = Exception.class)
    public void init_database_data_corrupted() throws Exception{
        File test_folder = new File("testData", "testData2");
        docFile.init_database(test_folder.getAbsolutePath());
    }

    @Test(expected = Exception.class)
    public void init_database_content_corrupted() throws Exception{
        File test_folder = new File("testData", "testData3");
        docFile.init_database(test_folder.getAbsolutePath());
    }

    @Test(expected = Exception.class)
    public void init_database_repvec_corrupted() throws Exception{
        File test_folder = new File("testData", "testData4");
        docFile.init_database(test_folder.getAbsolutePath());
    }
    

    @Test
    public void testInsertValid() {

        File insert_test = new File("testData","insertTest");

        Document d;
        try {
            d = new Document("txt", "Genesis II", "Jesus", "I don't remember this one");

        } catch (Exception e) {
            d = null;
            e.printStackTrace();
        }
        
        try {
            docFile.init_database(insert_test.getAbsolutePath());
        } catch (Exception e) {
            // Should not fail as insertTest is based on init_database_valid_case
            e.printStackTrace();
            assertTrue("Failed Initialization, get back to that test first", false);
        } 

        try {
            docFile.insert(d);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Error on insert", false);
        }

        try {
            verifyDirsAreEqual(Paths.get(insert_test.getAbsolutePath()), Paths.get((new File("testData","correctStateAfterInsert")).getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BiMap id2doc = docFile.get_id2doc();
        Integer id = id2doc.get(new PseudoDocument("txt", "Genesis II", "Jesus"));
        assertTrue("The ID in id2doc fails", 1==id);
        
    }

    @Test(expected = Exception.class)
    public void testInsertAlreadyExists() throws Exception{
        File insert_test = new File("testData","insertTest");

        Document d;
        try {
            d = new Document("txt", "Genesis I", "Jesus", "I don't remember this one");

        } catch (Exception e) {
            d = null;
            e.printStackTrace();
        }

        try {
            docFile.init_database(insert_test.getAbsolutePath());
        } catch (Exception e) {
            // Should not fail as insertTest is based on init_database_valid_case
            e.printStackTrace();
            assertTrue("Failed Initialization, get back to that test first", false);
        } 

        docFile.insert(d);

        

    }

    @Test
    public void testUpdateValid() {
        File insert_test = new File("testData","insertTest");

        Document d;
        try {
            d = new Document("txt", "Genesis I", "Jesus", "I don't remember this one");

        } catch (Exception e) {
            d = null;
            e.printStackTrace();
        }

        try {
            docFile.init_database(insert_test.getAbsolutePath());
        } catch (Exception e) {
            // Should not fail as insertTest is based on init_database_valid_case
            e.printStackTrace();
            assertTrue("Failed Initialization, get back to that test first", false);
        } 

        try {
            docFile.update(d, "I don't remember this one");
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        try {
            verifyDirsAreEqual(Paths.get(insert_test.getAbsolutePath()), Paths.get((new File("testData","correctStateAfterUpdate")).getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(expected = Exception.class)
    public void testUpdateNoDocument() throws Exception{

        File insert_test = new File("testData","insertTest");

        Document d;
        try {
            d = new Document("txt", "Genesis II", "Jesus", "I don't remember this one");

        } catch (Exception e) {
            d = null;
            e.printStackTrace();
        }
        
        try {
            docFile.init_database(insert_test.getAbsolutePath());
        } catch (Exception e) {
            // Should not fail as insertTest is based on init_database_valid_case
            e.printStackTrace();
            assertTrue("Failed Initialization, get back to that test first", false);
        } 

        
        docFile.update(d,"I don't remember this one");
        
    }

    @Test
    public void testDeleteValid(){
        File insert_test = new File("testData","insertTest");

        Document d;
        try {
            d = new Document("txt", "Genesis I", "Jesus", "I don't remember this one");

        } catch (Exception e) {
            d = null;
            e.printStackTrace();
        }

        try {
            docFile.init_database(insert_test.getAbsolutePath());
        } catch (Exception e) {
            // Should not fail as insertTest is based on init_database_valid_case
            e.printStackTrace();
            assertTrue("Failed Initialization, get back to that test first", false);
        } 

        try {
            docFile.delete(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        try {
            verifyDirsAreEqual(Paths.get(insert_test.getAbsolutePath()), Paths.get((new File("testData","correctStateAfterDelete")).getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BiMap id2doc = docFile.get_id2doc();
        Integer id = id2doc.get(new PseudoDocument("txt", "Genesis II", "Jesus"));
        assertTrue("The ID in id2doc fails", null==id);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
