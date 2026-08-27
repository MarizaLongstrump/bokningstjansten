package com.mariza.bokning.dto.hotel;

public class HotelResponse {


        private Long id;
        private String name;
        private String city;
        private int stars;


        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public int getStars() { return stars; }
        public void setStars(int stars) { this.stars = stars; }

    }
