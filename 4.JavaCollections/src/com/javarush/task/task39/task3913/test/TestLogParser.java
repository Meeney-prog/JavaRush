package com.javarush.task.task39.task3913.test;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.LogParser;
import com.javarush.task.task39.task3913.LogParser.LogNode;
import com.javarush.task.task39.task3913.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TestLogParser {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    Date lowEndDate = sdf.parse("01.01.2017 13:00:00");
    Date lowEndDateExcept = sdf.parse("01.01.2017 14:00:00");
    Date betweenDate = sdf.parse("01.02.2017 10:00:00");
    Date highEndDate = sdf.parse("29.12.30017 23:59:59");
    Date highEndDateExcept = sdf.parse("29.12.30017 23:59:58");
    Date empty = sdf.parse("29.12.30117 23:59:58");

    int onlyLE;
    int onlyHE;
    int onlyME;
    int LEME;
    int MEHE;
    int LEMEHE;
    LogParser logParser;


    private Set<String> lowEndDateIps = new HashSet<>();
    private Set<String> highEndDateIps = new HashSet<>();
    private Set<String> betweenEndDateIps = new HashSet<>();

    public TestLogParser() throws ParseException {
    }

    @Before
    public void preSetup() throws IOException {
        Path path = Files.createTempDirectory("SolutionTestTempDir");

        List<LogNode> file = createContentForFile1();
        file.forEach(this::addToSet);
        createFile(path, "file1", file);

        file = createContentForFile2();
        file.forEach(this::addToSet);
        createFile(path, "file2", file);

        logParser = new LogParser(path);
        onlyLE = lowEndDateIps.size();
        onlyHE = highEndDateIps.size();
        onlyME = betweenEndDateIps.size();
        LEME = onlyLE + onlyME;
        MEHE = onlyME + onlyHE;
        LEMEHE = onlyLE + onlyME + onlyHE;
    }

    @Test
    public void testLowBorder() {
        assertAndLog(onlyLE, logParser.getNumberOfUniqueIPs(null, lowEndDate));
        assertAndLog(onlyLE, logParser.getNumberOfUniqueIPs(lowEndDate, lowEndDate));
    }

    @Test
    public void testHighBorder() {
        assertAndLog(onlyHE, logParser.getNumberOfUniqueIPs(highEndDate, null));
        assertAndLog(onlyHE, logParser.getNumberOfUniqueIPs(highEndDate, highEndDate));
    }

    @Test
    public void testAllBorder() {
        assertAndLog(LEMEHE, logParser.getNumberOfUniqueIPs(lowEndDate, null));
        assertAndLog(LEMEHE, logParser.getNumberOfUniqueIPs(null, null));
        assertAndLog(LEMEHE, logParser.getNumberOfUniqueIPs(null, highEndDate));
    }

    @Test
    public void testMiddleWithBorders() {
        assertAndLog(LEME, logParser.getNumberOfUniqueIPs(lowEndDate, highEndDateExcept));
        assertAndLog(LEME, logParser.getNumberOfUniqueIPs(null, highEndDateExcept));
        assertAndLog(MEHE, logParser.getNumberOfUniqueIPs(lowEndDateExcept, highEndDate));
        assertAndLog(MEHE, logParser.getNumberOfUniqueIPs(lowEndDateExcept, null));
    }

    @Test
    public void testEmpty() {
        assertAndLog(0, logParser.getNumberOfUniqueIPs(empty, null));
        assertAndLog(0, logParser.getUniqueIPs(empty, null).size());
    }

    @Test
    public void testExcept() {
        assertAndLog(onlyME, logParser.getNumberOfUniqueIPs(lowEndDateExcept, highEndDateExcept));
    }

    private void assertAndLog(int i, int i1) {
        System.out.println("Expected: " + i + " Actual: " + i1);
        Assert.assertEquals(i, i1);
    }

    private void createFile(Path path, String name, List<LogNode> logLines) throws IOException {
        Path file = Files.createTempFile(path, name, ".log");
        List<String> output = logLines.stream()
                .map(this::generateContentString)
                .collect(Collectors.toList());
        Files.write(file, output, Charset.defaultCharset());
    }

    private String generateContentString(LogNode l) {
        String separator = "\t";
        return l.getIp() + separator + l.getName() + separator + sdf.format(new Date(l.getDate())) + separator + l.getEvent() +
                (l.getTask() != -1 ? " " + l.getTask() + separator : separator) + l.getStatus();
    }

    private void addToSet(LogNode l) {
        if (l.getDate().equals(lowEndDate.getTime()))
            lowEndDateIps.add(l.getIp());
        else if (l.getDate().equals(highEndDate.getTime()))
            highEndDateIps.add(l.getIp());
        else
            betweenEndDateIps.add(l.getIp());
    }

    private List<LogNode> createContentForFile1() {
        List<LogNode> file = new ArrayList<>();
        file.add(new LogNode("192.168.2.1", "Alex K", lowEndDate, Event.LOGIN, null, Status.FAILED));
        file.add(new LogNode("192.168.2.1", "Alex K", lowEndDate, Event.LOGIN, null, Status.ERROR));
        file.add(new LogNode("192.168.2.1", "Alex K", lowEndDate, Event.LOGIN, null, Status.OK));

        file.add(new LogNode("192.168.2.2", "Alex K", lowEndDate, Event.DOWNLOAD_PLUGIN, null, Status.OK));
        file.add(new LogNode("192.168.2.2", "Alex K", lowEndDate, Event.DOWNLOAD_PLUGIN, null, Status.FAILED));
        file.add(new LogNode("192.168.2.2", "Alex K", lowEndDate, Event.DOWNLOAD_PLUGIN, null, Status.ERROR));

        file.add(new LogNode("192.168.2.3", "Alex K", betweenDate, Event.SOLVE_TASK, "45", Status.OK));
        file.add(new LogNode("192.168.2.3", "Alex K", betweenDate, Event.SOLVE_TASK, "411155", Status.FAILED));
        file.add(new LogNode("192.168.2.3", "Alex K", betweenDate, Event.SOLVE_TASK, "45", Status.ERROR));

        file.add(new LogNode("192.168.2.4", "Alex K d4 ", highEndDate, Event.DONE_TASK, "45344423", Status.OK));
        file.add(new LogNode("192.168.2.4", "Alex K", highEndDate, Event.DONE_TASK, "45", Status.FAILED));
        file.add(new LogNode("192.168.2.4", "Alex K", highEndDate, Event.DONE_TASK, "4123135", Status.ERROR));

        file.add(new LogNode("192.168.2.5", "Alex K", highEndDate, Event.WRITE_MESSAGE, null, Status.OK));
        file.add(new LogNode("192.168.2.5", "Alex K", highEndDate, Event.WRITE_MESSAGE, null, Status.FAILED));
        file.add(new LogNode("192.168.2.5", "Alex K", highEndDate, Event.WRITE_MESSAGE, null, Status.ERROR));
        return file;
    }

    private List<LogNode> createContentForFile2() {
        List<LogNode> file = new ArrayList<>();
        file.add(new LogNode("192.168.1.1", "NoNameUser", highEndDate, Event.LOGIN, null, Status.FAILED));
        file.add(new LogNode("192.168.1.1", "NoNameUser", highEndDate, Event.LOGIN, null, Status.ERROR));
        file.add(new LogNode("192.168.1.1", "NoNameUser", highEndDate, Event.LOGIN, null, Status.OK));

        file.add(new LogNode("192.168.1.2", "NoNameUser", betweenDate, Event.DOWNLOAD_PLUGIN, null, Status.OK));
        file.add(new LogNode("192.168.1.2", "NoNameUser", betweenDate, Event.DOWNLOAD_PLUGIN, null, Status.FAILED));
        file.add(new LogNode("192.168.1.2", "NoNameUser", betweenDate, Event.DOWNLOAD_PLUGIN, null, Status.ERROR));

        file.add(new LogNode("192.168.1.3", "NoNameUser", betweenDate, Event.SOLVE_TASK, "42", Status.OK));
        file.add(new LogNode("192.168.1.3", "NoNameUser", betweenDate, Event.SOLVE_TASK, "42", Status.FAILED));
        file.add(new LogNode("192.168.1.3", "NoNameUser", betweenDate, Event.SOLVE_TASK, "424", Status.ERROR));

        file.add(new LogNode("192.168.1.4", "NoNameUser", lowEndDate, Event.DONE_TASK, "42", Status.OK));
        file.add(new LogNode("192.168.1.4", "NoNameUser", lowEndDate, Event.DONE_TASK, "412", Status.FAILED));
        file.add(new LogNode("192.168.1.4", "NoNameUser", lowEndDate, Event.DONE_TASK, "42", Status.ERROR));

        file.add(new LogNode("192.168.1.5", "NoNameUser", betweenDate, Event.WRITE_MESSAGE, null, Status.OK));
        file.add(new LogNode("192.168.1.5", "NoNameUser", betweenDate, Event.WRITE_MESSAGE, null, Status.FAILED));
        file.add(new LogNode("192.168.1.5", "NoNameUser", betweenDate, Event.WRITE_MESSAGE, null, Status.ERROR));

        file.add(new LogNode("192.168.0.1", "NoNameUser", betweenDate, Event.WRITE_MESSAGE, null, Status.OK));
        file.add(new LogNode("192.168.0.2", "NoNameUser", betweenDate, Event.WRITE_MESSAGE, null, Status.FAILED));
        file.add(new LogNode("192.168.0.3", "NoNameUser", betweenDate, Event.WRITE_MESSAGE, null, Status.ERROR));

        file.add(new LogNode("192.168.0.1", "NoNameUser", new Date(), Event.WRITE_MESSAGE, null, Status.OK));
        file.add(new LogNode("192.168.0.2", "NoNameUser", new Date(), Event.WRITE_MESSAGE, null, Status.FAILED));
        file.add(new LogNode("192.168.0.3", "NoNameUser", new Date(), Event.WRITE_MESSAGE, null, Status.ERROR));

        return file;
    }
}