public class Service {
    private final Licenta95Generator win95Gen;
    private final LicentaNTGenerator ntGen;
    private final SimCity2000 simCityGen;
    private final AdobePhotoshop6 ps6Gen;
    private final LinearCongruentialGenerator lcg;
    private final MersenneTwister mt;

    public Service() {                                      // Creare generatoare specifice
        this.win95Gen = new Licenta95Generator();
        this.ntGen = new LicentaNTGenerator();
        this.simCityGen = new SimCity2000();
        this.ps6Gen = new AdobePhotoshop6();
        this.lcg = new LinearCongruentialGenerator();
        this.mt = new MersenneTwister();
    }

    public String genereazaLicentaWindows95() { return win95Gen.genereazaCod(); }
    public String genereazaLicentaNT() { return ntGen.genereazaCod(); }
    public String genereazaLicentaSimCity2000() { return simCityGen.genereazaCod(); }
    public String genereazaLicentaPhotoshop6() { return ps6Gen.genereazaCod(); }
    public boolean valideazaLicentaWindows95(String cod) { return win95Gen.valideazaCod(cod); }
    public boolean valideazaLicentaNT(String cod) { return ntGen.valideazaCod(cod); }
    public boolean valideazaLicentaSimCity2000(String cod) { return simCityGen.valideazaCod(cod); }
    public boolean valideazaLicentaPhotoshop6(String cod) { return ps6Gen.valideazaCod(cod); }
    public int genereazaNumarRandomLCG() { return lcg.nextInt(); }
    public int genereazaNumarRandomMT() { return mt.nextInt(); }
}
