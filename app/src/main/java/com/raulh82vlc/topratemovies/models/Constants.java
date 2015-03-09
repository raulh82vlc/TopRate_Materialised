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

package com.raulh82vlc.topratemovies.models;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 * General constants used when variables passed
 * into Intents flow or General constants in the program
 */
public final class Constants {
    // param keys of activities
    public static final String A_NAME_FILM = "com.raulh82vlc.topratemovies.nFilm";
    public static final String A_RANKING = "com.raulh82vlc.topratemovies.nRanking";
    public static final String A_NAME_IMG = "com.raulh82vlc.topratemovies.nImgPath";
    // params keys of fragment
    public static final String F_AUTHOR = "com.raulh82vlc.topratemovies.paramAuthor";
    public static final String F_EMAIL = "com.raulh82vlc.topratemovies.paramEmail";
    public static final String F_NMOVIES = "com.raulh82vlc.topratemovies.nMovies";

    public static final int MIN = 1;
    public static final int MAX = 20;

    public static enum TOWARDS {TOP,BOTTOM};
}
