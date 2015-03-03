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

package com.raulh82vlc.imdbfilmstoprate.widgets;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.raulh82vlc.imdbfilmstoprate.R;
import com.raulh82vlc.imdbfilmstoprate.models.FilmJSONEntity;

import java.util.List;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 * Adapter of RecyclerView
 */
public class RecyclerViewAdapter
    extends RecyclerView.Adapter <ItemListViewHolder>
        implements View.OnClickListener
{
    private List<FilmJSONEntity> mFilms;
    private OnItemClickListenerInterface onItemClickFromList;
    private Context mContext;

    public RecyclerViewAdapter(Context ctx, List <FilmJSONEntity> iFilms)
    {
        mContext = ctx;
        if(iFilms != null)
            mFilms = iFilms;
    }


    public void setOnItemClickFromList(OnItemClickListenerInterface iOnItemClickFromList)
    {
        this.onItemClickFromList = iOnItemClickFromList;
    }


    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View aItemListView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_list_layout, parent, false);
        // Setting on click listener for each item
        aItemListView.setOnClickListener(this);
        return new ItemListViewHolder(aItemListView);
    }

    /**
     * onBindViewHolder is the responsible of natively
     * fitting the holder with the information corresponding to
     * each position of the RecyclerView, handling efficiently
     * the recycler view items
     * @param holder where the items are grouped
     * @param position position in the recycler view list
     */
    @Override
    public void onBindViewHolder(ItemListViewHolder holder, int position) {
        // Binding object instance
        FilmJSONEntity aFilm = mFilms.get(position);

        // Setting colors
        if(position % 2 == 0)
            holder.linearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.primary));
        else
            holder.linearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.primary_dark));

        // Setting Contents
        holder.txtTitle.setText(aFilm.getRanking() + ". "
                + aFilm.getTitle());
        holder.txtYear.setText("(" + aFilm.getYear() + ")");
        holder.txtRating.setText("" + aFilm.getRating());
        // Setting image to the imageview
        ImageLoader.getInstance().displayImage(aFilm.getUrlPoster(), holder.imgFilmThumbnail);
        // Setting the tag to restore the content
        holder.itemView.setTag(aFilm);
        holder.itemView.setActivated(true);
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    public FilmJSONEntity getItem(int position)
    {
        return mFilms.get(position);
    }


    /**
     * onClick regular method for the recycler view, this does
     * the transition of the view when
     * the OnItemClickFromListInterface and runs the thread
     * is called
     * @param view
     */
    @Override
    public void onClick(final View view) {
        if(onItemClickFromList != null)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickFromList.onItemFromListClick(view, (FilmJSONEntity) view.getTag());
                }
            }, 100);
        }
    }

    public interface OnItemClickListenerInterface {
        void onItemFromListClick(View iView, FilmJSONEntity iFilm);
    }

}

