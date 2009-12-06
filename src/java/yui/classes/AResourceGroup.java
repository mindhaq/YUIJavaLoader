/*
 *  Copyright (c) 2009, Amostudio,inc
 *  All rights reserved.
 *  Code licensed under the BSD License:
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *    * Neither the name of the Amostudio,inc  nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 *   THIS SOFTWARE IS PROVIDED BY Amostudio,inc ''AS IS'' AND ANY
 *   EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *   DISCLAIMED. IN NO EVENT SHALL Amostudio,inc  BE LIABLE FOR ANY
 *   DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package yui.classes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yui.classes.utils.HTTPUtils;

/**
 * expected format of the resource is following :
 * version_String+relative_path+resourceAlias+resourceFileName.ext
 * example:
 * 2.8.0/build/fonts/fonts-min.css
 *
 * Breakdown:
 * 2.8.0= version_String
 * build = relative_path
 * fonts = resourceAlias
 * fonts-min.css = resourceFileName.ext.
 *
 * @author leo
 */
public class AResourceGroup {

    Logger logger = LoggerFactory.getLogger(AResourceGroup.class);
    private List<String> _group;
    private String[] metaInfo;
    private String version;
    private String filterType;
    
    //TODO  should be ENUM
    private String contentType;
    private String queryString = "";

    AResourceGroup(String queryString) {
        parseUrl(queryString);
    }


    AResourceGroup(HttpServletRequest request) {
        parseRequest(request);
    }

    private void extractmetaInfo (String item) {

                this.contentType =(item.indexOf(".js") != -1) ? HTTPUtils.CONTENT_TYPE.JAVASCRIPT+"": HTTPUtils.CONTENT_TYPE.CSS+"";
                this.metaInfo = item.split("/");
                logger.info("metainfo:  " + Arrays.toString(this.getMetaInfo()));
                this.version = this.getMetaInfo()[0];
    }

     private void parseRequest(HttpServletRequest request) {
        try {
            queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
               logger.error("error occured getting query String" + ex );
        }

        _group = new ArrayList(request.getParameterMap().size());
        for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
            String i =  e.nextElement();
            logger.info("Adding Item: " +  i);
            _group.add( i);
        }

        extractmetaInfo(_group.get(0));

//        Map m = request.getParameterMap();
//
//         for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
//                 Map.Entry pairs = (Map.Entry) it.next();
//                 String name = (String) pairs.getKey();
//                 String[] value = (String[]) pairs.getValue();
//                     logger.info("name: " + name+" value "+Arrays.toString(value));
//         }

    }

    private void parseUrl(String _q) {
        String[] yuiFiles = null;

        try {
            if (_q != null && !_q.trim().equals("")) {
                queryString = URLDecoder.decode(_q, "UTF-8");
            }

            logger.info("queryStringis " + getQueryString());

            if (!queryString.equals("")) {
                yuiFiles = getQueryString().split("&");
                if (yuiFiles == null || yuiFiles.length == 0) {
                    logger.info("thre is nothing in query?" + getQueryString());
                    return;
                }

                extractmetaInfo(yuiFiles[0]);
                _group= Arrays.asList(yuiFiles);
            }

        } catch (UnsupportedEncodingException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }

    }

    AResourceGroup(Enumeration<String> params) {
    }

    public boolean isDebug() {
        return (getQueryString().indexOf("-debug.js") != -1);
    }

    public boolean isMin() {
        return (getQueryString().indexOf("-min.js") != -1);
    }

    /**
     * @return the _group
     */
    public List<String> getGroup() {
        return _group;
    }

    /**
     * @return the metaInfo
     */
    public String[] getMetaInfo() {
        return metaInfo;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the filterType
     */
    public String getFilterType() {
        return filterType;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @return the queryString
     */
    public String getQueryString() {
        return queryString;
    }
}
