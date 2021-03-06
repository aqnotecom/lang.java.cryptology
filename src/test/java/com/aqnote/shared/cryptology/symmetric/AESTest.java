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
package com.aqnote.shared.cryptology.symmetric;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;

import junit.framework.TestCase;

/**
 * 类AESTest.java的实现描述：AES算法测试类
 * 
 * @author "Peng Li"<aqnote@qq.com> May 8, 2012 1:13:16 PM
 */
public class AESTest extends TestCase {

    public void test() throws UnsupportedEncodingException, RuntimeException {
        System.out.println(AES.encrypt("testlip"));
        Assert.assertEquals(AES.decrypt(AES.encrypt("testlip")), "testlip");
    }
}
