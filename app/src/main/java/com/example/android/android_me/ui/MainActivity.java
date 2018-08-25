/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    public static final String INTENT_EXTRA = "intent_extra";
    public static final String HEAD_INDEX = "head_index";
    public static final String BODY_INDEX = "body_index";
    public static final String LEG_INDEX = "leg_index";

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // DONE (2) Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments4
        int bodyPosition = position/12;
        int index = position - bodyPosition*12;

        switch (bodyPosition) {

            case 0:
                headIndex = index;
                break;

            case 1:
                bodyIndex = index;
                break;

            case 2:
                legIndex = index;
                break;

        }

        // DONE (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle bundle = new Bundle();
        bundle.putInt(HEAD_INDEX, headIndex);
        bundle.putInt(BODY_INDEX, bodyIndex);
        bundle.putInt(LEG_INDEX, legIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtra(INTENT_EXTRA, bundle);

        // DONE (4) Get a reference to the "Next" button and launch the intent when this button is clicked
        button = (Button) findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

}
