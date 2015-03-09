/*
 * Copyright (C) ${YEAR} Raul Hernandez Lopez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.raul82vlc.topratemovies.testModels;

import com.raulh82vlc.topratemovies.models.SubDetailsJSONEntity;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Class to Test SubDetailsJSONEntity model
 * Created by Raul Hernandez Lopez on 03/03/2015.
 */
public class SubDetailsJSONEntityTest extends TestCase {

    private SubDetailsJSONEntity subDetailsJSONEntity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // instance of the film entity
        subDetailsJSONEntity = new SubDetailsJSONEntity();
    }

    @Test
    public void testSetAttributes()
    {
        subDetailsJSONEntity.setNameId("12AB");
        subDetailsJSONEntity.setName("Camino Jose Cela");
        assertEquals(subDetailsJSONEntity.getName(), "Camino Jose Cela");
        assertEquals(subDetailsJSONEntity.getNameId(), "12AB");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
