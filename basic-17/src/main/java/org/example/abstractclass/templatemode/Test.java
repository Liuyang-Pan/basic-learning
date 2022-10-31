package org.example.abstractclass.templatemode;

public class Test {
    public static void main(String[] args) {
        Pupil pupil = new Pupil();

        StudentMiddle studentMiddle = new StudentMiddle();
        pupil.write();
        System.out.println("----------------------");
        studentMiddle.write();
    }
}
