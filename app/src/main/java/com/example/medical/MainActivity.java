package com.example.medical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String,String>hashMap;
    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    DatabaseHelper helper;
androidx.appcompat.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        helper =new DatabaseHelper(this);
        searchView=findViewById(R.id.searchView);


          cursour(helper.getAlldata());



        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                String name = s;
                arrayList.clear();
                cursour(helper.seacrchList(name));
                return false;
            }
        });





    }

    public void cursour(Cursor cursor) {

        if(cursor!=null&&cursor.getCount()>0){

            while(cursor.moveToNext()){
                String brand_id=cursor.getString(0);
                String generic_id=cursor.getString(1);
                String company_id=cursor.getString(2);
                String brandId=cursor.getString(3);
                String form_id=cursor.getString(4);
                String strength_id=cursor.getString(5);
                String price_id=cursor.getString(6);
                String packsize_id=cursor.getString(7);


                hashMap=new HashMap<>();
                hashMap.put("brand",brand_id);
                hashMap.put("generic",generic_id);
                hashMap.put("company",company_id);
                hashMap.put("brandId",brandId);
                hashMap.put("form",form_id);
                hashMap.put("strength",strength_id);
                hashMap.put("price",price_id);
                hashMap.put("packsize",packsize_id);
                arrayList.add(hashMap);



            }


            recyclerView.setAdapter(new XAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        }
    }



    public class XAdapter extends RecyclerView.Adapter<XAdapter.Holder>{


        public class Holder extends RecyclerView.ViewHolder{
            TextView tvName,tvFrom,tvPrice;
            LinearLayout layout;

            public Holder(@NonNull View itemView) {
                super(itemView);
                tvName=itemView.findViewById(R.id.tvName);
                tvFrom=itemView.findViewById(R.id.tvFrom);
                tvPrice=itemView.findViewById(R.id.tvPrice);
                layout=itemView.findViewById(R.id.layout);



            }
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.item,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {

            hashMap=arrayList.get(position);
            String brand=hashMap.get("brand");
            String generic=hashMap.get("generic");
            String company=hashMap.get("company");
            String brandId=hashMap.get("brandId");
            String form_id=hashMap.get("form");
            String strength_id=hashMap.get("strength");
            String price_id=hashMap.get("price");
            String packsize=hashMap.get("packsize");

            holder.tvName.setText("Name: "+brandId);
            holder.tvFrom.setText("Categorie: "+form_id+"          strength: "+strength_id);
            holder.tvPrice.setText("Price: "+price_id);
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ShowDetails.class); // Replace YourActivity with the actual name of your activity
                    intent.putExtra("ID", generic);
                   startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }


}