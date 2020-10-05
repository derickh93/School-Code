package edu.ncc.nccdepartmentdatabase;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class MainActivity extends ListActivity {

    private DepartmentInfoSource datasource;
    private ArrayAdapter<DepartmentEntry> adapter;
    private static String ORDER_BY = DepartmentInfoHelper.LOCATION;
    static final int SEARCH_REQUEST = 1;  // The request code



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new DepartmentInfoSource(this);
        datasource.open();

        List<DepartmentEntry> values = datasource.getAllDepartments();

        // add departments to the database if it is currently empty
        if (values.isEmpty())
        {
            new ParseURL().execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view) {
        DepartmentEntry dept;
        List<DepartmentEntry> values;
        switch (view.getId()) {
            case R.id.show:
                values = datasource.getAllDepartments();
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
                break;
            case R.id.dean_btn:
                values = datasource.findDepartments(DepartmentInfoHelper.NAME + " LIKE ? OR " + DepartmentInfoHelper.NAME +
                        " LIKE ? ",null,"%Dean%");
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
                break;
            case R.id.a_d_button:
                values = datasource.findDepartments(DepartmentInfoHelper.LOCATION + " LIKE ? OR " + DepartmentInfoHelper.LOCATION + " LIKE ? OR "
                        + DepartmentInfoHelper.LOCATION + " LIKE ? OR " + DepartmentInfoHelper.LOCATION + " LIKE ? OR " + DepartmentInfoHelper.LOCATION +
                        " LIKE ? OR " + DepartmentInfoHelper.LOCATION + " LIKE ? OR " + DepartmentInfoHelper.LOCATION + " LIKE ? OR " + DepartmentInfoHelper.LOCATION +
                        " LIKE ? " , null,"%Cluster A%","%Building A%","%Cluster B%","%Building B%","%Cluster C%","%Building C%","%Cluster D%","%Building D%");
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
                break;
            case R.id.tower_btn:
                values = datasource.findDepartments(DepartmentInfoHelper.LOCATION + " LIKE ? OR " + DepartmentInfoHelper.LOCATION +
                        " LIKE ? ", ORDER_BY +" ASC","%Tower%");
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
                break;
            case R.id.center_btn:
                values = datasource.findDepartments(DepartmentInfoHelper.NAME + " LIKE ? OR " + DepartmentInfoHelper.NAME +
                        " LIKE ? ", null,"%Center%");
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
                break;
            case R.id.search_btn:
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                startActivityForResult(i,SEARCH_REQUEST);
                break;
        }
        if(view.getId() != R.id.search_btn)
         adapter.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        List<DepartmentEntry> values;
        if(requestCode == SEARCH_REQUEST) {
            if(resultCode == RESULT_OK) {
                if(data != null) {
                    String result = data.getStringExtra("KEY");
                    values = datasource.findDepartments(DepartmentInfoHelper.NAME + " LIKE ? OR " + DepartmentInfoHelper.NAME +
                            " LIKE ? ", null,"%"+result+"%");
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
                    setListAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void onDestroy()
    {
        datasource.close();
        super.onDestroy();
    }

    private class ParseURL extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String str;
            String deptName;
            String deptPhone;
            String deptLocation;
            String deptEmail;
            Document doc;
            int count = 0;

            try {
                // connect to the webpage
                doc = Jsoup.connect("http://www.ncc.edu/contactus/deptdirectory.shtml").get();

                // find the body of the webpage
                Elements tableEntries = doc.select("tbody");
                for (Element e : tableEntries)
                {
                    // look for a row in the table
                    Elements trs = e.getElementsByTag("tr");

                    // for each element in the row (there are 5)
                    for (Element e2 : trs)
                    {
                        // get the table descriptor
                        Elements tds = e2.getElementsByTag("td");

                        // ignore the first row
                        if (count > 0) {
                            // get the department name and remove the formatting tags
                            deptName = tds.get(0).text();

                            // get the department phone number
                            deptPhone = tds.get(1).text();

                            deptEmail = tds.get(3).text();

                            // get the department location
                            deptLocation = tds.get(4).text();

                            datasource.addDept(deptName, deptLocation, deptPhone, deptEmail);
                        }
                        count++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            //if you had a ui element, you could display the title
            Log.d("PARSING", "async task has completed");
        }
    }
}