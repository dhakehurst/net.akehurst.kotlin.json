/**
 * Copyright (C) 2019 Dr. David H. Akehurst (http://dr.david.h.akehurst.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.akehurst.kotlin.json

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class test_JsonProcessor {

    @Test
    fun empty() {

        val jsonString = ""

        assertFailsWith<JsonParserException> {
            Json.process(jsonString)
        }

    }

    @Test
    fun boolean_false() {

        val jsonString = "false"

        val actual = Json.process(jsonString)

        val expected = json("json") {
            primitive(false)
        }

        assertEquals(expected, actual)

    }

    @Test
    fun boolean_true() {

        val jsonString = "true"

        val actual = Json.process(jsonString)

        val expected = json("json") {
            primitive(true)
        }

        assertEquals(expected, actual)

    }

    @Test
    fun number_1() {

        val jsonString = "1"

        val actual = Json.process(jsonString)

        val expected = json("json") {
            primitive(1)
        }

        assertEquals(expected, actual)

    }

    @Test
    fun string() {

        val jsonString = "\"hello\""

        val actual = Json.process(jsonString)

        val expected = json("json") {
            primitive("hello")
        }

        assertEquals(expected, actual)

    }


    @Test
    fun emptyArray() {

        val jsonString = "[]"

        val actual = Json.process(jsonString)

        val expected = json("json") {
            arrayJson{}
        }

        assertEquals(expected, actual)

    }

    @Test
    fun array() {

        val jsonString = "[ 1, true, \"hello\", {} ]"

        val actual = Json.process(jsonString)

        val expected = json("json") {
            arrayJson {
                primitive(1)
                primitive(true)
                primitive("hello")
                objectJson { }
            }
        }

        assertEquals(expected, actual)

    }

    @Test
    fun emptyObject() {

        val jsonString = "{}"

        val actual = Json.process(jsonString);

        val expected = json("json") {
            objectJson { }
        }

        assertEquals(expected, actual)

    }


    @Test
    fun object_() {

        val jsonString = """
            {
                "bProp": true,
                "nProp" : 1,
                "sProp" : "hello",
                "aProp" : [ 1, true, "hello", {} ],
                "oProp" : {
                    "bProp": false,
                    "nProp" : 3.14
                }
            }
        """.trimIndent()

        val actual = Json.process(jsonString);

        val expected = json("json") {
            objectJson {
                property("bProp", true)
                property("nProp", true)
                property("sProp", true)
                property("aProp", true)
                property("oProp") {
                    objectJson {
                        property("bProp", true)
                        property("nProp", true)
                    }
                }
            }
        }

        assertEquals(expected, actual)

    }

}