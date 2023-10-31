public class PatientClient {
    String Fname,Lname,
            health_assurance_number, birth, address,
            phone_number,
            name_emergency_contact1, relation_emergency_contact1,
            name_emergency_contact2, relation_emergency_contact2;



    //CONSTRUCTORS

    public PatientClient(){

    }
    public PatientClient(String fname, String lname, String health_assurance_number,
                         String birth, String address, String phone_number,
                         String name_emergency_contact1, String relation_emergency_contact1,
                         String name_emergency_contact2, String relation_emergency_contact2) {
        Fname = fname;
        Lname = lname;
        this.health_assurance_number = health_assurance_number;
        this.birth = birth;
        this.address = address;
        this.phone_number = phone_number;
        this.name_emergency_contact1 = name_emergency_contact1;
        this.relation_emergency_contact1 = relation_emergency_contact1;
        this.name_emergency_contact2 = name_emergency_contact2;
        this.relation_emergency_contact2 = relation_emergency_contact2;
    }

    //GETTER & SETTER
    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getHealth_assurance_number() {
        return health_assurance_number;
    }

    public void setHealth_assurance_number(String health_assurance_number) {
        this.health_assurance_number = health_assurance_number;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName_emergency_contact1() {
        return name_emergency_contact1;
    }

    public void setName_emergency_contact1(String name_emergency_contact1) {
        this.name_emergency_contact1 = name_emergency_contact1;
    }

    public String getRelation_emergency_contact1() {
        return relation_emergency_contact1;
    }

    public void setRelation_emergency_contact1(String relation_emergency_contact1) {
        this.relation_emergency_contact1 = relation_emergency_contact1;
    }

    public String getName_emergency_contact2() {
        return name_emergency_contact2;
    }

    public void setName_emergency_contact2(String name_emergency_contact2) {
        this.name_emergency_contact2 = name_emergency_contact2;
    }

    public String getRelation_emergency_contact2() {
        return relation_emergency_contact2;
    }

    public void setRelation_emergency_contact2(String relation_emergency_contact2) {
        this.relation_emergency_contact2 = relation_emergency_contact2;
    }
}
