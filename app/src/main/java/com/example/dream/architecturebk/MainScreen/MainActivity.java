package com.example.dream.architecturebk.MainScreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dream.architecturebk.Data.DataUtil;
import com.example.dream.architecturebk.Data.TextInfo;
import com.example.dream.architecturebk.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
//        TextView textView=(TextView) findViewById(R.id.addText);
//        DataUtil dataUtil=new DataUtil();
//        textView.setText("添加文章--返回文章ID"+dataUtil.addText(12313,"测试标题","测试文章")+"");
//
//
//        textView=(TextView) findViewById(R.id.getUserInfo);
//        textView.setText("获取用户信息---返回文章数量"+dataUtil.getUserInfo(12313).getDocumentNum()+"");
//
//        textView=(TextView) findViewById(R.id.getTextInfo);
//        TextInfo textInfo=dataUtil.getTextInfoByUserID(12313).get(0);
//        textView.setText("获取文章信息liebiao---返回文章标题内容------"+textInfo.getTitle()+textInfo.getText());
//
//        textView=(TextView) findViewById(R.id.addUser);
//        textView.setText("添加用户---返回bool值"+dataUtil.addUser(118999,"sdjfaljdslafd")+"");
//
//        textView=(TextView) findViewById(R.id.login);
//        textView.setText("登陆----返回bool值"+dataUtil.login(999999,"666666")+"");
//
//
//        textView=(TextView) findViewById(R.id.deleteText);
//        textView.setText("删除文章-----返回bool值"+dataUtil.deleteText(1043)+"");
//
//        textView=(TextView) findViewById(R.id.updateText);
//        textView.setText("更新文章---返回bool值"+dataUtil.updateText(1025,"sjdlfaj测试更新","测试更新内容")+"");
//        textView=(TextView)findViewById(R.id.addText);
//        textView.setText("获取文章数量---返回int"+dataUtil.GetTotalDocNum());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.personalCenter){


        }else if(id==R.id.addText){


        }else if(id==R.id.myText){


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
