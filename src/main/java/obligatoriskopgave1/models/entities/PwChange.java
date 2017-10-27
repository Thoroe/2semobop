package obligatoriskopgave1.models.entities;

public class PwChange {
    String oldpw;
    String pw1;
    String pw2;

    public PwChange(String oldpw, String pw1, String pw2) {
        this.oldpw = oldpw;
        this.pw1 = pw1;
        this.pw2 = pw2;
    }

    public PwChange(){
    }

    public String getOldpw() {
        return oldpw;
    }

    public void setOldpw(String oldpw) {
        this.oldpw = oldpw;
    }

    public String getPw1() {
        return pw1;
    }

    public void setPw1(String pw1) {
        this.pw1 = pw1;
    }

    public String getPw2() {
        return pw2;
    }

    public void setPw2(String pw2) {
        this.pw2 = pw2;
    }
}
