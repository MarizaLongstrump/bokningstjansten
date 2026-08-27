package com.mariza.bokning.dto.hotel;

    public class UpdateHotelRequest {

        private String name;
        private String address;
        private String city;
        private int stars;

        public UpdateHotelRequest() {}

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public int getStars() {
            return stars;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }
    }
