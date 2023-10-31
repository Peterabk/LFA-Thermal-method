package com.example.bro;

public class User {

        public String f_name;
        public String l_name;
        public String role;
        public String username;
        public String password;


        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String f_name, String l_name, String role, String username,String password) {
            this.f_name = f_name;
            this.l_name = l_name;
            this.role = role;
            this.username = username;
            this.password = password;

        }
    }


