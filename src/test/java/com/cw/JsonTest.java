package com.cw;

import java.util.List;

/**
 * @author david.cai
 * @date 2018/8/3
 **/
public class JsonTest {

    private List<Customer> customer;

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public static class Customer {
        /**
         * userid : 25011116
         * email : test.user@example.com
         * firstname : Test
         * lastname : User
         * nationality : CN
         * countryOfResidence : CN
         * language : zh
         * phone : +861234567890
         * wechatid : 132424324234
         * updated : 2018-04-19T14:07:24.290+01:00
         * privacy : true
         * marketing : true
         */

        private String userid;
        private String email;
        private String firstname;
        private String lastname;
        private String nationality;
        private String countryOfResidence;
        private String language;
        private String phone;
        private String wechatid;
        private String updated;
        private boolean privacy;
        private boolean marketing;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getCountryOfResidence() {
            return countryOfResidence;
        }

        public void setCountryOfResidence(String countryOfResidence) {
            this.countryOfResidence = countryOfResidence;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWechatid() {
            return wechatid;
        }

        public void setWechatid(String wechatid) {
            this.wechatid = wechatid;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public boolean isPrivacy() {
            return privacy;
        }

        public void setPrivacy(boolean privacy) {
            this.privacy = privacy;
        }

        public boolean isMarketing() {
            return marketing;
        }

        public void setMarketing(boolean marketing) {
            this.marketing = marketing;
        }
    }
}
