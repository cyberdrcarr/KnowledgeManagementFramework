/*
 * ****************************************************************************************************************
 *  *
 *  * Copyright (C) 2012 by Cognitive Medical Systems, Inc (http://www.cognitivemedciine.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 *  * with the License. You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software distributed under the License is
 *  * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and limitations under the License.
 *  *
 *  ****************************************************************************************************************
 *
 * ****************************************************************************************************************
 *  * Socratic Grid contains components to which third party terms apply. To comply with these terms, the following
 *  * notice is provided:
 *  *
 *  * TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *  * Copyright (c) 2008, Nationwide Health Information Network (NHIN) Connect. All rights reserved.
 *  * Redistribution and use in source and binary forms, with or without modification, are permitted provided that
 *  * the following conditions are met:
 *  *
 *  * - Redistributions of source code must retain the above copyright notice, this list of conditions and the
 *  *     following disclaimer.
 *  * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 *  *     following disclaimer in the documentation and/or other materials provided with the distribution.
 *  * - Neither the name of the NHIN Connect Project nor the names of its contributors may be used to endorse or
 *  *     promote products derived from this software without specific prior written permission.
 *  *
 *  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 *  * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 *  * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION HOWEVER
 *  * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  *
 *  * END OF TERMS AND CONDITIONS
 *  *
 *  ****************************************************************************************************************
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.socraticgrid.presentationservices.utils.factQuery;

import org.socraticgrid.util.CommonUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author nhin
 */
public class EquipmentsQuery {
    
    private String classname = "\n"+this.getClass().getName()+":";
    private static EquipmentsQuery instance = null;
    
    public static EquipmentsQuery getInstance() {
        synchronized (EquipmentsQuery.class) {
            if (instance == null) {
                instance = new EquipmentsQuery();
            }
        }
        return instance;
    }
    
    public String createResponse(Map args, boolean useStubbedData) throws FileNotFoundException{

        String jsonResp = null;

        // EXTRACT out args to filter with
        String domain =         (String) args.get("domain");
        String patientId =      (String) args.get("patientId");
        String userId =         (String) args.get("userId");
        String responseType =   (String) args.get("responseType");
        String itemId =         (String) args.get("itemId");
        String code =           (String) args.get("code");
        String codeSystemCode = (String) args.get("codeSystemCode");
        String fromDate =       (String) args.get("fromDate");

        if (useStubbedData) {
            if (responseType.equalsIgnoreCase("detail"))
                jsonResp = this.getStubbedXMLDetailData(itemId);
            else
                jsonResp = this.getStubbedXMLListData();

        } else {
            if (responseType.equalsIgnoreCase("detail")) {
                jsonResp = this.mapFactDetail(itemId);
            } else {
                jsonResp = this.mapFactList(patientId);
            }
        }
        return jsonResp;

    }

    //TBD
    private String mapFactDetail(String itemId) {
        return this.classname+"KMR Fact mapping TBD";
    }
    private String mapFactList(String patientId) {
        return this.classname+"KMR Fact mapping TBD";
    }
    
    public String getStubbedXMLDetailData(String itemId) throws FileNotFoundException {
        
        String id = itemId;
        if (CommonUtil.strNullorEmpty(itemId)) {
System.out.println(classname+"No itemId given. DEFAULTING to 10");
            id = "10";
        }
        
        String filename = "/home/nhin/Properties/facts/data/getPatientData-Equipments_Detail_"+id+".json";
        System.out.println("PULLING detial STATIC DATA:  "+filename);
        
        String text = new Scanner( new File(filename) ).useDelimiter("\\A").next();
        return text;
    }
    
    public String getStubbedXMLListData() throws FileNotFoundException {
        String filename = "/home/nhin/Properties/facts/data/getPatientData-Equipments_List.json";
        System.out.println("PULLING list STATIC DATA:  "+filename);

        String text = new Scanner( new File(filename) ).useDelimiter("\\A").next();
        return text;
    }
    
    
    
    
    
}