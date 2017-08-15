package com.deepu.memorygame;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //maps imageButton id to image
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    //maps image to number of times its displayed
    HashMap<Integer,Integer> check = new HashMap<Integer,Integer>();
    int score;
    int first,second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=1;i<=10;i++){
            check.put(i,2);
        }
        setContentView(R.layout.activity_main);
    }

    public void restart(View view){
        map.clear();
        check.clear();
        
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 150, 150, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    public void imageClick(View view){
        //if the id of the image is present in the list then just show the image
        //if it is not present select an image from the left over images
        int id = view.getId();
        ImageButton imageButton = (ImageButton) findViewById(id);
        TextView textView = (TextView) findViewById(R.id.textView);
        score++;
        textView.setText("Your Score : "+score);

        if(first!=0&&second!=0){
            ImageButton new1 = (ImageButton) findViewById(first);
            ImageButton new2 = (ImageButton) findViewById(second);
            new1.setBackgroundResource(R.drawable.cards);
            new2.setBackgroundResource(R.drawable.cards);
            first = second = 0;
        }

        Log.d("sdaasdad", "Entered ");

        if(map.containsKey(id)){
            //display
            int img = map.get(id);
            String image = "draw"+img;
            int f = getResources().getIdentifier(image, "drawable", getPackageName());
            Drawable d = getResources().getDrawable(f);
            d = resize(d);
            imageButton.setBackground(d);

            if(first==0){
                first = id;
            }
            else if(second==0){
                second = id;
            }

        }else{
            //get the image map it and display
            Random random = new Random();
            int img;
            do {
                img = random.nextInt(10);
                img++;
            }while(check.get(img)==0);
            check.put(img,check.get(img)-1);

            String image = "draw"+img;
            int f = getResources().getIdentifier(image, "drawable", getPackageName());
            Drawable d = getResources().getDrawable(f);
            d = resize(d);
            imageButton.setBackground(d);

            map.put(id, img);

            if(first==0){
                first = id;
            }
            else if(second==0){
                second = id;
            }

        }

        if(map.get(first) == map.get(second) && first!=second){
            ImageButton new1 = (ImageButton) findViewById(first);
            ImageButton new2 = (ImageButton) findViewById(second);
            new1.setVisibility(View.INVISIBLE);
            new2.setVisibility(View.INVISIBLE);
        }

    }
}
