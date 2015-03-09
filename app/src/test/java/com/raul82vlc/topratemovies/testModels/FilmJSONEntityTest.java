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

import com.raulh82vlc.topratemovies.models.FilmJSONEntity;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Class to Test FilmJSONEntity model
 * Created by Raul Hernandez Lopez on 03/03/2015.
 */
public class FilmJSONEntityTest extends TestCase {

    private FilmJSONEntity film;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // instance of the film entity
        film = new FilmJSONEntity();
    }

    @Test
    public void testSetIdIMDB()
    {
        film.setIdIMDB("123456");
        assertEquals(film.getIdIMDB(), "123456");
    }

    @Test
    public void testSetUrlPoster()
    {
        film.setUrlPoster("http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX34_CR0,0,34,50_AL_.jpg");
        assertEquals(film.getUrlPoster(), "http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX34_CR0,0,34,50_AL_.jpg");
    }

    @Test
    public void testSetTitle()
    {
        film.setTitle("The Ring");
        assertEquals(film.getTitle(), "The Ring");
    }

    @Test
    public void testSetYear()
    {
        film.setYear("1920");
        assertEquals(film.getYear(), "1920");
    }

    @Test
    public void testSetRanking()
    {
        film.setRanking(1);
        assertEquals(film.getRanking(), 1);
    }

    @Test
    public void testSetRating()
    {
        film.setRating(9.8);
        assertEquals(film.getRating(), 9.8);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
