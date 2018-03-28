//
//
// Starter code for the Mobile Platform Development Assignment
// Seesion 2017/2018
//
//

package mpdproject.gcu.me.org.assignmenttest1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import static android.R.id.message;


import mpdproject.gcu.me.org.assignmenttest1.HTTP.NetworkHelper;
import mpdproject.gcu.me.org.assignmenttest1.model.DataItem;
import mpdproject.gcu.me.org.assignmenttest1.serv.serv;


public class MainActivity extends AppCompatActivity {

        private static final String JSON_URL =
                "http://560057.youcanlearnit.net/services/json/itemsfeed.php";
        private static final String XML_URL =
                "http://trafficscotland.org/rss/feeds/currentincidents.aspx";

        private boolean networkOk;
        TextView output;

        private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra(serv.MY_SERVICE_PAYLOAD)) {
                    DataItem[] dataItems = (DataItem[]) intent
                            .getParcelableArrayExtra(serv.MY_SERVICE_PAYLOAD);
                    for (DataItem item : dataItems) {
                        output.append(item.getTitle() + "\n");
                    }
                } else if (intent.hasExtra(serv.MY_SERVICE_EXCEPTION)){
                    String message = intent.getStringExtra(serv.MY_SERVICE_EXCEPTION);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            output = (TextView) findViewById(R.id.output);

            LocalBroadcastManager.getInstance(getApplicationContext())
                    .registerReceiver(mBroadcastReceiver,
                            new IntentFilter(serv.MY_SERVICE_MESSAGE));

            networkOk = NetworkHelper.hasNetworkAccess(this);
            output.append("Network ok: " + networkOk);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

            LocalBroadcastManager.getInstance(getApplicationContext())
                    .unregisterReceiver(mBroadcastReceiver);
        }

        public void runClickHandler(View view) {

            if (networkOk) {
                Intent intent = new Intent(this, serv.class);
                intent.setData(Uri.parse(XML_URL));
                startService(intent);
            } else {
                Toast.makeText(this, "Network not available!", Toast.LENGTH_SHORT).show();
            }
        }

        public void clearClickHandler(View view) {
            output.setText("");
        }
    //
    // Now that you have the xml data you can parse it
    //
    /**
     private LinkedList<WidgetClass> parseData (String dataToParse)
     {
     WidgetClass widget = null;
     LinkedList<WidgetClass> alist = null;
     try {
     XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
     factory.setNamespaceAware(true);
     XmlPullParser xpp = factory.newPullParser();
     xpp.setInput(new StringReader(dataToParse));
     int eventType = xpp.getEventType();
     while (eventType != XmlPullParser.END_DOCUMENT) {
     // Found a start tag
     if (eventType == XmlPullParser.START_TAG) {
     // Check which Tag we have
     if (xpp.getName().equalsIgnoreCase("widgetcollection")) {
     alist = new LinkedList<WidgetClass>();
     }
     else
     if (xpp.getName().equalsIgnoreCase("widget"))
     {
     Log.e("MyTag", "Item Start Tag found");
     widget = new WidgetClass();
     }
     else
     if (xpp.getName().equalsIgnoreCase("title"))
     {
     // Now just get the associated text
     String temp = xpp.nextText();
     // Do something with text
     Log.e("MyTag", "Title is " + temp);
     widget.setTitle(temp);
     }
     else
     // Check which Tag we have
     if (xpp.getName().equalsIgnoreCase("description")) {
     // Now just get the associated text
     String temp = xpp.nextText();
     // Do something with text
     Log.e("MyTag", "Geo is " + temp);
     widget.setGeo(temp);
     }
     else
     // Check which Tag we have
     if (xpp.getName().equalsIgnoreCase("Georrs:point"))
     {
     // Now just get the associated text
     String temp = xpp.nextText();
     // Do something with text
     Log.e("MyTag", "Desc is " + temp);
     widget.setDesc(temp);
     }
     }
     else
     if (eventType == XmlPullParser.END_TAG)
     {
     if (xpp.getName().equalsIgnoreCase("widget"))
     {
     Log.e("MyTag", "widget is " + widget.toString());
     alist.add(widget);
     }
     else
     if (xpp.getName().equalsIgnoreCase("widgetcollection"))
     {
     int size;
     size = alist.size();
     Log.e("MyTag", "widgetcollection size is " + size);
     }
     }


     // Get the next event
     eventType = xpp.next();

     } // End of while

     //return alist;
     } catch (XmlPullParserException ae1) {
     Log.e("MyTag", "Parsing error" + ae1.toString());
     } catch (IOException ae1) {
     Log.e("MyTag", "IO error during parsing");
     }

     Log.e("MyTag", "End document");

     return alist;

     }
     */

    }
