import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Service service = new Service();   //instanta de clasa service

        // array de licente
        String[] licenseArray = new String[] {
                service.genereazaLicentaWindows95(),
                service.genereazaLicentaNT(),
                service.genereazaLicentaSimCity2000(),
                service.genereazaLicentaPhotoshop6()
        };

        // lista neordonata de licente
        List<String> licenseList = new ArrayList<>(Arrays.asList(licenseArray));

        //SortedSet licente (ord alfabetica)
        Set<String> sortedLicenses = new TreeSet<>(licenseList);

        // afisare array
        System.out.println("Licente (array):"); for (String code : licenseArray) { System.out.println(code); }

        // printare lista
        System.out.println("\nLicente (List):"); for (String code : licenseList) { System.out.println(code); }

        // printare sorted set
        System.out.println("\nLicente sortate (SortedSet):"); for (String code : sortedLicenses) { System.out.println(code); }

        // arrayuri de numere random
        int[] randomLcg = new int[5]; int[] randomMt  = new int[5];
        for (int i = 0; i < 5; i++) { randomLcg[i] = service.genereazaNumarRandomLCG(); randomMt[i] = service.genereazaNumarRandomMT(); }

        // afisare numere random generate
        System.out.println("\nNumere random LCG:"); for (int n : randomLcg) System.out.print(n + " ");
        System.out.println("\nNumere random MT:"); for (int n : randomMt)  System.out.print(n + " ");

        LicentaDAO dao = LicentaDAO.getInstance();
        dao.delete(3);

        MersenneTwisterDAO dao1 = MersenneTwisterDAO.getInstance();
        MersenneTwister mt = new MersenneTwister();
        int num = mt.nextInt(1000);

        LinearCongruentialGenerator LCG = new LinearCongruentialGenerator();
        LinearCongruentialGeneratorDAO dao2 = LinearCongruentialGeneratorDAO.getInstance();
        int num2 = LCG.nextInt();
        dao2.create(LCG);

        OTP top = new OTP();
        System.out.println("\n\n\nOne Time Password: " + top.generateOTP());
        OTPDAO otpdao = OTPDAO.getInstance();

        otpdao.create(top);
    }
}
