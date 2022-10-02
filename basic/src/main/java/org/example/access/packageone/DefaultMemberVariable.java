package org.example.access.packageone;

import net.jcip.annotations.ThreadSafe;
import org.junit.jupiter.api.Test;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/9/28 17:00
 */
@ThreadSafe
public class DefaultMemberVariable {

    @Test
    public void DefaultTest() {
        MemberVariable memberVariable = new MemberVariable();
        System.out.println(memberVariable.stringDefault);
        System.out.println(memberVariable.stringProtected);
        System.out.println(memberVariable.stringPublic);
    }
}
