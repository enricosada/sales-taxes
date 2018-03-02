package io.sada.lmsalestaxes;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class AppTest2 {
    @Property public void concatenationLength(String s1, String s2) {
        Assert.assertEquals(s1.length() + s2.length(), (s1 + s2).length());
    }
}
