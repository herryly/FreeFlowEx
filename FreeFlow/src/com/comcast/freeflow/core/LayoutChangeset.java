/*******************************************************************************
 * Copyright 2013 Comcast Cable Communications Management, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.comcast.freeflow.core;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Rect;
import android.util.Pair;

public class LayoutChangeset {
	protected List<Pair<FreeFlowItem, Rect>> moved;
	protected List<Pair<FreeFlowItem, FreeFlowItem>> mappedBetweenAdapters;
	protected List<FreeFlowItem> removed;
	protected List<FreeFlowItem> added;


	public LayoutChangeset() {
		moved = new ArrayList<Pair<FreeFlowItem, Rect>>();
		removed = new ArrayList<FreeFlowItem>();
		added = new ArrayList<FreeFlowItem>();
		mappedBetweenAdapters = new ArrayList<Pair<FreeFlowItem,FreeFlowItem>>();
	}
	
	public void addToItemsFromPreviousAdapterPresentInNewAdapter(FreeFlowItem oldItem, FreeFlowItem newItem){
		mappedBetweenAdapters.add(new Pair<FreeFlowItem, FreeFlowItem>(oldItem, newItem));
	}
	
	public List<Pair<FreeFlowItem, FreeFlowItem>> getMappedBetweenAdapters(){
		return mappedBetweenAdapters;
	}

	public void addToMoved(FreeFlowItem proxy, Rect oldFrame) {
		moved.add(new Pair<FreeFlowItem, Rect>(proxy, oldFrame));
	}

	public void addToDeleted(FreeFlowItem proxy) {
		removed.add(proxy);
	}

	public void addToAdded(FreeFlowItem proxy) {
		added.add(proxy);
	}

	public List<FreeFlowItem> getAdded() {
		return added;
	}
	
	public List<FreeFlowItem> getRemoved() {
		return removed;
	}

	public List<Pair<FreeFlowItem, Rect>> getMoved() {
		return moved;
	}

	@Override
	public String toString() {
		return 	"Added: " + added.size() + "," +
				"Removed: " + removed.size()+ ","+
				"Moved: " + moved.size()+", "+
				"Mapped: "+mappedBetweenAdapters.size();
	}

	public boolean isEmpty() {
		return (added.size() == 0 && removed.size() == 0 && moved.size() == 0);	
	}

}
