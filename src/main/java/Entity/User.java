package Entity;
import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class User {
        @Id
        private String UserID;
        private String Username;
        private String PW;
        private String FullName;
        private String EmailAddress;
        private String HomeAddress;

        // Constructors
        public User() {}

        // Getters and Setters
        public String getUserID() {
            return UserID;
        }

        public void setUserID(String userID) {
            this.UserID = userID;
        }

        public String getUsername() {
            return Username;
        }

        public void setUsername(String username) {
            this.Username = username;
        }

        public String getPw() {
            return PW;
        }

        public void setPw(String pw) {
            this.PW = pw;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            this.FullName = fullName;
        }

        public String getEmailAddress() {
            return EmailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.EmailAddress = emailAddress;
        }

        public String getHomeAddress() {
            return HomeAddress;
        }

        public void setHomeAddress(String homeAddress) {
            this.HomeAddress = homeAddress;
        }
    }


