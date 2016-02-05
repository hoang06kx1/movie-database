package nguyen.hoang.movierating;

import junit.framework.TestCase;

import nguyen.hoang.movierating.Utils.Utils;

/**
 * Created by Hoang on 12/11/2015.
 */
public class MethodPasswordValidationTest extends TestCase{
    public void testLenght() {
        assertFalse(Utils.validatePassword(""));
        assertFalse(Utils.validatePassword("T"));
        assertFalse(Utils.validatePassword("T_"));
        assertFalse(Utils.validatePassword("1T_"));
        assertFalse(Utils.validatePassword("1T_a"));
        assertFalse(Utils.validatePassword("1T_aZ"));
        assertTrue(Utils.validatePassword("1T_aZ-"));
        assertTrue(Utils.validatePassword("1T_aZ-2"));
        assertTrue(Utils.validatePassword("1234567_@XHJHFKJDERBMBXNBVCGUuiopudfhsfjhewjrnbnfbdsf"));
    }

    public void testUppercase() {
        assertFalse(Utils.validatePassword("poioer12"));
        assertFalse(Utils.validatePassword("poioer12_@l"));
        assertFalse(Utils.validatePassword("@-54azr"));
        assertTrue(Utils.validatePassword("poioeR12_@l"));
        assertTrue(Utils.validatePassword("po-Radf"));
    }

    public void testSpecialCharacter() {
        assertFalse(Utils.validatePassword("poEAZ43T2"));
        assertFalse(Utils.validatePassword("poT43T2"));
        assertTrue(Utils.validatePassword("poT43T2_"));
        assertTrue(Utils.validatePassword("poT@25t"));
        assertTrue(Utils.validatePassword("pT:ztdT"));
        assertTrue(Utils.validatePassword("pT^ztdT"));
        assertTrue(Utils.validatePassword("pT*ztdT"));
        assertTrue(Utils.validatePassword("pT+ztdT"));
        assertTrue(Utils.validatePassword("pT|ztdT"));
    }

    public void testRandom() {
        assertTrue(Utils.validatePassword("Goin@123"));
        assertFalse(Utils.validatePassword("G@123"));
        assertFalse(Utils.validatePassword("L9lexcx4"));
        assertTrue(Utils.validatePassword("@L9lexcx4"));

    }

}
