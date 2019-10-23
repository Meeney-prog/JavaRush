package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private List<LogNode> logs = new ArrayList<>();

    public LogParser(Path logDir) {
        try {
            parsePath(logDir);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void parsePath(Path logDir) throws IOException, ParseException {
        List<Path> files = Files.list(logDir)
                .filter(f -> f.toString().endsWith(".log")).collect(Collectors.toList());
        for (Path file : files)
            try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
                while (reader.ready()) {
                    LogNode newNode = new LogNode(reader.readLine());
                    logs.add(newNode);
                }
            }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == event
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getStatus() == status
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return logs.stream()
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getEvent)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getIp().equals(ip)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.LOGIN
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.DOWNLOAD_PLUGIN
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.WRITE_MESSAGE
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.SOLVE_TASK
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.SOLVE_TASK
                        && log.getTask().equals(task)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.DONE_TASK
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.DONE_TASK
                        && log.getTask().equals(task)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == event
                        && log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getStatus() == Status.FAILED
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getStatus() == Status.ERROR
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.LOGIN
                        && log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.SOLVE_TASK
                        && log.getTask().equals(task)
                        && log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.DONE_TASK
                        && log.getTask().equals(task)
                        && log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.WRITE_MESSAGE
                        && log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.DOWNLOAD_PLUGIN
                        && log.getName().equals(user)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(log -> new Date(log.getDate()))
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getEvent)
                .collect(Collectors.toSet());
    }

    public Set<Status> getAllStatuses(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getStatus)
                .collect(Collectors.toSet());
    }

    public Set<Date> getAllDates() {
        return logs.stream()
                .map(log -> new Date(log.getDate()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getIp().equals(ip)
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getEventsForIP(user, after, before);
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getStatus() == Status.FAILED
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getStatus() == Status.ERROR
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .map(LogNode::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logs.stream()
                .filter(log -> log.getEvent() == Event.SOLVE_TASK
                        && log.getTask() == task
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logs.stream()
                .filter(log -> log.getEvent() == Event.DONE_TASK
                        && log.getTask() == task
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.SOLVE_TASK
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .collect(Collectors.toMap(LogNode::getTask, log -> 1, Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.getEvent() == Event.DONE_TASK
                        && log.getDate() >= (after == null ? 0 : after.getTime())
                        && log.getDate() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .collect(Collectors.toMap(LogNode::getTask, log -> 1, Integer::sum));
    }

    public Set simpleExecute(String query) {
        switch (query.trim()) {
            case "get ip":
                return getUniqueIPs(null, null);
            case "get user":
                return getAllUsers();
            case "get event":
                return getAllEvents(null, null);
            case "get status":
                return getAllStatuses(null, null);
            case "get date":
                return getAllDates();
            default:
                return null;
        }
    }

    @Override
    public Set execute(String query) {
        if (query.split(" ").length == 2)
            return simpleExecute(query);
        String pattern = "get (?<tag>\\w+)(\\sfor\\s(?<field>\\w+)\\s=\\s\"(?<value>.+?)\")?(\\sand date between" +
                "\\s\"(?<after>[\\d]+.[\\d]+.[\\d]+ [\\d]+:[\\d]+:[\\d]+)\"\\sand\\s\"" +
                "(?<before>[\\d]+.[\\d]+.[\\d]+ [\\d]+:[\\d]+:[\\d]+)\")?";
        Matcher matcher = Pattern.compile(pattern).matcher(query);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        if (matcher.find()) {
            String field1 = matcher.group("tag");
            String field2 = matcher.group("field");
            String value2 = matcher.group("value");
            Date after = null;
            Date before = null;
            try {
                if (matcher.group("after") != null && matcher.group("before") != null) {
                    after = format.parse(matcher.group("after"));
                    before = format.parse(matcher.group("before"));
                }
                if (field2.equals("date"))
                    value2 = format.parse(value2).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String value1 = value2;
            Date constAfter = after;
            Date constBefore = before;
            return logs.stream()
                    .filter(log -> log.get(field2).toString().equalsIgnoreCase(value1)
                            && log.getDate() > (constAfter == null ? 0 : constAfter.getTime())
                            && log.getDate() < (constBefore == null ? Long.MAX_VALUE : constBefore.getTime()))
                    .map(log -> log.get(field1))
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static class LogNode {
        private String ip;
        private String name;
        private Long date;
        private Event event;
        private Integer task;
        private Status status;

        private LogNode(String log) throws ParseException {
            String[] fields = log.split("\t");
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String ip = fields[0];
            String name = fields[1];
            Long date = format.parse(fields[2]).getTime();
            String[] eventAndTask = fields[3].split(" ");
            Event event = Event.valueOf(eventAndTask[0]);
            Integer task = -1;
            if (eventAndTask.length == 2)
                task = Integer.parseInt(eventAndTask[1]);
            Status status = Status.valueOf(fields[4]);
            this.ip = ip;
            this.name = name;
            this.date = date;
            this.event = event;
            this.task = task;
            this.status = status;
        }

        public Object get(String fieldName) {
            switch (fieldName.toLowerCase()) {
                case "ip":
                    return ip;
                case "user":
                    return name;
                case "date":
                    return new Date(date);
                case "status":
                    return status;
                case "event":
                    return event;
                case "task":
                    return task;
                default:
                    return null;
            }
        }

        public String getIp() {return ip;}

        public String getName() {return name;}

        public Long getDate() {return date;}

        public Event getEvent() {return event;}

        public Integer getTask() {return task;}

        public Status getStatus() {return status;}
    }
}