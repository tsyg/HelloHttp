package com.dataart.demo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.dataart.demo.hello;
/**
 * Unit test for the App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     *   PS. The defailt one  :)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     *  Message two words test
     */
    public void testMessage()
    {
	hello.MyHandler h = new hello.MyHandler();
	String message = h.getMessage();
	String [] words = message.split(" ");
	assertEquals("The first word should be 'Hello'","Hello,",words[0]);
	assertEquals("The second word should be 'HTTP'","HTTP!",words[1]);
    }

        public void testWord3()
    {
       
	hello.MyHandler h = new hello.MyHandler();
	String message = h.getMessage();
	String [] words = message.split(" ");
	String w2=words[2].split("=")[0];
	assertEquals("The third word should be 'Count'","Count",w2);   
    }

}
