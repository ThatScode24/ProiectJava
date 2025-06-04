import java.time.LocalDate;

public class OTP {
    private int id;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    private static final MersenneTwister mt = new MersenneTwister();
    private LocalDate generated_at;
    private String password;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getGeneratedAt() { return this.generated_at; }
    public void setGeneratedAt(LocalDate generated_at) { this.generated_at = generated_at; }

    public OTP() { this.generated_at = LocalDate.now(); }

    public String generateOTP() {
        int length = mt.nextInt(100);
        StringBuilder otp = new StringBuilder(length);

        for (int i = 0; i < length; i++) otp.append(CHARACTERS.charAt(mt.nextInt(CHARACTERS.length()))); this.password = otp.toString(); return otp.toString();
    }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }
}