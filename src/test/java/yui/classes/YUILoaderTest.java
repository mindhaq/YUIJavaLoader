/*
 *  Copyright (c) 2009, Amostudio,inc
 *  All rights reserved.
 *  Code licensed under the BSD License:
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the Amostudio,inc  nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY Amostudio,inc ''AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL Amostudio,inc  BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package yui.classes;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * TODO
 * @author leo
 */
public class YUILoaderTest {
  YUILoader loader;
  String yuiVersion;
  private static final Logger logger = LoggerFactory.getLogger(YUILoaderTest.class);

  public YUILoaderTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
    yuiVersion = System.getProperty("yui.version", "3.1.1");
    logger.info("[YUILoaderTest] yuiversion is:" + yuiVersion);
    loader = new YUILoader(yuiVersion);
    loader.filter = YUILoader.YUI_RAW;
    loader.load("yahoo", "dom", "calendar", "event", "tabview", "grids", "fonts", "reset", "logger");
    logger.info("Creating YUILoader loader");
  }

  @After
  public void tearDown() {
    loader = null;
  }


  /**
   * Test of script method, of class YUILoader.
   */
  @Test
  public void testScript() {
    logger.info("script");

    // lame but still better than nothing for now.
    String result = loader.script();
    assertNotNull(result);


  }

  /**
   * Test of css method, of class YUILoader.
   */
  @Test
  public void testCss() {
    logger.info("css");

    // lame but still better than nothing for now.
    String result = loader.css();
    assertNotNull(result);
  }


  /**
   * Test of css method, of class YUILoader.
   */
  @Test
  public void script_data() {
    logger.info("script_data");

    // lame but still better than nothing for now.
    Map result = loader.script_data();
    assertNotNull(result);
    assertTrue(Map.class.isAssignableFrom(result.getClass()));
  }


  @Test
  public void css_data() {
    logger.info("css_data");

    // lame but still better than nothing for now.
    Map result = loader.css_data();
    assertNotNull(result);
    assertTrue(Map.class.isAssignableFrom(result.getClass()));
  }


  @Ignore
  @Test
  public void testNoUndefined() {
    logger.info("css");
    assertEquals(loader.howManyUndefined(), 0);
  }


  /**
   * Test of getLink method, of class YUILoader.
   */
  @Test
  public void testGetLink() {
    logger.info("getLink");

    // lame but still better than nothing for now.
    String result = loader.script();
    assertNotNull(result);
  }

}
