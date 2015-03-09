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

package com.raulh82vlc.topratemovies.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raulh82vlc.topratemovies.R;


/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 * Clas Holder used for Recycler view management
 */
public class ItemListViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    ImageView imgFilmThumbnail;
    TextView txtTitle;
    TextView txtYear;
    TextView txtRating;

    /**
     * ItemListViewHolder is a container to hold all elements to fill
     * in a grouped way
     * @param iItemView
     */
    public ItemListViewHolder(View iItemView)
    {
        super(iItemView);
        linearLayout = (LinearLayout) iItemView.findViewById(R.id.item_list);
        txtTitle = (TextView) iItemView.findViewById(R.id.txtTitle);
        txtYear = (TextView) iItemView.findViewById(R.id.txtYear);
        txtRating = (TextView) iItemView.findViewById(R.id.txtRating);
        imgFilmThumbnail = (ImageView) iItemView.findViewById(R.id.imgThumbnail);
    }
}
