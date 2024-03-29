package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import test.database.DBTestSuite;
import test.domainlayer.model.ModelTestSuite;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DBTestSuite.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("DB Test was successful?: " + result.wasSuccessful());

        result = JUnitCore.runClasses(ModelTestSuite.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Model Test was successful?: " + result.wasSuccessful());
    }
}
