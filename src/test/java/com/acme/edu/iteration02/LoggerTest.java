//package com.acme.edu.iteration02;
//
//import com.acme.edu.logger.Logger;
//import com.acme.edu.SysoutCaptureAndAssertionAbility;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
//import static java.lang.System.lineSeparator;
//
//public class LoggerTest implements SysoutCaptureAndAssertionAbility {
//    //region given
//    @Before
//    public void setUpSystemOut() throws IOException {
//        resetOut();
//        captureSysout();
//    }
//
//    @After
//    public void tearDown() {
//        resetOut();
//        Logger.flush();
//    }
//    //endregion
//
//
//
//    //TODO: implement Logger solution to match specification as tests
//
//    @Test
//    public void shouldLogSequentIntegersAsSum() throws IOException {
//        //region when
//        Logger.log("str 1");
//        Logger.log(1);
//        Logger.log(2);
//        Logger.log("str 2");
//        Logger.log(0);
//        Logger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("str 1" + lineSeparator());
//        assertSysoutContains("3" + lineSeparator());
//        assertSysoutContains("str 2" + lineSeparator());
//        assertSysoutContains("0");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
//        //region when
//        Logger.log("str 1");
//        Logger.log(10);
//        Logger.log(Integer.MAX_VALUE);
//        Logger.log("str 2");
//        Logger.log(0);
//        Logger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("str 1" + lineSeparator());
//        assertSysoutContains(Integer.MAX_VALUE + lineSeparator());
//        assertSysoutContains("10" + lineSeparator());
//        assertSysoutContains("str 2" + lineSeparator());
//        assertSysoutContains("0" + lineSeparator());
//        //endregion
//    }
//
//    @Test
//    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
//        //region when
//        Logger.log("str 1");
//        Logger.log((byte)10);
//        Logger.log((byte)Byte.MAX_VALUE);
//        Logger.log("str 2");
//        Logger.log(0);
//        Logger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("str 1");
//        assertSysoutContains(Byte.MAX_VALUE + lineSeparator());
//        assertSysoutContains("10");
//        assertSysoutContains("str 2");
//        assertSysoutContains("0");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
//        //region when
//        Logger.log("str 1");
//        Logger.log("str 2");
//        Logger.log("str 2");
//        Logger.log(0);
//        Logger.log("str 2");
//        Logger.log("str 3");
//        Logger.log("str 3");
//        Logger.log("str 3");
//        Logger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("str 1");
//        assertSysoutContains("str 2 (x2)");
//        assertSysoutContains("0");
//        assertSysoutContains("str 2");
//        assertSysoutContains("str 3 (x3)");
//        //endregion
//    }
//}