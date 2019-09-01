package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("C:\\new.txt", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            /*User user = new User();
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            user.setBirthDate(new Date());
            user.setFirstName("John");
            user.setLastName("Dodo");
            javaRush.users.add(user);
            javaRush.users.add(user);*/
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            if (this.users.isEmpty()) {
                outputStream.write("null".getBytes());
            } else {
                outputStream.write((users.size() + "\n").getBytes());
                for (int i = 0; i < users.size(); i++) {
                    outputStream.write((users.get(i).getFirstName() + " " + users.get(i).getLastName() + " " + users.get(i).getBirthDate().getTime()
                            + " " + users.get(i).isMale() + " " + users.get(i).getCountry().getDisplayName() + "\n").getBytes());
                }
            }
            outputStream.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            int b = 0;
            String file = "";
            while ((b = inputStream.read()) != -1) {
                file += (char) b;
            }
            String[] lines = file.split("\n");
            if (lines[0].equals("null")) {
                this.users = new ArrayList<>();
            } else {
                int usersCount = Integer.parseInt(lines[0]);
                for (int i = 0; i < usersCount; i++) {
                    String[] usersFields = lines[i + 1].split(" ");
                    String firsName = usersFields[0];
                    String lastName = usersFields[1];
                    Date date = new Date(Long.parseLong(usersFields[2]));
                    boolean sex = Boolean.parseBoolean(usersFields[3]);
                    User.Country country = User.Country.valueOf(usersFields[4].toUpperCase());
                    User user = new User();
                    user.setFirstName(firsName);
                    user.setLastName(lastName);
                    user.setBirthDate(date);
                    user.setMale(sex);
                    user.setCountry(country);
                    this.users.add(user);
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
