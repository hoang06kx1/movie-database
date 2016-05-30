package nguyen.hoang.movierating;

import junit.framework.TestCase;

import nguyen.hoang.movierating.util.Utils;

/**
 * Performs Validation Test for e-mail validations.
 *
 *
 * @version $Revision$
 */
public class MethodEmailValidationTest extends TestCase {

    /**
     * The key used to retrieve the set of validation
     * rules from the xml file.
     */
    protected static String FORM_KEY = "emailForm";

    /**
     * The key used to retrieve the validator action.
     */
    protected static String ACTION = "email";

    public MethodEmailValidationTest(String name) {
        super(name);
    }

    protected void tearDown() {
    }

    /**
     * Tests the e-mail validation.
     */
    public void testEmail()  {
        assertTrue(Utils.validateEmail("jsmith@apache.org"));
    }

    /**
     * Tests the email validation with numeric domains.
     */
    public void testEmailWithNumericAddress()  {
        assertTrue(Utils.validateEmail("someone@[216.109.118.76]"));
        assertTrue(Utils.validateEmail("someone@yahoo.com"));
    }

    /**
     * Tests the e-mail validation.
     */
    public void testEmailExtension()  {
        assertTrue(Utils.validateEmail("jsmith@apache.org"));

        assertTrue(Utils.validateEmail("jsmith@apache.com"));

        assertTrue(Utils.validateEmail("jsmith@apache.net"));

        assertTrue(Utils.validateEmail("jsmith@apache.info"));

        assertFalse(Utils.validateEmail("jsmith@apache."));

        assertFalse(Utils.validateEmail("jsmith@apache.c"));

        assertTrue(Utils.validateEmail("someone@yahoo.museum"));

        assertFalse(Utils.validateEmail("someone@yahoo.mu-seum"));
    }

    /**
     * <p>Tests the e-mail validation with a dash in
     * the address.</p>
     */
    public void testEmailWithDash()  {
        assertTrue(Utils.validateEmail("andy.noble@data-workshop.com"));

        assertFalse(Utils.validateEmail("andy-noble@data-workshop.-com"));

        assertFalse(Utils.validateEmail("andy-noble@data-workshop.c-om"));

        assertFalse(Utils.validateEmail("andy-noble@data-workshop.co-m"));
    }

    /**
     * Tests the e-mail validation with a dot at the end of
     * the address.
     */
    public void testEmailWithDotEnd()  {
        assertFalse(Utils.validateEmail("andy.noble@data-workshop.com."));
    }

    /**
     * Tests the e-mail validation with an RCS-noncompliant character in
     * the address.
     */
    public void testEmailWithBogusCharacter()  {

        assertFalse(Utils.validateEmail("andy.noble@\u008fdata-workshop.com"));

        // The ' character is valid in an email username.
        assertTrue(Utils.validateEmail("andy.o'reilly@data-workshop.com"));

        // But not in the domain name.
        assertFalse(Utils.validateEmail("andy@o'reilly.data-workshop.com"));

        // The + character is valid in an email username.
        assertTrue(Utils.validateEmail("foo+bar@i.am.not.in.us.example.com"));

        // But not in the domain name
        assertFalse(Utils.validateEmail("foo+bar@example+3.com"));

        // Domains with only special characters aren't allowed (VALIDATOR-286) 
        assertFalse(Utils.validateEmail("test@%*.com"));
        assertFalse(Utils.validateEmail("test@^&#.com"));

    }

    public void testVALIDATOR_315() {
        assertFalse(Utils.validateEmail("me@at&t.net"));
        assertTrue(Utils.validateEmail("me@att.net")); // Make sure TLD is not the cause of the failure
    }

    public void testVALIDATOR_278() {
        assertFalse(Utils.validateEmail("someone@-test.com"));// hostname starts with dash/hyphen
        assertFalse(Utils.validateEmail("someone@test-.com"));// hostname ends with dash/hyphen
    }

    public void testValidator235() {
        String version = System.getProperty("java.version");
        if (version.compareTo("1.6") < 0) {
            System.out.println("Cannot run Unicode IDN tests");
            return; // Cannot run the test
        }
        assertTrue("xn--d1abbgf6aiiy.xn--p1ai should validate", Utils.validateEmail("someone@xn--d1abbgf6aiiy.xn--p1ai"));
        assertTrue("президент.рф should validate", Utils.validateEmail("someone@президент.рф"));
        assertTrue("www.b\u00fccher.ch should validate", Utils.validateEmail("someone@www.b\u00fccher.ch"));
        assertFalse("www.\uFFFD.ch FFFD should fail", Utils.validateEmail("someone@www.\uFFFD.ch"));
        assertTrue("www.b\u00fccher.ch should validate", Utils.validateEmail("someone@www.b\u00fccher.ch"));
        assertFalse("www.\uFFFD.ch FFFD should fail", Utils.validateEmail("someone@www.\uFFFD.ch"));
    }

    /**
     * Tests the email validation with commas.
     */
    public void testEmailWithCommas()  {
        assertFalse(Utils.validateEmail("joeblow@apa,che.org"));

        assertFalse(Utils.validateEmail("joeblow@apache.o,rg"));

        assertFalse(Utils.validateEmail("joeblow@apache,org"));

    }

    /**
     * Tests the email validation with spaces.
     */
    public void testEmailWithSpaces()  {
        assertFalse(Utils.validateEmail("joeblow @apache.org")); // TODO - this should be valid?

        assertFalse(Utils.validateEmail("joeblow@ apache.org"));

        assertTrue(Utils.validateEmail(" joeblow@apache.org")); // TODO - this should be valid?

        assertTrue(Utils.validateEmail("joeblow@apache.org "));

        assertFalse(Utils.validateEmail("joe blow@apache.org "));

        assertFalse(Utils.validateEmail("joeblow@apa che.org "));

    }

    /**
     * Tests the email validation with ascii control characters.
     * (i.e. Ascii chars 0 - 31 and 127)
     */
    public void testEmailWithControlChars()  {
        for (char c = 0; c < 32; c++) {
            assertFalse("Test control char " + ((int)c), Utils.validateEmail("foo" + c + "bar@domain.com"));
        }
        assertFalse("Test control char 127", Utils.validateEmail("foo" + ((char) 127) + "bar@domain.com"));
    }

    /**
     * VALIDATOR-296 - A / or a ! is valid in the user part,
     *  but not in the domain part 
     */
    public void testEmailWithSlashes() {
        assertTrue(
                "/ and ! valid in username",
                Utils.validateEmail("joe!/blow@apache.org")
        );
        assertFalse(
                "/ not valid in domain",
                Utils.validateEmail("joe@ap/ache.org")
        );
        assertFalse(
                "! not valid in domain",
                Utils.validateEmail("joe@apac!he.org")
        );
    }

    /**
     * Write this test according to parts of RFC, as opposed to the type of character
     * that is being tested.
     */
    public void testEmailUserName()  {

        assertTrue(Utils.validateEmail("joe1blow@apache.org"));

        assertTrue(Utils.validateEmail("joe$blow@apache.org"));

        assertTrue(Utils.validateEmail("joe-@apache.org"));

        assertTrue(Utils.validateEmail("joe_@apache.org"));

        assertTrue(Utils.validateEmail("joe+@apache.org")); // + is valid unquoted

        assertTrue(Utils.validateEmail("joe!@apache.org")); // ! is valid unquoted

        assertTrue(Utils.validateEmail("joe*@apache.org")); // * is valid unquoted

        assertTrue(Utils.validateEmail("joe'@apache.org")); // ' is valid unquoted

        assertTrue(Utils.validateEmail("joe%45@apache.org")); // % is valid unquoted

        assertTrue(Utils.validateEmail("joe?@apache.org")); // ? is valid unquoted

        assertTrue(Utils.validateEmail("joe&@apache.org")); // & ditto

        assertTrue(Utils.validateEmail("joe=@apache.org")); // = ditto

        assertTrue(Utils.validateEmail("+joe@apache.org")); // + is valid unquoted

        assertTrue(Utils.validateEmail("!joe@apache.org")); // ! is valid unquoted

        assertTrue(Utils.validateEmail("*joe@apache.org")); // * is valid unquoted

        assertTrue(Utils.validateEmail("'joe@apache.org")); // ' is valid unquoted

        assertTrue(Utils.validateEmail("%joe45@apache.org")); // % is valid unquoted

        assertTrue(Utils.validateEmail("?joe@apache.org")); // ? is valid unquoted

        assertTrue(Utils.validateEmail("&joe@apache.org")); // & ditto

        assertTrue(Utils.validateEmail("=joe@apache.org")); // = ditto

        assertTrue(Utils.validateEmail("+@apache.org")); // + is valid unquoted

        assertTrue(Utils.validateEmail("!@apache.org")); // ! is valid unquoted

        assertTrue(Utils.validateEmail("*@apache.org")); // * is valid unquoted

        assertTrue(Utils.validateEmail("'@apache.org")); // ' is valid unquoted

        assertTrue(Utils.validateEmail("%@apache.org")); // % is valid unquoted

        assertTrue(Utils.validateEmail("?@apache.org")); // ? is valid unquoted

        assertTrue(Utils.validateEmail("&@apache.org")); // & ditto

        assertTrue(Utils.validateEmail("=@apache.org")); // = ditto


        //UnQuoted Special characters are invalid

        assertFalse(Utils.validateEmail("joe.@apache.org")); // . not allowed at end of local part

        assertFalse(Utils.validateEmail(".joe@apache.org")); // . not allowed at start of local part

        assertFalse(Utils.validateEmail(".@apache.org")); // . not allowed alone

        assertTrue(Utils.validateEmail("joe.ok@apache.org")); // . allowed embedded

        assertFalse(Utils.validateEmail("joe..ok@apache.org")); // .. not allowed embedded

        assertFalse(Utils.validateEmail("..@apache.org")); // .. not allowed alone

        assertFalse(Utils.validateEmail("joe(@apache.org"));

        assertFalse(Utils.validateEmail("joe)@apache.org"));

        assertFalse(Utils.validateEmail("joe,@apache.org"));

        assertFalse(Utils.validateEmail("joe;@apache.org"));


        //Quoted Special characters are valid
        assertTrue(Utils.validateEmail("\"joe.\"@apache.org"));

        assertTrue(Utils.validateEmail("\".joe\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe+\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe!\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe*\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe'\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe(\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe)\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe,\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe%45\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe;\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe?\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe&\"@apache.org"));

        assertTrue(Utils.validateEmail("\"joe=\"@apache.org"));

        assertTrue(Utils.validateEmail("\"..\"@apache.org"));

        assertTrue(Utils.validateEmail("john56789.john56789.john56789.john56789.john56789.john56789.john@example.com"));

        assertFalse(Utils.validateEmail("john56789.john56789.john56789.john56789.john56789.john56789.john5@example.com"));

        assertTrue(Utils.validateEmail("\\>escape\\\\special\\^characters\\<@example.com"));

        assertTrue(Utils.validateEmail("Abc\\@def@example.com"));

        assertFalse(Utils.validateEmail("Abc@def@example.com"));

        assertTrue(Utils.validateEmail("space\\ monkey@example.com"));
    }

    public void testValidator293(){
        assertTrue(Utils.validateEmail("abc-@abc.com"));
        assertTrue(Utils.validateEmail("abc_@abc.com"));
        assertTrue(Utils.validateEmail("abc-def@abc.com"));
        assertTrue(Utils.validateEmail("abc_def@abc.com"));
        assertFalse(Utils.validateEmail("abc@abc_def.com"));
    }

    public void testValidator365() {
        assertFalse(Utils.validateEmail(
                "Loremipsumdolorsitametconsecteturadipiscingelit.Nullavitaeligulamattisrhoncusnuncegestasmattisleo." +
                        "Donecnonsapieninmagnatristiquedictumaacturpis.Fusceorciduifacilisisutsapieneuconsequatpharetralectus." +
                        "Quisqueenimestpulvinarutquamvitaeportamattisex.Nullamquismaurisplaceratconvallisjustoquisportamauris." +
                        "Innullalacusconvalliseufringillautvenenatissitametdiam.Maecenasluctusligulascelerisquepulvinarfeugiat." +
                        "Sedmolestienullaaliquetorciluctusidpharetranislfinibus.Suspendissemalesuadatinciduntduisitametportaarcusollicitudinnec." +
                        "Donecetmassamagna.Curabitururnadiampretiumveldignissimporttitorfringillaeuneque." +
                        "Duisantetelluspharetraidtinciduntinterdummolestiesitametfelis.Utquisquamsitametantesagittisdapibusacnonodio." +
                        "Namrutrummolestiediamidmattis.Cumsociisnatoquepenatibusetmagnisdisparturientmontesnasceturridiculusmus." +
                        "Morbiposueresedmetusacconsectetur.Etiamquisipsumvitaejustotempusmaximus.Sedultriciesplaceratvolutpat." +
                        "Integerlacuslectusmaximusacornarequissagittissitametjusto." +
                        "Cumsociisnatoquepenatibusetmagnisdisparturientmontesnasceturridiculusmus.Maecenasindictumpurussedrutrumex.Nullafacilisi." +
                        "Integerfinibusfinibusmietpharetranislfaucibusvel.Maecenasegetdolorlacinialobortisjustovelullamcorpersem." +
                        "Vivamusaliquetpurusidvariusornaresapienrisusrutrumnisitinciduntmollissemnequeidmetus." +
                        "Etiamquiseleifendpurus.Nuncfelisnuncscelerisqueiddignissimnecfinibusalibero." +
                        "Nuncsemperenimnequesitamethendreritpurusfacilisisac.Maurisdapibussemperfelisdignissimgravida." +
                        "Aeneanultricesblanditnequealiquamfinibusodioscelerisqueac.Aliquamnecmassaeumaurisfaucibusfringilla." +
                        "Etiamconsequatligulanisisitametaliquamnibhtemporquis.Nuncinterdumdignissimnullaatsodalesarcusagittiseu." +
                        "Proinpharetrametusneclacuspulvinarsedvolutpatliberoornare.Sedligulanislpulvinarnonlectuseublanditfacilisisante." +
                        "Sedmollisnislalacusauctorsuscipit.Inhachabitasseplateadictumst.Phasellussitametvelittemporvenenatisfeliseuegestasrisus." +
                        "Aliquameteratsitametnibhcommodofinibus.Morbiefficiturodiovelpulvinariaculis." +
                        "Aeneantemporipsummassaaconsecteturturpisfaucibusultrices.Praesentsodalesmaurisquisportafermentum." +
                        "Etiamnisinislvenenatisvelauctorutullamcorperinjusto.Proinvelligulaerat.Phasellusvestibulumgravidamassanonfeugiat." +
                        "Maecenaspharetraeuismodmetusegetefficitur.Suspendisseamet@gmail.com"));
    }

    public void testValidator374() {
        assertTrue(Utils.validateEmail("abc@school.school"));
    }
}