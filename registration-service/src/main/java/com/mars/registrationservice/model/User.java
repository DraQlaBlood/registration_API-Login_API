package com.mars.registrationservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection ="user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private UUID publisherId;

    private String pseudo;
    private String email;
    private String password;
    private Boolean enabled;
    private String contact;
    private String startDate;
    private int bids;


    private List<Locations> locations;
    private List<Roles> role;

    private VerificationToken verificationToken;

    public User() {
    }

    public User(UUID publisherId, String pseudo, String email, String password, Boolean enabled,
                String contact, String startDate, int bids, List<Locations> locations, List<Roles> role, VerificationToken verificationToken) {
        this.publisherId = publisherId;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.contact = contact;
        this.startDate = startDate;
        this.bids = bids;
        this.locations = locations;
        this.role = role;
        this.verificationToken = verificationToken;
    }

    public UUID getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(UUID publisherId) {
        this.publisherId = publisherId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }

    public List<Roles> getRole() {
        return role;
    }

    public void setRole(List<Roles> role)
    {
        this.role = role;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public int getBids() {
        return bids;
    }

    public void setBids(int bids) {
        this.bids = bids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getPublisherId(), user.getPublisherId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublisherId());
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonposts= "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonposts = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonposts;
    }
}
