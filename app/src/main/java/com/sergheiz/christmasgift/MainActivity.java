package com.sergheiz.christmasgift;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Looper;
import android.os.Process;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView greetTV, perTV, crTV, langTV;
    private String[] greetings;
    private Animation animation1, animation3, textfade;
    private String curLang;

    Context context;
    Resources resources;

    private LottieAnimationView centerAV, fwTCav, fwTLav, fwTRav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startService(new Intent(this, MyService.class));


        curLang = Locale.getDefault().getLanguage();


        greetTV = findViewById(R.id.greetingTV);
        perTV = findViewById(R.id.personTV);
        crTV = findViewById(R.id.bySZ);
        langTV = findViewById(R.id.langTVid);

        centerAV = findViewById(R.id.centerAVfront);
        fwTCav = findViewById(R.id.fwTC);
        fwTRav = findViewById(R.id.fwTR);
        fwTLav = findViewById(R.id.fwTL);


        greetings = getResources().getStringArray(R.array.greetingsList);


        getSupportActionBar().hide();


        animation1 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        textfade = AnimationUtils.loadAnimation(this, R.anim.textfadein);
        animation3 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        animation3.setStartOffset(3000);
        perTV.setAnimation(animation1);
        centerAV.setAnimation(animation3);


        int randomIndex = new Random().nextInt(greetings.length);
        String randomGreet = greetings[randomIndex];
        greetTV.setText(randomGreet);


        if (curLang.equals("de")) {
            langTV.setText("\uD83C\uDDE9\uD83C\uDDEA");
        } else if (curLang.equals("es")) {
            langTV.setText("\uD83C\uDDEA\uD83C\uDDF8");
        }else if (curLang.equals("fr")) {
            langTV.setText("\uD83C\uDDEB\uD83C\uDDF7");
        }else if (curLang.equals("it")) {
            langTV.setText("\uD83C\uDDEE\uD83C\uDDF9");
        }else if (curLang.equals("pt")) {
            langTV.setText("\uD83C\uDDF5\uD83C\uDDF9");
        }else if (curLang.equals("ru")) {
            langTV.setText("\uD83C\uDDF7\uD83C\uDDFA");
        }else if (curLang.equals("uk")) {
            langTV.setText("\uD83C\uDDFA\uD83C\uDDE6");
        }else if (curLang.equals("ro")) {
            langTV.setText("\uD83C\uDDF7\uD83C\uDDF4");
        }



        greetTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeText();
            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        centerAV.playAnimation();
                        fwTCav.setVisibility(View.VISIBLE);
                        fwTCav.playAnimation();

                        Animation fadeOut = new AlphaAnimation(1, 0);
                        fadeOut.setInterpolator(new AccelerateInterpolator());
                        fadeOut.setStartOffset(1000);
                        fadeOut.setDuration(1000);
                        perTV.setAnimation(fadeOut);
                        perTV.setVisibility(View.INVISIBLE);

                        Animation fadeIn = new AlphaAnimation(0, 1);
                        fadeIn.setInterpolator(new AccelerateInterpolator());
                        fadeIn.setStartOffset(1000);
                        fadeIn.setDuration(1000);
                        crTV.setAnimation(fadeIn);
                        crTV.setVisibility(View.VISIBLE);

                    }
                }, 3000);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        greetTV.startAnimation(textfade);
                        greetTV.setVisibility(View.VISIBLE);

                        fwTLav.setVisibility(View.VISIBLE);
                        fwTLav.playAnimation();
                        fwTRav.setVisibility(View.VISIBLE);
                        fwTRav.playAnimation();

                        Animation fadeOut = new AlphaAnimation(1, 0);
                        fadeOut.setInterpolator(new AccelerateInterpolator());
                        fadeOut.setStartOffset(3000);
                        fadeOut.setDuration(1000);
                        crTV.setAnimation(fadeOut);
                        crTV.setVisibility(View.INVISIBLE);

                        Animation fadeIn = new AlphaAnimation(0, 1);
                        fadeIn.setInterpolator(new AccelerateInterpolator());
                        fadeIn.setStartOffset(1000);
                        fadeIn.setDuration(1000);
                        langTV.setAnimation(fadeIn);
                        langTV.setVisibility(View.VISIBLE);

                    }
                }, 5000);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        centerAV.playAnimation();
                        fwTCav.playAnimation();
                        fwTLav.playAnimation();
                        fwTRav.playAnimation();
                    }
                });


            }
        }, 15000, 15000);

    }



    public void changeText() {
        int randomIndex = new Random().nextInt(greetings.length);
        String randomGreet = greetings[randomIndex];
        greetTV.setText(randomGreet);

        greetTV.startAnimation(textfade);

    }


    public void launchFW(View view) {
        fwTCav.setVisibility(View.VISIBLE);
        fwTCav.playAnimation();
        centerAV.playAnimation();
        centerAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fwTRav.setVisibility(View.VISIBLE);
                fwTRav.playAnimation();
                centerAV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fwTLav.setVisibility(View.VISIBLE);
                        fwTLav.playAnimation();
                        centerAV.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                launchFW(view);
                            }
                        });
                    }
                });
            }
        });
    }


    public void LangChange(View view) {
        context = LocaleHelper.setLocale(MainActivity.this, "en");
        resources = context.getResources();

        greetings = resources.getStringArray(R.array.greetingsList);

        changeText();
        langTV.setText("\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F");
        langTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocaleHelper.setLocale(MainActivity.this, "uk");
                resources = context.getResources();

                greetings = resources.getStringArray(R.array.greetingsList);

                changeText();
                langTV.setText("\uD83C\uDDFA\uD83C\uDDE6");
                langTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context = LocaleHelper.setLocale(MainActivity.this, "es");
                        resources = context.getResources();

                        greetings = resources.getStringArray(R.array.greetingsList);

                        changeText();
                        langTV.setText("\uD83C\uDDEA\uD83C\uDDF8");
                        langTV.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                context = LocaleHelper.setLocale(MainActivity.this, "ru");
                                resources = context.getResources();

                                greetings = resources.getStringArray(R.array.greetingsList);

                                changeText();
                                langTV.setText("\uD83C\uDDF7\uD83C\uDDFA");
                                langTV.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        context = LocaleHelper.setLocale(MainActivity.this, "pt");
                                        resources = context.getResources();

                                        greetings = resources.getStringArray(R.array.greetingsList);

                                        changeText();
                                        langTV.setText("\uD83C\uDDF5\uD83C\uDDF9");
                                        langTV.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                context = LocaleHelper.setLocale(MainActivity.this, "fr");
                                                resources = context.getResources();

                                                greetings = resources.getStringArray(R.array.greetingsList);

                                                changeText();
                                                langTV.setText("\uD83C\uDDEB\uD83C\uDDF7");
                                                langTV.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        context = LocaleHelper.setLocale(MainActivity.this, "it");
                                                        resources = context.getResources();

                                                        greetings = resources.getStringArray(R.array.greetingsList);

                                                        changeText();
                                                        langTV.setText("\uD83C\uDDEE\uD83C\uDDF9");
                                                        langTV.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                context = LocaleHelper.setLocale(MainActivity.this, "ro");
                                                                resources = context.getResources();

                                                                greetings = resources.getStringArray(R.array.greetingsList);

                                                                changeText();
                                                                langTV.setText("\uD83C\uDDF7\uD83C\uDDF4");
                                                                langTV.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        context = LocaleHelper.setLocale(MainActivity.this, "de");
                                                                        resources = context.getResources();

                                                                        greetings = resources.getStringArray(R.array.greetingsList);

                                                                        changeText();
                                                                        langTV.setText("\uD83C\uDDE9\uD83C\uDDEA");
                                                                        langTV.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                LangChange(view);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });


    }


    @Override
    public void onBackPressed() {

        android.os.Process.killProcess(android.os.Process.myPid());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(Process.myPid());
    }


}