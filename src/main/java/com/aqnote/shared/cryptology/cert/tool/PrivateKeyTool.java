/*
 * Copyright 2013-2023 "Peng Li"<aqnote@qq.com>
 * Licensed under the AQNote License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.aqnote.com/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aqnote.shared.cryptology.cert.tool;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.aqnote.shared.cryptology.cert.CertException;

/**
 * 类PrivateKeyTool.java的实现描述：私钥处理类
 * 
 * @author "Peng Li"<aqnote@qq.com> Nov 18, 2013 12:01:35 PM
 */
public class PrivateKeyTool {

    private static final String RSA_ALG       = "RSA";
    private static final String BEGIN_KEY     = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String END_KEY       = "-----END RSA PRIVATE KEY-----";
    private static final String lineSeparator = System.lineSeparator();

    public static PrivateKey coverString2PrivateKey(String base64PrivateKey) throws CertException {

        try {
            byte[] priEncoded = getKeyEncoded(base64PrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALG);
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(priEncoded);
            return keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new CertException(e);
        } catch (InvalidKeySpecException e) {
            throw new CertException(e);
        }
    }

    public static String coverPrivateKey2String(Key key) {
        String keyContent = Base64.encodeBase64String(key.getEncoded());
        return BEGIN_KEY + lineSeparator + keyContent + END_KEY;
    }

    private static byte[] getKeyEncoded(String base64KeyFile) {
        if (StringUtils.isEmpty(base64KeyFile)) {
            return null;
        }

        String tmpBase64KeyFile = base64KeyFile;
        String headLine = BEGIN_KEY + lineSeparator;
        if (base64KeyFile.startsWith(headLine)) {
            tmpBase64KeyFile = StringUtils.removeStart(base64KeyFile, headLine);
        }
        headLine = BEGIN_KEY + "\r\n";
        if (base64KeyFile.startsWith(headLine)) {
            tmpBase64KeyFile = StringUtils.removeStart(base64KeyFile, headLine);
        }

        if (tmpBase64KeyFile.endsWith(END_KEY)) {
            tmpBase64KeyFile = StringUtils.removeEnd(base64KeyFile, END_KEY);
        }

        return Base64.decodeBase64(tmpBase64KeyFile);
    }
}
