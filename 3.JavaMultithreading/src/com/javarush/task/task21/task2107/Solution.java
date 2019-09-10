package com.javarush.task.task21.task2107;

import javax.jws.soap.SOAPBinding;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable{

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);
            System.out.println(solution.users.equals(clone.users));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution solution = new Solution();
        Map<String, User> usersCopy = new LinkedHashMap();
        for (Map.Entry<String, User> pair : this.users.entrySet()) {
            usersCopy.put(pair.getKey(), pair.getValue());
        }
        solution.users = usersCopy;
        return solution;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
        protected User clone()throws CloneNotSupportedException{
            User user = new User(this.age, this.name);
            return user;
        }
    }
}
