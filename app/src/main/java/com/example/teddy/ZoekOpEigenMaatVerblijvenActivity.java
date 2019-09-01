package com.example.teddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.teddy.model.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ZoekOpEigenMaatVerblijvenActivity extends AppCompatActivity
        implements ExampleAdapter.AttributesAdapterListener {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Attributes> listAttributes;
    ArrayList<Attributes> filteredListAttributes;
    private SearchView searchView;

    String gemeente;
    String prijsType;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoek_op_eigen_maat_verblijven);

        Intent intent = getIntent();
        gemeente = intent.getStringExtra("gemeente");
        prijsType = intent.getStringExtra("prijsType");
        type = intent.getStringExtra("type");

        new GetAttributes().execute();

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

    }

    public class GetAttributes extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://geodata.antwerpen.be/arcgissql/rest/services/P_Portal/" +
                    "portal_publiek6/MapServer/634/query?where=1%3D1&outFields=*&outSR=4326&f=json";
            String jsonStr = sh.makeServiceCall(url);

            listAttributes = new ArrayList<>();
            filteredListAttributes = new ArrayList<>();

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("features");


                    // looping through All attributes
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject b = contacts.getJSONObject(i);
                        JSONObject c = b.getJSONObject("attributes");

                        Long id = c.getLong("OBJECTID");
                        String name = c.getString("naam_label");
                        final String type = c.getString("Type");
                        final String prijstype = c.getString("Prijstype");
                        String straat = c.getString("Straat");
                        String huisnr = c.getString("Huisnr");
                        String busnr =  c.getString("Busnr");
                        String postcode = c.getString("Postcode");
                        String district = c.getString("District");
                        String urlKinder = c.getString("URL");
                        String link = c.getString("Link");
                        String email = c.getString("Email");
                        String telefoon = c.getString("Telefoon");
                        String begindatum = c.getString("Begindatum");
                        String dienstlabel = c.getString("Dienst_label");
                        String capaReeel = c.getString("Capa_reeel");


                        JSONObject phone = b.getJSONObject("geometry");
                        Double y = phone.getDouble("y");
                        Double x = phone.getDouble("x");





                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        listAttributes.add(new Attributes(id,name,type,prijstype,straat,
                                huisnr,busnr,postcode,district,urlKinder,link,email,telefoon,begindatum,
                                dienstlabel,capaReeel, x, y));


                        filteredListAttributes = (ArrayList<Attributes>) listAttributes.stream()
                                .filter(p -> type.equals(p.getType())
                                        && prijsType.equals(p.getPrijstype())
                                        && gemeente.equals(p.getDistrict()))
                                .collect(Collectors.toList());






                    }



                    Collections.sort(filteredListAttributes, new Comparator<Attributes>() {
                        @Override
                        public int compare(Attributes o1, Attributes o2) {
                            return o1.getNaam_label().compareTo(o2.getNaam_label());
                        }
                    });



                } catch (final JSONException e) {
                    Log.e("Json parsing error", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e("could not get json", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            mAdapter = new ExampleAdapter(filteredListAttributes);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);


            mAdapter.setOnItemClickListener(position -> {
                Attributes attribuut = filteredListAttributes.get(position);
                String url_attribuut = attribuut.getURL();
                String link_attribuut = attribuut.getLink();
                String telefoon_attribuut = attribuut.getTelefoon();
                String email_attribuut = attribuut.getEmail();
                String naam_attribuut = attribuut.getNaam_label();
                Double xCoordinate = attribuut.getxCoordinate();
                Double yCoordinate = attribuut.getyCoordinate();



                Intent intent = new Intent(ZoekOpEigenMaatVerblijvenActivity.this,DetailActivity.class);
                intent.putExtra("urlattribuut",url_attribuut);
                intent.putExtra("linkattribuut",link_attribuut);
                intent.putExtra("telefoonattribuut",telefoon_attribuut);
                intent.putExtra("emailattribuut",email_attribuut);
                intent.putExtra("naamattribuut",naam_attribuut);
                intent.putExtra("xCoordinate",xCoordinate);
                intent.putExtra("yCoordinate",yCoordinate);


                startActivity(intent);


            });



        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                Log.d("onTextSumbit", "query inhoud: "+query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed

                mAdapter.getFilter().filter(query);
                Log.d("onTextChange", "query inhoud: "+query);

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onContactSelected(Attributes attribute) {
        Toast.makeText(getApplicationContext(), "Selected: " + attribute.getNaam_label(), Toast.LENGTH_LONG).show();
    }

}
