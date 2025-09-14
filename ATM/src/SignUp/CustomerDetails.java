package SignUp;

public class CustomerDetails {
    String name, fname, dob, gender, email, marital, address, city, state, seducation, soccupation, sincome;
    int pinCode;
    CustomerDetails(String name, String fname, String dob, String gender, String email, String marital, String address, String city, String state, int pinCode, String seducation, String soccupation, String sincome) {
        this.name = name;
        this.fname = fname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.marital = marital;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.seducation = seducation;
        this.soccupation = soccupation;
        this.sincome = sincome;
    }
}
