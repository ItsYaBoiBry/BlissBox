package asia.blissbox.blissboxpteltd;

import java.io.Serializable;

/**
 * Created by bryan on 22/1/2018.
 */

public class ObjectUsers implements Serializable {
    private int id;
    private int role_id;
    private int company_id;
    private String title;
    private String first_name;
    private String last_name;
    private String email;
    private String dob;
    private String designation;
    private String country;
    private String phone;
    private String postal_code;
    private String activation_code;
    private String getActivation_status;
    private String delete_at;
    private String created_at;
    private String updated_at;

    public ObjectUsers() {

    }

    public ObjectUsers(int id, int role_id, int company_id, String title, String first_name, String last_name, String email, String dob, String designation, String country, String phone, String postal_code, String activation_code, String getActivation_status, String delete_at, String created_at, String updated_at) {
        this.id = id;
        this.role_id = role_id;
        this.company_id = company_id;
        this.title = title;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.dob = dob;
        this.designation = designation;
        this.country = country;
        this.phone = phone;
        this.postal_code = postal_code;
        this.activation_code = activation_code;
        this.getActivation_status = getActivation_status;
        this.delete_at = delete_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public ObjectUsers(int id, int role_id, int company_id, String title, String first_name, String last_name, String email, String dob, String designation, String country, String phone, String postal_code) {
        this.id = id;
        this.role_id = role_id;
        this.company_id = company_id;
        this.title = title;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.dob = dob;
        this.designation = designation;
        this.country = country;
        this.phone = phone;
        this.postal_code = postal_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public String getGetActivation_status() {
        return getActivation_status;
    }

    public void setGetActivation_status(String getActivation_status) {
        this.getActivation_status = getActivation_status;
    }

    public String getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(String delete_at) {
        this.delete_at = delete_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
