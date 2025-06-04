public class Service {
    private final Licenta95Generator win95Gen;
    private final LicentaNTGenerator ntGen;
    private final SimCity2000 simCityGen;
    private final AdobePhotoshop6 ps6Gen;
    private final LinearCongruentialGenerator lcg;
    private final MersenneTwister mt;
    private final TextPad tp;

    public Service() {                                      // Creare generatoare specifice
        this.win95Gen = new Licenta95Generator();
        this.ntGen = new LicentaNTGenerator();
        this.simCityGen = new SimCity2000();
        this.ps6Gen = new AdobePhotoshop6();
        this.lcg = new LinearCongruentialGenerator();
        this.mt = new MersenneTwister();
        this.tp = new TextPad();
    }

    public String genereazaLicentaWindows95() { Audit.log("Licenta Windows95 generata."); return win95Gen.genereazaCod(); }
    public String genereazaLicentaNT() { Audit.log("Licenta Windows NT generata."); return ntGen.genereazaCod(); }
    public String genereazaLicentaSimCity2000() { Audit.log("Licenta SimCity 2000 generata."); return simCityGen.genereazaCod(); }
    public String genereazaLicentaPhotoshop6() { Audit.log("Licenta Photoshop 6 generata."); return ps6Gen.genereazaCod(); }
    public String genereazaLicentaTextPad() { Audit.log("Licenta TextPad generata."); return tp.genereazaCod(); }

    public boolean valideazaLicentaTextPad(String cod) { Audit.log("Validare licenta TextPad."); return tp.valideazaCod(cod); }
    public boolean valideazaLicentaWindows95(String cod) { Audit.log("Validare licenta Windows95."); return win95Gen.valideazaCod(cod); }
    public boolean valideazaLicentaNT(String cod) { Audit.log("Validare licenta Windows NT."); return ntGen.valideazaCod(cod); }
    public boolean valideazaLicentaSimCity2000(String cod) { Audit.log("Validare licenta SimCity 2000."); return simCityGen.valideazaCod(cod); }
    public boolean valideazaLicentaPhotoshop6(String cod) { Audit.log("Validare licenta Photoshop 6."); return ps6Gen.valideazaCod(cod); }

    public int genereazaNumarRandomLCG() { Audit.log("Numar random generat cu lcg."); return lcg.nextInt(); }
    public int genereazaNumarRandomMT() { Audit.log("Numar random generat cu Mersenne."); return mt.nextInt(); }

}
