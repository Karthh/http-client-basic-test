package net.stemtechnology.httpclientbasictest.app;

import java.util.List;

/**
 * Created by karthik on 7/27/14.
 */
public interface DataSink {
    public void takeData(List<Student> students);
}