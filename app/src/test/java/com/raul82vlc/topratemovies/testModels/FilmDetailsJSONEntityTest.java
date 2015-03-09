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


import com.raulh82vlc.topratemovies.models.FilmDetailsJSONEntity;
import com.raulh82vlc.topratemovies.models.SubDetailsJSONEntity;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to Test FilmDetailsJSONEntity model
 * Created by Raul Hernandez Lopez on 03/03/2015.
 */
public class FilmDetailsJSONEntityTest  extends TestCase {

    // instance of FilmDetailsJSONEntity
    private FilmDetailsJSONEntity filmDetails;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // instance of the film entity
        filmDetails = new FilmDetailsJSONEntity();

        List<SubDetailsJSONEntity> writers1 = new ArrayList<>();
        List<SubDetailsJSONEntity> directors1 = new ArrayList<>();
        SubDetailsJSONEntity newSub = new SubDetailsJSONEntity();
        newSub.setNameId("1A");
        newSub.setName("Cam1A");
        writers1.add(newSub);
        directors1.add(newSub);

        filmDetails.setWriters(writers1);
        filmDetails.setDirectors(directors1);
    }

    @Test
    public void testSetMetascore()
    {
        filmDetails.setMetascore("80/100");
        assertEquals(filmDetails.getMetascore(), "80/100");
    }

    @Test
    public void testSetTitle()
    {
        filmDetails.setTitle("The beauty and the beast");
        assertEquals(filmDetails.getTitle(), "The beauty and the beast");
    }

    @Test
    public void testSetPlot()
    {
        filmDetails.setPlot("Ergo ergo ergo ergo\n ergo ergo");
        assertEquals(filmDetails.getPlot(), "Ergo ergo ergo ergo\n" +
                " ergo ergo");
    }

    @Test
    public void testSetSimplePlot()
    {
        filmDetails.setSimplePlot("Ergo ergo ergo ergo");
        assertEquals(filmDetails.getSimplePlot(), "Ergo ergo ergo ergo");
    }

    @Test
    public void testSetYear()
    {
        filmDetails.setYear("1920");
        assertEquals(filmDetails.getYear(), "1920");
    }

    @Test
    public void testWriters()
    {
        assertEquals(filmDetails.getWriters().size(), 1);
        assertEquals(filmDetails.getWriters().get(0).getName(), "Cam1A");
    }

    @Test
    public void testSetUrlPoster()
    {
        filmDetails.setUrlPoster("http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX34_CR0,0,34,50_AL_.jpg");
        assertEquals(filmDetails.getUrlPoster(), "http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX34_CR0,0,34,50_AL_.jpg");
    }

    @Test
    public void testGetToString()
    {
        List<SubDetailsJSONEntity> writers = filmDetails.getWriters();
        SubDetailsJSONEntity newSub = new SubDetailsJSONEntity();
        newSub.setNameId("2A");
        newSub.setName("Cam2A");
        writers.add(newSub);
        filmDetails.setWriters(writers);

        assertEquals(filmDetails.getWriters().size(), 2);
        assertEquals(filmDetails.getToString(filmDetails.getWriters()), "Cam1A, Cam2A");
    }

    @Test
    public void testDirectors()
    {
        assertEquals(filmDetails.getDirectors().size(), 1);
        assertEquals(filmDetails.getDirectors().get(0).getName(), "Cam1A");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
