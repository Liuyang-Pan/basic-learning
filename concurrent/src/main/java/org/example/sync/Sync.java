package org.example.sync;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/9/30 16:44
 */
@Slf4j(topic = "Sync")
public class Sync {

    @Test
    public void SyncTest() {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
    }
}
