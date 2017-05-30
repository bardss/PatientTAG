package com.example.jakubaniola.patienttag;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.jakubaniola.patienttag.Adapters.TagFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckTagActivity extends AppCompatActivity implements ListenerInterface{

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    @BindView(R.id.change_fragment_floating_button)
    FloatingActionButton changeFragmentFB;

    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    private NfcAdapter mNfcAdapter;

    private boolean readTagOpened = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_tag);
        ButterKnife.bind(this);

        setupTabLayout();
        setupButton();
        initNFC();
    }

    private void setupTabLayout() {
        viewPager.setAdapter(new TagFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupButton(){
        changeFragmentFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readTagOpened) {
                    viewPager.setCurrentItem(0);
                    isWrite = false;
                } else {
                    viewPager.setCurrentItem(1);
                    isWrite = true;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels == 0 && readTagOpened == false){
                    changeFragmentFB.setImageDrawable(getResources().getDrawable(R.drawable.read_tag));
                    isWrite = true;
                    readTagOpened = true;
                } else if (positionOffsetPixels == 0 && readTagOpened == true) {
                    changeFragmentFB.setImageDrawable(getResources().getDrawable(R.drawable.add_new_tag));
                    isWrite = false;
                    readTagOpened = false;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void initNFC(){
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    public void onDialogDisplayed() {
        isDialogDisplayed = true;
    }

    @Override
    public void onDialogDismissed() {
        isDialogDisplayed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected,tagDetected,ndefDetected};

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if(mNfcAdapter!= null)
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mNfcAdapter!= null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        if(tag != null) {
            Ndef ndef = Ndef.get(tag);
            TagFragmentPagerAdapter tagFragmentPagerAdapter = ((TagFragmentPagerAdapter) viewPager.getAdapter());
            if (isDialogDisplayed) {
                if (isWrite) {
                    WriteTagFragment writeTagFragment = ((WriteTagFragment)tagFragmentPagerAdapter.getItem(1));
                    String messageToWrite = writeTagFragment.getIdToWrite();
                    writeTagFragment.onNfcDetected(ndef,messageToWrite);
                    Toast.makeText(this, "Text written", Toast.LENGTH_SHORT).show();
                } else {
                    ReadTagFragment readTagFragment = ((ReadTagFragment) tagFragmentPagerAdapter.getItem(0));
                    readTagFragment.onNfcDetected(ndef);
                }
            }
        }
    }

}
